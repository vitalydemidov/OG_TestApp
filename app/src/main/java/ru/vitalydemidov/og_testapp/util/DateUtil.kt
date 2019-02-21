package ru.vitalydemidov.og_testapp.util

enum class DataParseTemplate(val format: String) {
    // input date and time format from server
    INPUT_DATE_AND_TIME("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"),

    // output formats for rendering
    DAY_OF_MONTH("dd"),
    DAY_OF_WEEK("EEE"),
    DATE_AND_TIME("MMM dd, yyyy 'at' HH:mm")
}