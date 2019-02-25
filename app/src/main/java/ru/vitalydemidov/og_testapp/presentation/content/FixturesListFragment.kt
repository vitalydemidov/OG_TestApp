package ru.vitalydemidov.og_testapp.presentation.content

import android.os.Bundle
import android.support.annotation.UiThread
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.AppCompatSpinner
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import ru.vitalydemidov.og_testapp.R
import ru.vitalydemidov.og_testapp.base.adapter.BaseDelegateAdapter
import ru.vitalydemidov.og_testapp.base.model.BaseItem
import ru.vitalydemidov.og_testapp.base.view.BaseView
import ru.vitalydemidov.og_testapp.data.model.Competition
import ru.vitalydemidov.og_testapp.presentation.content.di.DaggerFixturesListComponent
import ru.vitalydemidov.og_testapp.presentation.content.di.FixturesListComponent
import ru.vitalydemidov.og_testapp.presentation.host.di.TabsActivityComponentProvider
import ru.vitalydemidov.og_testapp.util.FixtureType
import javax.inject.Inject

@UiThread
class FixturesListFragment :
    BaseView<FixturesListContract.View, FixturesListContract.Presenter>(),
    FixturesListContract.View {

    private lateinit var fixtureType: FixtureType
    private lateinit var fixturesSwipeRefreshLayout: SwipeRefreshLayout
    private lateinit var adapter: BaseDelegateAdapter<in Nothing>

    private var spinner: AppCompatSpinner? = null
    private var competitions: List<Competition> = arrayListOf()
    private var selectedCompetition: Competition? = null

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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_overflow, menu)

        val item = menu?.findItem(R.id.toolbar_spinner)
        spinner = item?.actionView as AppCompatSpinner

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onPrepareOptionsMenu(menu: Menu?) {
        super.onPrepareOptionsMenu(menu)

        val spinnerAdapter: ArrayAdapter<Competition> = ArrayAdapter(context, android.R.layout.simple_spinner_item, competitions)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner?.adapter = spinnerAdapter

        selectedCompetition?.let {
            spinner?.setSelection(competitions.indexOf(it))
        }

        var firstSelectionCallback = true
        spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (!firstSelectionCallback) {
                    Toast.makeText(context, "Selected: position=$position; id=$id", Toast.LENGTH_SHORT).show()
                    presenter.onCompetitionForSortingSelected(competitions[position])
                }
                firstSelectionCallback = false
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_fixtures_list, container, false)

        initFixturesSwipeRefreshLayout(rootView)
        initFixturesList(rootView)

        return rootView
    }

    override fun onDestroyView() {
        fixturesSwipeRefreshLayout.setOnRefreshListener(null)
        spinner?.onItemSelectedListener = null
        super.onDestroyView()
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

    override fun showLoadingProgress() {
        fixturesSwipeRefreshLayout.isRefreshing = true
    }

    override fun hideLoadingProgress() {
        fixturesSwipeRefreshLayout.isRefreshing = false
    }

    override fun showLoadingError(error: Throwable) {
        Toast.makeText(context, "${getString(R.string.toast_message_loading_error)}: ${error.message}", Toast.LENGTH_SHORT).show()
    }
    //endregion BaseView

    //region Contract
    override fun showFixtureList(fixtures: List<BaseItem<in Nothing>>) {
        adapter.setDataList(fixtures)
    }

    override fun showAvailableSortingByCompetition(competitions: List<Competition>, selected: Competition?) {
        this.competitions = competitions
        this.selectedCompetition = selected
        activity?.invalidateOptionsMenu()
    }
    //endregion Contract

    private fun createComponent(): FixturesListComponent {
         fixtureType = arguments?.getSerializable(ARG_FIXTURE_TYPE) as FixtureType

        return DaggerFixturesListComponent.builder()
            .fixtureType(fixtureType)
            .tabsActivityComponent((activity as TabsActivityComponentProvider).provideTabsActivityComponent())
            .build()
    }

    private fun initFixturesSwipeRefreshLayout(rootView: View) {
        fixturesSwipeRefreshLayout = rootView.findViewById(R.id.fixtures_swipe_refresh_layout)
        fixturesSwipeRefreshLayout.setOnRefreshListener { presenter.loadFixtures(forceRemote = true) }
        fixturesSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent)
    }

    private fun initFixturesList(rootView: View) {
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