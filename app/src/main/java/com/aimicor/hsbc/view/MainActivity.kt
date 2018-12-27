package com.aimicor.hsbc.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.aimicor.hsbc.R
import com.aimicor.hsbc.presenter.WebViewView
import com.aimicor.hsbc.view.PresenterDelegate.Companion.presenterDelegate
import com.github.satoshun.reactivex.webkit.data.OnPageFinished
import com.github.satoshun.reactivex.webkit.events
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), WebViewView, PresenterAware {
    override val presenterDelegate = presenterDelegate<WebViewView>(this) {
        bindLifeCycle(lifecycle)
        bindView(this@MainActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        webView.loadUrl(getString(R.string.url))
    }

    override fun pageFinishedEvent() = webView.events().ofType(OnPageFinished::class.java).map {  }!!

    override fun onPageFinished() {
        webView.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
    }
}
