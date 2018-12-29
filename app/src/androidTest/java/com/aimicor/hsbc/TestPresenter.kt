package com.aimicor.hsbc

import com.aimicor.hsbc.presenter.Presenter

open class TestPresenter: Presenter<Presenter.View> {
    override fun attach(view: Presenter.View) {}
    override fun detach(view: Presenter.View) {}
}