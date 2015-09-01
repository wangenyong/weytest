package com.wangenyong.weytest.fragments;


import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.wangenyong.weytest.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AnimationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AnimationFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @InjectView(R.id.tabs_animation) PagerSlidingTabStrip animationTabs;
    @InjectView(R.id.viewpager_animation)
    ViewPager animationViewPager;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AnimationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AnimationFragment newInstance(String param1, String param2) {
        AnimationFragment fragment = new AnimationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public AnimationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_animation, container, false);
        ButterKnife.inject(this, rootView);
        setupViewPager(animationViewPager);
        animationTabs.setViewPager(animationViewPager);
        return rootView;
    }


    private void setupViewPager(ViewPager viewPager) {
        AnimationAdapter adapter = new AnimationAdapter(getChildFragmentManager());
        adapter.addFragment(CircleMenuFragment.newInstance("", ""), getString(R.string.animation_circle_menu));
        adapter.addFragment(TestFragment.newInstance("Animation"), "Animation");
        viewPager.setAdapter(adapter);
    }


    static class AnimationAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public AnimationAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}
