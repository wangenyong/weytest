package com.wangenyong.weytest.activities;

import android.app.Service;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import com.wangenyong.mylibrary.tools.ShakeListenerUtils;
import com.wangenyong.weytest.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ShakeActivity extends AppCompatActivity implements Handler.Callback {
    @InjectView(R.id.toolbar_shake) Toolbar shakeToolbar;
    private ShakeListenerUtils shakeUtils;
    private SensorManager mSensorManager;
    private Vibrator vibrator;
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

        mHandler = new Handler(this);
        shakeUtils = new ShakeListenerUtils(this, mHandler);
    }


    @Override
    protected void onResume() {
        super.onResume();

        //获取传感器管理服务
        mSensorManager = (SensorManager) this.getSystemService(Service.SENSOR_SERVICE);
        vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
        //加速度传感器
        mSensorManager.registerListener(shakeUtils, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                //还有SENSOR_DELAY_UI、SENSOR_DELAY_FASTEST、SENSOR_DELAY_GAME等，
                //根据不同应用，需要的反应速率不同，具体根据实际情况设定
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(shakeUtils);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shake, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case ShakeListenerUtils.SENSOR_SHAKE:
                Toast.makeText(this, "Shaked! shaked!", Toast.LENGTH_SHORT).show();
                vibrator.vibrate(500);
                break;
        }

        return true;
    }
}
