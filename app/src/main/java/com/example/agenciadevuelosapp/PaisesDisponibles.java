package com.example.agenciadevuelosapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import androidx.viewpager.widget.ViewPager;

public class PaisesDisponibles extends AppCompatActivity {
    private ViewPager viewPager1, viewPager2, viewPager3, viewPager4, viewPager5, viewPager6;
    private int[] images1 = {
            R.drawable.fr1, R.drawable.fr2, R.drawable.fr3 };
    private int[] images2 = {
            R.drawable.el1, R.drawable.el2, R.drawable.el3};
    private int[] images3 = {
            R.drawable.ch1, R.drawable.ch2, R.drawable.ch3};
    private int[] images4 = {
            R.drawable.gt1, R.drawable.gt2, R.drawable.gt3};
    private int[] images5 = {
            R.drawable.espa1, R.drawable.espa2, R.drawable.espa3};
    private int[] images6 = {
            R.drawable.us1, R.drawable.us2, R.drawable.us3};

    private Handler handler;
    private Runnable imageRunnable;
    private int currentPage1 = 0;
    private int currentPage2 = 0;
    private int currentPage3 = 0;
    private int currentPage4 = 0;
    private int currentPage5 = 0;
    private int currentPage6 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paisesdisponibles);

        viewPager1 = findViewById(R.id.viewPager1);
        viewPager2 = findViewById(R.id.viewPager2);
        viewPager3 = findViewById(R.id.viewPager3);
        viewPager4 = findViewById(R.id.viewPager4);
        viewPager5 = findViewById(R.id.viewPager5);
        viewPager6 = findViewById(R.id.viewPager6);

        customPagerAdapter adapter1 = new customPagerAdapter(this, images1);
        viewPager1.setAdapter(adapter1);

        customPagerAdapter adapter2 = new customPagerAdapter(this, images2);
        viewPager2.setAdapter(adapter2);

        customPagerAdapter adapter3 = new customPagerAdapter(this, images3);
        viewPager3.setAdapter(adapter3);

        customPagerAdapter adapter4 = new customPagerAdapter(this, images4);
        viewPager4.setAdapter(adapter4);

        customPagerAdapter adapter5 = new customPagerAdapter(this, images5);
        viewPager5.setAdapter(adapter5);

        customPagerAdapter adapter6 = new customPagerAdapter(this, images6);
        viewPager6.setAdapter(adapter6);


        handler = new Handler();
        imageRunnable = new Runnable() {
            @Override
            public void run() {
                if (currentPage1 >= images1.length) {
                    currentPage1 = 0;
                }
                if (currentPage2 >= images2.length) {
                    currentPage2 = 0;
                }
                if (currentPage3 >= images3.length) {
                    currentPage3 = 0;
                }
                if (currentPage4 >= images4.length) {
                    currentPage4 = 0;
                }
                if (currentPage5 >= images5.length) {
                    currentPage5 = 0;
                }
                if (currentPage6 >= images6.length) {
                    currentPage6 = 0;
                }

                viewPager1.setCurrentItem(currentPage1);
                viewPager2.setCurrentItem(currentPage2);
                viewPager3.setCurrentItem(currentPage3);
                viewPager4.setCurrentItem(currentPage4);
                viewPager5.setCurrentItem(currentPage5);
                viewPager6.setCurrentItem(currentPage6);

                currentPage1++;
                currentPage2++;
                currentPage3++;
                currentPage4++;
                currentPage5++;
                currentPage6++;

                handler.postDelayed(this, 3000);
            }
        };


        handler.postDelayed(imageRunnable, 3000);
    }
}



