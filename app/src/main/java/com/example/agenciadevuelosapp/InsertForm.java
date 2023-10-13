package com.example.agenciadevuelosapp;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;

public class InsertForm extends AppCompatActivity {

    EditText titleNoteEditText, contentNoteEditText;
    ImageButton saveNoteBtn;
    TextView pageTitleTextView;
    String title,origen, destino, salida, regreso, hora, docId;

    boolean isEditMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_form);

        titleNoteEditText = findViewById(R.id.notes_title_text);
        contentNoteEditText = findViewById(R.id.origen);
        contentNoteEditText = findViewById(R.id.destino);
        contentNoteEditText = findViewById(R.id.salida);
        contentNoteEditText = findViewById(R.id.regreso);
        contentNoteEditText = findViewById(R.id.hora);
        saveNoteBtn = findViewById(R.id.save_note_btn);
        pageTitleTextView = findViewById(R.id.page_title);

        title = getIntent().getStringExtra("title");
        origen = getIntent().getStringExtra("origen");
        destino = getIntent().getStringExtra("destino");
        salida = getIntent().getStringExtra("salida");
        regreso = getIntent().getStringExtra("regreso");
        hora = getIntent().getStringExtra("hora");
        docId = getIntent().getStringExtra("docId");

        if(docId!=null && !docId.isEmpty()){
            isEditMode = true;
        }

        titleNoteEditText.setText(title);
        contentNoteEditText.setText(origen);
        contentNoteEditText.setText(destino);
        contentNoteEditText.setText(salida);
        contentNoteEditText.setText(regreso);
        contentNoteEditText.setText(hora);

        if(isEditMode){
            pageTitleTextView.setText("Editar nota");
        }

        saveNoteBtn.setOnClickListener((v) -> saveNote());


    }
    void saveNote() {
        String titleNote = titleNoteEditText.getText().toString();
        String origenNote = contentNoteEditText.getText().toString();
        String destinoNote = contentNoteEditText.getText().toString();
        String salidaNote = contentNoteEditText.getText().toString();
        String regresoNote = contentNoteEditText.getText().toString();
        String horaNote = contentNoteEditText.getText().toString();
        if (titleNote==null || titleNote.isEmpty()) {
            titleNoteEditText.setError("El titulo es requerido");
            return;
        }

        Note note = new Note();
        note.setTitle(titleNote);
        note.setOrigen(origenNote);
        note.setDestino(destinoNote);
        note.setSalida(salidaNote);
        note.setRegreso(regresoNote);
        note.setHora(horaNote);
        note.setDate(Timestamp.now());

        saveNoteToFirebase(note);
        finish();
    }

    void saveNoteToFirebase(Note note) {
        DocumentReference documentReference;
        if (isEditMode) {
            documentReference = Utility.getCollectionReferenceForNotes().document(docId);
        }else{
            documentReference = Utility.getCollectionReferenceForNotes().document();
        }

        documentReference.set(note).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(InsertForm.this, "Nota guardada", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(InsertForm.this, "No se pudo guardar la nota", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}