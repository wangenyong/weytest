package com.wangenyong.weytest.activities;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wangenyong.mylibrary.views.BlurringView;
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
    @InjectView(R.id.tv_say_guideview) TextView sayTv;

    private List<Fragment> fragmentList = new ArrayList<>();
    private GuideFragment fragment00, fragment01, fragment02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        ButterKnife.inject(this);

        fragment00 = GuideFragment.newInstance(getString(R.string.guideview_self_title), getString(R.string.guideview_self_content));
        fragment01 = GuideFragment.newInstance(getString(R.string.guideview_like_title), getString(R.string.guideview_like_content));
        fragment02 = GuideFragment.newInstance(getString(R.string.guideview_this_title), getString(R.string.guideview_this_content));

        fragmentList.add(fragment00);
        fragmentList.add(fragment01);
        fragmentList.add(fragment02);

        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/classicxing.ttf");
        sayTv.setTypeface(typeFace);

        //创建adapter
        GuideAdapter adapter = new GuideAdapter(getSupportFragmentManager());
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(adapter);

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
}
