package com.ashiqurrahman.rangedtimepickerdialog.library

import android.text.format.Time
import java.text.SimpleDateFormat
import java.util.*


/*
 * Created by : 
 * <a href="https://www.github.com/ashiqursuperfly">Ashiqur Rahman</a> on 7/2/20.
*/

object TimeFormatter {
    fun getFormattedTime(hr: Int, min: Int, is24Hr: Boolean): String {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hr)
        calendar.set(Calendar.MINUTE, min)
        val pattern = if (is24Hr) "HH:mm" else "hh:mm a"
        val formatter = SimpleDateFormat(pattern, Locale.getDefault())
        return formatter.format(calendar.time).toUpperCase(Locale.getDefault())
    }
}