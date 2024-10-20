package com.mercado.chalton.laboratoriocalificado02;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class Ejercicio01Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio01);


        View greenView = findViewById(R.id.greenView);
        Button showButton = findViewById(R.id.showButton);
        Button hideButton = findViewById(R.id.hideButton);


        greenView.setVisibility(View.GONE);


        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                greenView.setVisibility(View.VISIBLE);
            }
        });


        hideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                greenView.setVisibility(View.GONE);
            }
        });
    }
}