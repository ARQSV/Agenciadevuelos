package com.example.agenciadevuelosapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Registrarse extends AppCompatActivity {

    EditText email, user, pass;
    Button registerButton;

    private FirebaseAuth mAuth;
    FirebaseFirestore mFirestore;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);
        mFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        email = (EditText) findViewById(R.id.editTextemail);
        user = (EditText) findViewById(R.id.editTextUsername);
        pass = (EditText) findViewById(R.id.editTextpassword);
        registerButton = (Button) findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ema = email.getText().toString().trim();
                String username = user.getText().toString().trim();
                String password = pass.getText().toString().trim();

                if (ema.isEmpty() || username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Registrarse.this, "Complete los campos", Toast.LENGTH_SHORT).show();
                } else {
                    registerUser(ema, username, password);
                }

            }
        });

    }

    private void registerUser(String ema, String username, String password) {
        mAuth.createUserWithEmailAndPassword(ema, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    String id = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", id);
                    map.put("email", ema);
                    map.put("password", password);
                    map.put("Username", username);

                    mFirestore.collection("users").document(id).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            finish();
                            startActivity(new Intent(Registrarse.this, Cards.class));
                            Toast.makeText(Registrarse.this, "Usuario registrado con exitos", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e("TAG", "Error al intentar crear el usuario", e);
                            Toast.makeText(Registrarse.this, "Error al registrarse", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Log.e("TAG", "Error al completar la creación del usuario", task.getException());
                    Toast.makeText(Registrarse.this, "Error al registrarse", Toast.LENGTH_SHORT).show();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e){
                Toast.makeText(Registrarse.this, "Error al registrarse", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
