package com.example.agenciadevuelosapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Cards extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);
    }

    public void Reserva(View view) {
        Intent reserva = new Intent(this, ReservasdeVuelo.class);
        startActivity(reserva);
    }
}
