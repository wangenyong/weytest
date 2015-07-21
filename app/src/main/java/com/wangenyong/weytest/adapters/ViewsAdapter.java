package com.wangenyong.weytest.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wangenyong.weytest.R;
import com.wangenyong.weytest.bean.MyView;

import java.util.List;

/**
 * Created by wangenyong on 15/7/13.
 */
public class ViewsAdapter extends RecyclerView.Adapter<ViewsAdapter.ViewHolder> {
    private Context context;
    private List<MyView> myViewList;

    public ViewsAdapter(Context context, List<MyView> myViewList) {
        this.myViewList = myViewList;
        this.context = context;
    }

    @Override
    public ViewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.recycler_item_views, null);

        // create ViewHolder
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        final MyView myView = myViewList.get(position);
        viewHolder.viewTitleTv.setText(myView.getTitle());
        if (myView.getView().getParent() != null) {
            ((LinearLayout) myView.getView().getParent()).removeView(myView.getView());
        }
        viewHolder.viewLL.removeAllViews();
        viewHolder.viewLL.addView(myView.getView());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return myViewList.size();
    }

    // inner class to hold a reference to each item of RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView viewTitleTv;
        LinearLayout viewLL;


        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            viewTitleTv = (TextView) itemLayoutView.findViewById(R.id.tv_views_item);
            viewLL = (LinearLayout) itemLayoutView.findViewById(R.id.linear_layout_item_views);
        }
    }
}
