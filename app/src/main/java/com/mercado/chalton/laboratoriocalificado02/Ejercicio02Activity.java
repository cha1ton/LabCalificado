package com.mercado.chalton.laboratoriocalificado02;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Ejercicio02Activity extends AppCompatActivity {

    private EditText nombreClienteEditText;
    private EditText numeroClienteEditText;
    private EditText productosEditText;
    private EditText ciudadEditText;
    private EditText direccionEditText;
    private Button registrarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio02);

        nombreClienteEditText = findViewById(R.id.nombreClienteEditText);
        numeroClienteEditText = findViewById(R.id.numeroClienteEditText);
        productosEditText = findViewById(R.id.productosEditText);
        ciudadEditText = findViewById(R.id.ciudadEditText);
        direccionEditText = findViewById(R.id.direccionEditText);
        registrarButton = findViewById(R.id.registrarButton);

        registrarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarPedido();
            }
        });
    }

    private void registrarPedido() {
        String nombreCliente = nombreClienteEditText.getText().toString();
        String numeroCliente = numeroClienteEditText.getText().toString();
        String productos = productosEditText.getText().toString();
        String ciudad = ciudadEditText.getText().toString();
        String direccion = direccionEditText.getText().toString();

        if (nombreCliente.isEmpty() || numeroCliente.isEmpty() || productos.isEmpty() || ciudad.isEmpty() || direccion.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Redirigir a la pantalla de pedido
        Intent intent = new Intent(this, PedidoActivity.class);
        intent.putExtra("NOMBRE_CLIENTE", nombreCliente);
        intent.putExtra("NUMERO_CLIENTE", numeroCliente);
        intent.putExtra("PRODUCTOS", productos);
        intent.putExtra("DIRECCION", direccion);
        startActivity(intent);

        // Enviar mensaje por WhatsApp
        enviarWhatsApp(nombreCliente, productos, direccion);

        // Llamar al número del cliente
        hacerLlamada(numeroCliente);

        // Abrir Google Maps con la dirección
        abrirGoogleMaps(direccion);
    }

    private void enviarWhatsApp(String nombreCliente, String productos, String direccion) {
        String message = "Hola " + nombreCliente + ", tus productos: " + productos + " están en camino a la dirección: " + direccion;
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, message);
        sendIntent.setType("text/plain");
        sendIntent.setPackage("com.whatsapp");
        try {
            startActivity(sendIntent);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "WhatsApp no está instalado.", Toast.LENGTH_SHORT).show();
        }
    }

    private void hacerLlamada(String numeroCliente) {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:" + numeroCliente));
        startActivity(callIntent);
    }

    private void abrirGoogleMaps(String direccion) {
        String uri = "geo:0,0?q=" + Uri.encode(direccion);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }
}
