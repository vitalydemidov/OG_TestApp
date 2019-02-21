package ru.vitalydemidov.og_testapp.presentation.host

import android.os.Bundle
import android.support.annotation.UiThread
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import ru.vitalydemidov.og_testapp.OG_TestApp
import ru.vitalydemidov.og_testapp.R
import ru.vitalydemidov.og_testapp.presentation.host.di.DaggerTabsActivityComponent
import ru.vitalydemidov.og_testapp.presentation.host.di.TabsActivityComponent
import ru.vitalydemidov.og_testapp.presentation.host.di.TabsActivityModule
import javax.inject.Inject

@UiThread
class TabsActivity : AppCompatActivity() {

    internal lateinit var activityComponent: TabsActivityComponent
    private lateinit var tabsPagerAdapter: TabsPagerAdapter
    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        activityComponent = buildActivityComponent()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabs)

        viewPager = findViewById(R.id.view_pager)

        activityComponent.inject(this)
    }

    @Inject
    internal fun setAdapter(adapter: TabsPagerAdapter) {
        tabsPagerAdapter = adapter
        viewPager.adapter = tabsPagerAdapter
    }

    private fun buildActivityComponent(): TabsActivityComponent {
        return DaggerTabsActivityComponent.builder()
            .tabsActivityModule(TabsActivityModule(this))
            .appComponent((application as OG_TestApp).appComponent)
            .build()
    }

}
