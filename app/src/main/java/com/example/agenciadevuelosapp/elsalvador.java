package com.example.agenciadevuelosapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class elsalvador extends AppCompatActivity {

    LinearLayout ss, sa, st, info1, info2, info3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elsalvador);

        ImageButton btnback = findViewById(R.id.btnBack);
        info1 = findViewById(R.id.Info1);
        info2 = findViewById(R.id.Info2);
        info3 = findViewById(R.id.Info3);
        ss = findViewById(R.id.foto1);
        sa = findViewById(R.id.foto2);
        st = findViewById(R.id.foto3);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(elsalvador.this, Cards.class);
                startActivity(intent);
            }
        });
    }

    public void ElSalvador(View view) {
        Intent ESA = new Intent(this, elsalvador.class);
        startActivity(ESA);
    }


    public void showInfo1(View view) {
        info1.setVisibility(View.VISIBLE);
        info2.setVisibility(View.GONE);
        info3.setVisibility(View.GONE);
        ss.setVisibility(View.VISIBLE);
        sa.setVisibility(View.GONE);
        st.setVisibility(View.GONE);
    }

    public void showInfo2(View view) {
        info1.setVisibility(View.GONE);
        info2.setVisibility(View.VISIBLE);
        info3.setVisibility(View.GONE);
        ss.setVisibility(View.GONE);
        sa.setVisibility(View.VISIBLE);
        st.setVisibility(View.GONE);
    }

    public void showInfo3(View view) {
        info1.setVisibility(View.GONE);
        info2.setVisibility(View.GONE);
        info3.setVisibility(View.VISIBLE);
        ss.setVisibility(View.GONE);
        sa.setVisibility(View.GONE);
        st.setVisibility(View.VISIBLE);
    }
}