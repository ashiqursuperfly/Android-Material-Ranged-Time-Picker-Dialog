package com.ashiqurrahman.daterangepickerdialog.library

import android.os.Build
import android.widget.TimePicker
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ashiqurrahman.daterangepickerdialog.R
import kotlin.math.min


/*
 * Created by : 
 * <a href="https://www.github.com/ashiqursuperfly">Ashiqur Rahman</a> on 7/1/20.
*/

class PickStartTimeFragment: BaseFragment(), PagerAdapter.FragmentViewPagerLifecycle {

    private lateinit var sharedViewModel: SharedViewModel

    override fun getLayoutId(): Int {
        return R.layout.layout_fragment_pick_time
    }

    override fun afterOnViewCreated() {
        sharedViewModel = requireActivity().let { ViewModelProviders.of(it).get(SharedViewModel::class.java) }
        val tp = rootView.findViewById<TimePicker>(R.id.time_picker)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            sharedViewModel.startTimeLiveData.postValue(Pair(tp.hour, tp.minute))
        }else {
            sharedViewModel.startTimeLiveData.postValue(Pair(tp.currentHour, tp.currentMinute))
        }
        tp.setOnTimeChangedListener { view, hourOfDay, minute ->
            val time = Pair(hourOfDay, minute)
            sharedViewModel.startTimeLiveData.postValue(time)
        }
    }

    override fun onResumeFragment() {
        // Timber.d("Pick Drugs !!. Resume()")
    }

    override fun onPauseFragment() {
        // Timber.d("Pick Drugs !!. Pause()")
    }

}