package com.wangenyong.weytest.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wangenyong.mylibrary.adapters.adapterdelegates.AbsAdapterDelegate;
import com.wangenyong.weytest.R;
import com.wangenyong.weytest.bean.Item;
import com.wangenyong.weytest.bean.ThirdPartyItem;

import java.util.List;

/**
 * Created by wangenyong on 15/8/19.
 */
public class ThirdPartyAdapterDelegate extends AbsAdapterDelegate<List<Item>> {
    private LayoutInflater inflater;

    public ThirdPartyAdapterDelegate(Activity activity, int viewType) {
        super(viewType);
        inflater = activity.getLayoutInflater();
    }

    @Override public boolean isForViewType(@NonNull List<Item> items, int position) {
        return items.get(position) instanceof ThirdPartyItem;
    }

    @NonNull @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new ThirdPartyViewHolder(inflater.inflate(R.layout.recycler_item_third_party, parent, false));
    }

    @Override public void onBindViewHolder(@NonNull List<Item> items, int position,
                                           @NonNull RecyclerView.ViewHolder holder) {
        ThirdPartyViewHolder vh = (ThirdPartyViewHolder) holder;
        ThirdPartyItem thirdParty = (ThirdPartyItem) items.get(position);
        vh.imageView.setImageResource(thirdParty.getImage());
        vh.viewTitleTv.setText(thirdParty.getTitle());
    }


    static class ThirdPartyViewHolder extends RecyclerView.ViewHolder {
        TextView viewTitleTv;
        CardView cardView;
        ImageView imageView;

        public ThirdPartyViewHolder(View itemView) {
            super(itemView);
            viewTitleTv = (TextView) itemView.findViewById(R.id.tv_third_party_item);
            cardView = (CardView) itemView.findViewById(R.id.cardview_third_party_item);
            imageView = (ImageView) itemView.findViewById(R.id.img_third_party_item);
        }
    }
}
