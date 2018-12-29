package com.aimicor.hsbc

import android.support.test.rule.ActivityTestRule
import com.aimicor.hsbc.presenter.Presenter
import com.aimicor.hsbc.presenter.WebViewView
import com.aimicor.hsbc.view.MainFragmentActivity
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AttachDetachWebViewFragmentPresenterTest {

    @Mock
    private lateinit var webViewPresenter: Presenter<Presenter.View>

    @Rule
    @JvmField
    val activityRule = ActivityTestRule<MainFragmentActivity>(MainFragmentActivity::class.java)
    private val mainFragmentActivity by lazy { activityRule.activity as MainFragmentActivity }

    @Rule
    @JvmField
    val presenterOverridesRule = OverridesRule {
        bind<Presenter<WebViewView>>(overrides = true) with provider { webViewPresenter }
    }

    @Ignore // TODO only works in isolation from other tests in package
    @Test
    fun test_attach_and_detach_called() {
        val fragment = mainFragmentActivity.supportFragmentManager.findFragmentById(R.id.fragment) as Presenter.View
        Mockito.verify(webViewPresenter).attach(fragment)

        mainFragmentActivity.finish()
        Thread.sleep(2000)
        Mockito.verify(webViewPresenter).detach(fragment)

    }
}