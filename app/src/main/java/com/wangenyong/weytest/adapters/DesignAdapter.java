package com.wangenyong.weytest.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.wangenyong.mylibrary.adapters.adapterdelegates.AdapterDelegatesManager;
import com.wangenyong.weytest.bean.Item;

import java.util.List;

/**
 * Created by wangenyong on 15/8/7.
 */
public class DesignAdapter extends RecyclerView.Adapter {
    private AdapterDelegatesManager<List<Item>> delegatesManager;
    private List<Item> items;

    public DesignAdapter(Activity activity, List<Item> items) {
        this.items = items;

        // Delegates
        delegatesManager = new AdapterDelegatesManager<>();
        delegatesManager.addDelegate(new DesignBigAdapterDelegate(activity, 0));
        delegatesManager.addDelegate(new ThirdPartyAdapterDelegate(activity, 1));

    }

    public int getSpanCount(int position) {
        return items.get(position).getCount();
    }


    @Override public int getItemViewType(int position) {
        return delegatesManager.getItemViewType(items, position);
    }

    @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return delegatesManager.onCreateViewHolder(parent, viewType);
    }

    @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        delegatesManager.onBindViewHolder(items, position, holder);
    }

    @Override public int getItemCount() {
        return items.size();
    }
}
