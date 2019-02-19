package ru.vitalydemidov.og_testapp.data

import io.reactivex.Observable
import ru.vitalydemidov.og_testapp.data.model.Fixture
import ru.vitalydemidov.og_testapp.util.FixtureType

internal class FixturesRepository(
    private val fixturesLocalDataSource: FixturesDataSource,
    private val fixturesRemoteDataSource: FixturesDataSource
) : FixturesDataSource {

    /**
     * Добавить флаг forceLoad https://github.com/quangctkm9207/mvp-android-arch-component/blob/master/app/src/main/java/com/quangnguyen/stackoverflowclient/data/repository/QuestionRepository.java
     * Можно использовать оператор switchIfEmpty, что если в БД нет данных, то идем в сеть
     * а сначала в БД.
     * Флаг forceLoad позволит по pull to refresh загружать данные из сети принудительно
     */
    override fun getFixtures(type: FixtureType): Observable<List<Fixture>> =
        fixturesRemoteDataSource.getFixtures(type)

}