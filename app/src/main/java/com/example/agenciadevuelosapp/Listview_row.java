package com.example.agenciadevuelosapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Listview_row extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_row);

        ImageButton FinalizarCompra = findViewById(R.id.pagar);

        FinalizarCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Listview_row.this, Activity_compras.class);
                startActivity(intent);
            }
        });
    }
}
