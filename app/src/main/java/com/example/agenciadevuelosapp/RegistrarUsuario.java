package com.example.agenciadevuelosapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RegistrarUsuario extends AppCompatActivity {

    Button btn_registrarse;
    EditText name,email,password;

    FirebaseFirestore mFirestore;

    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);
        mFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();



        name = findViewById(R.id.editTextusername);
        email = findViewById(R.id.editTextUseremail);
        password = findViewById(R.id.editTextpassword);
        btn_registrarse = findViewById(R.id.btn_regristrarse);


        btn_registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameUser = name.getText().toString().trim();
                String emailUser = email.getText().toString().trim();
                String passUser = password.getText().toString().trim();

                if (nameUser.isEmpty() && emailUser.isEmpty() && passUser.isEmpty()){
                    Toast.makeText(RegistrarUsuario.this, "Completa los Datos", Toast.LENGTH_SHORT).show();
                }else {
                    registerUser(nameUser,emailUser,passUser);
                }
            }
        });

    }

    private void registerUser(String nameUser, String emailUser, String passUser) {
    mAuth.createUserWithEmailAndPassword(emailUser,passUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            String id = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
            Map<String,Object> map = new HashMap<>();
            map.put("name",nameUser);
            map.put("email",emailUser);
            map.put("password",passUser);

            mFirestore.collection("user").document(id).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    finish();
                    startActivity(new Intent(RegistrarUsuario.this, login.class));
                    Toast.makeText(RegistrarUsuario.this, "Usuario Registrado con exito", Toast.LENGTH_SHORT).show();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(RegistrarUsuario.this, "Error al guardar", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }).addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
            Toast.makeText(RegistrarUsuario.this, "Error al registrarse", Toast.LENGTH_SHORT).show();
        }
    });
    }
}