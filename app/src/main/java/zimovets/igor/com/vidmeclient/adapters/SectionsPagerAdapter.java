package zimovets.igor.com.vidmeclient.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import zimovets.igor.com.vidmeclient.fragment.LoginFragment;
import zimovets.igor.com.vidmeclient.fragment.PlaceholderVideoFragment;


/**
 * A {@link FragmentStatePagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

    public static final String LOG = SectionsPagerAdapter.class.getSimpleName();

    static final int NUM_ITEMS = 3;
    Context mContext;


    public SectionsPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;

    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        if (position == 0){
            Log.d(LOG, "fragment 1");
            return PlaceholderVideoFragment.newInstance(position);

        }else if (position == 1){
            Log.d(LOG, "fragment 2");
            return PlaceholderVideoFragment.newInstance(position);
        }
        else {
            return LoginFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return NUM_ITEMS;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "FEATURED";
            case 1:
                return "NEW";
            case 2:
                return "FEED";
        }
        return null;
    }

}