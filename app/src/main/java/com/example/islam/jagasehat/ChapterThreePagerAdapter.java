package com.example.islam.jagasehat;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ChapterThreePagerAdapter extends FragmentPagerAdapter {

    private final int PAGE_COUNT = 12; // Defining how many page in this chapter for the Viewpager
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
        } else if (position == 2) {
            return new ChapterThree2Fragment();
        } else if (position == 3) {
            return new ChapterThree3Fragment();
        } else if (position == 4) {
            return new ChapterThree4Fragment();
        } else if (position == 5) {
            return new ChapterThree5Fragment();
        } else if (position == 6) {
            return new ChapterThree6Fragment();
        } else if (position == 7) {
            return new ChapterThree7Fragment();
        } else if (position == 8) {
            return new ChapterThree8Fragment();
        } else if (position == 9) {
            return new ChapterThree9Fragment();
        } else if (position == 10) {
            return new ChapterThree10Fragment();
        } else {
            return new ChapterThreeDoneFragment();
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}
