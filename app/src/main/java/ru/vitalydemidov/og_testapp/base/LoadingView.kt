package ru.vitalydemidov.og_testapp.base

interface LoadingView {

    fun showLoadingProgress()

    fun hideLoadingProgress()

    fun showLoadingError(error: Throwable)

}