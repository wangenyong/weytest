package com.wangenyong.weytest.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import com.wangenyong.mylibrary.views.BlurringView;
import com.wangenyong.weytest.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class GuideActivity extends AppCompatActivity {
    @InjectView(R.id.blurredview_guideview) RelativeLayout blurredView;
    @InjectView(R.id.blurringView_guideview) BlurringView blurringView;
    @InjectView(R.id.viewpager_guideview) ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.inject(this);
    }

}
