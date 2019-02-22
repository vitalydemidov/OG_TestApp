package ru.vitalydemidov.og_testapp.presentation.content

import ru.vitalydemidov.og_testapp.base.BaseContract
import ru.vitalydemidov.og_testapp.base.model.BaseItem
import ru.vitalydemidov.og_testapp.util.FixtureType

interface FixturesListContract {

    interface View : BaseContract.View {

        fun showFixtureList(fixtures: List<BaseItem<in Nothing>>)

    }

    interface Presenter : BaseContract.Presenter<View> {

        /**
         * вероятно потом расширить до класса-обертки Filter,
         * в котором
         * 1) тип матчей
         * 2) флаг forceLoad
         * 3) тип соревнования (дополнительное задание)
         */
        fun loadFixtures(type: FixtureType)

    }

}