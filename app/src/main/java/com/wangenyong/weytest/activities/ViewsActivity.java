package com.wangenyong.weytest.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import com.afollestad.materialdialogs.ThemeSingleton;
import com.afollestad.materialdialogs.internal.MDTintHelper;
import com.afollestad.materialdialogs.simplelist.MaterialSimpleListAdapter;
import com.afollestad.materialdialogs.simplelist.MaterialSimpleListItem;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.PercentFormatter;
import com.wangenyong.mylibrary.tools.DimensionTools;
import com.wangenyong.mylibrary.views.TintableImageView;
import com.wangenyong.weytest.R;
import com.wangenyong.weytest.adapters.ViewsAdapter;
import com.wangenyong.weytest.bean.Component;
import com.wangenyong.weytest.bean.Constants;
import com.wangenyong.weytest.bean.MyView;
import com.wangenyong.weytest.dialogs.ChangelogDialog;
import com.wangenyong.weytest.dialogs.ColorChooserDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ViewsActivity extends AppCompatActivity implements ColorChooserDialog.Callback {
    @InjectView(R.id.toolbar_views) Toolbar viewsToolbar;
    @InjectView(R.id.collapsing_toolbar_views) CollapsingToolbarLayout viewsCoolapsingLayout;
    @InjectView(R.id.recyclerview_views) RecyclerView viewsRecyclerView;
    @InjectView(R.id.img_views_backdrop) ImageView backdropImg;

    private RecyclerView.LayoutManager mLayoutManager;
    private ViewsAdapter viewsAdapter;
    private List<MyView> myViews = new ArrayList<MyView>();
    LinearLayout.LayoutParams lp;
    private Toast mToast;

    protected String[] mMonths = new String[] {
            "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Okt", "Nov", "Dec"
    };

    protected String[] mParties = new String[] {
            "Party A", "Party B", "Party C", "Party D", "Party E", "Party F", "Party G", "Party H",
            "Party I", "Party J", "Party K", "Party L", "Party M", "Party N", "Party O", "Party P",
            "Party Q", "Party R", "Party S", "Party T", "Party U", "Party V", "Party W", "Party X",
            "Party Y", "Party Z"
    };

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
            case Component.IMAGE:
                initImage();
                break;
            case Component.CHART:
                initChart();
                break;
            case Component.DIALOG:
                initDialog();
                break;
            default:
                initButtons();
        }

        mLayoutManager = new LinearLayoutManager(this);
        viewsRecyclerView.setLayoutManager(mLayoutManager);
        viewsAdapter = new ViewsAdapter(this, myViews);
        viewsRecyclerView.setAdapter(viewsAdapter);
    }

    /**
     * Buttons
     */
    private void initButtons() {
        backdropImg.setImageResource(R.drawable.img_view_button_header);
        viewsCoolapsingLayout.setTitle(getString(R.string.view_button));
        myViews.clear();

        //button
        Button button = new Button(this);
        button.setText(getString(R.string.button_standard_en));
        button.setLayoutParams(lp);
        myViews.add(new MyView(getString(R.string.button_standard), button));

        //borderlessButton
        Button borderlessButton = (Button) getLayoutInflater().inflate(R.layout.button_borderless, null);
        borderlessButton.setLayoutParams(lp);
        myViews.add(new MyView(getString(R.string.button_borderless), borderlessButton));

        Button borderButton = new Button(this);
        borderButton.setText(getString(R.string.button_border_en));
        borderButton.setLayoutParams(lp);
        borderButton.setTextColor(getResources().getColor(R.color.primary_color));
        borderButton.setBackgroundResource(R.drawable.btn_selector_transparent_corner_boder);
        myViews.add(new MyView(getString(R.string.button_border), borderButton));

        //paperButton
        FrameLayout frameLayout = (FrameLayout) getLayoutInflater().inflate(R.layout.button_paper, null);
        frameLayout.setLayoutParams(lp);
        myViews.add(new MyView(getString(R.string.button_paper), frameLayout));

        //materialButton
        Button materialButton = new Button(this);
        materialButton.setText(getString(R.string.button_material_en));
        materialButton.setLayoutParams(lp);
        materialButton.setBackgroundResource(R.drawable.btn_material);
        myViews.add(new MyView(getString(R.string.button_material), materialButton));

        //tintableButton
        TintableImageView tintableButton = (TintableImageView) getLayoutInflater().inflate(R.layout.button_tintable, null);
        tintableButton.setLayoutParams(lp);
        myViews.add(new MyView(getString(R.string.button_tintable), tintableButton));
    }

    /**
     * Edittexts
     */
    private void initEdittexts() {
        backdropImg.setImageResource(R.drawable.img_view_edittext_header);
        viewsCoolapsingLayout.setTitle(getString(R.string.view_edittext));
        myViews.clear();

        //editText
        EditText editText = new EditText(this);
        editText.setHint(getString(R.string.edittext_standard_en));
        editText.setLayoutParams(lp);
        myViews.add(new MyView(getString(R.string.edittext_standard), editText));

        //cornerBorderEditText
        EditText cornerBorderEditText = new EditText(this);
        cornerBorderEditText.setHint(getString(R.string.edittext_border_corner_en));
        cornerBorderEditText.setLayoutParams(lp);
        cornerBorderEditText.setBackgroundResource(R.drawable.edittext_selector_corner_border);
        myViews.add(new MyView(getString(R.string.edittext_border_corner), cornerBorderEditText));
    }

    /**
     * Progresses
     */
    private void initProgress() {
        backdropImg.setImageResource(R.drawable.img_view_progress_header);
        viewsCoolapsingLayout.setTitle(getString(R.string.view_progress));
        myViews.clear();

        //progress
        ProgressBar progressBar = new ProgressBar(this);
        progressBar.setLayoutParams(lp);
        myViews.add(new MyView(getString(R.string.progress_standard), progressBar));

        //horizontalBar
        ProgressBar horizontalBar = (ProgressBar) getLayoutInflater().inflate(R.layout.progressbar_horizontal, null);
        horizontalBar.setLayoutParams(lp);
        myViews.add(new MyView(getString(R.string.progress_standard_horizontal), horizontalBar));

        //horizontalDeBar
        ProgressBar horizontalDeBar = (ProgressBar) getLayoutInflater().inflate(R.layout.progressbar_horizontal, null);
        horizontalDeBar.setLayoutParams(lp);
        horizontalDeBar.setIndeterminate(false);
        horizontalDeBar.setProgress(60);
        myViews.add(new MyView(getString(R.string.progress_standard_horizontal_de), horizontalDeBar));

        //customBar
        FrameLayout customBar = (FrameLayout) getLayoutInflater().inflate(R.layout.progressbar_custom, null);
        customBar.setLayoutParams(lp);
        myViews.add(new MyView(getString(R.string.progress_custom), customBar));
    }

    /**
     * Images
     */
    private void initImage() {
        backdropImg.setImageResource(R.drawable.img_view_imageview_header);
        viewsCoolapsingLayout.setTitle(getString(R.string.view_image));
        myViews.clear();

        //ImageView
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setImageResource(R.drawable.img_android);
        myViews.add(new MyView(getString(R.string.imageview_standard), imageView));
    }

    /**
     * Charts
     */
    private void initChart() {
        backdropImg.setImageResource(R.drawable.img_view_chart_header);
        viewsCoolapsingLayout.setTitle(getString(R.string.view_chart));
        myViews.clear();

        LinearLayout.LayoutParams chartLp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                DimensionTools.dpToPx(180, getResources()));
        int size = DimensionTools.dpToPx(16, getResources());
        chartLp.setMargins(size, size, size, size);

        //LineChart
        LineChart lineChart = new LineChart(this);
        lineChart.setLayoutParams(chartLp);
        lineChart.setDescription(getString(R.string.chart_line));

        ArrayList<Entry> valsComp1 = new ArrayList<Entry>();
        ArrayList<Entry> valsComp2 = new ArrayList<Entry>();

        Entry c1e1 = new Entry(60.000f, 0); // 0 == quarter 1
        valsComp1.add(c1e1);
        Entry c1e2 = new Entry(50.000f, 1); // 1 == quarter 2 ...
        valsComp1.add(c1e2);
        Entry c1e3 = new Entry(20.000f, 2); // 2 == quarter 3 ...
        valsComp1.add(c1e3);
        Entry c1e4 = new Entry(40.000f, 3); // 4 == quarter 4 ...
        valsComp1.add(c1e4);
        Entry c2e1 = new Entry(50.000f, 0); // 0 == quarter 1
        valsComp2.add(c2e1);
        Entry c2e2 = new Entry(30.000f, 1); // 1 == quarter 2 ...
        valsComp2.add(c2e2);
        Entry c2e3 = new Entry(40.000f, 2); // 2 == quarter 3 ...
        valsComp2.add(c2e3);
        Entry c2e4 = new Entry(10.000f, 3); // 4 == quarter 4 ...
        valsComp2.add(c2e4);

        LineDataSet setComp1 = new LineDataSet(valsComp1, "Company 1");
        setComp1.setAxisDependency(YAxis.AxisDependency.LEFT);
        setComp1.setColor(getResources().getColor(R.color.primary_color));
        setComp1.setCircleColor(getResources().getColor(R.color.primary_color));
        LineDataSet setComp2 = new LineDataSet(valsComp2, "Company 2");
        setComp2.setAxisDependency(YAxis.AxisDependency.LEFT);
        setComp2.setColor(getResources().getColor(R.color.accent_color));
        setComp2.setCircleColor(getResources().getColor(R.color.accent_color));

        ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
        dataSets.add(setComp1);
        dataSets.add(setComp2);

        ArrayList<String> lineXVals = new ArrayList<String>();
        lineXVals.add("1.Q"); lineXVals.add("2.Q"); lineXVals.add("3.Q"); lineXVals.add("4.Q");

        LineData data = new LineData(lineXVals, dataSets);

        lineChart.setData(data);
        lineChart.setDragEnabled(false);
        lineChart.setScaleEnabled(false);
        myViews.add(new MyView(getString(R.string.chart_line), lineChart));

        //PieChart
        PieChart pieChart = new PieChart(this);
        pieChart.setLayoutParams(chartLp);

        ArrayList<Entry> pieYVals1 = new ArrayList<Entry>();
        int pieCount = 3;
        float pieMult = 100;
        for (int i = 0; i < pieCount + 1; i++) {
            pieYVals1.add(new Entry((float) (Math.random() * pieMult) + pieMult / 5, i));
        }

        ArrayList<String> pieXVals = new ArrayList<String>();
        pieXVals.add("1.Q"); pieXVals.add("2.Q"); pieXVals.add("3.Q"); pieXVals.add("4.Q");

        PieDataSet pieDataSet = new PieDataSet(pieYVals1, "Election Results");
        pieDataSet.setSliceSpace(3f);
        pieDataSet.setSelectionShift(5f);

        ArrayList<Integer> colors = new ArrayList<Integer>();
        colors.add(getResources().getColor(R.color.primary_color));
        colors.add(getResources().getColor(R.color.accent_color));
        colors.add(getResources().getColor(R.color.dark_primary_color));
        colors.add(getResources().getColor(R.color.light_primary_color));

        pieDataSet.setColors(colors);
        PieData pieData = new PieData(pieXVals, pieDataSet);
        pieData.setValueFormatter(new PercentFormatter());
        pieData.setValueTextSize(8f);
        pieData.setValueTextColor(Color.WHITE);

        pieChart.setData(pieData);
        pieChart.setUsePercentValues(true);
        pieChart.setDescription(getString(R.string.chart_pie));
        pieChart.setDragDecelerationFrictionCoef(0.95f);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColorTransparent(false);
        pieChart.setTransparentCircleColor(Color.WHITE);
        pieChart.setHoleRadius(40f);
        pieChart.setTransparentCircleRadius(48f);
        pieChart.setDrawCenterText(true);
        pieChart.setCenterText("WoW!");
        pieChart.setCenterTextColor(getResources().getColor(R.color.primary_color));
        pieChart.setRotationAngle(0);
        pieChart.setRotationEnabled(true);
        pieChart.animateY(1500, Easing.EasingOption.EaseInOutQuad);
        /**
        Legend l = pieChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);
         */
        pieChart.highlightValues(null);
        myViews.add(new MyView(getString(R.string.chart_pie), pieChart));

        //BarChart
        BarChart barChart = new BarChart(this);
        barChart.setLayoutParams(chartLp);
        int barCount = 12;
        float barRange = 50;

        ArrayList<String> barXVals = new ArrayList<String>();
        for (int i = 0; i < barCount; i++) {
            barXVals.add(mMonths[i % 12]);
        }

        ArrayList<BarEntry> barYVals = new ArrayList<BarEntry>();

        for (int i = 0; i < barCount; i++) {
            float barMult = (barRange + 1);
            float val = (float) (Math.random() * barMult);
            barYVals.add(new BarEntry(val, i));
        }

        BarDataSet barDataSet = new BarDataSet(barYVals, "DataSet");
        barDataSet.setBarSpacePercent(35f);
        barDataSet.setColor(getResources().getColor(R.color.primary_color));

        ArrayList<BarDataSet> barDataSets = new ArrayList<BarDataSet>();
        barDataSets.add(barDataSet);

        BarData barData = new BarData(barXVals, barDataSets);
        barChart.setData(barData);
        barChart.setDrawValueAboveBar(true);
        barChart.setDescription("");
        // if more than 60 entries are displayed in the chart, no values will be drawn
        barChart.setMaxVisibleValueCount(60);
        // scaling can now only be done on x- and y-axis separately
        barChart.setPinchZoom(false);
        // draw shadows for each bar that show the maximum value
        barChart.setDrawBarShadow(false);
        barChart.setDrawGridBackground(true);

        XAxis barXAxis = barChart.getXAxis();
        barXAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        barXAxis.setDrawGridLines(true);
        barXAxis.setSpaceBetweenLabels(2);

        YAxis barLeftAxis = barChart.getAxisLeft();
        barLeftAxis.setLabelCount(6);
        barLeftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        barLeftAxis.setSpaceTop(15f);
        barLeftAxis.setDrawGridLines(true);

        YAxis barRightAxis = barChart.getAxisRight();
        barRightAxis.setDrawGridLines(false);
        barRightAxis.setLabelCount(6);
        barRightAxis.setSpaceTop(15f);

        /**
         Legend l = mChart.getLegend();
         l.setPosition(LegendPosition.BELOW_CHART_LEFT);
         l.setForm(LegendForm.SQUARE);
         l.setFormSize(9f);
         l.setTextSize(11f);
         l.setXEntrySpace(4f);
         */

        myViews.add(new MyView(getString(R.string.chart_bar), barChart));

    }

    /**
     * Dialogs
     */
    private EditText passwordInput;
    private View positiveAction;
    private MaterialDialog customDialog;
    static int selectedColorIndex = -1;

    private void initDialog() {
        backdropImg.setImageResource(R.drawable.img_view_dialog_header);
        viewsCoolapsingLayout.setTitle(getString(R.string.view_dialog));
        myViews.clear();

        //basicNoTitle
        Button basicNoTitle = new Button(this);
        basicNoTitle.setText(getString(R.string.dialog_basic_no_title));
        basicNoTitle.setLayoutParams(lp);
        basicNoTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialDialog materialDialog = new MaterialDialog.Builder(ViewsActivity.this)
                        .content(R.string.dialog_content_short)
                        .positiveText(R.string.dialog_confirm)
                        .negativeText(R.string.dialog_cancel).build();
                materialDialog.setCancelable(false);
                materialDialog.show();

            }
        });
        myViews.add(new MyView(getString(R.string.dialog_basic_no_title), basicNoTitle));

        //basic
        Button basic = new Button(this);
        basic.setText(getString(R.string.dialog_basic));
        basic.setLayoutParams(lp);
        basic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(ViewsActivity.this)
                        .title(R.string.dialog_title)
                        .content(R.string.dialog_content_short)
                        .positiveText(R.string.dialog_confirm)
                        .negativeText(R.string.dialog_cancel)
                        .show();
            }
        });
        myViews.add(new MyView(getString(R.string.dialog_basic), basic));

        //basicLongContent
        Button basicLongContent = new Button(this);
        basicLongContent.setLayoutParams(lp);
        basicLongContent.setText(getString(R.string.dialog_basic_long_content));
        basicLongContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(ViewsActivity.this)
                        .title(R.string.dialog_useGoogleLocationServices)
                        .content(R.string.dialog_loremIpsum)
                        .positiveText(R.string.dialog_agree)
                        .negativeText(R.string.dialog_disagree)
                        .show();
            }
        });
        myViews.add(new MyView(getString(R.string.dialog_basic_long_content), basicLongContent));

        //basicIcon
        Button basicIcon = new Button(this);
        basicIcon.setLayoutParams(lp);
        basicIcon.setText(getString(R.string.dialog_basic_icon));
        basicIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(ViewsActivity.this)
                        .iconRes(R.drawable.ic_action_android)
                        .limitIconToDefaultSize()
                        .title(R.string.dialog_title)
                        .content(R.string.dialog_content_short)
                        .positiveText(R.string.dialog_confirm)
                        .negativeText(R.string.dialog_cancel)
                        .show();
            }
        });
        myViews.add(new MyView(getString(R.string.dialog_basic_icon), basicIcon));

        //stackDialog
        Button stackDialog = new Button(this);
        stackDialog.setLayoutParams(lp);
        stackDialog.setText(getString(R.string.dialog_stack));
        stackDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(ViewsActivity.this)
                        .title(R.string.dialog_title)
                        .content(R.string.dialog_content_short)
                        .positiveText(R.string.dialog_confirm)
                        .negativeText(R.string.dialog_cancel)
                        .btnStackedGravity(GravityEnum.END)
                        .forceStacking(true)
                        .show();
            }
        });
        myViews.add(new MyView(getString(R.string.dialog_stack), stackDialog));

        //callbackDialog
        Button callbackDialog = new Button(this);
        callbackDialog.setLayoutParams(lp);
        callbackDialog.setText(getString(R.string.dialog_callback));
        callbackDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(ViewsActivity.this)
                        .title(R.string.dialog_title)
                        .content(R.string.dialog_content_short)
                        .positiveText(R.string.dialog_confirm)
                        .negativeText(R.string.dialog_cancel)
                        .neutralText(R.string.dialog_more_info)
                        .callback(new MaterialDialog.ButtonCallback() {
                            @Override
                            public void onPositive(MaterialDialog dialog) {
                                showToast("Positive!");
                            }

                            @Override
                            public void onNegative(MaterialDialog dialog) {
                                showToast("Negative...");
                            }

                            @Override
                            public void onNeutral(MaterialDialog dialog) {
                                showToast("Neutral");
                            }
                        })
                        .show();
            }
        });
        myViews.add(new MyView(getString(R.string.dialog_callback), callbackDialog));

        //listDialog
        Button listDialog = new Button(this);
        listDialog.setLayoutParams(lp);
        listDialog.setText(getString(R.string.dialog_list));
        listDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(ViewsActivity.this)
                        .title(R.string.dialog_title)
                        .items(R.array.dialog_socialNetworks)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog materialDialog, View view, int i, CharSequence charSequence) {
                                showToast(i + ": " + charSequence);
                            }
                        }).show();
            }
        });
        myViews.add(new MyView(getString(R.string.dialog_list), listDialog));

        //longListDialog
        Button longListDialog = new Button(this);
        longListDialog.setLayoutParams(lp);
        longListDialog.setText(getString(R.string.dialog_list_long));
        longListDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(ViewsActivity.this)
                        .items(R.array.dialog_states)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog materialDialog, View view, int i, CharSequence charSequence) {
                                showToast(i + ": " + charSequence);
                            }
                        })
                        .positiveText(R.string.dialog_cancel)
                        .show();
            }
        });

        myViews.add(new MyView(getString(R.string.dialog_list_long), longListDialog));

        //singleChoiceDialog
        Button singleChoiceDialog = new Button(this);
        singleChoiceDialog.setLayoutParams(lp);
        singleChoiceDialog.setText(getString(R.string.dialog_single_choice));
        singleChoiceDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(ViewsActivity.this)
                        .title(R.string.dialog_title)
                        .items(R.array.dialog_socialNetworks)
                        .itemsCallbackSingleChoice(2, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog materialDialog, View view, int i, CharSequence charSequence) {
                                showToast(i + ": " + charSequence);
                                return true;
                            }
                        })
                        .positiveText(R.string.dialog_confirm)
                        .show();
            }
        });
        myViews.add(new MyView(getString(R.string.dialog_single_choice), singleChoiceDialog));

        //multipleChoiceDialog
        Button multipleChoiceDialog = new Button(this);
        multipleChoiceDialog.setLayoutParams(lp);
        multipleChoiceDialog.setText(getString(R.string.dialog_multiple_choice));
        multipleChoiceDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(ViewsActivity.this)
                        .title(R.string.dialog_title)
                        .items(R.array.dialog_socialNetworks)
                        .itemsCallbackMultiChoice(new Integer[]{1, 3}, new MaterialDialog.ListCallbackMultiChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog materialDialog, Integer[] integers, CharSequence[] charSequences) {
                                StringBuilder str = new StringBuilder();
                                for (int i = 0; i < integers.length; i++) {
                                    if (i > 0) str.append('\n');
                                    str.append(integers[i]);
                                    str.append(": ");
                                    str.append(charSequences[i]);
                                }
                                showToast(str.toString());
                                return true;
                            }
                        })
                        .alwaysCallMultiChoiceCallback()
                        .positiveText(R.string.dialog_confirm)
                        .show();
            }
        });
        myViews.add(new MyView(getString(R.string.dialog_multiple_choice), multipleChoiceDialog));

        //simpleDialog
        Button simpleDialog = new Button(this);
        simpleDialog.setLayoutParams(lp);
        simpleDialog.setText(getString(R.string.dialog_simple_choice));

        final MaterialSimpleListAdapter adapter = new MaterialSimpleListAdapter(this);
        adapter.add(new MaterialSimpleListItem.Builder(this)
                .content("username@gmail.com")
                .icon(R.drawable.ic_action_android)
                .build());
        adapter.add(new MaterialSimpleListItem.Builder(this)
                .content("user02@gmail.com")
                .icon(R.drawable.ic_action_android)
                .build());
        adapter.add(new MaterialSimpleListItem.Builder(this)
                .content(R.string.dialog_more_info)
                .icon(R.drawable.ic_action_android)
                .build());

        simpleDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(ViewsActivity.this)
                        .title(R.string.dialog_title)
                        .adapter(adapter, new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog materialDialog, View view, int i, CharSequence charSequence) {
                                MaterialSimpleListItem item = adapter.getItem(i);
                                showToast(item.getContent().toString());
                            }
                        })
                        .show();
            }
        });
        myViews.add(new MyView(getString(R.string.dialog_simple_choice), simpleDialog));

        //customViewDialog
        Button customViewDialog = new Button(this);
        customViewDialog.setLayoutParams(lp);
        customViewDialog.setText(getString(R.string.dialog_custom_view));
        customViewDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog = new MaterialDialog.Builder(ViewsActivity.this)
                        .title(R.string.googleWifi)
                        .customView(R.layout.dialog_customview, true)
                        .positiveText(R.string.connect)
                        .negativeText(android.R.string.cancel)
                        .callback(new MaterialDialog.ButtonCallback() {
                            @Override
                            public void onPositive(MaterialDialog dialog) {
                                showToast("Password: " + passwordInput.getText().toString());
                            }

                            @Override
                            public void onNegative(MaterialDialog dialog) {

                            }
                        }).build();
                positiveAction = customDialog.getActionButton(DialogAction.POSITIVE);
                passwordInput = (EditText) customDialog.getCustomView().findViewById(R.id.password);
                passwordInput.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        positiveAction.setEnabled(s.toString().trim().length() > 0);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                });

                // Toggling the show password CheckBox will mask or unmask the password input EditText
                CheckBox checkbox = (CheckBox) customDialog.getCustomView().findViewById(R.id.showPassword);
                checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        passwordInput.setInputType(!isChecked ? InputType.TYPE_TEXT_VARIATION_PASSWORD : InputType.TYPE_CLASS_TEXT);
                        passwordInput.setTransformationMethod(!isChecked ? PasswordTransformationMethod.getInstance() : null);
                    }
                });

                int widgetColor = ThemeSingleton.get().widgetColor;
                MDTintHelper.setTint(checkbox,
                        widgetColor == 0 ? getResources().getColor(R.color.accent_color) : widgetColor);

                MDTintHelper.setTint(passwordInput,
                        widgetColor == 0 ? getResources().getColor(R.color.accent_color) : widgetColor);

                customDialog.show();
                positiveAction.setEnabled(false); // disabled by default
            }
        });

        myViews.add(new MyView(getString(R.string.dialog_custom_view), customViewDialog));

        //customWebViewDialog
        Button customWebViewDialog = new Button(this);
        customWebViewDialog.setLayoutParams(lp);
        customWebViewDialog.setText(getString(R.string.dialog_custom_webview));
        customWebViewDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangelogDialog.create(false, getResources().getColor(R.color.accent_color))
                        .show(getSupportFragmentManager(), "changelog");
            }
        });
        myViews.add(new MyView(getString(R.string.dialog_custom_webview), customWebViewDialog));

        //customColorChooserDialog
        Button customColorChooserDialog = new Button(this);
        customColorChooserDialog.setLayoutParams(lp);
        customColorChooserDialog.setText(getString(R.string.dialog_custom_color_chooser));
        customColorChooserDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ColorChooserDialog().show(ViewsActivity.this, selectedColorIndex);
            }
        });
        myViews.add(new MyView(getString(R.string.dialog_custom_color_chooser), customColorChooserDialog));

        //themeDialog
        Button themeDialog = new Button(this);
        themeDialog.setLayoutParams(lp);
        themeDialog.setText(getString(R.string.dialog_theme));
        themeDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(ViewsActivity.this)
                        .title(R.string.dialog_title)
                        .content(R.string.dialog_content_short)
                        .positiveText(R.string.dialog_confirm)
                        .negativeText(R.string.dialog_cancel)
                        .titleGravity(GravityEnum.CENTER)
                        .titleColorRes(R.color.white)
                        .contentColorRes(android.R.color.white)
                        .backgroundColorRes(R.color.material_blue_grey_800)
                        .positiveColor(Color.WHITE)
                        .negativeColorAttr(android.R.attr.textColorSecondaryInverse)
                        .theme(Theme.DARK)
                        .show();
            }
        });
        myViews.add(new MyView(getString(R.string.dialog_theme), themeDialog));

        //inputDialog
        Button inputDialog = new Button(this);
        inputDialog.setLayoutParams(lp);
        inputDialog.setText(getString(R.string.dialog_input));
        inputDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(ViewsActivity.this)
                        .title(R.string.dialog_title)
                        .content(R.string.input_content)
                        .inputType(InputType.TYPE_CLASS_TEXT |
                                InputType.TYPE_TEXT_VARIATION_PERSON_NAME |
                                InputType.TYPE_TEXT_FLAG_CAP_WORDS)
                        .inputMaxLength(16)
                        .positiveText(R.string.submit)
                        .input(R.string.input_hint, R.string.input_hint, false, new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(MaterialDialog dialog, CharSequence input) {
                                showToast("Hello, " + input.toString() + "!");
                            }
                        }).show();
            }
        });
        myViews.add(new MyView(getString(R.string.dialog_input), inputDialog));

        //indeterminateCircleDialog
        Button indeterminateCircleDialog = new Button(this);
        indeterminateCircleDialog.setLayoutParams(lp);
        indeterminateCircleDialog.setText(getString(R.string.dialog_progress_indeterminate_circle));
        indeterminateCircleDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(ViewsActivity.this)
                        .title(R.string.progress_dialog)
                        .content(R.string.please_wait)
                        .progress(true, 0)
                        .progressIndeterminateStyle(false)
                        .show();
            }
        });
        myViews.add(new MyView(getString(R.string.dialog_progress_indeterminate_circle), indeterminateCircleDialog));

        //indeterminateHorizontalDialog
        Button indeterminateHorizontalDialog = new Button(this);
        indeterminateHorizontalDialog.setLayoutParams(lp);
        indeterminateHorizontalDialog.setText(getString(R.string.dialog_progress_indeterminate_horizontal));
        indeterminateHorizontalDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(ViewsActivity.this)
                        .title(R.string.progress_dialog)
                        .content(R.string.please_wait)
                        .progress(true, 0)
                        .progressIndeterminateStyle(true)
                        .show();
            }
        });
        myViews.add(new MyView(getString(R.string.dialog_progress_indeterminate_horizontal), indeterminateHorizontalDialog));

    }

    @Override
    public void onColorSelection(int index, int color, int darker) {
        selectedColorIndex = index;
        //noinspection ConstantConditions
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(color));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(darker);
            getWindow().setNavigationBarColor(color);
        }
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

    private void showToast(String message) {
        if (mToast != null) {
            mToast.cancel();
            mToast = null;
        }
        mToast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        mToast.show();
    }

    private void showToast(@StringRes int message) {
        showToast(getString(message));
    }
}

