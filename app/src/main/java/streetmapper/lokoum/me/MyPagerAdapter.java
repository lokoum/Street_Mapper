package streetmapper.lokoum.me;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

/**
 * Created by lokoum on 9/19/16.
 */
public class MyPagerAdapter extends FragmentStatePagerAdapter {

    public MyPagerAdapter(FragmentManager fragmentManager){
        super(fragmentManager);

    }
    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        switch(position){
            case 0:
                MapFragment fm =   new MapFragment();
                return fm;
            case 1: return new APView();
            case 2: return new APGlobal();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return ("Map");
        }
        else if (position == 1) {
            return ("Actual list");
        }
        else {
            return ("Global list");
        }
    }
}