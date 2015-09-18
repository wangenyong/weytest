package com.wangenyong.weytest.activities;

import android.content.Intent;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.percent.PercentRelativeLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.wangenyong.mylibrary.tools.DimensionTools;
import com.wangenyong.mylibrary.views.BlurringView;
import com.wangenyong.mylibrary.views.CircularImageView;
import com.wangenyong.weytest.MainActivity;
import com.wangenyong.weytest.R;
import com.wangenyong.weytest.fragments.GuideFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class GuideActivity extends AppCompatActivity {
    @InjectView(R.id.blurredview_guideview) PercentRelativeLayout blurredView;
    @InjectView(R.id.blurringView_guideview) BlurringView blurringView;
    @InjectView(R.id.viewpager_guideview) ViewPager viewPager;
    @InjectView(R.id.header_guideview) CircularImageView headerImg;
    @InjectView(R.id.tv_say_guideview) TextView sayTv;
    @InjectView(R.id.img_like1_guideview) ImageView like1Img;
    @InjectView(R.id.img_like2_guideview) ImageView like2Img;
    @InjectView(R.id.img_like3_guideview) ImageView like3Img;
    @InjectView(R.id.img_like4_guideview) ImageView like4Img;
    @InjectView(R.id.img_like5_guideview) ImageView like5Img;
    @InjectView(R.id.img_logo_guideview) ImageView logoImg;
    @InjectView(R.id.tv_logo_name_guideview) TextView nameTv;
    @InjectView(R.id.btn_go_guideview) Button goBtn;

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

        goBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuideActivity.this, MainActivity.class);
                startActivity(intent);
                GuideActivity.this.finish();
            }
        });

        blurringView.setBlurredView(blurredView);

        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/classicxing.ttf");
        sayTv.setTypeface(typeFace);
        nameTv.setTypeface(typeFace);

        //创建adapter
        GuideAdapter adapter = new GuideAdapter(getSupportFragmentManager());
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(adapter);
        viewPager.setPageTransformer(true, new HKTransformer());


    }

    private void setFullscreen() {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
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
                    headerImg.setTranslationY(-DimensionTools.dpToPx(64+128, getResources()) * f);
                    sayTv.setTranslationY((screenHeight / 2 + sayTv.getHeight()) * f);

                    like1Img.setTranslationX(-screenWidth / 2 * p);
                    like2Img.setTranslationX(screenWidth/2 * p);
                    like3Img.setTranslationX(-screenWidth/2 * p);
                    like4Img.setTranslationX(screenWidth/2 * p);
                    like5Img.setTranslationX(-screenWidth / 2 * p);

                    like1Img.setAlpha(f);
                    like2Img.setAlpha(f);
                    like3Img.setAlpha(f);
                    like4Img.setAlpha(f);
                    like5Img.setAlpha(f);

                    logoImg.setAlpha(0f);
                    nameTv.setAlpha(0f);
                    goBtn.setAlpha(0f);

                } else if (-2 < currentPosition && currentPosition <= -1) {
                    logoImg.setAlpha(f);
                    nameTv.setAlpha(f);
                    goBtn.setAlpha(f);

                    if (f > 0.5) {
                        goBtn.setEnabled(true);
                    } else {
                        goBtn.setEnabled(false);
                    }

                    like1Img.setTranslationX(screenWidth / 2 * f);
                    like2Img.setTranslationX(-screenWidth/2 * f);
                    like3Img.setTranslationX(screenWidth/2 * f);
                    like4Img.setTranslationX(-screenWidth/2 * f);
                    like5Img.setTranslationX(-screenWidth / 2 * f);

                    like1Img.setAlpha(p);
                    like2Img.setAlpha(p);
                    like3Img.setAlpha(p);
                    like4Img.setAlpha(p);
                    like5Img.setAlpha(p);
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
