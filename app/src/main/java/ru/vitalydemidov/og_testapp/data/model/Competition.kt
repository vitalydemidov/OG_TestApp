package ru.vitalydemidov.og_testapp.data.model

const val ALL_COMPETITION_ID = Long.MAX_VALUE
const val ALL_COMPETITION_NAME = "All"

data class Competition(
    val id: Long,
    val name: String
) {

    override fun toString(): String = name

}