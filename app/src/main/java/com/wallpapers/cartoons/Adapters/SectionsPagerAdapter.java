package com.wallpapers.cartoons.Adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.wallpapers.cartoons.Fragments.FavoriteFragment;
import com.wallpapers.cartoons.Fragments.HotFragment;
import com.wallpapers.cartoons.Fragments.NewFragment;
import com.wallpapers.cartoons.Fragments.RandomFragment;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
//        return PlaceholderFragment.newInstance(position + 1);
        switch (position) {
            case 0:
                return NewFragment.newInstance();
            case 1:
                return HotFragment.newInstance();
            case 2:
                return RandomFragment.newInstance();
            case 3:
                return FavoriteFragment.newInstance();

        }
        return null; //does not happen
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "NEW";
            case 1:
                return "HOT";
            case 2:
                return "RANDOM";
            case 3:
                return "";
            default:
                return null;
        }

//        return super.getPageTitle(position);
    }
}
