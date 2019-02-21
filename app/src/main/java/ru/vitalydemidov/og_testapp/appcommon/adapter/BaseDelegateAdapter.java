package ru.vitalydemidov.og_testapp.appcommon.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.ViewGroup;
import ru.vitalydemidov.og_testapp.appcommon.model.BaseItem;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseDelegateAdapter<VM extends BaseItem> extends RecyclerView.Adapter<AbstractBaseItemViewHolder<VM>> {

    private SparseArray<BaseViewTypeDelegate> delegateMap = new SparseArray<>();

    private List<BaseItem> dataList = new ArrayList<>();

    public void setDataList(List<BaseItem> list) {
        dataList = list;
        notifyDataSetChanged();
    }

    public void addViewTypeDelegate(int itemType, @NonNull BaseViewTypeDelegate delegate) {
        delegateMap.append(itemType, delegate);
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public AbstractBaseItemViewHolder<VM> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BaseViewTypeDelegate delegate = getViewTypeDelegate(viewType);
        if (delegate != null) {
            return delegate.onCreateViewHolder(parent, viewType);
        } else {
            throw new IllegalArgumentException("Invalid viewType");
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(@NonNull AbstractBaseItemViewHolder<VM> holder, int position) {
        BaseViewTypeDelegate delegate = getViewTypeDelegate(holder.getItemViewType());
        VM item = (VM) dataList.get(position);
        if (delegate != null && item != null) {
            delegate.onBindViewHolder(holder, item);
        }
    }

    @Nullable
    private BaseViewTypeDelegate getViewTypeDelegate(int itemType) {
        return delegateMap.get(itemType);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return dataList.get(position).getType();
    }

}
