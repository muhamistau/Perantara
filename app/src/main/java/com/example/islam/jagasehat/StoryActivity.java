package com.example.islam.jagasehat;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

public class StoryActivity extends AppCompatActivity {

    ViewPager viewPager;
    ImageView viewPagerNext;
    ImageView viewPagerPrev;
    int chapterNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        Boolean storyFirstRun = getSharedPreferences("PREFERENCE_STORY", MODE_PRIVATE)
                .getBoolean("storyFirstRun", true);

        viewPagerNext = (ImageView) findViewById(R.id.view_pager_next);
        viewPagerPrev = (ImageView) findViewById(R.id.view_pager_prev);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        WormDotsIndicator wormDotsIndicator = (WormDotsIndicator) findViewById(R.id.worm_dots_indicator);

        chapterNumber = Integer.parseInt(getIntent().getStringExtra("chapterNumber"));
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

        if (chapterNumber == 1) {
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    if (position == 0) {
                        // Fill with code to start audio for Fragment 1
                        Toast.makeText(StoryActivity.this, "Position 0", Toast.LENGTH_SHORT).show();
                    } else if (position == 1) {
                        // Fill with code to end Fragment 1 Audio and start audio for Fragment 2
                        Toast.makeText(StoryActivity.this, "Position 1", Toast.LENGTH_SHORT).show();
                    } else if (position == 2) {
                        // Fill with code to end Fragment 2 Audio and start audio for Fragment 3
                        Toast.makeText(StoryActivity.this, "Position 2", Toast.LENGTH_SHORT).show();
                    } else {
                        // Fill with code to end Fragment 3 Audio and start audio for Fragment 4
                        Toast.makeText(StoryActivity.this, "Position 3", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

            if (storyFirstRun) {
                TapTargetView.showFor(StoryActivity.this, TapTarget.forView(findViewById(R.id.view_pager_next), "Tekan panah untuk melanjutkan ke dialog selanjutnya, anda juga dapat menggeser layar untuk melanjutkan dialog")
                                .outerCircleColor(R.color.colorPrimary)
                                .transparentTarget(true)
                                .textColor(R.color.white)
                                .cancelable(false),
                        new TapTargetView.Listener() {
                            @Override
                            public void onTargetClick(TapTargetView view) {
                                super.onTargetClick(view);
                                viewPagerNext = (ImageView) findViewById(R.id.view_pager_next);
                                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
                            }
                        });
                // Remember to uncomment this line
                getSharedPreferences("PREFERENCE_STORY", MODE_PRIVATE).edit()
                        .putBoolean("storyFirstRun", false).apply();
            }
        } else if (chapterNumber == 2) {
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    if (position == 0) {
                        // Fill with code to start audio for Fragment 1
                        Toast.makeText(StoryActivity.this, "Position 0", Toast.LENGTH_SHORT).show();
                    } else if (position == 1) {
                        // Fill with code to end Fragment 1 Audio and start audio for Fragment 2
                        Toast.makeText(StoryActivity.this, "Position 1", Toast.LENGTH_SHORT).show();
                    } else if (position == 2) {
                        // Fill with code to end Fragment 2 Audio and start audio for Fragment 3
                        Toast.makeText(StoryActivity.this, "Position 2", Toast.LENGTH_SHORT).show();
                    } else {
                        // Fill with code to end Fragment 3 Audio and start audio for Fragment 4
                        Toast.makeText(StoryActivity.this, "Position 3", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        } else if (chapterNumber == 3) {
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    if (position == 0) {
                        // Fill with code to start audio for Fragment 1
                        Toast.makeText(StoryActivity.this, "Position 0", Toast.LENGTH_SHORT).show();
                    } else {
                        // Fill with code to end Fragment 3 Audio and start audio for Fragment 4
                        Toast.makeText(StoryActivity.this, "Position 1", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        } else {
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    if (position == 0) {
                        // Fill with code to start audio for Fragment 1
                        Toast.makeText(StoryActivity.this, "Position 0", Toast.LENGTH_SHORT).show();
                    } else if (position == 1) {
                        // Fill with code to end Fragment 1 Audio and start audio for Fragment 2
                        Toast.makeText(StoryActivity.this, "Position 1", Toast.LENGTH_SHORT).show();
                    } else if (position == 2) {
                        // Fill with code to end Fragment 2 Audio and start audio for Fragment 3
                        Toast.makeText(StoryActivity.this, "Position 2", Toast.LENGTH_SHORT).show();
                    } else {
                        // Fill with code to end Fragment 3 Audio and start audio for Fragment 4
                        Toast.makeText(StoryActivity.this, "Position 3", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }
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

    public void viewPagerNext(View view) {
        viewPagerNext = (ImageView) findViewById(R.id.view_pager_next);
        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
    }

    public void viewPagerPrev(View view) {
        viewPagerPrev = (ImageView) findViewById(R.id.view_pager_prev);
        viewPager.setCurrentItem(viewPager.getCurrentItem() - 1, true);
    }

}
