package ru.vitalydemidov.og_testapp.domain

import ru.vitalydemidov.og_testapp.base.model.BaseItem
import ru.vitalydemidov.og_testapp.data.model.Competition

class FixturesResult(
    val fixtures: List<BaseItem<in Nothing>>,
    val competitions: List<Competition>
)