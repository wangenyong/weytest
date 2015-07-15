package com.wangenyong.weytest.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.wangenyong.mylibrary.tools.DimensionTools;
import com.wangenyong.weytest.R;
import com.wangenyong.weytest.adapters.ViewsAdapter;
import com.wangenyong.weytest.bean.Component;
import com.wangenyong.weytest.bean.Constants;
import com.wangenyong.weytest.bean.MyView;

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
    private List<MyView> myViews = new ArrayList<MyView>();
    LinearLayout.LayoutParams lp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_views);

        ButterKnife.inject(this);
        setSupportActionBar(viewsToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        int type = intent.getIntExtra(Constants.INTENT_VIEW, Component.BUTTON);

        lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        int size = DimensionTools.dpToPx(16, getResources());
        lp.setMargins(size, size, size, size);

        switch (type) {
            case Component.BUTTON:
                initButtons();
                break;
            case Component.EDITTEXT:
                initEdittexts();
                break;
            case Component.PROGRESS:
                initProgress();
                break;
            default:
                initButtons();
        }

        mLayoutManager = new LinearLayoutManager(this);
        viewsRecyclerView.setLayoutManager(mLayoutManager);
        viewsAdapter = new ViewsAdapter(this, myViews);
        viewsRecyclerView.setAdapter(viewsAdapter);
    }



    private void initButtons() {
        backdropImg.setImageResource(R.drawable.img_view_button_header);
        viewsCoolapsingLayout.setTitle(getString(R.string.view_button));
        myViews.clear();

        Button button = new Button(this);
        button.setText(getString(R.string.button_standard_en));
        button.setLayoutParams(lp);
        myViews.add(new MyView(getString(R.string.button_standard), button));

        Button borderlessButton = (Button) getLayoutInflater().inflate(R.layout.button_borderless, null);
        borderlessButton.setLayoutParams(lp);
        myViews.add(new MyView(getString(R.string.button_borderless), borderlessButton));

        Button borderButton = new Button(this);
        borderButton.setText(getString(R.string.button_border_en));
        borderButton.setLayoutParams(lp);
        borderButton.setTextColor(getResources().getColor(R.color.primary_color));
        borderButton.setBackgroundResource(R.drawable.btn_selector_transparent_corner_boder);
        myViews.add(new MyView(getString(R.string.button_border), borderButton));

        FrameLayout frameLayout = (FrameLayout) getLayoutInflater().inflate(R.layout.button_paper, null);
        frameLayout.setLayoutParams(lp);
        myViews.add(new MyView(getString(R.string.button_paper), frameLayout));

        Button materialButton = new Button(this);
        materialButton.setText(getString(R.string.button_material_en));
        materialButton.setLayoutParams(lp);
        materialButton.setBackgroundResource(R.drawable.btn_material);
        myViews.add(new MyView(getString(R.string.button_material), materialButton));
    }

    private void initEdittexts() {
        backdropImg.setImageResource(R.drawable.img_view_edittext_header);
        viewsCoolapsingLayout.setTitle(getString(R.string.view_edittext));
        myViews.clear();

        EditText editText = new EditText(this);
        editText.setHint(getString(R.string.edittext_standard_en));
        editText.setLayoutParams(lp);
        myViews.add(new MyView(getString(R.string.edittext_standard), editText));

        EditText cornerBorderEditText = new EditText(this);
        cornerBorderEditText.setHint(getString(R.string.edittext_border_corner_en));
        cornerBorderEditText.setLayoutParams(lp);
        cornerBorderEditText.setBackgroundResource(R.drawable.edittext_selector_corner_border);
        myViews.add(new MyView(getString(R.string.edittext_border_corner), cornerBorderEditText));
    }

    private void initProgress() {
        backdropImg.setImageResource(R.drawable.img_view_progress_header);
        viewsCoolapsingLayout.setTitle(getString(R.string.view_progress));
        myViews.clear();

        ProgressBar progressBar = new ProgressBar(this);
        progressBar.setLayoutParams(lp);
        myViews.add(new MyView(getString(R.string.progress_standard), progressBar));
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
