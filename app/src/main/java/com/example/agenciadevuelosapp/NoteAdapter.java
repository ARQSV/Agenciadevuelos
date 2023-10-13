package com.example.agenciadevuelosapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;


public class NoteAdapter extends FirestoreRecyclerAdapter<Note, NoteAdapter.NoteViewHolder> {



    //Esta es la clase principal que implementa el adaptador personalizado.
    private Context context;


    public NoteAdapter(@NonNull FirestoreRecyclerOptions<Note> options, Context context) {
        super(options);
        this.context = context;

    }

    //se realiza la vinculación de datos entre el modelo
    //Note y las vistas de un elemento de la lista en el RecyclerView
    @Override
    protected void onBindViewHolder(@NonNull NoteViewHolder holder, int position, @NonNull Note note) {
        holder.titleTextView.setText(note.title);
        holder.contentTextView.setText(note.Origen);
        holder.contentTextView.setText(note.Destino);
        holder.contentTextView.setText(note.Salida);
        holder.contentTextView.setText(note.Regreso);
        holder.contentTextView.setText(note.Hora);
        holder.timestampTextView.setText(Utility.timestampToString(note.date));

        holder.itemView.setOnClickListener((v) -> {
            Intent intent   = new Intent(context, InsertForm.class);
            intent.putExtra("title", note.title);
            intent.putExtra("Origen", note.Origen);
            intent.putExtra("Destino", note.Destino);
            intent.putExtra("Salida", note.Salida);
            intent.putExtra("Regreso", note.Regreso);
            intent.putExtra("Hora", note.Hora);
            String docId = this.getSnapshots().getSnapshot(position).getId();
            intent.putExtra("docId", docId);

            context.startActivity(intent);

        });

        holder.deleteImageView.setOnClickListener(view -> {
            //Eliminar nota
            String docId = this.getSnapshots().getSnapshot(position).getId();
            deleteNote(docId);
        });

    }
    //Eliminar nota
    private void deleteNote(String docId) {
        // Obtén la referencia a la colección de notas del usuario actual
        CollectionReference userNotesRef = Utility.getCollectionReferenceForNotes();

        // Utiliza la referencia del documento para eliminar la nota
        userNotesRef.document(docId)
                .delete()
                .addOnSuccessListener(aVoid -> {
                    // La nota se eliminó con éxito, puedes mostrar un mensaje si lo deseas
                    Utility.showToast(context, "Nota eliminada con éxito");
                })
                .addOnFailureListener(e -> {
                    // Ocurrió un error al eliminar la nota, muestra un mensaje de error
                    Utility.showToast(context, "Error al eliminar la nota");
                });
    }


    //se utiliza para crear la vista de cada elemento de la lista
    //Este método se llama cuando se necesita crear una nueva vista para un elemento de la lista.
    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_row, parent, false);
        return new NoteViewHolder(view);
    }

    class NoteViewHolder extends RecyclerView.ViewHolder{
        TextView titleTextView, contentTextView, timestampTextView;
        ImageView deleteImageView;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titulo);
            contentTextView = itemView.findViewById(R.id.Origen);
            contentTextView = itemView.findViewById(R.id.Destino);
            contentTextView = itemView.findViewById(R.id.salida);
            contentTextView = itemView.findViewById(R.id.regreso);
            contentTextView = itemView.findViewById(R.id.hora);
            deleteImageView = itemView.findViewById(R.id.deleteNote);

        }
    }
    public void updateOptions(FirestoreRecyclerOptions<Note> options) {
        super.updateOptions(options);
    }

}
