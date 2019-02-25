package ru.vitalydemidov.og_testapp.presentation.content

import ru.vitalydemidov.og_testapp.base.model.BaseItem
import ru.vitalydemidov.og_testapp.base.presenter.BasePresenter
import ru.vitalydemidov.og_testapp.domain.FixturesFilter
import ru.vitalydemidov.og_testapp.domain.FixturesListInteractor
import ru.vitalydemidov.og_testapp.util.FixtureType

internal class FixturesListPresenter(
    private val fixturesListInteractor: FixturesListInteractor,
    private val type: FixtureType
) : BasePresenter<FixturesListContract.View>(),
    FixturesListContract.Presenter {

    private var dataList: List<BaseItem<in Nothing>> = arrayListOf()
    private var filter: FixturesFilter = FixturesFilter(type)

    init {
        loadFixtures()
    }

    override fun attachView(view: FixturesListContract.View) {
        super.attachView(view)
        view.showFixtureList(dataList)
    }

    override fun loadFixtures(forceRemote: Boolean) {
        filter = filter.copy(forceRemote = forceRemote)

        view?.showLoadingProgress()

        disposable.set(
            fixturesListInteractor.getFixturesList(filter)
                .doOnTerminate { view?.hideLoadingProgress() }
                .subscribe(
                    { fixtures -> processDataListResult(fixtures) },
                    { error -> view?.showLoadingError(error) }
                )
        )
    }

    private fun processDataListResult(data: List<BaseItem<in Nothing>>) {
        dataList = data
        view?.showFixtureList(dataList)
    }

}