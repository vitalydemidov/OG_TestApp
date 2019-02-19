package ru.vitalydemidov.og_testapp.data

import io.reactivex.Observable
import ru.vitalydemidov.og_testapp.data.model.Fixture
import ru.vitalydemidov.og_testapp.util.FixtureType

interface FixturesDataSource {

    fun getFixtures(type: FixtureType): Observable<List<Fixture>>

}