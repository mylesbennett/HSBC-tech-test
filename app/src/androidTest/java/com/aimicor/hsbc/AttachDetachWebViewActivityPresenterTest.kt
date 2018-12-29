package com.aimicor.hsbc

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.aimicor.hsbc.presenter.Presenter
import com.aimicor.hsbc.presenter.WebViewView
import com.aimicor.hsbc.view.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider
import org.mockito.Mockito
import org.mockito.Mockito.verify

@RunWith(AndroidJUnit4::class)
class AttachDetachWebViewActivityPresenterTest {

    private val webViewPresenter = Mockito.mock(TestPresenter::class.java)

    @Rule
    @JvmField
    val activityRule = ActivityTestRule<MainActivity>(MainActivity::class.java)
    private val mainActivity by lazy { activityRule.activity as MainActivity }

    @Rule
    @JvmField
    val presenterOverridesRule = OverridesRule {
        bind<Presenter<WebViewView>>(overrides = true) with provider { webViewPresenter }
    }

    @Test
    fun test_attach_and_detach_called() {
        verify(webViewPresenter).attach(mainActivity)

        mainActivity.finish()
        Thread.sleep(2000)
        verify(webViewPresenter).detach(mainActivity)

    }
}