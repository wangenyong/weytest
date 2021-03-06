package com.wangenyong.weytest.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wangenyong.weytest.R;
import com.wangenyong.weytest.adapters.ThirdPartyAdapter;
import com.wangenyong.weytest.bean.ImgTxtItem;
import com.wangenyong.weytest.bean.Item;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThirdPartyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThirdPartyFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @Bind(R.id.recyclerview_third_party) RecyclerView thirdPartyRecyclerView;
    private ThirdPartyAdapter thirdPartyAdapter;
    private List<Item> items = new ArrayList<Item>();

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ThirdPartyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ThirdPartyFragment newInstance(String param1, String param2) {
        ThirdPartyFragment fragment = new ThirdPartyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ThirdPartyFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_third_party, container, false);
        ButterKnife.bind(this, rootView);

        items.add(new ImgTxtItem(R.drawable.img_third_party_fab, "com.getbase.floatingactionbutton.sample", getString(R.string.third_party_fab)));
        items.add(new ImgTxtItem(R.drawable.img_third_party_mpchart, "com.xxmassdeveloper.mpchartexample", getString(R.string.third_party_mpchart)));
        items.add(new ImgTxtItem(R.drawable.img_third_party_progressbar, "fr.castorflex.android.smoothprogressbar.sample", getString(R.string.third_party_progressbar)));
        items.add(new ImgTxtItem(R.drawable.img_third_party_wave_swipe, "jp.co.recruit_lifestyle.sample", getString(R.string.third_party_wave_swipe)));
        items.add(new ImgTxtItem(R.drawable.img_third_party_edittext, "com.github.florent37.materialtextfield.sample", getString(R.string.third_party_editext)));
        items.add(new ImgTxtItem(R.drawable.img_third_party_span, "com.flavienlaurent.spanimated", getString(R.string.third_party_span)));
        thirdPartyAdapter = new ThirdPartyAdapter(getActivity(), items);
        thirdPartyRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        thirdPartyRecyclerView.setAdapter(thirdPartyAdapter);
        thirdPartyRecyclerView.setItemAnimator(new DefaultItemAnimator());

        return rootView;
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
