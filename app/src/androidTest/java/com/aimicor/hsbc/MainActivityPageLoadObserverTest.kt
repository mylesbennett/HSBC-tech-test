package com.aimicor.hsbc

import android.support.test.annotation.UiThreadTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.aimicor.hsbc.view.MainActivity
import io.reactivex.observers.TestObserver
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityPageLoadObserverTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    private val mainActivity by lazy { activityRule.activity as MainActivity }
    private val pageLoadedObserver = TestObserver<Unit>()

    @Before
    @UiThreadTest
    fun set_up() {
        mainActivity.pageFinishedEvent().subscribe(pageLoadedObserver)
    }

    @After
    fun tear_down() {
        pageLoadedObserver.dispose()
    }
    @Test
    fun test_page_loaded_observable() {
        Thread.sleep(2000)
        pageLoadedObserver.assertValueCount(1)
    }
}