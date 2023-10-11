package com.example.agenciadevuelosapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter {

    private final Activity context;
    private final ArrayList<ItemModel> itemArray;

    public ListAdapter(Activity context, ArrayList<ItemModel> items){
        super(context,R.layout.listview_row , items);
        this.context=context;
        this.itemArray = items;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.listview_row, null,true);

        TextView origenTextField = (TextView) rowView.findViewById(R.id.Origen);
        TextView destinoTextField = (TextView) rowView.findViewById(R.id.Destino);
        TextView salidaTextField = (TextView) rowView.findViewById(R.id.Fechadesalida);
        TextView regresoTextField = (TextView) rowView.findViewById(R.id.Fechaderegreso);
        TextView horaTextField = (TextView) rowView.findViewById(R.id.Hora);
        TextView pagoTextField = (TextView) rowView.findViewById(R.id.Tipodepago);


        origenTextField.setText(itemArray.get(position).origen);
        destinoTextField.setText(itemArray.get(position).destino);
        salidaTextField.setText(itemArray.get(position).salida);
        regresoTextField.setText(itemArray.get(position).regreso);
        horaTextField.setText(itemArray.get(position).hora);
        pagoTextField.setText(itemArray.get(position).pago);


        return rowView;
    }

}