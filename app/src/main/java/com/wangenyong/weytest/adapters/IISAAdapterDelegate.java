package com.wangenyong.weytest.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.wangenyong.mylibrary.adapters.adapterdelegates.AbsAdapterDelegate;
import com.wangenyong.weytest.R;
import com.wangenyong.weytest.bean.Item;
import com.wangenyong.weytest.bean.MyDesign;

import java.util.List;

/**
 * Created by wangenyong on 15/8/20.
 */
public class IISAAdapterDelegate extends AbsAdapterDelegate<List<Item>> {
    private LayoutInflater inflater;
    private Activity activity;

    public IISAAdapterDelegate(Activity activity, int viewType) {
        super(viewType);
        inflater = activity.getLayoutInflater();
        this.activity = activity;
    }

    @Override public boolean isForViewType(@NonNull List<Item> items, int position) {
        return items.get(position) instanceof MyDesign;
    }

    @NonNull @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new DesignBigViewHolder(inflater.inflate(R.layout.recycler_item_img_icon_style_a, parent, false));
    }

    @Override public void onBindViewHolder(@NonNull List<Item> items, int position,
                                           @NonNull RecyclerView.ViewHolder holder) {
        DesignBigViewHolder vh = (DesignBigViewHolder) holder;
        final MyDesign myDesign = (MyDesign) items.get(position);

        vh.mainImg.setImageResource(myDesign.getImageId());
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent launchIntent = activity.getPackageManager().getLaunchIntentForPackage(myDesign.getPackageName());
                    activity.startActivity(launchIntent);
                } catch (NullPointerException e) {
                    Toast.makeText(activity, activity.getString(R.string.third_party_null_error), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    static class DesignBigViewHolder extends RecyclerView.ViewHolder {
        ImageView mainImg;
        ImageView favoriateImg;


        public DesignBigViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            mainImg = (ImageView) itemLayoutView.findViewById(R.id.img_img_icon_style_a_item);
            favoriateImg = (ImageView) itemLayoutView.findViewById(R.id.img_img_icon_style_a_favorite);
        }
    }
}
