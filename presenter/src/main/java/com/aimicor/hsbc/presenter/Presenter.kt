package com.aimicor.hsbc.presenter

interface Presenter<in VIEW : Presenter.View> {
    fun attach(view: VIEW)
    fun detach(view: VIEW)
    interface View
}