package com.wangenyong.weytest.fragments;


import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wangenyong.mylibrary.views.CircleButtonLayout;
import com.wangenyong.weytest.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CircleMenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CircleMenuFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Toast mToast;

    //@InjectView(R.id.layout_circle_menu) CircleMenuLayout circleMenuLayout;
    @InjectView(R.id.layout_circle_button) CircleButtonLayout circleButtonLayout;

    private String[] mItemTexts = new String[] {"添加", "备份", "通知", "定位", "播放"};
    private int[] mItemImgs = new int[] {R.drawable.ic_circle_menu_add,
            R.drawable.ic_circle_menu_backup, R.drawable.ic_circle_menu_notifications_on,
            R.drawable.ic_circle_menu_location, R.drawable.ic_circle_menu_play};


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CircleMenuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CircleMenuFragment newInstance(String param1, String param2) {
        CircleMenuFragment fragment = new CircleMenuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public CircleMenuFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_circle_menu, container, false);
        ButterKnife.inject(this, rootView);

        //circleMenuLayout.setMenuItemIconsAndTexts(mItemImgs, mItemTexts);
        circleButtonLayout.setmItemImgs(mItemImgs);
        circleButtonLayout.setOnMenuItemClickListener(new CircleButtonLayout.OnMenuItemClickListener() {
            @Override
            public void itemClick(View view, int pos) {
                showToast(String.valueOf(pos));
            }

            @Override
            public void itemCenterClick(View view) {
                showToast(R.string.animation_circle_menu);
            }
        });


        return rootView;
    }


    private void showToast(String message) {
        if (mToast != null) {
            mToast.cancel();
            mToast = null;
        }
        mToast = Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT);
        mToast.show();
    }

    private void showToast(@StringRes int message) {
        showToast(getString(message));
    }

}
