package com.ashiqurrahman.daterangepickerdialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ashiqurrahman.daterangepickerdialog.library.TimeRangePickerDialog

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val d = TimeRangePickerDialog()
        d.isCancelable = false
        d.show(supportFragmentManager, "")
    }
}
