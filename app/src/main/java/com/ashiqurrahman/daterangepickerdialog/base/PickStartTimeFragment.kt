package com.ashiqurrahman.daterangepickerdialog.base

import androidx.recyclerview.widget.LinearLayoutManager
import com.ashiqurrahman.daterangepickerdialog.R


/*
 * Created by : 
 * <a href="https://www.github.com/ashiqursuperfly">Ashiqur Rahman</a> on 7/1/20.
*/

class PickStartTimeFragment: BaseFragment(), PagerAdapter.FragmentViewPagerLifecycle {

    private fun observeData() {

    }

    override fun getLayoutId(): Int {
        return R.layout.layout_fragment_pick_time
    }

    override fun afterOnViewCreated() {
        observeData()
    }

    override fun onResumeFragment() {
        // Timber.d("Pick Drugs !!. Resume()")
    }

    override fun onPauseFragment() {
        // Timber.d("Pick Drugs !!. Pause()")
    }

}