package com.wangenyong.weytest.dialogs;

import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.wangenyong.mylibrary.tools.PhotoTools;
import com.wangenyong.weytest.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wangenyong on 15/7/24.
 */
public class ImageViewDialog extends DialogFragment {
    @Bind(R.id.img_dialog) ImageView dialogImg;

    public static ImageViewDialog create(String mPhotoPath) {
        ImageViewDialog dialog = new ImageViewDialog();
        Bundle args = new Bundle();
        args.putString("photo_path", mPhotoPath);
        dialog.setArguments(args);
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_imageview, null);
        ButterKnife.bind(this, view);

        MaterialDialog dialog = new MaterialDialog.Builder(getActivity())
                .customView(view, false)
                .positiveText(android.R.string.ok)
                .build();
        Bitmap bitmap = PhotoTools.scalePhotos(640, 640, getArguments().getString("photo_path"));
        dialogImg.setImageBitmap(bitmap);
        return dialog;
    }
}
