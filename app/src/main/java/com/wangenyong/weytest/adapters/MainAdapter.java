package com.wangenyong.weytest.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wangenyong.weytest.R;
import com.wangenyong.weytest.activities.ViewsActivity;
import com.wangenyong.weytest.bean.Component;

import java.util.List;

/**
 * Created by wangenyong on 15/7/10.
 */
public class MainAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<Component> componentList;
    private OnItemClickLitener mOnItemClickLitener;

    public MainAdapter(Context context, List<Component> componentList) {
        this.context = context;
        this.componentList = componentList;
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_main, null);
        // create ViewHolder
        RecyclerView.ViewHolder viewHolder = new MainViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final Component component = componentList.get(position);
        final MainViewHolder mainViewHolder = (MainViewHolder) viewHolder;
        mainViewHolder.iconImg.setImageResource(component.getIconId());
        mainViewHolder.iconImg.setColorFilter(component.getColor());
        mainViewHolder.titleTv.setText(component.getTitle());

        if (mOnItemClickLitener != null)
        {
            mainViewHolder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int pos = mainViewHolder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(mainViewHolder.itemView, pos);
                    Intent intent = new Intent(context, ViewsActivity.class);
                    context.startActivity(intent);
                }
            });

            mainViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View v)
                {
                    int pos = mainViewHolder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(mainViewHolder.itemView, pos);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return componentList.size();
    }


    /**
    public void addData(int position) {
        componentList.add(position, "Insert One");
        notifyItemInserted(position);
    }
     */

    public void removeData(int position) {
        componentList.remove(position);
        notifyItemRemoved(position);
    }



    // inner class to hold a reference to each item of RecyclerView
    public static class MainViewHolder extends RecyclerView.ViewHolder {
        ImageView iconImg;
        TextView titleTv;


        public MainViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            iconImg = (ImageView) itemLayoutView.findViewById(R.id.img_main_item);
            titleTv = (TextView) itemLayoutView.findViewById(R.id.tv_main_item);
        }
    }



    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view , int position);
    }
}
