package com.aimicor.hsbc

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.aimicor.hsbc.presenter.Presenter
import com.aimicor.hsbc.presenter.WebViewView
import com.aimicor.hsbc.view.MainFragmentActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@RunWith(AndroidJUnit4::class)
class AttachDetachWebViewFragmentPresenterTest {

    private val webViewPresenter = mock(TestPresenter::class.java)

    @Rule
    @JvmField
    val activityRule = ActivityTestRule<MainFragmentActivity>(MainFragmentActivity::class.java)
    private val mainFragmentActivity by lazy { activityRule.activity as MainFragmentActivity }

    @Rule
    @JvmField
    val presenterOverridesRule = OverridesRule {
        bind<Presenter<WebViewView>>(overrides = true) with provider { webViewPresenter }
    }

    @Test
    fun test_attach_and_detach_called() {
        val fragment = mainFragmentActivity.supportFragmentManager.findFragmentById(R.id.fragment) as Presenter.View
        verify(webViewPresenter).attach(fragment)

        mainFragmentActivity.finish()
        Thread.sleep(2000)
        verify(webViewPresenter).detach(fragment)

    }

}