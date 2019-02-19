package ru.vitalydemidov.og_testapp.presentation.host

import android.os.Bundle
import android.support.annotation.UiThread
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import ru.vitalydemidov.og_testapp.R

@UiThread
class TabsActivity : AppCompatActivity() {

    private lateinit var tabsPagerAdapter: TabsPagerAdapter
    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabs)

        tabsPagerAdapter = TabsPagerAdapter(resources, supportFragmentManager)

        viewPager = findViewById(R.id.view_pager)
        viewPager.adapter = tabsPagerAdapter
    }

}
