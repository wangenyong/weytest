package com.wangenyong.weytest.activities;

import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wangenyong.mylibrary.tools.DimensionTools;
import com.wangenyong.mylibrary.views.BlurringView;
import com.wangenyong.mylibrary.views.CircularImageView;
import com.wangenyong.weytest.R;
import com.wangenyong.weytest.fragments.GuideFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class GuideActivity extends AppCompatActivity {
    @InjectView(R.id.blurredview_guideview) RelativeLayout blurredView;
    @InjectView(R.id.blurringView_guideview) BlurringView blurringView;
    @InjectView(R.id.viewpager_guideview) ViewPager viewPager;
    @InjectView(R.id.header_guideview) CircularImageView headerImg;
    @InjectView(R.id.tv_say_guideview) TextView sayTv;

    private List<Fragment> fragmentList = new ArrayList<>();
    private GuideFragment fragment00, fragment01, fragment02;
    private float currentPosition = 0;
    private int screenWidth;
    private int screenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_guide);

        ButterKnife.inject(this);

        determineScreenSize();

        fragment00 = GuideFragment.newInstance(getString(R.string.guideview_self_title), getString(R.string.guideview_self_content));
        fragment01 = GuideFragment.newInstance(getString(R.string.guideview_like_title), getString(R.string.guideview_like_content));
        fragment02 = GuideFragment.newInstance(getString(R.string.guideview_this_title), getString(R.string.guideview_this_content));

        fragmentList.add(fragment00);
        fragmentList.add(fragment01);
        fragmentList.add(fragment02);

        blurringView.setBlurredView(blurredView);

        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/classicxing.ttf");
        sayTv.setTypeface(typeFace);

        //创建adapter
        GuideAdapter adapter = new GuideAdapter(getSupportFragmentManager());
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(adapter);
        viewPager.setPageTransformer(true, new HKTransformer());
    }

    class GuideAdapter extends FragmentPagerAdapter {
        public GuideAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }

    class HKTransformer implements ViewPager.PageTransformer {
        @Override
        public void transformPage(View view, float position) {
            if (fragment00.getView() == view) {
                currentPosition = position;
            }

            blurringView.invalidate();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when moving to the left page
            } else if (position <= 1) { // (0,1]
                // Fade the page out.
                float p = Math.abs(position);
                float f = (1 - p);
                if (-1 < currentPosition && currentPosition <= 0) {
                    headerImg.setTranslationY(-DimensionTools.dpToPx(64+120, getResources()) * f);
                    sayTv.setTranslationY((screenHeight/2 + sayTv.getHeight()) * f);
                } else if (-2 < currentPosition && currentPosition <= -1) {

                }
            } else {// (1,+Infinity]
                // This page is way off-screen to the right.
            }
        }
    }

    private void determineScreenSize() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
    }
}
