package com.example.agenciadevuelosapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class China extends AppCompatActivity {

    LinearLayout honkong, shangai, pekin, info1, info2, info3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_china);

        ImageButton btnback = findViewById(R.id.btnBack);
        info1 = findViewById(R.id.Info1);
        info2 = findViewById(R.id.Info2);
        info3 = findViewById(R.id.Info3);
        honkong = findViewById(R.id.foto1);
        shangai = findViewById(R.id.foto2);
        pekin = findViewById(R.id.foto3);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(China.this, Cards.class);
                startActivity(intent);
            }
        });
    }

    public void China(View view) {
        Intent china = new Intent(this, China.class);
        startActivity(china);
    }


    public void showInfo1(View view) {
        info1.setVisibility(View.VISIBLE);
        info2.setVisibility(View.GONE);
        info3.setVisibility(View.GONE);
        honkong.setVisibility(View.VISIBLE);
        shangai.setVisibility(View.GONE);
        pekin.setVisibility(View.GONE);
    }

    public void showInfo2(View view) {
        info1.setVisibility(View.GONE);
        info2.setVisibility(View.VISIBLE);
        info3.setVisibility(View.GONE);
        honkong.setVisibility(View.GONE);
        shangai.setVisibility(View.VISIBLE);
        pekin.setVisibility(View.GONE);
    }

    public void showInfo3(View view) {
        info1.setVisibility(View.GONE);
        info2.setVisibility(View.GONE);
        info3.setVisibility(View.VISIBLE);
        honkong.setVisibility(View.GONE);
        shangai.setVisibility(View.GONE);
        pekin.setVisibility(View.VISIBLE);
    }
}