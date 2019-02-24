package ru.vitalydemidov.og_testapp.domain

import ru.vitalydemidov.og_testapp.data.model.Competition
import ru.vitalydemidov.og_testapp.util.FixtureType

data class FixturesFilter(
    val type: FixtureType,
    val competition: Competition? = null,
    val forceRemote: Boolean = false
)