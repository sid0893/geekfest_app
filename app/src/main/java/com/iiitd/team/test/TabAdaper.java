package com.iiitd.team.test;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by hp on 21-03-2015.
 */
public class TabAdaper extends FragmentPagerAdapter {

    public TabAdaper(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                // Top Rated fragment activity
                return new PostRequestFragment();
            case 1:
                // Games fragment activity
                return new PendingRequestFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
