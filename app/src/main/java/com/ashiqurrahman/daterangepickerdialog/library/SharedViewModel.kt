package com.ashiqurrahman.daterangepickerdialog.library

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


/*
 * Created by : 
 * <a href="https://www.github.com/ashiqursuperfly">Ashiqur Rahman</a> on 7/1/20.
*/

class SharedViewModel: ViewModel() {
    val startTimeLiveData = MutableLiveData<Pair<Int,Int>>()
    val endTimeLiveData = MutableLiveData<Pair<Int,Int>>()





}