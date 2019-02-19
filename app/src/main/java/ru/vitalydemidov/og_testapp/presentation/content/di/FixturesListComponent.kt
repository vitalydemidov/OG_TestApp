package ru.vitalydemidov.og_testapp.presentation.content.di

import dagger.BindsInstance
import dagger.Component
import ru.vitalydemidov.og_testapp.di.AppComponent
import ru.vitalydemidov.og_testapp.presentation.content.FixturesListContract
import ru.vitalydemidov.og_testapp.presentation.content.FixturesListFragment
import ru.vitalydemidov.og_testapp.util.FixtureType

@Component(
    dependencies = [AppComponent::class],
    modules = [FixturesListModule::class]
)
@PerFragmentScope
interface FixturesListComponent {

    fun inject(fixturesListFragment: FixturesListFragment)

    fun getFixturesListPresenter(): FixturesListContract.Presenter

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun fixtureType(type: FixtureType): Builder

        fun appComponent(appComponent: AppComponent): Builder

        fun build(): FixturesListComponent
    }

}