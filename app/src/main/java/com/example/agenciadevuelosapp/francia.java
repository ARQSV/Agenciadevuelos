package com.example.agenciadevuelosapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class francia extends AppCompatActivity {

    LinearLayout marsella, paris, lille, info1, info2, info3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_francia);

        ImageButton btnback = findViewById(R.id.btnBack);
        info1 = findViewById(R.id.Info1);
        info2 = findViewById(R.id.Info2);
        info3 = findViewById(R.id.Info3);
        paris = findViewById(R.id.foto1);
        marsella = findViewById(R.id.foto2);
        lille = findViewById(R.id.foto3);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(francia.this, Cards.class);
                startActivity(intent);
            }
        });
    }

    public void Francia(View view) {
        Intent francia = new Intent(this, francia.class);
        startActivity(francia);
    }

    public void showInfo1(View view) {
        info1.setVisibility(View.VISIBLE);
        info2.setVisibility(View.GONE);
        info3.setVisibility(View.GONE);
        paris.setVisibility(View.VISIBLE);
        marsella.setVisibility(View.GONE);
        lille.setVisibility(View.GONE);
    }

    public void showInfo2(View view) {
        info1.setVisibility(View.GONE);
        info2.setVisibility(View.VISIBLE);
        info3.setVisibility(View.GONE);
        paris.setVisibility(View.GONE);
        marsella.setVisibility(View.VISIBLE);
        lille.setVisibility(View.GONE);
    }

    public void showInfo3(View view) {
        info1.setVisibility(View.GONE);
        info2.setVisibility(View.GONE);
        info3.setVisibility(View.VISIBLE);
        paris.setVisibility(View.GONE);
        marsella.setVisibility(View.GONE);
        lille.setVisibility(View.VISIBLE);
    }
}