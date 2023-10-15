package com.example.agenciadevuelosapp;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Requisitos_viaje extends AppCompatActivity {

    private ExpandableListView expandableListViewRequisitos;
    private RequisitosAdapter requisitosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requisitos_viaje);

        List<String> listDataHeader = new ArrayList<>();
        HashMap<String, List<String>> listDataChild = new HashMap<>();

        listDataHeader.add(getBoldText("Documento de Identidad"));
        List<String> requisito1 = new ArrayList<>();
        requisito1.add(getBoldText("Pasaporte:") + " En muchos casos, se requiere un pasaporte válido para viajar internacionalmente. Asegúrate de que tu pasaporte tenga una validez suficiente antes de la fecha de viaje.\n \n" +
                getBoldText("Visa:") + " Algunos países requieren una visa para ingresar. Verifica si el destino al que viajas exige una visa y asegúrate de obtenerla con anticipación si es necesario.");

        listDataHeader.add(getBoldText("Visados y Permisos"));
        List<String> requisito2 = new ArrayList<>();
        requisito2.add(getBoldText("Visa de Turista:") + " Para estancias turísticas en muchos países.\n \n" +
                getBoldText("Visa de Trabajo:") + " Si planeas trabajar en el país de destino.\n \n" +
                getBoldText("Permisos Especiales:") + " Algunos países pueden requerir permisos especiales para ciertos propósitos, como estudios, voluntariado, etc.");

        listDataHeader.add(getBoldText("Vacunas"));
        List<String> requisito3 = new ArrayList<>();
        requisito3.add(getBoldText("Vacunas Básicas:") + "\n \n" +
                "Hepatitis A y B\n" +
                "Tétanos, difteria y tos ferina\n" +
                "Sarampión, paperas y rubéola (triple vírica)\n \n" +
                getBoldText("Vacunas Recomendadas:") + "\n \n" +
                "Fiebre tifoidea\n" +
                "Meningitis\n" +
                "Encefalitis japonesa\n \n" +
                getBoldText("Vacunas Específicas del Destino:") + "\n \n" +
                "Algunos destinos pueden requerir vacunas específicas, como la fiebre amarilla, dependiendo de la región y la temporada.");

        listDataChild.put(listDataHeader.get(0), requisito1);
        listDataChild.put(listDataHeader.get(1), requisito2);
        listDataChild.put(listDataHeader.get(2), requisito3);

        expandableListViewRequisitos = findViewById(R.id.expandableListViewRequisitos);

        requisitosAdapter = new RequisitosAdapter(this, listDataHeader, listDataChild);
        expandableListViewRequisitos.setAdapter(requisitosAdapter);

        expandableListViewRequisitos.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
            String descripcionRequisito = listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition);
            showToast(descripcionRequisito);
            return false;
        });
    }

    private String getBoldText(String text) {
        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(new StyleSpan(Typeface.BOLD), 0, text.length(), 0);
        return spannableString.toString();
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}