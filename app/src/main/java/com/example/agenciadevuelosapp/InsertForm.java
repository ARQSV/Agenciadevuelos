package com.example.agenciadevuelosapp;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class InsertForm extends AppCompatActivity {

    private EditText Origen;
    private EditText Destino;
    private EditText FechaS;
    private EditText FechaR;
    private EditText Hora;
    private RadioGroup Pago;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_form);

        Origen = findViewById(R.id.Origen);
        Destino = findViewById(R.id.Destino);

        FechaS = findViewById(R.id.editTextDate);
        FechaR = findViewById(R.id.editTextDate2);
        Hora = findViewById(R.id.editTextTime);
        Pago = findViewById(R.id.paymentMethodRadioGroup);

        FechaS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(FechaS);
            }
        });

        FechaR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(FechaR);
            }
        });

        Hora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(Hora);
            }
        });


        Button btn = findViewById(R.id.addButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAndSendData();
            }
        });
    }

    private void showDatePickerDialog(final EditText dateEditText) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String formattedDate = String.format("%02d-%02d-%d", dayOfMonth, month + 1, year);
                dateEditText.setText(formattedDate);
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    private void showTimePickerDialog(final EditText timeEditText) {
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                String formattedTime = String.format("%02d:%02d", hourOfDay, minute);
                timeEditText.setText(formattedTime);
            }
        }, hour, minute, true); // true para formato de 24 horas
        timePickerDialog.show();
    }

    private void validateAndSendData() {
        String origen = Origen.getText().toString().trim();
        String destino = Destino.getText().toString().trim();
        String salida = FechaS.getText().toString().trim();
        String regreso = FechaR.getText().toString().trim();
        String hora = Hora.getText().toString().trim();
        int selectedPaymentMethod = Pago.getCheckedRadioButtonId();

        if (TextUtils.isEmpty(origen) || TextUtils.isEmpty(destino) || TextUtils.isEmpty(salida) || TextUtils.isEmpty(regreso) || TextUtils.isEmpty(hora) || selectedPaymentMethod == -1) {
            Toast.makeText(InsertForm.this, "Debes rellenar todos los campos y seleccionar un método de pago.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (origen.equalsIgnoreCase(destino)) {
            Toast.makeText(InsertForm.this, "El origen y el destino no pueden ser iguales.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (salida.equals(regreso)) {
            Toast.makeText(InsertForm.this, "La fecha de regreso no puede ser igual a la fecha de salida.", Toast.LENGTH_SHORT).show();
            return;
        }

        String[] paisesPermitidos = {"El Salvador", "Francia", "España", "Guatemala", "Estados Unidos", "China"};
        if (!Arrays.asList(paisesPermitidos).contains(origen) || !Arrays.asList(paisesPermitidos).contains(destino)) {
            Toast.makeText(InsertForm.this, "Países no válidos. Verifica los países disponibles.", Toast.LENGTH_SHORT).show();
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);
        Date dateSalida, dateRegreso;

        try {
            dateSalida = dateFormat.parse(salida);
            dateRegreso = dateFormat.parse(regreso);
        } catch (ParseException e) {
            Toast.makeText(InsertForm.this, "Formato de fecha incorrecto.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (dateRegreso.before(dateSalida)) {
            Toast.makeText(InsertForm.this, "La fecha de regreso debe ser posterior a la fecha de salida.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (Pattern.compile("\\d").matcher(origen).find() || Pattern.compile("\\d").matcher(destino).find()) {
            Toast.makeText(InsertForm.this, "Los campos de origen y destino no deben contener números.", Toast.LENGTH_SHORT).show();
            return;
        }

    }
}




