package com.aimicor.hsbc.view

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.content.Context
import com.aimicor.hsbc.presenter.Presenter
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class PresenterDelegate<VIEW : Presenter.View> private constructor(context: Context) : KodeinAware, LifecycleObserver {
    override val kodein: Kodein by closestKodein(context)
    private val presenter: Presenter<VIEW> by instance()

    private lateinit var attachedLifecycle: Lifecycle
    private lateinit var view: VIEW

    companion object {
        fun <VIEW : Presenter.View> presenterDelegate(
            context: Context?,
            body: PresenterDelegate<VIEW>.() -> Unit
        ): PresenterDelegate<VIEW> {
            val presenterDelegate = PresenterDelegate<VIEW>(context!!)
            body(presenterDelegate)
            return presenterDelegate
        }
    }

    fun bindLifeCycle(lifecycle: Lifecycle) {
        attachedLifecycle = lifecycle
        attachedLifecycle.addObserver(this)
    }

    fun bindView(view: VIEW) {
        this.view = view
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun attach() {
        presenter.attach(view)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun detach() {
        presenter.detach(view)
        attachedLifecycle.removeObserver(this)
    }
}

interface PresenterAware {
    val presenterDelegate: PresenterDelegate<out Presenter.View>
}
