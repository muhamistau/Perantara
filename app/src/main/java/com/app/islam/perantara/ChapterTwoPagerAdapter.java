package com.app.islam.perantara;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ChapterTwoPagerAdapter extends FragmentPagerAdapter {

    private final int PAGE_COUNT = 10; // Defining how many page in this chapter for the Viewpager
    private Context context;

    // Default Constructor for PagerAdapter
    ChapterTwoPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    // Defining which fragment at which page
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new ChapterWelcomeFragment();
        } else if (position == 1) {
            return new ChapterTwo1Fragment();
        } else if (position == 2) {
            return new ChapterTwo2Fragment();
        } else if (position == 3) {
            return new ChapterTwo3Fragment();
        } else if (position == 4) {
            return new ChapterTwo4Fragment();
        } else if (position == 5) {
            return new ChapterTwo5Fragment();
        } else if (position == 6) {
            return new ChapterTwo6Fragment();
        } else if (position == 7) {
            return new ChapterTwo7Fragment();
        } else if (position == 8) {
            return new ChapterTwo8Fragment();
        } else {
            return new ChapterTwoDoneFragment();
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}
