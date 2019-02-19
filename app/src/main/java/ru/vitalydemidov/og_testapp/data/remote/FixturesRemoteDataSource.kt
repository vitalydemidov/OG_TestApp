package ru.vitalydemidov.og_testapp.data.remote

import io.reactivex.Observable
import ru.vitalydemidov.og_testapp.data.FixturesDataSource
import ru.vitalydemidov.og_testapp.data.model.Fixture
import ru.vitalydemidov.og_testapp.util.FixtureType

class FixturesRemoteDataSource(
    private val fixturesApi: FixturesApi
) : FixturesDataSource {

    override fun getFixtures(type: FixtureType): Observable<List<Fixture>> =
        when (type) {
            FixtureType.UPCOMING -> fixturesApi.getFixturesUpcoming()
            FixtureType.FINAL -> fixturesApi.getFixturesFinal()
        }

}