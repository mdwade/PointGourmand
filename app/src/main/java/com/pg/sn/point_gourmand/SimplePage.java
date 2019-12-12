package com.pg.sn.point_gourmand;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by macbookpro on 08/05/2018.
 */

public class SimplePage extends FragmentPagerAdapter {

    private String tabTitles[] = new String[] { "Home","Se Deconnecter","Panier"};

    public SimplePage(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0 : return new HomeFragment();
            case 1 : return new NotificationsFragment();
            case 2:  return new ProfileFragment();
            default:  return null;
        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return tabTitles[position];
    }
}
