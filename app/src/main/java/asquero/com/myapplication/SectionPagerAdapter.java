package asquero.com.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Anmol on 23-Mar-18.
 */

class SectionPagerAdapter extends FragmentPagerAdapter{

    public SectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                Upcoming upcomingFrag = new Upcoming();
                return upcomingFrag;
            case 1:
                Live liveFrag = new Live();
                return liveFrag;
            case 2:
                Expired expiredFrag = new Expired();
                return expiredFrag;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
