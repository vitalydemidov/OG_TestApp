package ru.vitalydemidov.og_testapp.presentation.content.viewholder

import android.view.View
import android.widget.TextView
import ru.vitalydemidov.og_testapp.R
import ru.vitalydemidov.og_testapp.base.adapter.AbstractBaseItemViewHolder
import ru.vitalydemidov.og_testapp.presentation.content.viewmodel.DateDividerVM

class DateDividerViewHolder(itemView: View) : AbstractBaseItemViewHolder<DateDividerVM>(itemView) {

    private val label: TextView = itemView.findViewById(R.id.item_list_date_divider_label)

    override fun bindHolder(viewModel: DateDividerVM) {
        label.text = viewModel.formatterDate
    }

}