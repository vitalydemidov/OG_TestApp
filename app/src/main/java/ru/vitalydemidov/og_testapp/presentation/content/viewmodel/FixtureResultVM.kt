package ru.vitalydemidov.og_testapp.presentation.content.viewmodel

import android.text.Spannable

class FixtureResultVM(
    val competition: String,
    val venue: String,
    val dateAndTime: Spannable,
    val homeTeam: String,
    val awayTeam: String,
    val homeTeamScore: Spannable,
    val awayTeamScore: Spannable
)