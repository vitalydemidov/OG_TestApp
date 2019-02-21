package ru.vitalydemidov.og_testapp.domain

import io.reactivex.Observable
import ru.vitalydemidov.og_testapp.appcommon.model.BaseItem
import ru.vitalydemidov.og_testapp.util.FixtureType

interface FixturesListInteractor {

    fun getFixturesList(type: FixtureType): Observable<List<BaseItem<in Nothing>>>

}