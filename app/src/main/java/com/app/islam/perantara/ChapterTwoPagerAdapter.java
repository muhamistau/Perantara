package com.app.islam.perantara;

import android.content.Context;

import com.app.islam.perantara.chapter2.ChapterTwo1Fragment;
import com.app.islam.perantara.chapter2.ChapterTwo21Fragment;
import com.app.islam.perantara.chapter2.ChapterTwo2Fragment;
import com.app.islam.perantara.chapter2.ChapterTwo3Fragment;
import com.app.islam.perantara.chapter2.ChapterTwo4Fragment;
import com.app.islam.perantara.chapter2.ChapterTwo5Fragment;
import com.app.islam.perantara.chapter2.ChapterTwo6Fragment;
import com.app.islam.perantara.chapter2.ChapterTwo7Fragment;
import com.app.islam.perantara.chapter2.ChapterTwo8Fragment;
import com.app.islam.perantara.chapter2.ChapterTwoDoneFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ChapterTwoPagerAdapter extends FragmentPagerAdapter {

    private final int PAGE_COUNT = 11; // Defining how many page in this chapter for the Viewpager
    private Context context;

    // Default Constructor for PagerAdapter
    ChapterTwoPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    // Defining which fragment at which page
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ChapterWelcomeFragment();
            case 1:
                return new ChapterTwo1Fragment();
            case 2:
                return new ChapterTwo2Fragment();
            case 3:
                return new ChapterTwo21Fragment();
            case 4:
                return new ChapterTwo3Fragment();
            case 5:
                return new ChapterTwo4Fragment();
            case 6:
                return new ChapterTwo5Fragment();
            case 7:
                return new ChapterTwo6Fragment();
            case 8:
                return new ChapterTwo7Fragment();
            case 9:
                return new ChapterTwo8Fragment();
            default:
                return new ChapterTwoDoneFragment();

        }
//        if (position == 0) {
//            return new ChapterWelcomeFragment();
//        } else if (position == 1) {
//            return new ChapterTwo1Fragment();
//        } else if (position == 2) {
//            return new ChapterTwo21Fragment();
//        } else if (position == 3) {
//            return new ChapterTwo2Fragment();
//        } else if (position == 4) {
//            return new ChapterTwo3Fragment();
//        } else if (position == 5) {
//            return new ChapterTwo4Fragment();
//        } else if (position == 6) {
//            return new ChapterTwo5Fragment();
//        } else if (position == 7) {
//            return new ChapterTwo6Fragment();
//        } else if (position == 8) {
//            return new ChapterTwo7Fragment();
//        } else if (position == 9) {
//            return new ChapterTwo8Fragment();
//        } else {
//            return new ChapterTwoDoneFragment();
//        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}
