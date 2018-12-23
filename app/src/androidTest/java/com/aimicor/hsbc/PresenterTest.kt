package com.aimicor.hsbc

import android.support.test.rule.ActivityTestRule
import com.aimicor.hsbc.presenter.Presenter
import com.aimicor.hsbc.presenter.WebViewView
import com.aimicor.hsbc.view.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(MockitoJUnitRunner::class)
class PresenterTest {

    @Mock
    private lateinit var webViewPresenter: Presenter<Presenter.View>

    @Rule
    @JvmField
    val activityRule = ActivityTestRule<MainActivity>(MainActivity::class.java)
    private val mainActivity by lazy { activityRule.activity as MainActivity }

    @Rule
    @JvmField
    val presenterOverridesRule = PresenterOverridesRule {
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
