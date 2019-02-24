package ru.vitalydemidov.og_testapp.data.local

import io.reactivex.Flowable
import ru.vitalydemidov.og_testapp.data.FixturesDataSource
import ru.vitalydemidov.og_testapp.data.local.db.FixtureDatabase
import ru.vitalydemidov.og_testapp.data.model.Fixture
import ru.vitalydemidov.og_testapp.domain.FixturesFilter
import ru.vitalydemidov.og_testapp.util.FixtureType

class FixturesLocalDataSource(
    private val fixturesDatabase: FixtureDatabase
) : FixturesDataSource {

    override fun getFixtures(filter: FixturesFilter): Flowable<List<Fixture>> {
        return when (filter.type) {
            FixtureType.UPCOMING -> fixturesDatabase.fixturesDao().getAllByDateAsc(filter.type.typeStr)
            FixtureType.FINAL -> fixturesDatabase.fixturesDao().getAllByDateDesc(filter.type.typeStr)
        }
    }

    override fun insertFixtures(fixtures: List<Fixture>) {
        fixturesDatabase.fixturesDao().insertAll(fixtures)
    }

    override fun deleteFixturesByType(type: FixtureType) {
        fixturesDatabase.fixturesDao().deleteAllByType(type.typeStr)
    }

}