package com.example.agenciadevuelosapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Cards extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);

        CardView cardViewElSalvador = findViewById(R.id.cardView3);
        CardView cardViewFrancia = findViewById(R.id.cardView4);
        CardView cardViewChina = findViewById(R.id.cardView5);
        CardView cardViewCentroAyuda = findViewById(R.id.cardView7);
        CardView cardViewRequisitos = findViewById(R.id.cardView8);
        Button  botondereserva = findViewById(R.id.botonreserva);



        cardViewElSalvador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Cards.this, elsalvador.class);
                startActivity(intent);
            }
        });

        cardViewFrancia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Cards.this, francia.class);
                startActivity(intent);
            }
        });

        cardViewChina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Cards.this, China.class);
                startActivity(intent);
            }
        });

        cardViewCentroAyuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Cards.this, FAQ.class);
                startActivity(intent);
            }
        });

        cardViewRequisitos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Cards.this, Requisitos_viaje.class);
                startActivity(intent);
            }
        });

        botondereserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Cards.this, MainActivity2.class);
                startActivity(intent);
            }
        });
    }
}