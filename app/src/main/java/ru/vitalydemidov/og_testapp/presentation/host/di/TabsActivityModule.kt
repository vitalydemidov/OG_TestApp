package ru.vitalydemidov.og_testapp.presentation.host.di

import android.content.Context
import android.support.v7.app.AppCompatActivity
import dagger.Module
import dagger.Provides
import ru.vitalydemidov.og_testapp.base.mapper.BaseItemMapper
import ru.vitalydemidov.og_testapp.presentation.host.TabsPagerAdapter

@Module
class TabsActivityModule(private val activity: AppCompatActivity) {

    @Provides
    @PerActivityScope
    internal fun provideTabsPagerAdapter(context: Context): TabsPagerAdapter {
        return TabsPagerAdapter(context.resources, activity.supportFragmentManager)
    }

    @Provides
    @PerActivityScope
    internal fun provideBaseItemMapper(): BaseItemMapper =
        BaseItemMapper()

}