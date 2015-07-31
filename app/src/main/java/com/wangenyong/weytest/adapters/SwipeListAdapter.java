package com.wangenyong.weytest.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wangenyong.weytest.R;
import com.wangenyong.weytest.bean.SwipeListItem;

import java.util.List;

/**
 * Created by wangenyong on 15/7/31.
 */
public class SwipeListAdapter extends RecyclerView.Adapter<SwipeListAdapter.ViewHolder> {
    private Context context;
    private List<SwipeListItem> swipeListItemList;

    public SwipeListAdapter(Context context, List<SwipeListItem> swipeListItemList) {
        this.context = context;
        this.swipeListItemList = swipeListItemList;
    }

    @Override
    public SwipeListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.recycler_item_swip_list, null);

        // create ViewHolder
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        final SwipeListItem swipeListItem = swipeListItemList.get(position);
        viewHolder.imageView.setImageResource(swipeListItem.getImgId());
    }

    @Override
    public int getItemCount() {
        return swipeListItemList.size();
    }

    // inner class to hold a reference to each item of RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;


        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            imageView = (ImageView) itemLayoutView.findViewById(R.id.img_swipe_list_item);
        }
    }
}
