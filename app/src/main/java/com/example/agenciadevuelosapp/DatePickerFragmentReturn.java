package com.example.agenciadevuelosapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import java.util.Calendar;

public class DatePickerFragmentReturn extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    private EditText editTextDateReturn;

    public DatePickerFragmentReturn(EditText editTextDateReturn) {
        this.editTextDateReturn = editTextDateReturn;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        String selectedDate = String.format("%02d/%02d/%04d", day, month + 1, year);
        editTextDateReturn.setText(selectedDate);
    }
}

