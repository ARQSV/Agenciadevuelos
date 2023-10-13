package com.example.agenciadevuelosapp;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class InsertForm extends AppCompatActivity {

    String[] paisesPermitidos = {"El Salvador", "Francia", "España", "Guatemala", "Estados Unidos", "China"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_form);

        final EditText Origen = findViewById(R.id.Origen);
        final EditText Destino = findViewById(R.id.Destino);

        Origen.setInputType(InputType.TYPE_CLASS_TEXT);
        Destino.setInputType(InputType.TYPE_CLASS_TEXT);

        final EditText FechaS = findViewById(R.id.editTextDate);
        final EditText FechaR = findViewById(R.id.editTextDate2);

        FechaS.setInputType(InputType.TYPE_DATETIME_VARIATION_DATE);
        FechaR.setInputType(InputType.TYPE_DATETIME_VARIATION_DATE);

        final EditText Hora = findViewById(R.id.editTextTime);
        final EditText Pago = findViewById(R.id.textViewPago);

        Button btn = findViewById(R.id.addButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String origen = Origen.getText().toString();
                String destino = Destino.getText().toString();
                String salida = FechaS.getText().toString().replace("/", "-");
                String regreso = FechaR.getText().toString().replace("/", "-");
                String hora = Hora.getText().toString();
                String pago = Pago.getText().toString();

                if (!Arrays.asList(paisesPermitidos).contains(origen)) {
                    Toast.makeText(InsertForm.this, "Este país no está disponible en nuestra agencia, por favor verifica los países disponibles", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!Arrays.asList(paisesPermitidos).contains(destino)) {
                    Toast.makeText(InsertForm.this, "Destino no válido. Verifica los países disponibles", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(origen) || TextUtils.isEmpty(destino) || TextUtils.isEmpty(salida) || TextUtils.isEmpty(regreso) || TextUtils.isEmpty(hora) || TextUtils.isEmpty(pago)) {
                    Toast.makeText(InsertForm.this, "Debes rellenar todos los campos", Toast.LENGTH_SHORT).show();
                } else if (origen.equalsIgnoreCase(destino)) {
                    Toast.makeText(InsertForm.this, "El origen y el destino no pueden ser iguales", Toast.LENGTH_SHORT).show();
                } else {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    dateFormat.setLenient(false);
                    Date dateSalida, dateRegreso;
                    try {
                        dateSalida = dateFormat.parse(salida);
                        dateRegreso = dateFormat.parse(regreso);

                        if (dateRegreso.equals(dateSalida)) {
                            Toast.makeText(InsertForm.this, "La fecha de regreso no puede ser igual a la fecha de salida", Toast.LENGTH_SHORT).show();
                        } else if (dateRegreso.before(dateSalida)) {
                            Toast.makeText(InsertForm.this, "La fecha de regreso debe ser posterior a la fecha de salida", Toast.LENGTH_SHORT).show();
                        } else {
                            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
                            timeFormat.setLenient(false);
                            Date time;
                            try {
                                time = timeFormat.parse(hora);
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
                    } catch (ParseException e) {
                        Toast.makeText(InsertForm.this, "Formato de fecha incorrecto", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}





