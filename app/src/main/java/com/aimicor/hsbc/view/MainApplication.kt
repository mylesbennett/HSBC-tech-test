package com.aimicor.hsbc.view

import android.app.Application
import com.aimicor.hsbc.presenter.Presenter
import com.aimicor.hsbc.presenter.WebViewPresenter
import com.aimicor.hsbc.presenter.WebViewView
import com.aimicor.hsbc.view.PresenterDelegate.Companion.presenterBindingsOverride
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

class MainApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        bind<Presenter<WebViewView>>() with provider { WebViewPresenter() }
        presenterBindingsOverride()
    }
}