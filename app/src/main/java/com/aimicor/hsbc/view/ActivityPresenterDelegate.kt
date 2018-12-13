package com.aimicor.hsbc.view

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.support.v4.app.FragmentActivity
import com.aimicor.hsbc.presenter.Presenter
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class ActivityPresenterDelegate<VIEW: Presenter.View>(
    val activity: FragmentActivity,
    val view: VIEW) : KodeinAware, LifecycleObserver {

    override val kodein: Kodein by closestKodein(activity)
    private val presenter: Presenter<VIEW> by instance()

    init {
        activity.lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun create() {
        presenter.attach(view)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun destroy() {
        presenter.detach(view)
        activity.lifecycle.removeObserver(this)
    }

}