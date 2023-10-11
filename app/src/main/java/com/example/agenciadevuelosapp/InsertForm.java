package com.example.agenciadevuelosapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_form);

        final EditText Origen = findViewById(R.id.Origen);
        final EditText Destino = findViewById(R.id.Destino);
        final EditText FechaS = findViewById(R.id.editTextDate);
        final EditText FechaR = findViewById(R.id.editTextDate2);
        final EditText Hora = findViewById(R.id.editTextTime);
        final EditText Pago = findViewById(R.id.textViewPago);

        Button btn = findViewById(R.id.addButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String origen = Origen.getText().toString();
                String destino = Destino.getText().toString();
                String salida = FechaS.getText().toString();
                String regreso = FechaR.getText().toString();
                String hora = Hora.getText().toString();
                String pago = Pago.getText().toString();

                if (origen.isEmpty() || destino.isEmpty() || salida.isEmpty() || regreso.isEmpty() || hora.isEmpty() || pago.isEmpty()) {
                    Toast.makeText(InsertForm.this, "Debes rellenar todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent();
                    intent.putExtra("origen", origen);
                    intent.putExtra("destino", destino);
                    intent.putExtra("salida", salida);
                    intent.putExtra("regreso", regreso);
                    intent.putExtra("hora", hora);
                    intent.putExtra("pago", pago);

                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
            }
        });
    }
}
