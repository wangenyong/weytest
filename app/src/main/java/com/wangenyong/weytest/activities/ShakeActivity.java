package com.wangenyong.weytest.activities;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.wangenyong.mylibrary.tools.ShakeDetector;
import com.wangenyong.weytest.R;

import java.util.Random;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ShakeActivity extends AppCompatActivity {
    @InjectView(R.id.toolbar_shake) Toolbar shakeToolbar;
    @InjectView(R.id.tv_shake) TextView shakeTv;

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;
    private MaterialDialog dialog;
    private ImageView imageView;
    private TypedArray imgs;
    private Random rand;

    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake);

        ButterKnife.inject(this);
        setSupportActionBar(shakeToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.setStatusBarColor(getResources().getColor(R.color.dark_primary_color));
        }

        dialog = new MaterialDialog.Builder(ShakeActivity.this)
                .customView(R.layout.dialog_imageview, false)
                .build();
        imageView =(ImageView) dialog.getCustomView().findViewById(R.id.img_dialog);
        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/classicxing.ttf");
        shakeTv.setTypeface(typeFace);

        imgs = getResources().obtainTypedArray(R.array.shake_image);
        rand = new Random();

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector(this, R.raw.shotgun_reloadd);
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {

            @Override
            public void onShake(int count) {
                /*
                 * The following method, "handleShakeEvent(count):" is a stub //
                 * method you would use to setup whatever you want done once the
                 * device has been shook.
                 */
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }

                final int rndInt = rand.nextInt(imgs.length());
                final int resID = imgs.getResourceId(rndInt, 0);
                imageView.setImageResource(resID);
                dialog.show();

            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }
}
