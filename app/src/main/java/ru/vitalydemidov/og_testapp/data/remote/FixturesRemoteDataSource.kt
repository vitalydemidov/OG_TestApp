package ru.vitalydemidov.og_testapp.data.remote

import io.reactivex.Flowable
import ru.vitalydemidov.og_testapp.data.FixturesDataSource
import ru.vitalydemidov.og_testapp.data.model.Competition
import ru.vitalydemidov.og_testapp.data.model.Fixture
import ru.vitalydemidov.og_testapp.data.remote.api.FixturesApi
import ru.vitalydemidov.og_testapp.domain.FixturesFilter
import ru.vitalydemidov.og_testapp.util.FixtureType

class FixturesRemoteDataSource(
    private val fixturesApi: FixturesApi
) : FixturesDataSource {

    override fun getFixtures(filter: FixturesFilter): Flowable<List<Fixture>> =
        when (filter.type) {
            FixtureType.UPCOMING -> fixturesApi.getFixturesUpcoming()
            FixtureType.FINAL -> fixturesApi.getFixturesFinal()
        }

    override fun insertFixtures(fixtures: List<Fixture>) {
        throw UnsupportedOperationException("Unsupported operation")
    }

    override fun deleteFixturesByType(type: FixtureType) {
        throw UnsupportedOperationException("Unsupported operation")
    }

    override fun getAllCompetitions(): Flowable<List<Competition>> {
        throw UnsupportedOperationException("Unsupported operation")
    }

    override fun deleteAllCompetitions() {
        throw UnsupportedOperationException("Unsupported operation")
    }

    override fun insertCompetition(competition: Competition) {
        throw UnsupportedOperationException("Unsupported operation")
    }

}