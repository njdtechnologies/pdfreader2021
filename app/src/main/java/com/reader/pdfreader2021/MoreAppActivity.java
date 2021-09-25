package com.reader.pdfreader2021;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class MoreAppActivity extends AppCompatActivity {
    private ViewPager viewPager;

    /* access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_more_app);
        setTitle("Ads By Developer");
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("New Release"));
        tabLayout.addTab(tabLayout.newTab().setText("Latest"));
        tabLayout.addTab(tabLayout.newTab().setText("Trending"));
        tabLayout.addTab(tabLayout.newTab().setText("Popular"));
        tabLayout.setTabGravity(0);
        ViewPager viewPager2 = (ViewPager) findViewById(R.id.pager);
        this.viewPager = viewPager2;
        viewPager2.setAdapter(new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount()));
        this.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener((TabLayout.OnTabSelectedListener) new TabLayout.OnTabSelectedListener() {
            /* class com.reader.pdfreader2021.apps.MoreAppActivity.AnonymousClass1 */

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabReselected(TabLayout.Tab tab) {
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabSelected(TabLayout.Tab tab) {
                MoreAppActivity.this.viewPager.setCurrentItem(tab.getPosition());
            }
        });
    }

    @Override // androidx.activity.ComponentActivity
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
