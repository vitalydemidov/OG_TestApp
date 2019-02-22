package ru.vitalydemidov.og_testapp

import android.app.Application
import ru.vitalydemidov.og_testapp.data.di.DaggerFixturesDataSourceComponent
import ru.vitalydemidov.og_testapp.data.di.FixturesDataSourceComponent
import ru.vitalydemidov.og_testapp.data.di.FixturesDataSourceModule
import ru.vitalydemidov.og_testapp.data.remote.di.ApiModule
import ru.vitalydemidov.og_testapp.di.AppComponent
import ru.vitalydemidov.og_testapp.di.AppComponentProvider
import ru.vitalydemidov.og_testapp.di.AppModule
import ru.vitalydemidov.og_testapp.di.DaggerAppComponent

class OG_TestApp : Application(), AppComponentProvider {

    private val appComponent = buildAppComponent()

    private fun buildAppComponent(): AppComponent {
        val fixtureDataSourceComponent: FixturesDataSourceComponent =
            DaggerFixturesDataSourceComponent.builder()
                .fixturesDataSourceModule(FixturesDataSourceModule())
                .apiModule(ApiModule())
                .build()

        return DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .fixturesDataSourceComponent(fixtureDataSourceComponent)
            .build()
    }

    //region AppComponentProvider interface implementation
    override fun provideAppComponent(): AppComponent = appComponent
    //endregion AppComponentProvider interface implementation

}