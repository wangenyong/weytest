package com.wangenyong.weytest.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.wangenyong.mylibrary.adapters.adapterdelegates.AbsAdapterDelegate;
import com.wangenyong.mylibrary.views.SImageView;
import com.wangenyong.weytest.R;
import com.wangenyong.weytest.bean.Item;
import com.wangenyong.weytest.bean.ThirdPartyItem;

import java.util.List;

/**
 * Created by wangenyong on 15/8/19.
 */
public class ITSAAdapterDelegate extends AbsAdapterDelegate<List<Item>> {
    private LayoutInflater inflater;
    private Activity activity;

    public ITSAAdapterDelegate(Activity activity, int viewType) {
        super(viewType);
        inflater = activity.getLayoutInflater();
        this.activity = activity;
    }

    @Override public boolean isForViewType(@NonNull List<Item> items, int position) {
        return items.get(position) instanceof ThirdPartyItem;
    }

    @NonNull @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new ThirdPartyViewHolder(inflater.inflate(R.layout.recycler_item_img_txt_style_a, parent, false));
    }

    @Override public void onBindViewHolder(@NonNull List<Item> items, int position,
                                           @NonNull RecyclerView.ViewHolder holder) {
        ThirdPartyViewHolder vh = (ThirdPartyViewHolder) holder;
        final ThirdPartyItem thirdParty = (ThirdPartyItem) items.get(position);
        vh.imageView.setImageResource(thirdParty.getImage());
        vh.viewTitleTv.setText(thirdParty.getTitle());
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent launchIntent = activity.getPackageManager().getLaunchIntentForPackage(thirdParty.getLink());
                    activity.startActivity(launchIntent);
                } catch (NullPointerException e) {
                    Toast.makeText(activity, activity.getString(R.string.third_party_null_error), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    static class ThirdPartyViewHolder extends RecyclerView.ViewHolder {
        TextView viewTitleTv;
        CardView cardView;
        SImageView imageView;

        public ThirdPartyViewHolder(View itemView) {
            super(itemView);
            viewTitleTv = (TextView) itemView.findViewById(R.id.tv_img_txt_style_a_item);
            cardView = (CardView) itemView.findViewById(R.id.cardview_img_txt_style_a_item);
            imageView = (SImageView) itemView.findViewById(R.id.img_img_txt_style_a_item);
        }
    }
}
