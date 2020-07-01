package com.ashiqurrahman.rangedtimepickerdialog.library

import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.ashiqurrahman.rangedtimepickerdialog.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.textview.MaterialTextView

/*
 * Created by : 
 * <a href="https://www.github.com/ashiqursuperfly">Ashiqur Rahman</a> on 7/1/20.
*/

class TimeRangePickerDialog(
    var onPickedTimeTime: OnPickedTimeRangePick,
    var startLabel: String = "Start",
    var endLabel: String = "End",
    var is24HourView: Boolean = false
) : BaseDialog() {
    private lateinit var sectionsPagerAdapter: PagerAdapter
    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout
    private lateinit var sharedViewModel: SharedViewModel

    interface OnPickedTimeRangePick {
        fun onPickedTime(startHour: Int, startMinute: Int, endHour: Int, endMinute: Int)
    }

    private fun observeData() {
        sharedViewModel.startTimeLiveData.observe(viewLifecycleOwner, Observer {
            postSelectedTimeToTab(0, it)
        })

        sharedViewModel.endTimeLiveData.observe(viewLifecycleOwner, Observer {
            postSelectedTimeToTab(1, it)
        })
    }

    override fun getLayoutID(): Int {
        return R.layout.layout_rangepickerdialogmain
    }

    override fun afterOnCreateView() {
        sharedViewModel = requireActivity().let { ViewModelProviders.of(it).get(SharedViewModel::class.java) }

        sectionsPagerAdapter = PagerAdapter(childFragmentManager)
        viewPager = mRootView.findViewById(R.id.view_pager)
        tabLayout = mRootView.findViewById(R.id.tabs)
        viewPager.adapter = sectionsPagerAdapter
        viewPager.addOnPageChangeListener(sectionsPagerAdapter.getPageChangeListener())
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {}

            override fun onPageSelected(newPosition: Int) {
                val selectedTv = tabLayout.getTabAt(newPosition)?.customView?.findViewById<MaterialTextView>(R.id.tv_time)
                val disabledTv = tabLayout.getTabAt((newPosition + 1)% sectionsPagerAdapter.count)?.customView?.findViewById<MaterialTextView>(R.id.tv_time)

                selectedTv?.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorAccent))
                disabledTv?.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorDisabled))

            }
        }
        )


        tabLayout.setupWithViewPager(viewPager)

        val btnOk = mRootView.findViewById<MaterialButton>(R.id.btn_ok)
        val btnCancel = mRootView.findViewById<MaterialButton>(R.id.btn_cancel)
        btnOk.setOnClickListener {
            if (viewPager.currentItem == 0) {
                viewPager.setCurrentItem(1, true)
            } else if (viewPager.currentItem == 1) {
                // post results
                val startTime = sharedViewModel.startTimeLiveData.value
                val endTime = sharedViewModel.endTimeLiveData.value
                onPickedTimeTime.onPickedTime(
                    startTime?.first ?: 0,
                    startTime?.second ?: 0,
                    endTime?.first ?: 0,
                    endTime?.second ?: 0
                )
                dismiss()
            }
        }
        btnCancel.setOnClickListener {
            dismiss()
        }
        sharedViewModel.is24HourFormat.postValue(is24HourView)
        observeData()
    }

    private fun postSelectedTimeToTab(selected: Int, time: Pair<Int, Int>) {
        val hr = if (time.first < 10) "0${time.first}" else "${time.first}"
        val min = if (time.second < 10) "0${time.second}" else "${time.second}"
        val timeText = "$hr:$min"
        val selectedTab = tabLayout.getTabAt(selected)
        if (selectedTab?.customView == null) {
            selectedTab?.customView = sectionsPagerAdapter.createTabCustomView(
                requireContext(),
                if (selected == 0) startLabel else endLabel,
                timeText,
                selected
            )
        } else {
            val selectedView = selectedTab.customView
            selectedView?.findViewById<MaterialTextView>(R.id.tv_title)?.text = startLabel
            val selectedTv = selectedView?.findViewById<MaterialTextView>(R.id.tv_time)
            selectedTv?.text = timeText
        }
    }

}