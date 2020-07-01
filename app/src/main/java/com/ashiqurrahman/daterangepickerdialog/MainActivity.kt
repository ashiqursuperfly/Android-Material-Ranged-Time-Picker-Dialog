package com.ashiqurrahman.daterangepickerdialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ashiqurrahman.rangedtimepickerdialog.library.TimeRangePickerDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val d = TimeRangePickerDialog(
            startLabel = "START",
            endLabel = "END",
            is24HourView = false,
            onPickedTimeTime = object : TimeRangePickerDialog.OnPickedTimeRangePick {
                override fun onPickedTime(
                    startHour: Int,
                    startMinute: Int,
                    endHour: Int,
                    endMinute: Int
                ) {
                    val str = ":${startMinute} ${endHour}:${endMinute}"
                    text_view.text = str
                }

            }
        )
        d.isCancelable = false
        d.show(supportFragmentManager, "")
    }
}
