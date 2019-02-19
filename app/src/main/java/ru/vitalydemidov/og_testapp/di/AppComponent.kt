package ru.vitalydemidov.og_testapp.di

import android.content.Context
import dagger.Component
import ru.vitalydemidov.og_testapp.data.FixturesDataSource
import ru.vitalydemidov.og_testapp.data.di.FixturesDataSourceComponent
import ru.vitalydemidov.og_testapp.data.di.Repository

@Component(
    modules = [AppModule::class],
    dependencies = [FixturesDataSourceComponent::class]
)
@PerAppScope
interface AppComponent {

    fun getAppContext(): Context

    @Repository
    fun getFixturesDataSourceRepository(): FixturesDataSource

}