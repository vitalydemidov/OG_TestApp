package ru.vitalydemidov.og_testapp.presentation.content

import ru.vitalydemidov.og_testapp.appcommon.BasePresenter
import ru.vitalydemidov.og_testapp.appcommon.model.BaseItem
import ru.vitalydemidov.og_testapp.domain.FixturesListInteractor
import ru.vitalydemidov.og_testapp.util.FixtureType

internal class FixturesListPresenter(
    private val fixturesListInteractor: FixturesListInteractor,
    private val type: FixtureType
) : BasePresenter<FixturesListContract.View>(),
    FixturesListContract.Presenter {

    private var dataList: List<BaseItem<in Nothing>> = arrayListOf()

    init {
        loadFixtures(type)
    }

    override fun attachView(view: FixturesListContract.View) {
        super.attachView(view)
        view.showFixtureList(dataList)
    }

    override fun loadFixtures(type: FixtureType) {
        disposable.set(
            fixturesListInteractor.getFixturesList(type)
//                .map {  }     // маппинг данных на ui потоке?
                .subscribe(
                    { fixtures -> processDataListResult(fixtures) },
                    { error -> view?.showError(error) }
                )
        )
    }

    private fun processDataListResult(data: List<BaseItem<in Nothing>>) {
        dataList = data
        view?.showFixtureList(dataList)
    }

}