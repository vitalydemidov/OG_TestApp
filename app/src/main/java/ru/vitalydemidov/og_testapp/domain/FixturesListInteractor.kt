package ru.vitalydemidov.og_testapp.domain

import io.reactivex.Flowable
import ru.vitalydemidov.og_testapp.base.model.BaseItem

interface FixturesListInteractor {

    fun getFixturesList(filter: FixturesFilter): Flowable<List<BaseItem<in Nothing>>>

}