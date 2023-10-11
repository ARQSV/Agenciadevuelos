package com.example.agenciadevuelosapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class China extends AppCompatActivity {

    private ViewPager viewPager2;
    private ImagePagerAdapter2 adapter2;
    private int currentPage = 0;
    private static final long AUTO_SCROLL_DELAY = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_china);

        viewPager2 = findViewById(R.id.viewPager2);
        adapter2 = new ImagePagerAdapter2(this);
        viewPager2.setAdapter(adapter2);

        startAutoScroll();
    }

    private void startAutoScroll() {
        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            public void run() {
                if (currentPage == adapter2.getCount() - 1) {
                    currentPage = 0;
                } else {
                    currentPage++;
                }
                viewPager2.setCurrentItem(currentPage, true);
            }
        };

        handler.postDelayed(update, AUTO_SCROLL_DELAY);

        viewPager2.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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