package ru.vitalydemidov.og_testapp.base

import android.support.annotation.NonNull

interface BaseContract {

    interface View : LoadingView

    interface Presenter<VIEW : BaseContract.View> {

        fun attachView(@NonNull view: VIEW)

        fun detachView()

        fun onDestroy()

    }

}