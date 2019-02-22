package ru.vitalydemidov.og_testapp.appcommon

interface LoadingView {

    fun showLoadingProgress()

    fun hideLoadingProgress()

    fun showLoadingError(error: Throwable)

}