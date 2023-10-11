package com.example.agenciadevuelosapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
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
                String title = data.getExtras().getString("title");
                String desc = data.getExtras().getString("desc");
                addItem(title, desc);
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
                                String title = (String) document.getData().get("title");
                                String desc = (String) document.getData().get("desc");
                                items.add(new ItemModel(document.getId(), title, desc));
                            }
                            list.notifyDataSetChanged();
                        } else {
                            Toast.makeText(MainActivity2.this, "Error al obtener datos", Toast.LENGTH_SHORT).show();
                        }
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
    }

    private void addItem(String title, String desc) {
        items.add(new ItemModel("", title, desc));
        list.notifyDataSetChanged();
        Map<String, Object> item = new HashMap<>();
        item.put("title", title);
        item.put("desc", desc);
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