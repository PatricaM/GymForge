package com.example.gymforge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DietasActivity extends AppCompatActivity {

    Button desayuno, snack, comida, cena, salir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dietas);

        desayuno = findViewById(R.id.btnDesayuno);
        snack = findViewById(R.id.btnSnacks);
        comida = findViewById(R.id.btnComida);
        cena = findViewById(R.id.btnCena);
        salir = findViewById(R.id.btn_salir);

        desayuno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(DietasActivity.this,Desayuno.class);
                in.putExtra("tipoComida", "Desayuno");
                startActivity(in);
            }
        });

        snack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(DietasActivity.this,Desayuno.class);
                in.putExtra("tipoComida", "Snack");
                startActivity(in);
            }
        });

        comida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(DietasActivity.this,Desayuno.class);
                in.putExtra("tipoComida", "Comida");
                startActivity(in);
            }
        });

        cena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(DietasActivity.this,Desayuno.class);
                in.putExtra("tipoComida", "Cena");
                startActivity(in);
            }
        });


    }
}