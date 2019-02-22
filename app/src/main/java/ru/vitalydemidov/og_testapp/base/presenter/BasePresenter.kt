package ru.vitalydemidov.og_testapp.base.presenter

import io.reactivex.disposables.SerialDisposable
import ru.vitalydemidov.og_testapp.base.contract.BaseContract

abstract class BasePresenter<VIEW : BaseContract.View> :
    BaseContract.Presenter<VIEW> {

    protected var view: VIEW? = null
    protected val disposable: SerialDisposable = SerialDisposable()

    override fun attachView(view: VIEW) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    override fun onDestroy() {
        if (!disposable.isDisposed) {
            disposable.dispose()
        }
    }

}