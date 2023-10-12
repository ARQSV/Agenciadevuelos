package com.example.agenciadevuelosapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {

    ArrayList<ItemModel> items = new ArrayList<>();
    ListAdapter list;
    ListView listView;
    SwipeRefreshLayout swipeRefreshLayout;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        swipeRefreshLayout = findViewById(R.id.refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadDataFromDB();
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, InsertForm.class);
                startActivityForResult(intent, 1);
            }
        });

        list = new ListAdapter(this, items);
        listView = findViewById(R.id.list);
        listView.setAdapter(list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                removeItem(position);
            }
        });

        this.loadDataFromDB();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == Activity.RESULT_CANCELED) {
                    break;
                }
                String origen = data.getExtras().getString("origen");
                String destino = data.getExtras().getString("destino");
                String salida = data.getExtras().getString("salida");
                String regreso = data.getExtras().getString("regreso");
                String hora= data.getExtras().getString("hora");
                String pago = data.getExtras().getString("pago");

                addItem(origen, destino,salida,regreso,hora,pago);
                break;
        }
    }

    protected void loadDataFromDB() {
        swipeRefreshLayout.setRefreshing(true);
        db.collection("lista").orderBy("created", Query.Direction.ASCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        list.clear();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String origen = (String) document.getData().get("origen");
                                String destino = (String) document.getData().get("destino");
                                String salida = (String) document.getData().get("salida");
                                String regreso = (String) document.getData().get("regreso");
                                String hora = (String) document.getData().get("hora");
                                String pago = (String) document.getData().get("pago");
                                items.add(new ItemModel(document.getId(), origen, destino,salida,regreso,hora,pago));
                            }
                            list.notifyDataSetChanged();
                        } else {
                            Toast.makeText(MainActivity2.this, "Error al obtener datos", Toast.LENGTH_SHORT).show();
                        }
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
    }

    private void addItem(String origen, String destino,String salida,String regreso,String hora, String pago) {
        items.add(new ItemModel("", origen, destino,salida,regreso,hora,pago));
        list.notifyDataSetChanged();
        Map<String, Object> item = new HashMap<>();
        item.put("origen", origen);
        item.put("destino", destino);
        item.put("salida", salida);
        item.put("regreso", regreso);
        item.put("hora", hora);
        item.put("pago", pago);
        item.put("created", new Timestamp(new Date()));
        db.collection("lista")
                .add(item)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        loadDataFromDB();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity2.this, "No se pudo agregar el ítem", Toast.LENGTH_SHORT).show();
                        loadDataFromDB();
                    }
                });
    }

    private void removeItem(final int index) {
        ItemModel item = items.get(index);
        final String itemId = item.getId();

        db.collection("lista").document(itemId).delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Successfully deleted the item from Firestore, now update the UI
                        items.remove(index);
                        list.notifyDataSetChanged();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle the error here
                        String errorMessage = e.getMessage();
                        Log.e("DeleteItemError", "Error deleting item: " + errorMessage);
                        Toast.makeText(MainActivity2.this, "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
