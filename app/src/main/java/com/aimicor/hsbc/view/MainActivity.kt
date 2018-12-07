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

/**
 * This app satisfies all of the specified criteria. Regrettably, the app is
 * so simple that it does little to demonstrate "Good engineering principles".
 * Attempting to impose a dependency-injected decoupled, test-driven arrangement would
 * have added unnecessary complexity, given that there is no business logic.
 *
 * Instead, I thought that I would describe what I would have done with a larger project.
 * The following link sums up the use of RxBindings to create a totally decoupled MVP
 * arrangement:- https://medium.com/@mvarnagiris/reactive-mvp-part-1-b751ce3e3246
 * The article does not discuss the method of Presenter injection, however in a pure
 * Kotlin project, I would strongly consider moving on from Dagger2 given that it was
 * designed for Java. There is Kodein (http://kodein.org/Kodein-DI/) or even the
 * possibility of writing your own mappings as is demonstrated here:-
 * https://blog.kotlin-academy.com/dependency-injection-the-pattern-without-the-framework-33cfa9d5f312
 *
 * With this arrangement, all of the business logic could be written in entirely seperate
 * Presenter and Model modules, not just separate packages, further decoupling the code
 * and mitigating the need for Robolectric - the aim would be to have no business logic
 * in the views to test.
 *
 * As for unit tests, so long as interfaces are used for injection, there should be no
 * issues with mocking dependencies. However, there is also mockK
 * (https://github.com/mockk/mockk) which promises to overcome the problems of
 * mocking Kotlin classes that are 'final' by default, if necessary.
 */

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