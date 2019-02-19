package ru.vitalydemidov.og_testapp.domain

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.vitalydemidov.og_testapp.data.FixturesDataSource
import ru.vitalydemidov.og_testapp.data.model.Fixture
import ru.vitalydemidov.og_testapp.util.FixtureType

internal class FixturesListInteractorImpl(
    private val fixturesRepository: FixturesDataSource
) : FixturesListInteractor {

    override fun getFixturesList(type: FixtureType): Observable<List<Fixture>> {
        return fixturesRepository.getFixtures(type)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}