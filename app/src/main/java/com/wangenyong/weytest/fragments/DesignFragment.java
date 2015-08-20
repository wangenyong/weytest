package com.wangenyong.weytest.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wangenyong.weytest.R;
import com.wangenyong.weytest.adapters.DesignAdapter;
import com.wangenyong.weytest.bean.Item;
import com.wangenyong.weytest.bean.MyDesign;
import com.wangenyong.weytest.bean.ThirdPartyItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DesignFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DesignFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @InjectView(R.id.recyclerview_design) RecyclerView designRecyclerView;
    private DesignAdapter designAdapter;
    private List<Item> myDesigns = new ArrayList<Item>();
    private RecyclerView.LayoutManager mLayoutManager;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DesignFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DesignFragment newInstance(String param1, String param2) {
        DesignFragment fragment = new DesignFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public DesignFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_design, container, false);
        ButterKnife.inject(this, rootView);

        myDesigns.clear();
        myDesigns.add(new MyDesign(R.drawable.img_design_wallet, "com.wangenyong.wallet", 4));
        myDesigns.add(new ThirdPartyItem(R.drawable.img_design_slidemenu, "com.wangenyong.slidemenu", "SlideMenu" , 2));

        final GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 4);
        //mLayoutManager = new LinearLayoutManager(getActivity());

        designRecyclerView.setLayoutManager(gridLayoutManager);
        designAdapter = new DesignAdapter(getActivity(), myDesigns);
        designRecyclerView.setAdapter(designAdapter);

        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return designAdapter.getSpanCount(position);
            }
        });

        return rootView;
    }


}
