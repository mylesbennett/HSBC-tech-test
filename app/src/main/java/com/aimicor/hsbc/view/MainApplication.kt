package com.aimicor.hsbc.view

import android.app.Application
import com.aimicor.hsbc.presenter.Presenter
import com.aimicor.hsbc.presenter.WebViewPresenter
import com.aimicor.hsbc.presenter.WebViewView
import com.github.salomonbrys.kodein.*

class MainApplication : Application(), KodeinAware {
    override val kodein by Kodein.lazy {
        bind<Presenter<WebViewView>>() with provider { WebViewPresenter() }
    }
}