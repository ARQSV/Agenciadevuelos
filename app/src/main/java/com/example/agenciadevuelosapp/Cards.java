package com.example.agenciadevuelosapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agenciadevuelosapp.R;

public class Cards extends AppCompatActivity {

    private CheckBox checkBoxIdaVuelta;
    private CheckBox checkBoxSoloIda;
    private EditText editTextSalida;
    private EditText editTextEntrada;
    private Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);

        checkBoxIdaVuelta = findViewById(R.id.checkBoxIdaVuelta);
        checkBoxSoloIda = findViewById(R.id.checkBoxSoloIda);
        editTextSalida = findViewById(R.id.editTextSalida);
        editTextEntrada = findViewById(R.id.editTextEntrada);
        searchButton = findViewById(R.id.buttonSearch);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtener el estado de las casillas de verificación
                boolean isIdaVuelta = checkBoxIdaVuelta.isChecked();
                boolean isSoloIda = checkBoxSoloIda.isChecked();

                // Obtener las fechas de salida y entrada ingresadas por el usuario
                String salida = editTextSalida.getText().toString();
                String entrada = editTextEntrada.getText().toString();

                // Realizar acciones en función de los valores obtenidos
                if (isIdaVuelta) {
                    // El usuario ha seleccionado "Ida y Vuelta"
                    // Puedes realizar acciones específicas aquí
                } else if (isSoloIda) {
                    // El usuario ha seleccionado "Solo Ida"
                    // Puedes realizar acciones específicas aquí
                }

                // Puedes realizar más acciones aquí, como enviar los datos a una actividad de resultados.
            }
        });
    }
}