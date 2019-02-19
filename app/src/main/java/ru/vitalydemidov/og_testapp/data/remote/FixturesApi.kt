package ru.vitalydemidov.og_testapp.data.remote

import io.reactivex.Observable
import retrofit2.http.GET
import ru.vitalydemidov.og_testapp.data.model.Fixture

interface FixturesApi {

    @GET("fixtures.json")
    fun getFixturesUpcoming(): Observable<List<Fixture>>

    @GET("results.json")
    fun getFixturesFinal(): Observable<List<Fixture>>

}