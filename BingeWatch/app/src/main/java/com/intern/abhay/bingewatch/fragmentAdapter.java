package com.intern.abhay.bingewatch;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class fragmentAdapter extends FragmentPagerAdapter
{
    fragmentAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int position)
    {
        if (position == 0)
        {
            return new Movies();
        }
        else if (position == 1)
        {
            return new TVShows();
        }
        else
            return null;
    }

    @Override
    public int getCount()
    {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        if (position == 0)
        {
            return "MOVIES";
        }
        else if (position == 1)
        {
            return "TV SHOWS";
        }
        else
            return null;
    }
}