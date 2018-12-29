package com.aimicor.hsbc.view

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import com.aimicor.hsbc.presenter.Presenter

object presenterDelegateSupport {
    fun <T> presenterDelegate(view: T) where T : FragmentActivity, T : Presenter.View =
        PresenterDelegate.presenterDelegate<T>(view) {
            bindLifeCycle(view.lifecycle)
            bindView(view)
        }

    fun <T> presenterDelegate(view: T) where T : Fragment, T : Presenter.View =
        PresenterDelegate.presenterDelegate<T>(view.context) {
            bindLifeCycle(view.lifecycle)
            bindView(view)
        }
}