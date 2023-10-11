package com.example.agenciadevuelosapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


import android.os.Bundle;
import android.os.Handler;


public class elsalvador extends AppCompatActivity {

    private ViewPager viewPager;
    private ImagePagerAdapter3 adapter;
    private int currentPage = 0;
    private static final long AUTO_SCROLL_DELAY = 3000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elsalvador);

        viewPager = findViewById(R.id.viewPager3);
        adapter = new ImagePagerAdapter3(this);
        viewPager.setAdapter(adapter);

        startAutoScroll();
    }

    private void startAutoScroll() {
        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            public void run() {
                if (currentPage == adapter.getCount() - 1) {
                    currentPage = 0;
                } else {
                    currentPage++;
                }
                viewPager.setCurrentItem(currentPage, true);
            }
        };

        handler.postDelayed(update, AUTO_SCROLL_DELAY);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                handler.removeCallbacks(update);
                handler.postDelayed(update, AUTO_SCROLL_DELAY);
            }
        });
    }
}