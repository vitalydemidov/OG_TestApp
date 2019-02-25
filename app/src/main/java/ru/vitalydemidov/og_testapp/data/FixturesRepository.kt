package ru.vitalydemidov.og_testapp.data

import io.reactivex.Flowable
import ru.vitalydemidov.og_testapp.data.model.Fixture
import ru.vitalydemidov.og_testapp.domain.FixturesFilter
import ru.vitalydemidov.og_testapp.util.FixtureType

internal class FixturesRepository(
    private val fixturesLocalDataSource: FixturesDataSource,
    private val fixturesRemoteDataSource: FixturesDataSource
) : FixturesDataSource {

    override fun getFixtures(filter: FixturesFilter): Flowable<List<Fixture>> {
        return if (filter.forceRemote) {
            // refresh data from remote
            refreshFixtures(filter)
        } else {
            // load data from local db;
            // if there is no data in db then refresh data from remote
            fixturesLocalDataSource.getFixtures(filter)
                .take(1)
                .filter { list -> !list.isEmpty() }
                .switchIfEmpty(refreshFixtures(filter))
        }
    }

    override fun insertFixtures(fixtures: List<Fixture>) {
        fixturesLocalDataSource.insertFixtures(fixtures)
    }

    override fun deleteFixturesByType(type: FixtureType) {
        fixturesLocalDataSource.deleteFixturesByType(type)
    }

    private fun refreshFixtures(filter: FixturesFilter): Flowable<List<Fixture>> {
        return fixturesRemoteDataSource.getFixtures(filter)
            .doOnNext { fixtures ->
                // clear previous fixtures by type in db
                deleteFixturesByType(filter.type)
                // insert new fixtures in db
                insertFixtures(fixtures)
            }
    }

}