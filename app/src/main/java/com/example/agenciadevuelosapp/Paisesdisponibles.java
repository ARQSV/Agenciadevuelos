package com.example.agenciadevuelosapp;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class Paisesdisponibles extends AppCompatActivity {
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paisesdisponibles);

        viewPager = findViewById(R.id.viewPager);
        PageAdapter adapter = new PageAdapter(this); // Cambia ImagePagerAdapter a PageAdapter
        viewPager.setAdapter(adapter);
    }
}

