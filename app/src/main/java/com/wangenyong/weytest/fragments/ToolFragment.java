package com.wangenyong.weytest.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.simplelist.MaterialSimpleListAdapter;
import com.afollestad.materialdialogs.simplelist.MaterialSimpleListItem;
import com.wangenyong.mylibrary.zxing.Intents;
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
public class ToolFragment extends Fragment implements ToolsAdapter.OnItemClickLitener {
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
        toolsAdapter.setOnItemClickLitener(this);
        toolRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        toolRecyclerView.setAdapter(toolsAdapter);
        toolRecyclerView.setItemAnimator(new DefaultItemAnimator());


        return rootView;
    }


    protected void initData() {
        myTools.clear();
        myTools.add(new MyTool(R.drawable.img_tools_scanner, getString(R.string.tools_qrcode), MyTool.Types.QRCODE));
        myTools.add(new MyTool(R.drawable.img_tools_photo, getString(R.string.tools_photo), MyTool.Types.PHOTO));
        myTools.add(new MyTool(R.drawable.img_tools_date, getString(R.string.tools_date), MyTool.Types.DATE));
        myTools.add(new MyTool(R.drawable.img_tools_shake, getString(R.string.tools_shake), MyTool.Types.SHAKE));
        myTools.add(new MyTool(R.drawable.img_tools_map, getString(R.string.tools_map), MyTool.Types.MAP));
        myTools.add(new MyTool(R.drawable.img_tools_gps, getString(R.string.tools_gps), MyTool.Types.GPS));
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (myTools.get(position).getToolTypes()) {
            case QRCODE:
                Intent intent = new Intent(Intents.Scan.ACTION);
                intent.putExtra(Intents.Scan.RESULT_DISPLAY_DURATION_MS, 0L);
                startActivityForResult(intent, 100);
                break;
            case PHOTO:
                final MaterialSimpleListAdapter adapter = new MaterialSimpleListAdapter(getActivity());
                adapter.add(new MaterialSimpleListItem.Builder(getActivity())
                        .content(getString(R.string.tools_photo_dialog_camera))
                        .icon(R.drawable.ic_action_photo_camera)
                        .build());
                adapter.add(new MaterialSimpleListItem.Builder(getActivity())
                        .content(getString(R.string.tools_photo_dialog_album))
                        .icon(R.drawable.ic_action_photo_album)
                        .build());
                new MaterialDialog.Builder(getActivity())
                    .adapter(adapter, new MaterialDialog.ListCallback() {
                        @Override
                        public void onSelection(MaterialDialog materialDialog, View view, int i, CharSequence charSequence) {
                            MaterialSimpleListItem item = adapter.getItem(i);
                            Toast.makeText(getActivity(), String.valueOf(i), Toast.LENGTH_SHORT).show();
                            materialDialog.dismiss();
                        }
                    })
                    .negativeText(getString(R.string.dialog_cancel))
                    .show();
                break;
            default:
                Toast.makeText(getActivity(), getString(R.string.toools_developing), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemLongClick(View view, int position) {

    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        // TODO Auto-generated method stub
        /**
         Log.d("QRCode Result", "requestCode: " + String.valueOf(requestCode));
         Log.d("QRCode Result", "resultCode: " + String.valueOf(resultCode));
         Log.d("QRCode Result", "intentAction: " + intent.getAction());
         Log.d("QRCode Result", "scanResult: " + intent.getStringExtra(Intents.Scan.RESULT));
         */
        if (intent != null) {
            Toast.makeText(getActivity(), intent.getStringExtra(Intents.Scan.RESULT), Toast.LENGTH_SHORT).show();
        }
    }
}
