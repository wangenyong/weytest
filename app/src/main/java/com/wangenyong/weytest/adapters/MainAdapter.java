package com.wangenyong.weytest.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wangenyong.weytest.R;

import java.util.List;

/**
 * Created by wangenyong on 15/7/10.
 */
public class MainAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<String> stringList;
    private OnItemClickLitener mOnItemClickLitener;

    public MainAdapter(Context context, List<String> stringList) {
        this.context = context;
        this.stringList = stringList;
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
        final String s = stringList.get(position);
        final MainViewHolder mainViewHolder = (MainViewHolder) viewHolder;
        mainViewHolder.mainTitleTv.setText(s);

        if (mOnItemClickLitener != null)
        {
            mainViewHolder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int pos = mainViewHolder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(mainViewHolder.itemView, pos);
                }
            });

            mainViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View v)
                {
                    int pos = mainViewHolder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(mainViewHolder.itemView, pos);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }


    public void addData(int position) {
        stringList.add(position, "Insert One");
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        stringList.remove(position);
        notifyItemRemoved(position);
    }



    // inner class to hold a reference to each item of RecyclerView
    public static class MainViewHolder extends RecyclerView.ViewHolder {
        TextView mainTitleTv;


        public MainViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            mainTitleTv = (TextView) itemLayoutView.findViewById(R.id.tv_main_item);
        }
    }



    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view , int position);
    }
}
