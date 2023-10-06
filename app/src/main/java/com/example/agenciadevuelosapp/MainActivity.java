package com.example.agenciadevuelosapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView gifImageView = findViewById(R.id.gifImageView);
        Glide.with(this).asGif().load(R.drawable.avioninicio).into(gifImageView);

        // Crear un objeto Handler para programar la transición
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // Crear un Intent para cambiar a Login
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
                finish(); // Opcional, para cerrar MainActivity después de la transición
            }
        };

        // Programar la transición después de 4 segundos (4000 milisegundos)
        handler.postDelayed(runnable, 4000);
    }

    // Método para pasar a la actividad Cards
    public void Siguiente(View view) {
        Intent intent = new Intent(this, Cards.class);
        startActivity(intent);
    }
}
