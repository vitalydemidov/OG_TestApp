package ru.vitalydemidov.og_testapp.data.model

import android.arch.persistence.room.Embedded

data class CompetitionStage(
    @Embedded(prefix = "competition_")
    val competition: Competition,
    val stage: String?,
    val leg: String?
)