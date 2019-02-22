package ru.vitalydemidov.og_testapp.base.adapter

import android.view.ViewGroup
import ru.vitalydemidov.og_testapp.base.model.BaseItem

abstract class BaseViewTypeDelegate<VM, VH : AbstractBaseItemViewHolder<VM>> {

    abstract fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbstractBaseItemViewHolder<VM>

    open fun onBindViewHolder(holder: VH, item: BaseItem<VM>) {
        holder.bind(item)
    }

}