package com.wangenyong.weytest.fragments;


import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Toast;

import com.wangenyong.weytest.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PopButtonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PopButtonFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @InjectView(R.id.fab_anim_pop_up) FloatingActionButton popUpFab;
    @InjectView(R.id.fab_anim_pop_up_one) FloatingActionButton popUpOneFab;
    @InjectView(R.id.fab_anim_pop_up_two) FloatingActionButton popUpTwoFab;
    @InjectView(R.id.fab_anim_pop_up_three) FloatingActionButton popUpThreeFab;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private boolean isPopUp = false;
    private Toast mToast;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PopButtonFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PopButtonFragment newInstance(String param1, String param2) {
        PopButtonFragment fragment = new PopButtonFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public PopButtonFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_pop_button, container, false);
        ButterKnife.inject(this, rootView);

        popUpFab.setOnClickListener(this);
        popUpOneFab.setOnClickListener(this);
        popUpTwoFab.setOnClickListener(this);
        popUpThreeFab.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_anim_pop_up:
                if (isPopUp)
                    popUpBack();
                else popUp();
                isPopUp = !isPopUp;
                break;
            case R.id.fab_anim_pop_up_one:
                showToast("Pop One");
                break;
            case R.id.fab_anim_pop_up_two:
                showToast("Pop Two");
                break;
            case R.id.fab_anim_pop_up_three:
                showToast("Pop Three");
                break;
            default:
        }
    }

    private void popUp() {
        popUpOneFab.animate()
                .translationY(-600f)
                .rotation(720f)
                .setInterpolator(new DecelerateInterpolator())
                .setDuration(500)
                .alpha(1f)
                .start();

        popUpTwoFab.animate()
                .translationY(-400f)
                .rotation(720f)
                .setInterpolator(new DecelerateInterpolator())
                .setDuration(500)
                .alpha(1f)
                .start();

        popUpThreeFab.animate()
                .translationY(-200f)
                .rotation(720f)
                .setInterpolator(new DecelerateInterpolator())
                .setDuration(500)
                .alpha(1f)
                .start();
    }

    private void popUpBack() {
        popUpOneFab.animate()
                .translationY(0f)
                .rotation(0f)
                .setInterpolator(new DecelerateInterpolator())
                .setDuration(500)
                .alpha(0f)
                .start();

        popUpTwoFab.animate()
                .translationY(0f)
                .rotation(0f)
                .setInterpolator(new DecelerateInterpolator())
                .setDuration(500)
                .alpha(0f)
                .start();

        popUpThreeFab.animate()
                .translationY(0f)
                .rotation(0f)
                .setInterpolator(new DecelerateInterpolator())
                .setDuration(500)
                .alpha(0f)
                .start();
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
