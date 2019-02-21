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
import ru.vitalydemidov.og_testapp.appcommon.adapter.BaseDelegateAdapter
import ru.vitalydemidov.og_testapp.appcommon.model.BaseItem
import ru.vitalydemidov.og_testapp.presentation.content.di.DaggerFixturesListComponent
import ru.vitalydemidov.og_testapp.presentation.content.di.FixturesListComponent
import ru.vitalydemidov.og_testapp.presentation.host.TabsActivity
import ru.vitalydemidov.og_testapp.util.FixtureType
import javax.inject.Inject

@UiThread
class FixturesListFragment :
    BaseView<FixturesListContract.View, FixturesListContract.Presenter>(),
    FixturesListContract.View {

    private lateinit var fixtureType: FixtureType

    private lateinit var adapter: BaseDelegateAdapter<in Nothing>

    private var fixturesListComponent: FixturesListComponent? = null
        get() {
            if (field == null) {
                field = createComponent()
            }
            return field
        }

    @Inject
    internal fun setAdapter(adapter: BaseDelegateAdapter<in Nothing>) {
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
            recycledViewPool.setMaxRecycledViews(R.id.date_divider_item_id, DATE_DIVIDER_ITEM_VIEW_POOL)
            recycledViewPool.setMaxRecycledViews(
                when (fixtureType) {
                    FixtureType.UPCOMING -> R.id.fixture_upcoming_item_id
                    FixtureType.FINAL -> R.id.fixture_final_item_id
                },
                FIXTURE_ITEM_VIEW_POOL
            )
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
        fixturesListComponent!!.inject(this)
    }

    override fun initPresenter(): FixturesListContract.Presenter {
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

    private fun createComponent(): FixturesListComponent {
         fixtureType = arguments?.getSerializable(ARG_FIXTURE_TYPE) as FixtureType

        return DaggerFixturesListComponent.builder()
            .fixtureType(fixtureType)
            .tabsActivityComponent((activity as TabsActivity).activityComponent)
            .build()
    }

    companion object {

        internal const val DATE_DIVIDER_ITEM_VIEW_POOL = 5
        internal const val FIXTURE_ITEM_VIEW_POOL = 10

        private const val ARG_FIXTURE_TYPE = "fixture_type"

        fun newInstance(fixtureType: FixtureType): FixturesListFragment {
            val fragment = FixturesListFragment()
            val args = Bundle()
            args.putSerializable(ARG_FIXTURE_TYPE, fixtureType)
            fragment.arguments = args
            return fragment
        }
    }

}