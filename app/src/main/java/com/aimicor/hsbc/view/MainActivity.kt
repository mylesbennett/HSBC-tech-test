package com.aimicor.hsbc.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.webkit.WebView
import com.aimicor.hsbc.R
import com.aimicor.hsbc.presenter.Presenter
import com.aimicor.hsbc.presenter.WebViewView
import com.github.satoshun.reactivex.webkit.data.OnPageFinished
import com.github.satoshun.reactivex.webkit.events
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity(), KodeinAware, WebViewView {
    override val kodein: Kodein by closestKodein()
    private val presenter: Presenter<WebViewView> by instance()

    private val webView by lazy { findViewById<WebView>(R.id.web_view) }
    private val progressBar by lazy { findViewById<View>(R.id.progressbar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.attach(this)
        webView.loadUrl(getString(R.string.url))
    }

    override fun pageFinishedEvent() = webView.events().ofType(OnPageFinished::class.java).map {  }!!

    override fun onPageFinished() {
        webView.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
    }

    override fun onDestroy() {
        presenter.detach(this)
        super.onDestroy()
    }
}
