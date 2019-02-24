package ru.vitalydemidov.og_testapp.util

const val FIXTURE_TYPE_UPCOMING = "FixtureUpcoming"
const val FIXTURE_TYPE_FINAL = "FixtureFinal"

enum class FixtureType(val typeStr: String) {
    UPCOMING(FIXTURE_TYPE_UPCOMING),
    FINAL(FIXTURE_TYPE_FINAL)
}