package ru.vitalydemidov.og_testapp.data.di

import dagger.Component
import ru.vitalydemidov.og_testapp.data.FixturesDataSource
import ru.vitalydemidov.og_testapp.data.remote.di.ApiModule

@Component(
    modules = [
        ApiModule::class,
        FixturesDataSourceModule::class
    ]
)
@FixturesDataSourceScope
interface FixturesDataSourceComponent {

    @Repository
    fun getFixturesDataSourceRepository(): FixturesDataSource

}