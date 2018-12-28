package com.aimicor.hsbc.view

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aimicor.hsbc.R
import com.aimicor.hsbc.presenter.WebViewView
import com.aimicor.hsbc.view.PresenterDelegate.Companion.presenterDelegate
import com.github.satoshun.reactivex.webkit.data.OnPageFinished
import com.github.satoshun.reactivex.webkit.events
import kotlinx.android.synthetic.main.activity_main.*
/*
 * Redundant class demonstrating PresenterDelegate usage on a Fragment
 */
class MainFragment : Fragment(), WebViewView, PresenterAware {
    override lateinit var presenterDelegate : PresenterDelegate<WebViewView>

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        presenterDelegate = presenterDelegate(context) {
            bindLifeCycle(lifecycle)
            bindView(this@MainFragment)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.activity_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webView.loadUrl(getString(R.string.url))
    }

    override fun pageFinishedEvent() = webView.events().ofType(OnPageFinished::class.java).map {  }!!

    override fun onPageFinished() {
        webView.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
    }
}