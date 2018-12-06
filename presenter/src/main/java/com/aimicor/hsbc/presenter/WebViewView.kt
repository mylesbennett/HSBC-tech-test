package com.aimicor.hsbc.presenter

import io.reactivex.Observable

interface WebViewView : Presenter.View {
    fun pageFinishedEvent(): Observable<*>
    fun onPageFinished()
}