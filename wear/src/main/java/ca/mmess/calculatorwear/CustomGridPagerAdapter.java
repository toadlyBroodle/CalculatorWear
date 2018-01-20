package ca.mmess.calculatorwear;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.wearable.view.CardFragment;
import android.support.wearable.view.FragmentGridPagerAdapter;
import android.view.Gravity;
import android.view.View;

/**
 * Constructs fragments as requested by the GridViewPager. For each row a
 * different background is provided.
 */
public class CustomGridPagerAdapter extends FragmentGridPagerAdapter {

    private final Context mContext;

    public CustomGridPagerAdapter(Context ctx, FragmentManager fm) {
        super(fm);
        mContext = ctx;
    }

    /** A simple container for static data in each page */
    private static class Page {

        String titleRes;
        String textRes;

        public Page(String titleRes, String textRes) {
            this.titleRes = titleRes;
            this.textRes = textRes;
        }
    }

    private final Page[][] PAGES = {
            {
                    new Page("1", "one"),
                    new Page("2", "two"),
                    new Page("3", "three")
            },

    };

    @Override
    public Fragment getFragment(int row, int col) {
        Page page = PAGES[row][col];

        switch (col) {
            case 0: SimpleFragment simpFrag = new SimpleFragment();
                return simpFrag;
            case 1: AdvancedFragment advFrag = new AdvancedFragment();
                return advFrag;
            default:
                ColorFragment colorFrag = new ColorFragment();

                return colorFrag;
        }
    }

    @Override
    public int getRowCount() {
        return PAGES.length;
    }

    @Override
    public int getColumnCount(int rowNum) {
        return PAGES[rowNum].length;
    }
}
