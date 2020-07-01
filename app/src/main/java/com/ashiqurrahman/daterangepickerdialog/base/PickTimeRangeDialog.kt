package com.ashiqurrahman.daterangepickerdialog.base

import androidx.viewpager.widget.ViewPager
import com.ashiqurrahman.daterangepickerdialog.R
import com.google.android.material.tabs.TabLayout

/*
 * Created by : 
 * <a href="https://www.github.com/ashiqursuperfly">Ashiqur Rahman</a> on 7/1/20.
*/

class PickTimeRangeDialog : BaseDialog(){

    override fun getLayoutID(): Int {
        return R.layout.layout_rangepickerdialogmain
    }

    override fun afterOnCreateView() {
        val sectionsPagerAdapter = PagerAdapter(childFragmentManager)
        val viewPager = mRootView.findViewById<ViewPager>(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter

        val tabLayout = mRootView.findViewById<TabLayout>(R.id.tabs)
        tabLayout.setupWithViewPager(viewPager)

        viewPager.addOnPageChangeListener(sectionsPagerAdapter.getPageChangeListener())
    }
}