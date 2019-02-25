package ru.vitalydemidov.og_testapp.data

import io.reactivex.Flowable
import ru.vitalydemidov.og_testapp.data.model.Competition
import ru.vitalydemidov.og_testapp.data.model.Fixture
import ru.vitalydemidov.og_testapp.domain.FixturesFilter
import ru.vitalydemidov.og_testapp.util.FixtureType

interface FixturesDataSource {

    fun getFixtures(filter: FixturesFilter): Flowable<List<Fixture>>

    fun insertFixtures(fixtures: List<Fixture>)

    fun deleteFixturesByType(type: FixtureType)

    fun getAllCompetitions(): Flowable<List<Competition>>

    fun deleteAllCompetitions()

    fun insertCompetition(competition: Competition)

}