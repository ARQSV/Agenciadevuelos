package com.example.agenciadevuelosapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<ItemModel> {

    private final Activity context;
    private final ArrayList<ItemModel> itemArray;

    public ListAdapter(Activity context, ArrayList<ItemModel> items) {
        super(context, R.layout.listview_row, items);
        this.context = context;
        this.itemArray = items;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.listview_row, null, false);


        EditText origenTextField = rowView.findViewById(R.id.Origen);
        EditText destinoTextField = rowView.findViewById(R.id.Destino);
        EditText salidaTextField = rowView.findViewById(R.id.salida);
        EditText regresoTextField = rowView.findViewById(R.id.regreso);
        EditText horaTextField = rowView.findViewById(R.id.hora);

        ItemModel currentItem = itemArray.get(position);

        origenTextField.setText(currentItem.getOrigen());
        destinoTextField.setText(currentItem.getDestino());
        salidaTextField.setText(currentItem.getOrigen());
        regresoTextField.setText(currentItem.getDestino());
        horaTextField.setText(currentItem.getOrigen());


        return rowView;
    }
}
