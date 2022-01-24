package ir.parka.decision

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class PagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(
    fm!!
) {
    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "راهنما"
            1 -> "محاسبه"
            2 -> "سئوالات متداول"
            else -> "محاسبه"
        }
    }

    override fun getItem(index: Int): Fragment {
        when (index) {
            0 ->            // Menu fragment activity
                return FragmentHelp()
            1 ->            // Calculator fragment activity
                return FragmentCalculator()
            2 ->            // Help fragment activity
                return FragmentFAQ()
        }
        return FragmentHelp()
    }

    override fun getCount(): Int {
        // get item count - equal to number of tabs
        return 3
    }
}