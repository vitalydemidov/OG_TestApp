package ru.vitalydemidov.og_testapp.base

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View

abstract class BaseView<
    VIEW : BaseContract.View,
    PRESENTER : BaseContract.Presenter<VIEW>>
: Fragment(), BaseContract.View {

    protected lateinit var presenter: PRESENTER

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val baseViewModel: BaseViewModel<VIEW, PRESENTER> =
            ViewModelProviders.of(this).get(BaseViewModel::class.java) as BaseViewModel<VIEW, PRESENTER>
        if (baseViewModel.presenter == null) {
            baseViewModel.presenter = initPresenter()
        }
        presenter = baseViewModel.presenter as PRESENTER

        inject()
    }

    @Suppress("UNCHECKED_CAST")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this as VIEW)
    }

    override fun onDestroyView() {
        presenter.detachView()
        super.onDestroyView()
    }

    protected abstract fun inject()

    protected abstract fun initPresenter(): PRESENTER

}