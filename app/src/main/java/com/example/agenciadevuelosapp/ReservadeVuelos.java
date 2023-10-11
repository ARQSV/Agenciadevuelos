package com.example.agenciadevuelosapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class ReservadeVuelos extends AppCompatActivity {

    private Spinner spinnerOrigin, spinnerDestination, spinnerAirlines;
    private EditText etDate, etReturnDate, etTime;
    private CheckBox checkBoxPayment, checkBoxPago;
    private Button btnReserve;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservasde_vuelo);

        spinnerOrigin = findViewById(R.id.spinnerOrigin);
        spinnerDestination = findViewById(R.id.spinnerDestination);
        spinnerAirlines = findViewById(R.id.spinnerAirlines);
        etDate = findViewById(R.id.etDate);
        etReturnDate = findViewById(R.id.etReturnDate);
        etTime = findViewById(R.id.etTime);
        checkBoxPayment = findViewById(R.id.checkBoxPayment);
        checkBoxPago = findViewById(R.id.checkBoxpago);
        btnReserve = findViewById(R.id.btnReserve);


        String[] countries = getResources().getStringArray(R.array.countries_array);


        ArrayList<String> countryList = new ArrayList<>();
        countryList.add("Seleccione un País");
        countryList.addAll(Arrays.asList(countries));


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, countryList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOrigin.setAdapter(adapter);
        spinnerDestination.setAdapter(adapter);


        String[] airlines = getResources().getStringArray(R.array.airlines_array);


        ArrayList<String> airlineList = new ArrayList<>();
        airlineList.add("Seleccione una Aerolínea");
        airlineList.addAll(Arrays.asList(airlines));


        ArrayAdapter<String> customAdapter = new ArrayAdapter<String>(this, R.layout.custom_spinner_item, airlineList) {
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.custom_spinner_item, parent, false);
                }

                TextView textView = convertView.findViewById(R.id.text);
                ImageView icon = convertView.findViewById(R.id.icon);

                textView.setText(airlineList.get(position));
                icon.setImageResource(R.drawable.baseline_airplanemode_active_24);

                return convertView;
            }
        };

        customAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerAirlines.setAdapter(customAdapter);

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
                String origin = spinnerOrigin.getSelectedItem().toString();
                String destination = spinnerDestination.getSelectedItem().toString();
                String date = etDate.getText().toString().trim();
                String returnDate = etReturnDate.getText().toString().trim();
                String time = etTime.getText().toString().trim();

                if (origin.isEmpty() || destination.isEmpty() || date.isEmpty() || returnDate.isEmpty() || time.isEmpty()) {
                    Toast.makeText(ReservadeVuelos.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (checkBoxPayment.isChecked() && checkBoxPago.isChecked()) {
                    Toast.makeText(ReservadeVuelos.this, "Solo se puede seleccionar un método de pago", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(ReservadeVuelos.this, "La fecha de regreso debe ser posterior a la fecha de ida", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                if (origin.equalsIgnoreCase(destination)) {
                    Toast.makeText(ReservadeVuelos.this, "El país de origen y destino no pueden ser el mismo", Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });
    }

    private void showDatePickerDialog(final EditText editText) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        editText.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                },
                year, month, day
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



