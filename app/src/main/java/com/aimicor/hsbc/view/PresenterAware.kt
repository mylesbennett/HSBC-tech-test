package com.aimicor.hsbc.view

import com.aimicor.hsbc.presenter.Presenter

interface PresenterAware<VIEW: Presenter.View> {
    val presenterDelegate: PresenterDelegate<VIEW>
}