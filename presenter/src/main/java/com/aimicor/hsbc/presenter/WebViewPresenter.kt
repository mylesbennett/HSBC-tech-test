package com.aimicor.hsbc.presenter

class WebViewPresenter : AbstractPresenter<WebViewView>() {

    override fun onAttach(view: WebViewView) {
        unsubscribeOnDetach(view.pageFinishedEvent().subscribe() { view.onPageFinished() })
    }
}