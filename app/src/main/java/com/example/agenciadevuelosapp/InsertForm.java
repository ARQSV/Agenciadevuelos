package com.example.agenciadevuelosapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

                if (TextUtils.isEmpty(origen) || TextUtils.isEmpty(destino) || TextUtils.isEmpty(salida) || TextUtils.isEmpty(regreso) || TextUtils.isEmpty(hora) || TextUtils.isEmpty(pago)) {
                    Toast.makeText(InsertForm.this, "Debes rellenar todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    dateFormat.setLenient(false);
                    Date dateSalida, dateRegreso;
                    try {
                        dateSalida = dateFormat.parse(salida);
                        dateRegreso = dateFormat.parse(regreso);
                    } catch (ParseException e) {
                        Toast.makeText(InsertForm.this, "Formato de fecha incorrecto", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (dateRegreso.before(dateSalida)) {
                        Toast.makeText(InsertForm.this, "La fecha de regreso debe ser posterior a la fecha de salida", Toast.LENGTH_SHORT).show();
                    } else {
                        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
                        timeFormat.setLenient(false);
                        try {
                            Date time = timeFormat.parse(hora);
                        } catch (ParseException e) {
                            Toast.makeText(InsertForm.this, "Formato de hora incorrecto", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (origen.matches(".*\\d.*") || destino.matches(".*\\d.*") || pago.matches(".*\\d.*")) {
                            Toast.makeText(InsertForm.this, "Los campos de origen, destino y pago no deben contener números", Toast.LENGTH_SHORT).show();
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
                }
            }
        });
    }
}

