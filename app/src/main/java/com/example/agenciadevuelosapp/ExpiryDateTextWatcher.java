package com.example.agenciadevuelosapp;

import android.text.Editable;
import android.text.TextWatcher;

public class ExpiryDateTextWatcher implements TextWatcher {

    private static final int MAX_LENGTH = 5; // MM/YY

    private boolean isFormatting;
    private int slashCount;

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // No se necesita implementar
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        // No se necesita implementar
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (isFormatting) {
            return;
        }

        isFormatting = true;
        String input = s.toString();

        if (s.length() > 0 && (s.charAt(0) != '0' && s.charAt(0) != '1')) {
            // Si el primer dígito no es '0' ni '1', lo formateamos agregando '0' al principio
            s.insert(0, "0");
        }

        if (s.length() > 2 && s.charAt(2) != '/') {
            // Si el tercer carácter no es una barra '/', lo formateamos
            s.insert(2, "/");
        }

        if (s.length() > MAX_LENGTH) {
            // Limitar la longitud a MM/YY
            s.replace(0, s.length(), s.subSequence(0, MAX_LENGTH));
        }

        // Contar las barras '/'
        slashCount = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '/') {
                slashCount++;
            }
        }

        isFormatting = false;
    }
}

