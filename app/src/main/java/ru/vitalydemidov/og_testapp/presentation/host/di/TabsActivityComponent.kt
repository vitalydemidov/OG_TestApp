package ru.vitalydemidov.og_testapp.presentation.host.di

import dagger.Component

@Component(modules = [TabsActivityModule::class])
interface TabsActivityComponent {

    @Component.Builder
    interface Builder {

        fun tabsActivityModule(tabsActivityModule: TabsActivityModule): Builder

        fun build(): TabsActivityComponent
    }

}