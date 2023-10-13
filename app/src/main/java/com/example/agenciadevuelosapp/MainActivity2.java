package com.example.agenciadevuelosapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.PopupMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.Query;



public class MainActivity2 extends AppCompatActivity {

    //Se declaran variables miembro para representar las vistas en la actividad
    FloatingActionButton addNoteBtn;
    //agregar
    RecyclerView recyclerView;
    ImageButton menuBtn;
    NoteAdapter noteAdapter;
    SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        addNoteBtn = findViewById(R.id.add_note_btn);
        recyclerView = findViewById(R.id.recyler_view);
        menuBtn = findViewById(R.id.menu_btn);
        searchView = findViewById(R.id.searchViewhome);
        searchView.clearFocus();

        addNoteBtn.setOnClickListener((v) -> startActivity(new Intent(MainActivity2.this, InsertForm.class)));

        menuBtn.setOnClickListener((v) -> showMenu());
        setupRecyclerView();


        recyclerView.setAdapter(noteAdapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Limpia el foco del SearchView para evitar problemas de cierre inesperado
                searchView.clearFocus();
                updateQuery(newText);
                return true;
            }
        });

    }
    //metodo para buscar las notas
    private void updateQuery(String searchText) {
        CollectionReference userNotesRef = Utility.getCollectionReferenceForNotes();

        Query query;

        if (TextUtils.isEmpty(searchText)) {
            query = userNotesRef;
        } else {
            // Utiliza una consulta que busque notas que contengan el texto ingresado en el título
            query = userNotesRef.whereGreaterThanOrEqualTo("title", searchText)
                    .whereLessThan("title", searchText + "\uf8ff");
        }

        FirestoreRecyclerOptions<Note> options = new FirestoreRecyclerOptions.Builder<Note>()
                .setQuery(query, Note.class)
                .build();

        // Actualiza las opciones del adaptador
        noteAdapter.updateOptions(options);
    }


    //para cerrar secion
    void showMenu() {
        PopupMenu popupMenu = new PopupMenu(MainActivity2.this,menuBtn);
        popupMenu.getMenu().add("Salir");
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener( new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if ("Salir".equals(menuItem.getTitle())) {
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(MainActivity2.this, login.class));
                    finish();
                    return true;
                }
                return false;
            }
        });
    }

    void setupRecyclerView(){//configurar el RecyclerView y mostrar la lista de notas


        //Obtiene una consulta de Firebase Firestore para las notas utilizando
        Query query = Utility.getCollectionReferenceForNotes();
        //Se crea una instancia de la clase FirestoreRecyclerOptions
        //que contiene la configuración de la lista de notas
        FirestoreRecyclerOptions<Note> options = new FirestoreRecyclerOptions.Builder<Note>()
                .setQuery(query, Note.class).build();

        //Configura el RecyclerView con un LinearLayoutManager y asigna el adaptador noteAdapter al RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        noteAdapter = new NoteAdapter(options, this);
        recyclerView.setAdapter(noteAdapter);
    }


    //Estos métodos son parte del ciclo de vida de la actividad y se utilizan para administrar la escucha de cambios en Firestore y notificar al adaptador cuando la actividad se encuentra en diferentes estados.
    @Override
    protected void onStart() {
        super.onStart();
        noteAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (noteAdapter != null) {
            noteAdapter.stopListening();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        noteAdapter.notifyDataSetChanged();
    }
}
