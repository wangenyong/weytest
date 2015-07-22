package com.wangenyong.weytest.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wangenyong.weytest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangenyong on 15/7/22.
 */
public class ToolsAdapter extends RecyclerView.Adapter<ToolsAdapter.ViewHolder> {
    private List<String> mDatas;
    private LayoutInflater mInflater;
    private List<Integer> mHeights;

    public ToolsAdapter(Context context, List<String> mDatas) {
        mInflater = LayoutInflater.from(context);
        this.mDatas = mDatas;

        mHeights = new ArrayList<Integer>();
        for (int i = 0; i < mDatas.size(); i++)
        {
            mHeights.add( (int) (100 + Math.random() * 300));
        }
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
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        ViewGroup.LayoutParams lp = viewHolder.viewTitleTv.getLayoutParams();
        lp.height = mHeights.get(position);

        viewHolder.viewTitleTv.setLayoutParams(lp);
        viewHolder.viewTitleTv.setText(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    // inner class to hold a reference to each item of RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView viewTitleTv;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            viewTitleTv = (TextView) itemLayoutView.findViewById(R.id.tv_tools_item);
        }
    }
}
