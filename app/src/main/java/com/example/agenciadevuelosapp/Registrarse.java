package com.example.agenciadevuelosapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import java.util.regex.Pattern;

public class Registrarse extends AppCompatActivity {

    private EditText editTextEmail, editTextUsername, editTextPassword;
    private Button registerButton;
    private TextView loginTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        editTextEmail = findViewById(R.id.editTextemail);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextpassword);
        registerButton = findViewById(R.id.registerButton);
        loginTextView = findViewById(R.id.loginTextView);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextEmail.getText().toString().trim();
                String username = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if (email.isEmpty() || username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Registrarse.this, "Todos los campos son requeridos", Toast.LENGTH_SHORT).show();
                } else if (!isValidEmail(email)) {
                    Toast.makeText(Registrarse.this, "Ingrese un correo válido", Toast.LENGTH_SHORT).show();
                } else if (!isValidUsername(username)) {
                    Toast.makeText(Registrarse.this, "Ingrese la primera letra en mayúscula para su usuario", Toast.LENGTH_SHORT).show();
                } else if (!isValidPassword(password)) {
                    Toast.makeText(Registrarse.this, "Su contraseña debe ser mayor o igual a 8 dígitos y contener letras y números", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Registrarse.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                }
            }
        });

        loginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Registrarse.this, Login.class);
                startActivity(intent);
            }
        });
    }

    private boolean isValidEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidUsername(String username) {
        return Pattern.compile("^[A-Z][a-zA-Z]*$").matcher(username).matches();
    }

    private boolean isValidPassword(String password) {
        return Pattern.compile("^(?=.*[0-9])(?=.*[a-zA-Z]).{8,}$").matcher(password).matches();
    }
}

