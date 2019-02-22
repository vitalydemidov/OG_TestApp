package ru.vitalydemidov.og_testapp.base.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import ru.vitalydemidov.og_testapp.base.model.BaseItem

abstract class AbstractBaseItemViewHolder<VM>(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    fun bind(item: BaseItem<VM>) {
        bindHolder(item.data)
    }

    protected abstract fun bindHolder(viewModel: VM)

}