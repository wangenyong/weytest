package com.wangenyong.weytest.activities;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.wangenyong.mylibrary.tools.DimensionTools;
import com.wangenyong.weytest.R;
import com.wangenyong.weytest.adapters.ViewsAdapter;
import com.wangenyong.weytest.bean.CustomView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ViewsActivity extends AppCompatActivity {
    @InjectView(R.id.toolbar_views) Toolbar viewsToolbar;
    @InjectView(R.id.collapsing_toolbar_views) CollapsingToolbarLayout viewsCoolapsingLayout;
    @InjectView(R.id.recyclerview_views) RecyclerView viewsRecyclerView;
    @InjectView(R.id.img_views_backdrop) ImageView backdropImg;

    private RecyclerView.LayoutManager mLayoutManager;
    private ViewsAdapter viewsAdapter;
    private List<CustomView> customViews = new ArrayList<CustomView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_views);

        ButterKnife.inject(this);
        setSupportActionBar(viewsToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        backdropImg.setImageResource(R.drawable.img_view_button_header);

        initCustomViews();

        mLayoutManager = new LinearLayoutManager(this);
        viewsRecyclerView.setLayoutManager(mLayoutManager);
        viewsAdapter = new ViewsAdapter(this, customViews);
        viewsRecyclerView.setAdapter(viewsAdapter);
    }

    private void initCustomViews() {
        customViews.clear();

        Button borderButton = new Button(this);
        borderButton.setText(getString(R.string.button_border));
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        int size = DimensionTools.dpToPx(16, getResources());
        lp.setMargins(size, size, size, size);
        borderButton.setLayoutParams(lp);
        borderButton.setTextColor(getResources().getColor(R.color.primary_color));
        borderButton.setBackgroundResource(R.drawable.btn_selector_transparent_corner_boder);

        customViews.add(new CustomView(getString(R.string.button_border), borderButton));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_buttons, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
