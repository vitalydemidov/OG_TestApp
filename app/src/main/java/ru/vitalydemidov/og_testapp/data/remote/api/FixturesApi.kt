package ru.vitalydemidov.og_testapp.data.remote.api

import io.reactivex.Flowable
import retrofit2.http.GET
import ru.vitalydemidov.og_testapp.data.model.Fixture

interface FixturesApi {

    @GET("fixtures.json")
    fun getFixturesUpcoming(): Flowable<List<Fixture>>

    @GET("results.json")
    fun getFixturesFinal(): Flowable<List<Fixture>>

}