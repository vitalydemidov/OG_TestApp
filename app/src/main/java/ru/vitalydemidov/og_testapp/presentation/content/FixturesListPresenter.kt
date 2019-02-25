package ru.vitalydemidov.og_testapp.presentation.content

import ru.vitalydemidov.og_testapp.base.model.BaseItem
import ru.vitalydemidov.og_testapp.base.presenter.BasePresenter
import ru.vitalydemidov.og_testapp.data.model.Competition
import ru.vitalydemidov.og_testapp.domain.FixturesFilter
import ru.vitalydemidov.og_testapp.domain.FixturesListInteractor
import ru.vitalydemidov.og_testapp.domain.FixturesResult
import ru.vitalydemidov.og_testapp.util.FixtureType

internal class FixturesListPresenter(
    private val fixturesListInteractor: FixturesListInteractor,
    private val type: FixtureType
) : BasePresenter<FixturesListContract.View>(),
    FixturesListContract.Presenter {

    private var dataList: List<BaseItem<in Nothing>> = arrayListOf()
    private var competitions: List<Competition> = arrayListOf()
    private var filter: FixturesFilter = FixturesFilter(type)

    init {
        loadFixtures()
    }

    //region BasePresenter
    override fun attachView(view: FixturesListContract.View) {
        super.attachView(view)
        view.showAvailableSortingByCompetition(competitions, filter.competition)
        view.showFixtureList(dataList)
    }
    //endregion BasePresenter

    //region Contract
    override fun loadFixtures(forceRemote: Boolean) {
        filter = filter.copy(forceRemote = forceRemote)

        loadFixturesInternal(filter)
    }

    override fun onCompetitionForSortingSelected(competition: Competition?) {
        if (filter.competition?.equals(competition) == true) {
            return
        }

        filter = filter.copy(
            competition = competition,
            forceRemote = false
        )

        loadFixturesInternal(filter)
    }
    //endregion Contract

    private fun loadFixturesInternal(filter: FixturesFilter) {
        view?.showLoadingProgress()

        disposable.set(
            fixturesListInteractor.getFixturesList(filter)
                .doOnTerminate { view?.hideLoadingProgress() }
                .subscribe(
                    { result -> processDataListResult(result) },
                    { error -> view?.showLoadingError(error) }
                )
        )
    }

    private fun processDataListResult(result: FixturesResult) {
        dataList = result.fixtures
        view?.showFixtureList(dataList)

        if (competitions != result.competitions) {
            competitions = result.competitions
            view?.showAvailableSortingByCompetition(competitions, filter.competition)
        }
    }

}