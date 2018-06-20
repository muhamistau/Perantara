package com.example.islam.jagasehat;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

public class PengenalanTokohActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengenalan_tokoh);

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        WormDotsIndicator wormDotsIndicator = (WormDotsIndicator) findViewById(R.id.worm_dots_indicator);
        PengenalanTokohPagerAdapter adapter = new PengenalanTokohPagerAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(adapter);
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

//    public void finishChapter() {
//        Intent resultIntent = new Intent();
//        resultIntent.putExtra("finished", 1);
//        setResult(Activity.RESULT_OK, resultIntent);
//        finish();
//    }
}
