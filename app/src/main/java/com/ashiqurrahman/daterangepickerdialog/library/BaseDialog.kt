package com.ashiqurrahman.daterangepickerdialog.library

import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.ashiqurrahman.daterangepickerdialog.R


/*
 * Created by : 
 * <a href="https://www.github.com/ashiqursuperfly">Ashiqur Rahman</a> on 7/1/20.
*/

abstract class BaseDialog: DialogFragment() {

    lateinit var mRootView: View

    abstract fun getLayoutID(): Int

    abstract fun afterOnCreateView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setStyle(STYLE_NO_TITLE, android.R.style.Theme_Material_Light_Dialog_Alert)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        mRootView = inflater.inflate(getLayoutID(), container, false)

        afterOnCreateView()

        return mRootView
    }

    override fun onResume() {
        super.onResume()
        val window: Window? = dialog?.window
        window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            activity!!.resources.getDimensionPixelSize(R.dimen.dialog_height)
        )
        window?.setGravity(Gravity.CENTER)
    }


}