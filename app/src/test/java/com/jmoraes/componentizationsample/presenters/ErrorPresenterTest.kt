package com.jmoraes.componentizationsample.presenters

import com.jmoraes.componentizationsample.eventTypes.ScreenStateEvent
import com.jmoraes.componentizationsample.eventTypes.UserInteractionEvent
import com.jmoraes.componentizationsample.views.ErrorView
import io.reactivex.subjects.PublishSubject
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class ErrorPresenterTest {
    private var destroyObservable: PublishSubject<Unit> = PublishSubject.create()
    private var screenStateEvent: PublishSubject<ScreenStateEvent> = PublishSubject.create()
    private lateinit var presenter : ErrorPresenter<ScreenStateEvent, UserInteractionEvent>
    private var view = Mockito.mock(ErrorView::class.java)

    @Before
    fun setUp() {
        presenter = ErrorPresenter(view, screenStateEvent, destroyObservable)
    }

    @Test
    fun testLoading() {
        screenStateEvent.onNext(ScreenStateEvent.Loading)
        Mockito.verify(view, Mockito.times(1)).hide()
        Mockito.verify(view, Mockito.times(0)).show()
    }

    @Test
    fun testLoaded() {
        screenStateEvent.onNext(ScreenStateEvent.Loaded)
        Mockito.verify(view, Mockito.times(1)).hide()
        Mockito.verify(view, Mockito.times(0)).show()
    }

    @Test
    fun testError() {
        screenStateEvent.onNext(ScreenStateEvent.Error)
        Mockito.verify(view, Mockito.times(0)).hide()
        Mockito.verify(view, Mockito.times(1)).show()
    }
}