package com.example.agenciadevuelosapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class Compras extends AppCompatActivity {

    private Spinner spinnerFlights;
    private TextView textViewPrice;
    private Map<String, Double> flightPrices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compras);

        spinnerFlights = findViewById(R.id.spinner_flights);
        textViewPrice = findViewById(R.id.textview_price);

        // Asigna precios a las opciones del vuelo
        flightPrices = new HashMap<>();
        flightPrices.put("Vuelo Económico", 500.0);
        flightPrices.put("Vuelo de Lujo", 1000.0);

        // Configura el Spinner con opciones de vuelo
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.flight_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFlights.setAdapter(adapter);

        // Agrega un oyente para el Spinner
        spinnerFlights.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Actualiza el precio cuando se selecciona un vuelo
                String selectedFlight = spinnerFlights.getSelectedItem().toString();
                double price = flightPrices.get(selectedFlight);
                textViewPrice.setText("Precio: $" + price);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Aquí puedes manejar qué sucede si no se selecciona nada
                Toast.makeText(Compras.this, "Selecciona un vuelo", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
