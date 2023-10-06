package com.example.agenciadevuelosapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;
    private Button loginButton;
    private TextView registerTextView;

    private static final String USUARIO_PRED = "admin123@gmail.com";
    private static final String CONTRASEÑA_PRED = "admin123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = findViewById(R.id.editTextemail);
        editTextPassword = findViewById(R.id.editTextpassword);
        loginButton = findViewById(R.id.registerButton);
        registerTextView = findViewById(R.id.loginTextView);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if (email.equals(USUARIO_PRED) && password.equals(CONTRASEÑA_PRED)) {
                    Intent intent = new Intent(Login.this, Cards.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Login.this, "Sus credenciales no coinciden", Toast.LENGTH_SHORT).show();
                }
            }
        });

        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Registrarse.class);
                startActivity(intent);
            }
        });
    }
}
