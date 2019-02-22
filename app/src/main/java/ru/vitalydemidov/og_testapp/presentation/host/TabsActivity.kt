package ru.vitalydemidov.og_testapp.presentation.host

import android.os.Bundle
import android.support.annotation.UiThread
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import ru.vitalydemidov.og_testapp.R
import ru.vitalydemidov.og_testapp.di.AppComponentProvider
import ru.vitalydemidov.og_testapp.presentation.host.di.DaggerTabsActivityComponent
import ru.vitalydemidov.og_testapp.presentation.host.di.TabsActivityComponent
import ru.vitalydemidov.og_testapp.presentation.host.di.TabsActivityComponentProvider
import ru.vitalydemidov.og_testapp.presentation.host.di.TabsActivityModule
import javax.inject.Inject

@UiThread
class TabsActivity : AppCompatActivity(), TabsActivityComponentProvider {

    private lateinit var activityComponent: TabsActivityComponent
    private lateinit var tabsPagerAdapter: TabsPagerAdapter
    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        activityComponent = buildActivityComponent()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabs)
        setSupportActionBar(findViewById(R.id.activity_tabs_toolbar))
        viewPager = findViewById(R.id.view_pager)

        activityComponent.inject(this)
    }

    @Inject
    internal fun setAdapter(adapter: TabsPagerAdapter) {
        tabsPagerAdapter = adapter
        viewPager.adapter = tabsPagerAdapter

        val tabLayout: TabLayout = findViewById(R.id.activity_tabs_tab_layout)
        tabLayout.setupWithViewPager(viewPager)
    }

    private fun buildActivityComponent(): TabsActivityComponent {
        return DaggerTabsActivityComponent.builder()
            .tabsActivityModule(TabsActivityModule(this))
            .appComponent((application as AppComponentProvider).provideAppComponent())
            .build()
    }

    //region TabsActivityComponentProvider interface implementation
    override fun provideTabsActivityComponent(): TabsActivityComponent = activityComponent
    //endregion TabsActivityComponentProvider interface implementation

}
