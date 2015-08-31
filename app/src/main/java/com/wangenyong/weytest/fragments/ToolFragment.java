package com.wangenyong.weytest.fragments;


import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v13.app.FragmentCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.simplelist.MaterialSimpleListAdapter;
import com.afollestad.materialdialogs.simplelist.MaterialSimpleListItem;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.wangenyong.mylibrary.itemtouchhelper.SimpleItemTouchHelperCallback;
import com.wangenyong.mylibrary.tools.PhotoTools;
import com.wangenyong.mylibrary.zxing.Intents;
import com.wangenyong.weytest.R;
import com.wangenyong.weytest.activities.GpsActivity;
import com.wangenyong.weytest.activities.MapActivity;
import com.wangenyong.weytest.activities.ShakeActivity;
import com.wangenyong.weytest.adapters.ToolsAdapter;
import com.wangenyong.weytest.bean.MyTool;
import com.wangenyong.weytest.dialogs.ImageViewDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    final private int REQUEST_CODE_TAKE_PHOTO_PERMISSIONS = 123;
    final private int REQUEST_CODE_QR_PERMISSIONS = 124;

    @InjectView(R.id.recyclerview_tool) RecyclerView toolRecyclerView;
    private ToolsAdapter toolsAdapter;
    private List<MyTool> myTools = new ArrayList<MyTool>();
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String mCameraPhotoPath;
    private final static int CAMERA_CODE = 20;
    private final static int ALBUM_CODE = 21;
    private final static int QR_CODE = 22;

    private ItemTouchHelper mItemTouchHelper;
    private PhotoTools photoTools;


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

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(toolsAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(toolRecyclerView);

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

    private MaterialCalendarView materialCalendarView;
    @Override
    public void onItemClick(View view, MyTool.Types type) {
        switch (type) {
            case QRCODE:
                startQRCodeScan();
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
                            photoTools = new PhotoTools(getString(R.string.tools_photo_dir), getString(R.string.tools_photo_album), "jpg");
                            if (i == 0) {
                                takePhotoFromCamera();
                            } else if (i == 1) {
                                photoTools.takeAlbumPhotos(ToolFragment.this, ALBUM_CODE);
                            }
                            materialDialog.dismiss();
                        }
                    })
                    .negativeText(getString(R.string.dialog_cancel))
                    .show();
                break;
            case DATE:
                MaterialDialog materialDialog = new MaterialDialog.Builder(getActivity())
                        .customView(R.layout.dialog_date_picker, true)
                        .positiveText(R.string.dialog_confirm)
                        .callback(new MaterialDialog.ButtonCallback() {
                            @Override
                            public void onPositive(MaterialDialog dialog) {
                                if (materialCalendarView.getSelectedDate() != null) {
                                    Toast.makeText(getActivity(), materialCalendarView.getSelectedDate().toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .build();
                materialCalendarView = (MaterialCalendarView) materialDialog.getCustomView().findViewById(R.id.calendarView_dialog);
                materialDialog.show();
                break;
            case SHAKE:
                Intent shakeIntent = new Intent(getActivity(), ShakeActivity.class);
                startActivity(shakeIntent);
                break;
            case MAP:
                Intent mapIntent = new Intent(getActivity(), MapActivity.class);
                startActivity(mapIntent);
                break;
            case GPS:
                Intent gpsIntent = new Intent(getActivity(), GpsActivity.class);
                startActivity(gpsIntent);
                break;
            default:
                Toast.makeText(getActivity(), getString(R.string.toools_developing), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemLongClick(View view, int position) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case CAMERA_CODE:
                if (resultCode == Activity.RESULT_OK && mCameraPhotoPath != null) {
                    ImageViewDialog.create(mCameraPhotoPath).show(getFragmentManager(), "camera_photo");
                }
                break;
            case ALBUM_CODE:
                if (resultCode == Activity.RESULT_OK && intent != null) {
                    //获取图片的Uri
                    Uri selectedImage = intent.getData();
                    //获取图片路径
                    String[] filePathColumn = { MediaStore.Images.Media.DATA };
                    Cursor cursor = getActivity().getContentResolver().query(selectedImage,
                            filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String picturePath = cursor.getString(columnIndex);
                    cursor.close();

                    ImageViewDialog.create(picturePath).show(getFragmentManager(), "album_photo");
                }
                break;
            case QR_CODE:
                if (resultCode == Activity.RESULT_OK && intent != null) {
                    new MaterialDialog.Builder(getActivity())
                            .title(R.string.tools_qrcode_dialog_title)
                            .content(intent.getStringExtra(Intents.Scan.RESULT))
                            .positiveText(android.R.string.ok)
                            .show();
                }
                break;
            default:
        }

    }

    private void takePhotoFromCamera() {
        List<String> permissionsNeeded = new ArrayList<String>();
        final List<String> permissionsList = new ArrayList<String>();

        if (!addPermission(permissionsList, Manifest.permission.CAMERA))
            permissionsNeeded.add("CAMERA");
        if (!addPermission(permissionsList, Manifest.permission.WRITE_EXTERNAL_STORAGE))
            permissionsNeeded.add("WRITE_EXTERNAL_STORAGE");
        if (!addPermission(permissionsList, Manifest.permission.READ_EXTERNAL_STORAGE))
            permissionsNeeded.add("READ_EXTERNAL_STORAGE");

        if (permissionsList.size() > 0) {
            if (permissionsNeeded.size() > 0) {
                // Need Rationale
                String message = "You need to grant access to " + permissionsNeeded.get(0);
                for (int i = 1; i < permissionsNeeded.size(); i++)
                    message = message + ", " + permissionsNeeded.get(i);
                showMessageOKCancel(message,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FragmentCompat.requestPermissions(ToolFragment.this, permissionsList.toArray(new String[permissionsList.size()]),
                                        REQUEST_CODE_TAKE_PHOTO_PERMISSIONS);
                            }
                        });
                return;
            }
            FragmentCompat.requestPermissions(ToolFragment.this, permissionsList.toArray(new String[permissionsList.size()]),
                    REQUEST_CODE_TAKE_PHOTO_PERMISSIONS);
            return;
        }

        mCameraPhotoPath = photoTools.takeCameraPhotos(ToolFragment.this, CAMERA_CODE);

    }


    private void startQRCodeScan() {
        int hasWriteContactsPermission = ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CAMERA);
        if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
            if (!FragmentCompat.shouldShowRequestPermissionRationale(ToolFragment.this,
                    Manifest.permission.CAMERA)) {
                showMessageOKCancel("You need to allow access to Contacts",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FragmentCompat.requestPermissions(ToolFragment.this,
                                        new String[] {Manifest.permission.CAMERA},
                                        REQUEST_CODE_QR_PERMISSIONS);
                            }
                        });
                return;
            }
            FragmentCompat.requestPermissions(ToolFragment.this,
                    new String[] {Manifest.permission.CAMERA},
                    REQUEST_CODE_QR_PERMISSIONS);
            return;
        }

        Intent scanIntent = new Intent(Intents.Scan.ACTION);
        scanIntent.putExtra(Intents.Scan.RESULT_DISPLAY_DURATION_MS, 0L);
        startActivityForResult(scanIntent, QR_CODE);
    }

    private boolean addPermission(List<String> permissionsList, String permission) {
        if (ContextCompat.checkSelfPermission(getActivity(), permission) != PackageManager.PERMISSION_GRANTED) {
            permissionsList.add(permission);
            // Check for Rationale Option
            if (!FragmentCompat.shouldShowRequestPermissionRationale(ToolFragment.this, permission))
                return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_TAKE_PHOTO_PERMISSIONS:
            {
                Map<String, Integer> perms = new HashMap<String, Integer>();
                // Initial
                perms.put(Manifest.permission.CAMERA, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.READ_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.WRITE_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
                // Fill with results
                for (int i = 0; i < permissions.length; i++)
                    perms.put(permissions[i], grantResults[i]);
                // Check for ACCESS_FINE_LOCATION
                if (perms.get(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                        && perms.get(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                        && perms.get(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    // All Permissions Granted
                    mCameraPhotoPath = photoTools.takeCameraPhotos(ToolFragment.this, CAMERA_CODE);
                } else {
                    // Permission Denied
                    Toast.makeText(getActivity(), "Some Permission is Denied", Toast.LENGTH_SHORT)
                            .show();
                }
            }
            break;
            case REQUEST_CODE_QR_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    Intent scanIntent = new Intent(Intents.Scan.ACTION);
                    scanIntent.putExtra(Intents.Scan.RESULT_DISPLAY_DURATION_MS, 0L);
                    startActivityForResult(scanIntent, QR_CODE);
                } else {
                    // Permission Denied
                    Toast.makeText(getActivity(), "CAMERA Denied", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(getActivity())
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }
}
