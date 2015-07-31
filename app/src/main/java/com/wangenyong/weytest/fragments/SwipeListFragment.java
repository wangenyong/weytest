package com.wangenyong.weytest.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wangenyong.weytest.R;
import com.wangenyong.weytest.adapters.SwipeListAdapter;
import com.wangenyong.weytest.bean.SwipeListItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SwipeListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SwipeListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @InjectView(R.id.recyclerview_swipe_list) RecyclerView swipListRecyclerview;
    private RecyclerView.LayoutManager mLayoutManager;
    private SwipeListAdapter swipeListAdapter;
    private List<SwipeListItem> swipeListItems = new ArrayList<SwipeListItem>();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SwipeListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SwipeListFragment newInstance(String param1, String param2) {
        SwipeListFragment fragment = new SwipeListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public SwipeListFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_swipe_list, container, false);
        ButterKnife.inject(this, rootView);

        initSwipeList();

        mLayoutManager = new LinearLayoutManager(getActivity());
        swipListRecyclerview.setLayoutManager(mLayoutManager);
        swipeListAdapter = new SwipeListAdapter(getActivity(), swipeListItems);
        swipListRecyclerview.setAdapter(swipeListAdapter);

        return rootView;
    }

    private void initSwipeList() {
        swipeListItems.clear();

        swipeListItems.add(new SwipeListItem(R.drawable.img_shake_meili));
        swipeListItems.add(new SwipeListItem(R.drawable.img_shake_guaishulin));
        swipeListItems.add(new SwipeListItem(R.drawable.img_shake_sun));
        swipeListItems.add(new SwipeListItem(R.drawable.img_shake_haiou));
        swipeListItems.add(new SwipeListItem(R.drawable.img_shake_huyang));



    }

}
