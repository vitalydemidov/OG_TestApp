package ru.vitalydemidov.og_testapp.presentation.content.adapter

import android.support.annotation.UiThread
import android.view.LayoutInflater
import android.view.ViewGroup
import ru.vitalydemidov.og_testapp.R
import ru.vitalydemidov.og_testapp.appcommon.adapter.AbstractBaseItemViewHolder
import ru.vitalydemidov.og_testapp.appcommon.adapter.BaseDelegateAdapter
import ru.vitalydemidov.og_testapp.appcommon.adapter.BaseViewTypeDelegate
import ru.vitalydemidov.og_testapp.appcommon.model.BaseItem
import ru.vitalydemidov.og_testapp.presentation.content.viewholder.DateDividerViewHolder
import ru.vitalydemidov.og_testapp.presentation.content.viewholder.FixtureUpcomingViewHolder
import ru.vitalydemidov.og_testapp.presentation.content.viewmodel.DateDividerVM
import ru.vitalydemidov.og_testapp.presentation.content.viewmodel.FixtureUpcomingVM

@UiThread
class FixturesUpcomingListAdapter : BaseDelegateAdapter<BaseItem<in Nothing>>() {

    init {
        addViewTypeDelegate(
            R.id.fixture_upcoming_item_id,
            object : BaseViewTypeDelegate<FixtureUpcomingVM, AbstractBaseItemViewHolder<FixtureUpcomingVM>>() {

                override fun onCreateViewHolder(
                    parent: ViewGroup,
                    viewType: Int
                ): AbstractBaseItemViewHolder<FixtureUpcomingVM> =
                    FixtureUpcomingViewHolder(
                        LayoutInflater.from(parent.context).inflate(R.layout.item_list_fixture_upcoming, parent, false)
                    )
            }
        )

        addViewTypeDelegate(
            R.id.date_divider_item_id,
            object : BaseViewTypeDelegate<DateDividerVM, AbstractBaseItemViewHolder<DateDividerVM>>() {

                override fun onCreateViewHolder(
                    parent: ViewGroup,
                    viewType: Int
                ): AbstractBaseItemViewHolder<DateDividerVM> =
                    DateDividerViewHolder(
                        LayoutInflater.from(parent.context).inflate(R.layout.item_list_date_divider, parent, false)
                    )
            }
        )
    }

}