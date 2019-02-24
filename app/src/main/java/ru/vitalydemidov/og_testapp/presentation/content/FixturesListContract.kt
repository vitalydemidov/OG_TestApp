package ru.vitalydemidov.og_testapp.presentation.content

import ru.vitalydemidov.og_testapp.base.contract.BaseContract
import ru.vitalydemidov.og_testapp.base.model.BaseItem
import ru.vitalydemidov.og_testapp.data.model.Competition

interface FixturesListContract {

    interface View : BaseContract.View {

        fun showFixtureList(fixtures: List<BaseItem<in Nothing>>)

        fun showAvailableSortingByCompetition(competitions: List<Competition>)

    }

    interface Presenter : BaseContract.Presenter<View> {

        fun loadFixtures(forceRemote: Boolean = false)

    }

}