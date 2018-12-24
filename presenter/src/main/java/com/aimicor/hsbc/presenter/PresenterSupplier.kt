package com.aimicor.hsbc.presenter

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

interface Presenter<in VIEW : Presenter.View> {
    fun attach(view: VIEW)
    fun detach(view: VIEW)
    interface View
}

abstract class AbstractPresenter<in VIEW : Presenter.View> : Presenter<VIEW> {

    private lateinit var viewSubscriptions: CompositeDisposable

    override fun attach(view: VIEW) {
        viewSubscriptions = CompositeDisposable()
        onAttach(view)
    }

    override fun detach(view: VIEW) {
        this.viewSubscriptions.dispose()
    }

    protected fun unsubscribeOnDetach(subscription: Disposable) {
        viewSubscriptions.add(subscription)
    }

    abstract protected fun onAttach(view : VIEW)
}