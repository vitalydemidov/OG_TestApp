package ru.vitalydemidov.og_testapp.data.model

data class Fixture(
    val id: Long,
    val type: String,
    val homeTeam: Team,
    val awayTeam: Team,
    val date: String,
    val competitionStage: CompetitionStage,
    val venue: Venue,
    val state: String,
    val score: Score?
)