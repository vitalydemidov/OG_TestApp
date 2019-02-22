package ru.vitalydemidov.og_testapp.base.view

interface LoadingView {

    fun showLoadingProgress()

    fun hideLoadingProgress()

    fun showLoadingError(error: Throwable)

}