package ru.vitalydemidov.og_testapp.presentation.content.adapter

import android.support.annotation.UiThread
import android.view.LayoutInflater
import android.view.ViewGroup
import ru.vitalydemidov.og_testapp.R
import ru.vitalydemidov.og_testapp.base.adapter.AbstractBaseItemViewHolder
import ru.vitalydemidov.og_testapp.base.adapter.BaseDelegateAdapter
import ru.vitalydemidov.og_testapp.base.adapter.BaseViewTypeDelegate
import ru.vitalydemidov.og_testapp.base.model.BaseItem
import ru.vitalydemidov.og_testapp.presentation.content.viewholder.DateDividerViewHolder
import ru.vitalydemidov.og_testapp.presentation.content.viewholder.FixtureFinalViewHolder
import ru.vitalydemidov.og_testapp.presentation.content.viewmodel.DateDividerVM
import ru.vitalydemidov.og_testapp.presentation.content.viewmodel.FixtureResultVM

@UiThread
class FixturesFinalListAdapter : BaseDelegateAdapter<BaseItem<in Nothing>>() {

    init {
        addViewTypeDelegate(
            R.id.fixture_final_item_id,
            object : BaseViewTypeDelegate<FixtureResultVM, AbstractBaseItemViewHolder<FixtureResultVM>>() {

                override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbstractBaseItemViewHolder<FixtureResultVM> =
                    FixtureFinalViewHolder(
                        LayoutInflater.from(parent.context).inflate(R.layout.item_list_fixture_final, parent, false)
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