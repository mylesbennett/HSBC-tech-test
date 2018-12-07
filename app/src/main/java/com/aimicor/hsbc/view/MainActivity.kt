package com.aimicor.hsbc.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.webkit.WebView
import com.aimicor.hsbc.R
import com.aimicor.hsbc.presenter.Presenter
import com.aimicor.hsbc.presenter.WebViewView
import com.aimicor.rxwebview.events
import com.github.salomonbrys.kodein.KodeinInjector
import com.github.salomonbrys.kodein.android.AppCompatActivityInjector
import com.github.salomonbrys.kodein.instance
import com.github.satoshun.reactivex.webkit.data.OnPageFinished

class MainActivity : AppCompatActivity(), AppCompatActivityInjector, WebViewView {
    override val injector: KodeinInjector = KodeinInjector()
    private val presenter: Presenter<WebViewView> by instance()

    private val webView by lazy { findViewById<WebView>(R.id.web_view) }
    private val progressBar by lazy { findViewById<View>(R.id.progressbar) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeInjector()
        setContentView(R.layout.activity_main)
        presenter.attach(this)
        webView.loadUrl(getString(R.string.url))
    }

    override fun pageFinishedEvent() = webView.events().ofType(OnPageFinished::class.java)

    override fun onPageFinished() {
        webView.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
    }

    override fun onDestroy() {
        presenter.detach(this)
        destroyInjector()
        super.onDestroy()
    }
}