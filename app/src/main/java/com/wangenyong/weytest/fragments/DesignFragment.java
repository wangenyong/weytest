package com.wangenyong.weytest.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wangenyong.mylibrary.itemtouchhelper.SimpleItemTouchHelperCallback;
import com.wangenyong.weytest.R;
import com.wangenyong.weytest.adapters.DesignAdapter;
import com.wangenyong.weytest.bean.ImgIconItem;
import com.wangenyong.weytest.bean.ImgTxtItem;
import com.wangenyong.weytest.bean.Item;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

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

    @Bind(R.id.recyclerview_design) RecyclerView designRecyclerView;
    private DesignAdapter designAdapter;
    private List<Item> myDesigns = new ArrayList<Item>();
    private RecyclerView.LayoutManager mLayoutManager;

    private ItemTouchHelper mItemTouchHelper;

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
        ButterKnife.bind(this, rootView);

        myDesigns.clear();

        myDesigns.add(new ImgTxtItem(R.drawable.img_design_slidemenu, "com.wangenyong.slidemenu", "SlideMenu", 2));
        myDesigns.add(new ImgTxtItem(R.drawable.img_design_list_drag_swipe, "com.wangenyong.listdragswipe", "List", 2));
        myDesigns.add(new ImgTxtItem(R.drawable.img_design_menu_profile, "com.wangenyong.menuprofile", "MenuProfile", 2));
        myDesigns.add(new ImgTxtItem(R.drawable.img_design_textinputlayout, "com.wangenyong.textinputlayoutdemo", "Login", 2));
        myDesigns.add(new ImgIconItem(R.drawable.img_design_wallet, "com.wangenyong.wallet", 4));


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

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(designAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(designRecyclerView);

        return rootView;
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
