package com.example.agenciadevuelosapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FAQ extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        List<String> listDataHeader = new ArrayList<>();
        HashMap<String, List<String>> listDataChild = new HashMap<>();

        listDataHeader.add("¿Cómo reservo un vuelo?");
        List<String> pregunta1 = new ArrayList<>();
        pregunta1.add("Puedes reservar un vuelo utilizando nuestra aplicación o visitando nuestro sitio web.");

        listDataHeader.add("¿Cuáles son las políticas de cancelación?");
        List<String> pregunta2 = new ArrayList<>();
        pregunta2.add("Las políticas de cancelación varían según la aerolínea. Puedes encontrar esta información durante el proceso de reserva.");

        listDataHeader.add("¿Cómo puedo realizar un cambio en mi reserva?");
        List<String> pregunta3 = new ArrayList<>();
        pregunta3.add("Puedes realizar cambios en tu reserva directamente a través de nuestra aplicación o contactando a nuestro servicio de atención al cliente.");

        listDataHeader.add("¿Cuáles son las restricciones de equipaje?");
        List<String> pregunta4 = new ArrayList<>();
        pregunta4.add("Las restricciones de equipaje varían según la aerolínea y la clase de tu boleto. Puedes encontrar información detallada durante el proceso de reserva o contactando a la aerolínea.");

        listDataHeader.add("¿Qué debo hacer si pierdo mi vuelo?");
        List<String> pregunta5 = new ArrayList<>();
        pregunta5.add("En caso de perder tu vuelo, comunícate con nuestro equipo de soporte lo antes posible. Podemos ayudarte a encontrar soluciones alternativas.");

        listDataHeader.add("¿Qué debo hacer si mi vuelo se retrasa o cancela?");
        List<String> pregunta6 = new ArrayList<>();
        pregunta6.add("En caso de retrasos o cancelaciones, comunícate con nuestro equipo de soporte. Te proporcionaremos la información actualizada y te ayudaremos con opciones alternativas.");

        listDataHeader.add("¿Cómo puedo obtener un reembolso por mi boleto?");
        List<String> pregunta7 = new ArrayList<>();
        pregunta7.add("Las políticas de reembolso varían según la tarifa y las condiciones de la aerolínea. Consulta las políticas específicas durante el proceso de reserva o comunícate con nuestro equipo de soporte.");

        listDataHeader.add("¿Cuándo debo llegar al aeropuerto antes de mi vuelo?");
        List<String> pregunta8 = new ArrayList<>();
        pregunta8.add("Se recomienda llegar al aeropuerto al menos dos horas antes de vuelos nacionales y tres horas antes de vuelos internacionales para completar el proceso de check-in y seguridad.");

        listDataChild.put(listDataHeader.get(0), pregunta1);
        listDataChild.put(listDataHeader.get(1), pregunta2);
        listDataChild.put(listDataHeader.get(2), pregunta3);
        listDataChild.put(listDataHeader.get(3), pregunta4);
        listDataChild.put(listDataHeader.get(4), pregunta5);
        listDataChild.put(listDataHeader.get(5), pregunta6);
        listDataChild.put(listDataHeader.get(6), pregunta7);
        listDataChild.put(listDataHeader.get(7), pregunta8);

        ExpandableListView expandableListView = findViewById(R.id.expandableListView);

        FAQAdapter faqAdapter = new FAQAdapter(this, listDataHeader, listDataChild);
        expandableListView.setAdapter(faqAdapter);

        expandableListView.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
            String respuesta = listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition);
            Toast.makeText(getApplicationContext(), respuesta, Toast.LENGTH_SHORT).show();
            return false;
        });
    }
}