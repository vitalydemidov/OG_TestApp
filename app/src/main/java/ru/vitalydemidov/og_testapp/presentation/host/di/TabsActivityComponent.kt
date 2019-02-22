package ru.vitalydemidov.og_testapp.presentation.host.di

import android.content.Context
import dagger.Component
import ru.vitalydemidov.og_testapp.base.BaseItemMapper
import ru.vitalydemidov.og_testapp.data.FixturesDataSource
import ru.vitalydemidov.og_testapp.data.di.Repository
import ru.vitalydemidov.og_testapp.di.AppComponent
import ru.vitalydemidov.og_testapp.presentation.host.TabsActivity

@Component(
    dependencies = [AppComponent::class],
    modules = [TabsActivityModule::class]
)
@PerActivityScope
interface TabsActivityComponent {

    fun inject(tabsActivity: TabsActivity)

    fun getBaseItemMapper(): BaseItemMapper

    fun getAppContext(): Context

    @Repository
    fun getFixturesDataSourceRepository(): FixturesDataSource

}