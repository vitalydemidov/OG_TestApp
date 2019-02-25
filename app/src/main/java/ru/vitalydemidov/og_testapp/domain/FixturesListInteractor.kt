package ru.vitalydemidov.og_testapp.domain

import io.reactivex.Flowable

interface FixturesListInteractor {

    fun getFixturesList(filter: FixturesFilter): Flowable<FixturesResult>

}