package ru.vitalydemidov.og_testapp.appcommon.adapter

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import ru.vitalydemidov.og_testapp.appcommon.model.BaseItem

abstract class BaseDelegateAdapter<VM> :
    RecyclerView.Adapter<AbstractBaseItemViewHolder<VM>>() {

    private val viewTypeDelegates = SparseArrayCompat<BaseViewTypeDelegate<VM, AbstractBaseItemViewHolder<VM>>>()

    internal var dataList: List<BaseItem<VM>> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    protected fun addViewTypeDelegate(
        itemType: Int,
        viewTypeDelegate: BaseViewTypeDelegate<VM, AbstractBaseItemViewHolder<VM>>
    ) {
        viewTypeDelegates.append(itemType, viewTypeDelegate)
    }

    internal fun getViewTypeDelegate(itemType: Int): BaseViewTypeDelegate<VM, AbstractBaseItemViewHolder<VM>>? =
        viewTypeDelegates.get(itemType)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbstractBaseItemViewHolder<VM> {
        val viewTypeDelegate = getViewTypeDelegate(viewType)
        return viewTypeDelegate?.onCreateViewHolder(parent, viewType)
            ?: throw IllegalArgumentException("Invalid viewType")
    }

    override fun onBindViewHolder(holder: AbstractBaseItemViewHolder<VM>, position: Int) {
        val viewTypeDelegate = getViewTypeDelegate(holder.itemViewType)
        val item = dataList[position]
        viewTypeDelegate?.onBindViewHolder(holder, item)
    }

    override fun getItemCount(): Int = dataList.size

}