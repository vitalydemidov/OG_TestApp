package ru.vitalydemidov.og_testapp

import android.app.Application
import ru.vitalydemidov.og_testapp.data.di.FixturesDataSourceModule
import ru.vitalydemidov.og_testapp.data.local.di.DatabaseModule
import ru.vitalydemidov.og_testapp.data.remote.di.ApiModule
import ru.vitalydemidov.og_testapp.di.AppComponent
import ru.vitalydemidov.og_testapp.di.AppComponentProvider
import ru.vitalydemidov.og_testapp.di.AppModule
import ru.vitalydemidov.og_testapp.di.DaggerAppComponent

class OG_TestApp : Application(), AppComponentProvider {

    private val appComponent = buildAppComponent()

    private fun buildAppComponent(): AppComponent {
        return DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .databaseModule(DatabaseModule())
            .apiModule(ApiModule())
            .fixturesDataSourceModule(FixturesDataSourceModule())
            .build()
    }

    //region AppComponentProvider interface implementation
    override fun provideAppComponent(): AppComponent = appComponent
    //endregion AppComponentProvider interface implementation

}