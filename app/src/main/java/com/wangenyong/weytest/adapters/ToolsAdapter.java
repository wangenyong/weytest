package com.wangenyong.weytest.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wangenyong.weytest.R;
import com.wangenyong.weytest.bean.MyTool;

import java.util.List;

/**
 * Created by wangenyong on 15/7/22.
 */
public class ToolsAdapter extends RecyclerView.Adapter<ToolsAdapter.ViewHolder> {
    private List<MyTool> myTools;
    private LayoutInflater mInflater;
    private OnItemClickLitener mOnItemClickLitener;

    public ToolsAdapter(Context context, List<MyTool> myTools) {
        mInflater = LayoutInflater.from(context);
        this.myTools = myTools;
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }


    @Override
    public ToolsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.recycler_item_tools, parent, false);

        // create ViewHolder
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        final MyTool myTool = myTools.get(position);
        viewHolder.imageView.setImageResource(myTool.getImage());
        viewHolder.viewTitleTv.setText(myTool.getTitle());

        if (mOnItemClickLitener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickLitener.onItemClick(v, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return myTools.size();
    }

    // inner class to hold a reference to each item of RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView viewTitleTv;
        CardView cardView;
        ImageView imageView;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            viewTitleTv = (TextView) itemLayoutView.findViewById(R.id.tv_tools_item);
            cardView = (CardView) itemLayoutView.findViewById(R.id.cardview_tools_item);
            imageView = (ImageView) itemLayoutView.findViewById(R.id.img_tools_item);
        }
    }


    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }
}
