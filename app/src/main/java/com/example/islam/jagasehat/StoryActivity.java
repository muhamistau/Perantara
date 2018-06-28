package com.example.islam.jagasehat;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

public class StoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengenalan_tokoh);

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        WormDotsIndicator wormDotsIndicator = (WormDotsIndicator) findViewById(R.id.worm_dots_indicator);

        int chapterNumber = Integer.parseInt(getIntent().getStringExtra("chapterNumber"));
        ChapterOnePagerAdapter chapterOnePagerAdapter;
        ChapterTwoPagerAdapter chapterTwoPagerAdapter;
        ChapterThreePagerAdapter chapterThreePagerAdapter;
        ChapterFourPagerAdapter chapterFourPagerAdapter;

        if (chapterNumber == 1) {
            chapterOnePagerAdapter = new ChapterOnePagerAdapter(getSupportFragmentManager(), this);
            viewPager.setAdapter(chapterOnePagerAdapter);
        } else if (chapterNumber == 2) {
            chapterTwoPagerAdapter = new ChapterTwoPagerAdapter(getSupportFragmentManager(), this);
            viewPager.setAdapter(chapterTwoPagerAdapter);
        } else if (chapterNumber == 3) {
            chapterThreePagerAdapter = new ChapterThreePagerAdapter(getSupportFragmentManager(), this);
            viewPager.setAdapter(chapterThreePagerAdapter);
        } else {
            chapterFourPagerAdapter = new ChapterFourPagerAdapter(getSupportFragmentManager(), this);
            viewPager.setAdapter(chapterFourPagerAdapter);
        }

        wormDotsIndicator.setViewPager(viewPager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_out_down, R.anim.slide_in_down);
    }

}
