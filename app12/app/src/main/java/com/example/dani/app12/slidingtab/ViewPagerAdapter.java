package com.example.dani.app12.slidingtab;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.dani.app12.Tab1;
import com.example.dani.app12.Tab2;


/**
 * Created by dani on 26/03/16.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter implements SlidingTabLayout.IconTabProvider {

    int tabIcons[];
    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created


    // Build a Constructor and assign the passed Values to appropriate values in the class
   public ViewPagerAdapter(FragmentManager fm, CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;

    }


    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {

            if (position == 0) // if the position is 0 we are returning the First tab
            {
                Tab1 tab1 = new Tab1();
                return tab1;
            }else{

                Tab2 tab2 = new Tab2();
                return tab2;
            }
    }

    // This method return the titles for the Tabs in the Tab Strip

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    // This method return the Number of tabs for the tabs Strip

    @Override
    public int getCount() {
        return NumbOfTabs;
    }

    @Override
    public int getPageIconResId(int position) {
        return tabIcons[position];
    }


}