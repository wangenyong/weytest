package com.wangenyong.weytest.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wangenyong.mylibrary.decorators.DividerGridItemDecoration;
import com.wangenyong.weytest.R;
import com.wangenyong.weytest.adapters.MainAdapter;
import com.wangenyong.weytest.bean.Component;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @InjectView(R.id.recyclerview_home) RecyclerView homeRecyclerView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private List<Component> components = new ArrayList<Component>();


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public MainFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.inject(this, rootView);


        initComponents();
        homeRecyclerView.setHasFixedSize(true);
        homeRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        MainAdapter mainAdapter = new MainAdapter(getActivity(), components);
        homeRecyclerView.addItemDecoration(new DividerGridItemDecoration(getActivity()));
        homeRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mainAdapter.setOnItemClickLitener(new MainAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getActivity(), position + " click",
                        Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(getActivity(), position + " long click",
                        Toast.LENGTH_SHORT).show();
            }
        });
        homeRecyclerView.setAdapter(mainAdapter);

        return rootView;
    }


    private void initComponents() {
        components.clear();
        components.add(new Component(R.drawable.ic_view_button, getString(R.string.view_button),
                getResources().getColor(R.color.accent_color), Component.BUTTON));
        components.add(new Component(R.drawable.ic_view_edittext, getString(R.string.view_edittext),
                getResources().getColor(R.color.accent_color), Component.EDITTEXT));
        components.add(new Component(R.drawable.ic_view_progress, getString(R.string.view_progress),
                getResources().getColor(R.color.accent_color), Component.PROGRESS));
        components.add(new Component(R.drawable.ic_view_imageview, getString(R.string.view_image),
                getResources().getColor(R.color.accent_color), Component.IMAGE));
        components.add(new Component(R.drawable.ic_view_chart, getString(R.string.view_chart),
                getResources().getColor(R.color.accent_color), Component.CHART));
        components.add(new Component(R.drawable.ic_view_dialog, getString(R.string.view_dialog),
                getResources().getColor(R.color.accent_color), Component.DIALOG));
    }

}
