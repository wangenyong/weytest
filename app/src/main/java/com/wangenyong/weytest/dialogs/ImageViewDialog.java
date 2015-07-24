package com.wangenyong.weytest.dialogs;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.wangenyong.mylibrary.tools.PhotoTools;
import com.wangenyong.weytest.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by wangenyong on 15/7/24.
 */
public class ImageViewDialog extends DialogFragment {
    @InjectView(R.id.img_dialog) ImageView dialogImg;

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
        ButterKnife.inject(this, view);

        MaterialDialog dialog = new MaterialDialog.Builder(getActivity())
                .title(R.string.tools_photo_title)
                .titleColor(getResources().getColor(R.color.primary_color))
                .customView(view, false)
                .positiveText(android.R.string.ok)
                .build();
        Bitmap bitmap = PhotoTools.scalePhotos(640, 640, getArguments().getString("photo_path"));
        dialogImg.setImageBitmap(bitmap);
        return dialog;
    }
}
