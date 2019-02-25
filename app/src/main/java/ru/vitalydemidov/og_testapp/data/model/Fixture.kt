package ru.vitalydemidov.og_testapp.data.model

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import ru.vitalydemidov.og_testapp.data.local.db.FixtureDatabase.Companion.FIXTURES_TABLE_NAME

@Entity(tableName = FIXTURES_TABLE_NAME)
data class Fixture(
    @PrimaryKey
    val id: Long,
    val type: String,
    @Embedded(prefix = "home_team_")
    val homeTeam: Team,
    @Embedded(prefix = "away_team_")
    val awayTeam: Team,
    val date: String,
    @Embedded(prefix = "competition_stage_")
    val competitionStage: CompetitionStage,
    @Embedded(prefix = "venue_")
    val venue: Venue,
    val state: String?,  // почему-то у одного из матчей с типом FixtureUpcoming нет поля state
    @Embedded(prefix = "score_")
    val score: Score?
)