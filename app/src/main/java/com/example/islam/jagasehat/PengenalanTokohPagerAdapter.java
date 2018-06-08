package com.example.islam.jagasehat;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PengenalanTokohPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 3;
    private Context context;

    public PengenalanTokohPagerAdapter(FragmentManager fm, Context context) {

        super(fm);
        this.context = context;

    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {

            return new PengenalanTokoh1Fragment();

        } else if (position == 1) {

            return new PengenalanTokoh2Fragment();

        } else {

            return new PengenalanTokoh3Fragment();

        }
    }

    @Override
    public int getCount() {

        return PAGE_COUNT;

    }
}
