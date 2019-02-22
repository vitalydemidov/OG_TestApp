package ru.vitalydemidov.og_testapp.base.viewmodel

import android.arch.lifecycle.ViewModel
import ru.vitalydemidov.og_testapp.base.contract.BaseContract

class BaseViewModel<
    VIEW : BaseContract.View,
    PRESENTER : BaseContract.Presenter<VIEW>>
: ViewModel() {

    internal var presenter: PRESENTER? = null
        set(value) {
            if (field == null) {
                field = value
            }
        }

    override fun onCleared() {
        super.onCleared()
        presenter?.onDestroy()
        presenter = null
    }

}