package com.aimicor.hsbc.presenter

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class Presenter<in VIEW : Presenter.View> {
    private lateinit var viewSubscriptions: CompositeDisposable

    fun attach(view: VIEW) {
        viewSubscriptions = CompositeDisposable()
        onAttach(view)
    }

    fun detach(view: VIEW) {
        this.viewSubscriptions.dispose()
    }

    protected fun unsubscribeOnDetach(subscription: Disposable) {
        viewSubscriptions.add(subscription)
    }

    abstract protected fun onAttach(view : VIEW)

    interface View
}