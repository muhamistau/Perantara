package com.app.islam.perantara;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ChapterOnePagerAdapter extends FragmentPagerAdapter {

    private final int PAGE_COUNT = 5; // Defining how many page in this chapter for the Viewpager
    private Context context;

    // Default Constructor for PagerAdapter
    ChapterOnePagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    // Defining which fragment at which page
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new ChapterWelcomeFragment();
        } else if (position == 1) {
            return new ChapterOne1Fragment();
        } else if (position == 2) {
            return new ChapterOne2Fragment();
        } else if (position == 3) {
            return new ChapterOne3Fragment();
        } else {
            return new ChapterOneDoneFragment();
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}
