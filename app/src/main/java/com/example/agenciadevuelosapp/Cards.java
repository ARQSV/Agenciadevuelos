package com.example.agenciadevuelosapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

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
        Button botondereserva = findViewById(R.id.botonreserva);

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

        // Encuentra el botón para cerrar sesión (ImageButton)
        ImageButton btnCerrarSesion = findViewById(R.id.btn_cerrar);

        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Muestra un cuadro de diálogo de confirmación
                showLogoutConfirmationDialog();
            }
        });
    }

    private void showLogoutConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Cerrar Sesión");
        builder.setMessage("¿Quieres cerrar sesión?");
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Agrega aquí la lógica para cerrar la sesión del usuario.
                // Por ejemplo, puedes iniciar otra actividad de inicio de sesión o borrar datos de sesión.
                // Luego, finaliza la actividad actual para volver a la pantalla de inicio de sesión.
                Intent intent = new Intent(Cards.this, login.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    public void Salir(View view) {
        // Cierra la sesión si el usuario hace clic en el botón de cerrar sesión
        showLogoutConfirmationDialog();
    }

    public void reservar(View view) {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

    public void paises(View view) {
        Intent intent = new Intent(this, PaisesDisponibles.class);
        startActivity(intent);
    }
}
