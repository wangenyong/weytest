package com.wangenyong.weytest.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wangenyong.weytest.R;
import com.wangenyong.weytest.adapters.ToolsAdapter;
import com.wangenyong.weytest.bean.MyTool;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ToolFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ToolFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @InjectView(R.id.recyclerview_tool) RecyclerView toolRecyclerView;
    private ToolsAdapter toolsAdapter;
    private List<MyTool> myTools = new ArrayList<MyTool>();
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ToolFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ToolFragment newInstance(String param1, String param2) {
        ToolFragment fragment = new ToolFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ToolFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_tool, container, false);
        ButterKnife.inject(this, rootView);

        initData();
        toolsAdapter = new ToolsAdapter(getActivity(), myTools);
        toolRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        toolRecyclerView.setAdapter(toolsAdapter);
        toolRecyclerView.setItemAnimator(new DefaultItemAnimator());

        return rootView;
    }


    protected void initData() {
        myTools.clear();
        myTools.add(new MyTool(R.drawable.img_tools_scanner, getString(R.string.tools_qrcode)));
        myTools.add(new MyTool(R.drawable.img_tools_photo, getString(R.string.tools_photo)));
        myTools.add(new MyTool(R.drawable.img_tools_date, getString(R.string.tools_date)));
        myTools.add(new MyTool(R.drawable.img_tools_shake, getString(R.string.tools_shake)));
        myTools.add(new MyTool(R.drawable.img_tools_map, getString(R.string.tools_map)));
        myTools.add(new MyTool(R.drawable.img_tools_gps, getString(R.string.tools_gps)));
    }
}
