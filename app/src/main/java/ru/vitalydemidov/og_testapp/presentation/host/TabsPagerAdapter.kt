package ru.vitalydemidov.og_testapp.presentation.host

import android.content.res.Resources
import android.support.annotation.UiThread
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import ru.vitalydemidov.og_testapp.R
import ru.vitalydemidov.og_testapp.presentation.content.FixturesListFragment
import ru.vitalydemidov.og_testapp.util.FixtureType
import ru.vitalydemidov.og_testapp.util.TabInfo

@UiThread
internal class TabsPagerAdapter(
    private val resources: Resources,
    fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager) {

    private val tabs = arrayOf(
        TabInfo(FixtureType.UPCOMING, R.string.tab_label_fixtures),
        TabInfo(FixtureType.FINAL, R.string.tab_label_results)
    )

    override fun getItem(position: Int): Fragment =
        FixturesListFragment.newInstance(tabs[position].type)

    override fun getCount(): Int = tabs.size

    override fun getPageTitle(position: Int): CharSequence? =
        resources.getString(tabs[position].labelResId)

}