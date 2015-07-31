package com.wangenyong.weytest.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wangenyong.mylibrary.tools.DimensionTools;
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
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {
        final SwipeListItem swipeListItem = swipeListItemList.get(position);
        viewHolder.imageView.setImageResource(swipeListItem.getImgId());
        viewHolder.linearLayout.setTranslationX(DimensionTools.dpToPx(300, context.getResources()));
        viewHolder.titleTv.setText(swipeListItem.getTitle());
        viewHolder.introTv.setText(swipeListItem.getIntro());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.linearLayout.animate().translationX(0).setInterpolator(new DecelerateInterpolator()).setDuration(400).start();
            }
        });
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.animate().translationX(DimensionTools.dpToPx(300, context.getResources())).setInterpolator(new AccelerateInterpolator()).setDuration(400).start();
            }
        });
    }

    @Override
    public int getItemCount() {
        return swipeListItemList.size();
    }

    // inner class to hold a reference to each item of RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        LinearLayout linearLayout;
        TextView titleTv;
        TextView introTv;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            imageView = (ImageView) itemLayoutView.findViewById(R.id.img_swipe_list_item);
            linearLayout = (LinearLayout) itemLayoutView.findViewById(R.id.linearlayout_swip_list_item);
            titleTv = (TextView) itemLayoutView.findViewById(R.id.tv_swipe_list_item_title);
            introTv = (TextView) itemLayoutView.findViewById(R.id.tv_swipe_list_item_intro);
        }
    }
}
