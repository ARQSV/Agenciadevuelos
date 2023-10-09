package com.example.agenciadevuelosapp;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.regex.Pattern;

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
                    if (isValidEmail(email) && isValidPassword(password)) {
                        Toast.makeText(Login.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Login.this, "Por favor, ingrese credenciales válidas", Toast.LENGTH_SHORT).show();
                    }
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

    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPassword(String password) {
        Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-zA-Z]).{8,}$");
        return pattern.matcher(password).matches();
    }
}
