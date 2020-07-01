package com.ashiqurrahman.rangedtimepickerdialog.library

import android.os.Build
import android.widget.TimePicker
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ashiqurrahman.rangedtimepickerdialog.R


/*
 * Created by : 
 * <a href="https://www.github.com/ashiqursuperfly">Ashiqur Rahman</a> on 7/1/20.
*/

class PickEndTimeFragment : BaseFragment(), PagerAdapter.FragmentViewPagerLifecycle {

    private lateinit var sharedViewModel: SharedViewModel

    override fun getLayoutId(): Int {
        return R.layout.layout_fragment_pick_time
    }

    override fun afterOnViewCreated() {
        sharedViewModel = requireActivity().let { ViewModelProviders.of(it).get(SharedViewModel::class.java) }

        val tp = rootView.findViewById<TimePicker>(R.id.time_picker)

        sharedViewModel.is24HourFormat.observe(viewLifecycleOwner,
            Observer {
                tp.setIs24HourView(it)
            })

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            sharedViewModel.endTimeLiveData.postValue(Pair(tp.hour, tp.minute))
        } else {
            sharedViewModel.endTimeLiveData.postValue(Pair(tp.currentHour, tp.currentMinute))
        }

        tp.setOnTimeChangedListener { _, hourOfDay, minute ->
            val time = Pair(hourOfDay, minute)
            sharedViewModel.endTimeLiveData.postValue(time)
        }
    }

    override fun onResumeFragment() {}

    override fun onPauseFragment() {}

}