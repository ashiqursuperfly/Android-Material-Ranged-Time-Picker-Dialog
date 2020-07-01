package com.ashiqurrahman.daterangepickerdialog.library

import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.ashiqurrahman.daterangepickerdialog.R
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
            val hr = it.first
            val min = it.second
            val time = "$hr:$min"
            val tab = tabLayout.getTabAt(0)
            if (tab?.customView == null) tab?.customView = getTabCustomView(startLabel,time)
            else {
                val view = tab.customView
                view?.findViewById<MaterialTextView>(R.id.tv_title)?.text = startLabel
                view?.findViewById<MaterialTextView>(R.id.tv_time)?.text = time
            }
        })

        sharedViewModel.endTimeLiveData.observe(viewLifecycleOwner, Observer {
            val hr = it.first
            val min = it.second
            val time = "$hr:$min"
            val tab = tabLayout.getTabAt(1)
            if(tab?.customView == null)tab?.customView = getTabCustomView(endLabel,time)
            else {
                val view = tab.customView
                view?.findViewById<MaterialTextView>(R.id.tv_title)?.text = endLabel
                view?.findViewById<MaterialTextView>(R.id.tv_time)?.text = time
            }
        })
    }

    override fun getLayoutID(): Int {
        return R.layout.layout_rangepickerdialogmain
    }

    override fun afterOnCreateView() {
        sectionsPagerAdapter = PagerAdapter(childFragmentManager)
        viewPager = mRootView.findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        viewPager.addOnPageChangeListener(sectionsPagerAdapter.getPageChangeListener())

        tabLayout = mRootView.findViewById(R.id.tabs)
        tabLayout.setupWithViewPager(viewPager)

        val btnOk = mRootView.findViewById<MaterialButton>(R.id.btn_ok)
        val btnCancel = mRootView.findViewById<MaterialButton>(R.id.btn_cancel)
        btnOk.setOnClickListener {
            if (viewPager.currentItem == 0) {
                viewPager.setCurrentItem(1, true)
            }
            else if (viewPager.currentItem == 1) {
                val startTime = sharedViewModel.startTimeLiveData.value
                val endTime = sharedViewModel.endTimeLiveData.value
                onPickedTimeTime.onPickedTime(
                    startTime?.first?:0,
                    startTime?.second?:0,
                    endTime?.first ?: 0,
                    endTime?.second?: 0
                )
                dismiss()
            }
        }
        btnCancel.setOnClickListener {
            dismiss()
        }

        sharedViewModel = requireActivity().let { ViewModelProviders.of(it).get(SharedViewModel::class.java) }
        sharedViewModel.is24HourFormat.postValue(is24HourView)
        observeData()
    }

    private fun getTabCustomView(header: String, text: String): View {
        val v: View = LayoutInflater.from(requireContext()).inflate(R.layout.custom_tab, null)
        val title = v.findViewById(R.id.tv_title) as MaterialTextView
        title.text = header

        val time = v.findViewById(R.id.tv_time) as MaterialTextView
        time.text = text
        return v
    }
}