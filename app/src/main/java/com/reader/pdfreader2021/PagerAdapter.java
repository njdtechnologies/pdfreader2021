package com.reader.pdfreader2021;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    private int mNumOfTabs;

    PagerAdapter(FragmentManager fragmentManager, int i) {
        super(fragmentManager);
        this.mNumOfTabs = i;
    }

    @Override // androidx.fragment.app.FragmentStatePagerAdapter
    public Fragment getItem(int i) {
        if (i == 0) {
            return new NewApps();
        }
        if (i == 1) {
            return new LatestApps();
        }
        if (i == 2) {
            return new TrendingApps();
        }
        if (i != 3) {
            return null;
        }
        return new PopularApps();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.mNumOfTabs;
    }
}
