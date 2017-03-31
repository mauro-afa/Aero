package com.example.mauro.test1;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

/**
 * Created by mfigueroa on 25/06/2015.
 */
public class TabsPagerAdapter extends FragmentPagerAdapter {

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int index) {
        switch (index) {
           case 0:
                return new comida();
            case 1:
                return new Mantenimiento();
            case 2:
                return new Maletero();
            case 3:
                return new Acomodadores();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}

