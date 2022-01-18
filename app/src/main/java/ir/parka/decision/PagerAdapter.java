package ir.parka.decision;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {

	public PagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public CharSequence getPageTitle(int position) {
		switch(position)
		{
		case 0:
			return "راهنما";
		case 1:
			return "محاسبه";
		case 2:
			return "سئوالات متداول";
		default:
			return "محاسبه";
		}
	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
		case 0:
			// Menu fragment activity
			return new FragmentHelp();
		case 1:
			// Calculator fragment activity
			return new FragmentCalculator();
		case 2:
			// Help fragment activity
			return new FragmentFAQ();
		}

		return null;
	}

	@Override
	public int getCount() {
		// get item count - equal to number of tabs
		return 3;
	}

}
