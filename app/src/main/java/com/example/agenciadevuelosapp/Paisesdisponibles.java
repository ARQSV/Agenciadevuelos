package com.example.agenciadevuelosapp;

import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

public class Paisesdisponibles extends AppCompatActivity {
    private ViewPager[] viewPagers = new ViewPager[6];
    private int[] adapterIds = {R.id.viewPager1, R.id.viewPager2, R.id.viewPager3, R.id.viewPager4, R.id.viewPager5, R.id.viewPager6};
    private int currentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paisesdisponibles);

        for (int i = 0; i < viewPagers.length; i++) {
            viewPagers[i] = findViewById(adapterIds[i]);
        }

        final CarouselPagerAdapter adapter = new CarouselPagerAdapter(getSupportFragmentManager());
        for (ViewPager viewPager : viewPagers) {
            viewPager.setAdapter(adapter);
        }

        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            public void run() {
                if (currentPage == 6) {
                    currentPage = 0;
                }
                for (int i = 0; i < viewPagers.length; i++) {
                    viewPagers[i].setCurrentItem(currentPage, true);
                }
                currentPage++;
            }
        };

        int delay = 3000;
        int period = 3000;
        handler.postDelayed(update, delay);
    }
}

