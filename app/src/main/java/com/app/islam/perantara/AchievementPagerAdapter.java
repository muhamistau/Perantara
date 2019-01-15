package com.app.islam.perantara;

import android.content.Context;

import com.app.islam.perantara.achievements.Achievement1Fragment;
import com.app.islam.perantara.achievements.Achievement2Fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class AchievementPagerAdapter extends FragmentPagerAdapter {

    private final int PAGE_COUNT = 2; // Defining how many page in this chapter for the Viewpager
    private Context context;

    public AchievementPagerAdapter(@NonNull FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Achievement1Fragment();
            default:
                return new Achievement2Fragment();
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}
