package com.wangenyong.weytest.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wangenyong.weytest.R;

import java.util.List;

/**
 * Created by wangenyong on 15/8/7.
 */
public class DesignAdapter extends RecyclerView.Adapter<DesignAdapter.ViewHolder> {
    private Context context;
    private List<Integer> images;

    public DesignAdapter(Context context, List<Integer> images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public DesignAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.recycler_item_design, null);

        // create ViewHolder
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {
        final Integer id = images.get(position);

        viewHolder.mainImg.setImageResource(id);

        viewHolder.favoriateImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.favoriateImg.setColorFilter(context.getResources().getColor(R.color.favoriate));
            }
        });

        viewHolder.mainImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    // inner class to hold a reference to each item of RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mainImg;
        ImageView favoriateImg;


        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            mainImg = (ImageView) itemLayoutView.findViewById(R.id.img_design_item);
            favoriateImg = (ImageView) itemLayoutView.findViewById(R.id.img_design_item_favorite);
        }
    }
}
