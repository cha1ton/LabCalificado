package com.mercado.chalton.laboratoriocalificado02;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class PedidoActivity extends AppCompatActivity {

    private Button llamarButton;
    private Button wspButton;
    private Button mapsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        llamarButton = findViewById(R.id.llamarButton);
        wspButton = findViewById(R.id.wspButton);
        mapsButton = findViewById(R.id.mapsButton);

        String nombreCliente = getIntent().getStringExtra("NOMBRE_CLIENTE");
        String productos = getIntent().getStringExtra("PRODUCTOS");
        String direccion = getIntent().getStringExtra("DIRECCION");
        String numeroCliente = getIntent().getStringExtra("NUMERO_CLIENTE");

        llamarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Validación del número de cliente
                if (!numeroCliente.matches("\\d+")) { // Verifica que el número solo contenga dígitos
                    Toast.makeText(PedidoActivity.this, "Número de cliente inválido.", Toast.LENGTH_SHORT).show();
                    return; // Sale del método si el número es inválido
                }

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + numeroCliente));
                startActivity(intent);
            }
        });

        wspButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://wa.me/?text=Hola " + nombreCliente + ", tus productos: " + productos + " están en camino a la dirección: " + direccion));
                try {
                    startActivity(intent);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(PedidoActivity.this, "WhatsApp no está instalado.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mapsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:0,0?q=" + Uri.encode(direccion)));
                startActivity(intent);
            }
        });
    }
}
