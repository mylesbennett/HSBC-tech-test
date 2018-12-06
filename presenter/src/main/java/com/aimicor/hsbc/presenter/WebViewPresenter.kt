package com.aimicor.hsbc.presenter

class WebViewPresenter : Presenter<WebViewView>() {

    override fun onAttach(view: WebViewView) {
        unsubscribeOnDetach(view.pageFinishedEvent().subscribe() { view.onPageFinished() })
    }
}