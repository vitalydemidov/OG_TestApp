package ru.vitalydemidov.og_testapp.presentation.content

import android.os.Bundle
import android.support.annotation.UiThread
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import ru.vitalydemidov.og_testapp.R
import ru.vitalydemidov.og_testapp.appcommon.BaseView
import ru.vitalydemidov.og_testapp.appcommon.adapter.BaseDelegateAdapterJava
import ru.vitalydemidov.og_testapp.appcommon.model.BaseItem
import ru.vitalydemidov.og_testapp.presentation.content.di.DaggerFixturesListComponent
import ru.vitalydemidov.og_testapp.presentation.content.di.FixturesListComponent
import ru.vitalydemidov.og_testapp.presentation.content.viewmodel.FixtureUpcomingVM
import ru.vitalydemidov.og_testapp.presentation.host.TabsActivity
import ru.vitalydemidov.og_testapp.util.FixtureType
import javax.inject.Inject

@UiThread
class FixturesListFragment :
    BaseView<FixturesListContract.View, FixturesListContract.Presenter>(),
    FixturesListContract.View {

    private var fixturesListComponent: FixturesListComponent? = null
    private lateinit var adapter: BaseDelegateAdapterJava<in Nothing>

    @Inject
    internal fun setAdapter(adapter: BaseDelegateAdapterJava<in Nothing>) {
        this.adapter = adapter
    }

    //region Lifecycle
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_fixtures_list, container, false)

        val fixturesList: RecyclerView = rootView.findViewById(R.id.fixtures_list)
        val linearLayoutManager = LinearLayoutManager(context)

        with(fixturesList) {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            addItemDecoration(DividerItemDecoration(context, linearLayoutManager.orientation))
            recycledViewPool.setMaxRecycledViews(R.id.divider_item_id, 3)    // задать размер пула, когда будут реализованы типы
            adapter = this@FixturesListFragment.adapter
        }

        return rootView
    }

    override fun onDestroy() {
        fixturesListComponent = null
        super.onDestroy()
    }
    //endregion Lifecycle

    //region BaseView
    override fun inject() {
        if (fixturesListComponent == null) {
            fixturesListComponent = createComponent()
        }
        fixturesListComponent!!.inject(this)
    }

    override fun initPresenter(): FixturesListContract.Presenter {
        if (fixturesListComponent == null) {
            fixturesListComponent = createComponent()
        }
        return fixturesListComponent!!.getFixturesListPresenter()
    }
    //endregion BaseView

    //region Contract
    override fun showFixtureList(fixtures: List<BaseItem<in Nothing>>) {
        Log.d("FixturesListFragment", "fixtures: $fixtures")
        adapter.setDataList(fixtures)
    }

    override fun showError(error: Throwable) {
        Toast.makeText(context, R.string.toast_message_loading_error, Toast.LENGTH_SHORT).show()
        Log.d("FixturesListFragment", "Error: $error")
    }
    //endregion Contract

    companion object {

        private const val ARG_FIXTURE_TYPE = "fixture_type"

        fun newInstance(fixtureType: FixtureType): FixturesListFragment {
            val fragment = FixturesListFragment()
            val args = Bundle()
            args.putSerializable(ARG_FIXTURE_TYPE, fixtureType)
            fragment.arguments = args
            return fragment
        }
    }

    private fun createComponent(): FixturesListComponent {
        val fixtureType: FixtureType =
            arguments?.getSerializable(ARG_FIXTURE_TYPE) as FixtureType

        return DaggerFixturesListComponent.builder()
            .fixtureType(fixtureType)
            .tabsActivityComponent((activity as TabsActivity).activityComponent)
            .build()
    }

}