package ru.vitalydemidov.og_testapp.data.local

import io.reactivex.Observable
import ru.vitalydemidov.og_testapp.data.FixturesDataSource
import ru.vitalydemidov.og_testapp.data.model.Fixture
import ru.vitalydemidov.og_testapp.util.FixtureType

class FixturesLocalDataSource : FixturesDataSource {

    override fun getFixtures(type: FixtureType): Observable<List<Fixture>> {
        return Observable.empty<List<Fixture>>()
    }

}