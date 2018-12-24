package com.aimicor.hsbc.presenter

import io.reactivex.subjects.PublishSubject
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class WebViewPresenterTest {

    val presenter = WebViewPresenter()
    val view = mock(WebViewView::class.java)
    val event = PublishSubject.create<Unit>()

    @Before
    fun `set up`() {
        `when`(view.pageFinishedEvent()).thenReturn(event)
    }

    @Test
    fun `test that onPageFinished is called on event`() {
        presenter.attach(view)
        event.onNext(Unit)
        verify(view).onPageFinished()
    }
}