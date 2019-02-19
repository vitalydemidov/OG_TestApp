package ru.vitalydemidov.og_testapp.appcommon

import android.support.annotation.NonNull

interface BaseContract {

    interface View

    interface Presenter<VIEW : BaseContract.View> {

        fun attachView(@NonNull view: VIEW)

        fun detachView()

        fun onDestroy()

    }

}