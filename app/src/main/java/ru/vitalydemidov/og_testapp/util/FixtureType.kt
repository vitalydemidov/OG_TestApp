package ru.vitalydemidov.og_testapp.util

import android.support.annotation.StringRes
import ru.vitalydemidov.og_testapp.R

enum class FixtureType(@StringRes type: Int) {
    UPCOMING(R.string.fixture_type_upcoming),
    FINAL(R.string.fixture_type_final)
}