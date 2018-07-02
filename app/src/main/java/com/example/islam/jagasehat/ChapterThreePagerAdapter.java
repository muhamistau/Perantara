package com.example.islam.jagasehat;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ChapterThreePagerAdapter extends FragmentPagerAdapter {

    private final int PAGE_COUNT = 3; // Defining how many page in this chapter for the Viewpager
    private Context context;

    ChapterThreePagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new ChapterWelcomeFragment();
        } else if (position == 1) {
            return new ChapterThree1Fragment();
        } else {
            return new ChapterThreeDoneFragment();
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}
