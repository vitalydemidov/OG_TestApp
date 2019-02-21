package ru.vitalydemidov.og_testapp.presentation.content.viewmodel

import android.text.Spannable

class FixtureUpcomingVM(
    val competition: String,
    val venue: String,
    val dateAndTime: Spannable,
    val homeTeam: String,
    val awayTeam: String,
    val postponedBannerVisible: Boolean,
    val dayOfMonth: String,
    val dayOfWeek: String
)