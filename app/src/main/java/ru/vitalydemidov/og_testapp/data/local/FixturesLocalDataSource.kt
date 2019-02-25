package ru.vitalydemidov.og_testapp.data.local

import io.reactivex.Flowable
import ru.vitalydemidov.og_testapp.data.FixturesDataSource
import ru.vitalydemidov.og_testapp.data.FixturesRepository.Companion.ALL_COMPETITION
import ru.vitalydemidov.og_testapp.data.local.db.FixtureDatabase
import ru.vitalydemidov.og_testapp.data.model.Competition
import ru.vitalydemidov.og_testapp.data.model.Fixture
import ru.vitalydemidov.og_testapp.domain.FixturesFilter
import ru.vitalydemidov.og_testapp.util.FixtureType

class FixturesLocalDataSource(
    private val fixturesDatabase: FixtureDatabase
) : FixturesDataSource {

    private val competitions: MutableSet<Competition> = mutableSetOf(ALL_COMPETITION)

    override fun getFixtures(filter: FixturesFilter): Flowable<List<Fixture>> {
        return when (filter.type) {
            FixtureType.UPCOMING ->
                fixturesDatabase.fixturesDao().getAllByDateAsc(
                    filter.type.typeStr,
                    filter.competition?.id
                )
            FixtureType.FINAL ->
                fixturesDatabase.fixturesDao().getAllByDateDesc(
                    filter.type.typeStr,
                    filter.competition?.id
                )
        }
    }

    override fun insertFixtures(fixtures: List<Fixture>) {
        fixturesDatabase.fixturesDao().insertAll(fixtures)
    }

    override fun deleteFixturesByType(type: FixtureType) {
        fixturesDatabase.fixturesDao().deleteAllByType(type.typeStr)
    }

    override fun insertCompetition(competition: Competition) {
        competitions.add(competition)
    }

    override fun deleteAllCompetitions() {
        competitions.clear()
        competitions.add(ALL_COMPETITION)
    }

    override fun getAllCompetitions(): Flowable<List<Competition>> =
        Flowable.just(competitions.toList())

}