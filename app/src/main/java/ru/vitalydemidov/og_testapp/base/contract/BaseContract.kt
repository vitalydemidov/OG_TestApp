package ru.vitalydemidov.og_testapp.base.contract

import android.support.annotation.NonNull
import ru.vitalydemidov.og_testapp.base.view.LoadingView

interface BaseContract {

    interface View : LoadingView

    interface Presenter<VIEW : View> {

        fun attachView(@NonNull view: VIEW)

        fun detachView()

        fun onDestroy()

    }

}