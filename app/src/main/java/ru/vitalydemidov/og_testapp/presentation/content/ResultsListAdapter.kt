package ru.vitalydemidov.og_testapp.presentation.content

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.vitalydemidov.og_testapp.R
import ru.vitalydemidov.og_testapp.appcommon.adapter.AbstractBaseItemViewHolder
import ru.vitalydemidov.og_testapp.appcommon.adapter.BaseDelegateAdapterJava
import ru.vitalydemidov.og_testapp.appcommon.adapter.BaseViewTypeDelegate
import ru.vitalydemidov.og_testapp.appcommon.model.BaseItem
import ru.vitalydemidov.og_testapp.presentation.content.viewholder.DateDividerViewHolder
import ru.vitalydemidov.og_testapp.presentation.content.viewholder.FixtureResultViewHolder
import ru.vitalydemidov.og_testapp.presentation.content.viewmodel.DateDividerVM
import ru.vitalydemidov.og_testapp.presentation.content.viewmodel.FixtureResultVM

class ResultsListAdapter : BaseDelegateAdapterJava<BaseItem<in Nothing>>() {

    init {
        addViewTypeDelegate(
            R.id.fixture_final_item_id,
            object : BaseViewTypeDelegate<FixtureResultVM, AbstractBaseItemViewHolder<FixtureResultVM>>() {

                override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbstractBaseItemViewHolder<FixtureResultVM> =
                    FixtureResultViewHolder(
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