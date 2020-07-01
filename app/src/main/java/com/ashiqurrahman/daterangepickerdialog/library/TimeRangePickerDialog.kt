package com.ashiqurrahman.daterangepickerdialog.library

import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
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
    var startLabel: String = "Start",
    var endLabel: String = "End",
    var is24HourView: Boolean = false
) : BaseDialog(){

    private lateinit var sectionsPagerAdapter: PagerAdapter
    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout
    private lateinit var sharedViewModel: SharedViewModel

    private fun observeData() {
        sharedViewModel.startTimeLiveData.observe(viewLifecycleOwner, Observer {
            val hr = it.first
            val min = it.second

            val tab = tabLayout.getTabAt(0)
            tab?.customView = getTabCustomView(startLabel,"$hr:$min")

        })

        sharedViewModel.endTimeLiveData.observe(viewLifecycleOwner, Observer {
            val hr = it.first
            val min = it.second

            val tab = tabLayout.getTabAt(1)
            tab?.customView = getTabCustomView(endLabel,"$hr:$min")
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
                // TODO: callback results
                dismiss()
            }
        }
        btnCancel.setOnClickListener {
            dismiss()
        }

        sharedViewModel = requireActivity().let { ViewModelProviders.of(it).get(SharedViewModel::class.java) }
        observeData()
    }

    fun getTabCustomView(header: String, text: String): View {
        val v: View = LayoutInflater.from(requireContext()).inflate(R.layout.custom_tab, null)
        val title = v.findViewById(R.id.tv_title) as MaterialTextView
        title.text = header

        val time = v.findViewById(R.id.tv_time) as MaterialTextView
        time.text = text
        return v
    }
}