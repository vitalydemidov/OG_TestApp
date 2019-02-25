package ru.vitalydemidov.og_testapp.data

import io.reactivex.Flowable
import ru.vitalydemidov.og_testapp.data.model.ALL_COMPETITION_ID
import ru.vitalydemidov.og_testapp.data.model.ALL_COMPETITION_NAME
import ru.vitalydemidov.og_testapp.data.model.Competition
import ru.vitalydemidov.og_testapp.data.model.Fixture
import ru.vitalydemidov.og_testapp.domain.FixturesFilter
import ru.vitalydemidov.og_testapp.util.FixtureType

internal class FixturesRepository(
    private val fixturesLocalDataSource: FixturesDataSource,
    private val fixturesRemoteDataSource: FixturesDataSource
) : FixturesDataSource {

    override fun getFixtures(filter: FixturesFilter): Flowable<List<Fixture>> {
        var mappedFilter = filter
        if (filter.competition?.equals(ALL_COMPETITION) == true) {
            mappedFilter = mappedFilter.copy(competition = null)
        }
        return if (mappedFilter.forceRemote) {
            // refresh data from remote
            refreshFixtures(mappedFilter)
        } else {
            // load data from local db;
            // if there is no data in db then refresh data from remote
            fixturesLocalDataSource.getFixtures(mappedFilter)
                .take(1)
                .filter { list -> !list.isEmpty() }
                .switchIfEmpty(refreshFixtures(mappedFilter))
        }
    }

    override fun insertFixtures(fixtures: List<Fixture>) {
        fixturesLocalDataSource.insertFixtures(fixtures)
    }

    override fun deleteFixturesByType(type: FixtureType) {
        fixturesLocalDataSource.deleteFixturesByType(type)
    }

    override fun getAllCompetitions(): Flowable<List<Competition>> =
        fixturesLocalDataSource.getAllCompetitions()

    override fun deleteAllCompetitions() {
        fixturesLocalDataSource.deleteAllCompetitions()
    }

    override fun insertCompetition(competition: Competition) {
        fixturesLocalDataSource.insertCompetition(competition)
    }

    private fun refreshFixtures(filter: FixturesFilter): Flowable<List<Fixture>> {
        return fixturesRemoteDataSource.getFixtures(filter)
            .doOnNext { fixtures ->
                // clear previous fixtures by type in db
                deleteFixturesByType(filter.type)
                if (filter.competition == null) {
                    deleteAllCompetitions()
                }
                // insert new fixtures in db
                insertFixtures(fixtures)
            }
            .flatMap { fixtures -> Flowable.fromIterable(fixtures) }
            .doOnNext { fixture -> insertCompetition(fixture.competitionStage.competition) }
            .filter {
                fixture -> when (filter.competition) {
                    null -> true
                    else -> fixture.competitionStage.competition.id == filter.competition.id
                }
            }
            .toList()
            .toFlowable()
    }

    companion object {
        internal val ALL_COMPETITION = Competition(ALL_COMPETITION_ID, ALL_COMPETITION_NAME)
    }

}