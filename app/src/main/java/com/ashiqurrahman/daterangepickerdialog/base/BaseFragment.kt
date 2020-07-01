package com.ashiqurrahman.daterangepickerdialog.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.MaterialToolbar

/*
 * Created by : 
 * <a href="https://www.github.com/ashiqursuperfly">Ashiqur Rahman</a> on 7/1/20.
*/
abstract class BaseFragment: Fragment(), View.OnClickListener {

    abstract fun getLayoutId(): Int

    abstract fun afterOnViewCreated()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        afterOnViewCreated()
    }

    override fun onClick(v: View?) { }

    fun setClickListener(vararg views: View) {
        views.forEach { view -> view.setOnClickListener(this) }
    }

    private var mContext: Context? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onDetach() {
        super.onDetach()
        mContext = null
    }
}