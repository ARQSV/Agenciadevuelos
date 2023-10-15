package com.example.agenciadevuelosapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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


        TextView origenTextField = rowView.findViewById(R.id.Origen);
        TextView destinoTextField = rowView.findViewById(R.id.Destino);
        TextView salidaTextField = rowView.findViewById(R.id.Fechadesalida);
        TextView regresoTextField = rowView.findViewById(R.id.Fechaderegreso);
        TextView horaTextField = rowView.findViewById(R.id.Hora);
        TextView pagoTextField = rowView.findViewById(R.id.Tipodepago);


        ItemModel currentItem = itemArray.get(position);


        origenTextField.setText(currentItem.getOrigen());
        destinoTextField.setText(currentItem.getDestino());
        salidaTextField.setText(currentItem.getSalida());
        regresoTextField.setText(currentItem.getRegreso());
        horaTextField.setText(currentItem.getHora());
        pagoTextField.setText(currentItem.getPago());

        return rowView;
    }
}
