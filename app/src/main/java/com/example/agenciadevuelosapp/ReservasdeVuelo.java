package com.example.agenciadevuelosapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class ReservasdeVuelo extends AppCompatActivity {

    private AutoCompleteTextView etOrigin, etDestination;
    private EditText etDate, etReturnDate, etTime;
    private CheckBox checkBoxPayment, checkBoxPago;
    private Button btnReserve;

    private final String[] allowedCountries = {"Francia", "El Salvador", "China", "Guatemala"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservasde_vuelo);

        etOrigin = findViewById(R.id.etOrigin);
        etDestination = findViewById(R.id.etDestination);
        etDate = findViewById(R.id.etDate);
        etReturnDate = findViewById(R.id.etReturnDate);
        etTime = findViewById(R.id.etTime);
        checkBoxPayment = findViewById(R.id.checkBoxPayment);
        checkBoxPago = findViewById(R.id.checkBoxpago);
        btnReserve = findViewById(R.id.btnReserve);


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, allowedCountries);
        etOrigin.setAdapter(adapter);
        etDestination.setAdapter(adapter);

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(etDate);
            }
        });

        etReturnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(etReturnDate);
            }
        });

        etTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(etTime);
            }
        });


        checkBoxPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxPayment.isChecked()) {
                    checkBoxPago.setChecked(false);
                }
            }
        });

        checkBoxPago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxPago.isChecked()) {
                    checkBoxPayment.setChecked(false);
                }
            }
        });

        btnReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String origin = etOrigin.getText().toString().trim();
                String destination = etDestination.getText().toString().trim();
                String date = etDate.getText().toString().trim();
                String returnDate = etReturnDate.getText().toString().trim();
                String time = etTime.getText().toString().trim();

                if (origin.isEmpty() || destination.isEmpty() || date.isEmpty() || returnDate.isEmpty() || time.isEmpty()) {
                    Toast.makeText(ReservasdeVuelo.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (checkBoxPayment.isChecked() && checkBoxPago.isChecked()) {
                    Toast.makeText(ReservasdeVuelo.this, "Solo se puede seleccionar un método de pago", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!returnDate.isEmpty()) {
                    String[] dateParts = date.split("/");
                    String[] returnDateParts = returnDate.split("/");

                    int day = Integer.parseInt(dateParts[0]);
                    int month = Integer.parseInt(dateParts[1]);
                    int year = Integer.parseInt(dateParts[2]);

                    int returnDay = Integer.parseInt(returnDateParts[0]);
                    int returnMonth = Integer.parseInt(returnDateParts[1]);
                    int returnYear = Integer.parseInt(returnDateParts[2]);

                    if (returnYear < year || (returnYear == year && returnMonth < month) ||
                            (returnYear == year && returnMonth == month && returnDay <= day)) {
                        Toast.makeText(ReservasdeVuelo.this, "La fecha de regreso debe ser posterior a la fecha de ida", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                if (origin.equalsIgnoreCase(destination)) {
                    Toast.makeText(ReservasdeVuelo.this, "El país de origen y destino no pueden ser el mismo", Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });
    }

    private void showDatePickerDialog(final EditText editText) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        editText.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                },
                year, month, dayOfMonth
        );

        datePickerDialog.show();
    }

    private void showTimePickerDialog(final EditText editText) {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        editText.setText(hourOfDay + ":" + minute);
                    }
                },
                hour, minute, true
        );

        timePickerDialog.show();
    }
}