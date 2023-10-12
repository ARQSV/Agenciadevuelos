package com.example.agenciadevuelosapp;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class CarouselPagerAdapter extends FragmentStatePagerAdapter {

    private int[][] imageResources = {
            {R.drawable.fr1, R.drawable.fr2, R.drawable.fr3},
            {R.drawable.el1, R.drawable.el2, R.drawable.el3},
            {R.drawable.ch1, R.drawable.ch2, R.drawable.ch3},
            {R.drawable.gt1, R.drawable.gt2, R.drawable.gt3},
            {R.drawable.espa1, R.drawable.espa2, R.drawable.espa3},
            {R.drawable.us1, R.drawable.us2, R.drawable.us3}
    };

    public CarouselPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        int arrayPosition = position % imageResources.length;
        int imageResource = imageResources[arrayPosition][position % imageResources[arrayPosition].length];
        return ImageFragment.newInstance(imageResource);
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }
}

