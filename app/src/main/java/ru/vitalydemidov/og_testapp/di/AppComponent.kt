package ru.vitalydemidov.og_testapp.di

import android.content.Context
import dagger.Component
import ru.vitalydemidov.og_testapp.data.FixturesDataSource
import ru.vitalydemidov.og_testapp.data.di.FixturesDataSourceModule
import ru.vitalydemidov.og_testapp.data.di.Repository
import ru.vitalydemidov.og_testapp.data.local.di.DatabaseModule
import ru.vitalydemidov.og_testapp.data.remote.di.ApiModule

@Component(
    modules = [
        AppModule::class,
        DatabaseModule::class,
        ApiModule::class,
        FixturesDataSourceModule::class
    ]
)
@PerAppScope
interface AppComponent {

    fun getAppContext(): Context

    @Repository
    fun getFixturesDataSourceRepository(): FixturesDataSource

}