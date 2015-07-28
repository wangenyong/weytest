package com.wangenyong.weytest.activities;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.wangenyong.mylibrary.views.PaperButton;
import com.wangenyong.weytest.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class GpsActivity extends AppCompatActivity {
    @InjectView(R.id.toolbar_gps) Toolbar gpsToolbar;
    @InjectView(R.id.radiogroup_gps_selectmode) RadioGroup selectModeRadioGroup;
    @InjectView(R.id.radiogroup_gps_selectCoordinates) RadioGroup selectCoordinatesRadioGroup;
    @InjectView(R.id.tv_gps_selectmode_explain) TextView modeInfoTv;
    @InjectView(R.id.edittext_gps_frequence) EditText frequenceEdttxt;
    @InjectView(R.id.checkbox_geofencelog) CheckBox geofencelogCheckBox;
    @InjectView(R.id.tv_gps_location_result) TextView loactionResultTv;
    @InjectView(R.id.btn_gps_addfence) PaperButton startLocationBtn;

    private LocationClient mLocationClient;
    private LocationClientOption.LocationMode tempMode = LocationClientOption.LocationMode.Hight_Accuracy;
    private String tempcoor="gcj02";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);

        ButterKnife.inject(this);
        setSupportActionBar(gpsToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.setStatusBarColor(getResources().getColor(R.color.dark_primary_color));
        }

        mLocationClient = ((LocationApplication)getApplication()).mLocationClient;
        ((LocationApplication)getApplication()).mLocationResult = loactionResultTv;

        startLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initLocation();

                if (startLocationBtn.getText().equals(getString(R.string.tools_gps_start))) {
                    mLocationClient.start();
                    startLocationBtn.setText(getString(R.string.tools_gps_stop));
                } else {
                    mLocationClient.stop();
                    startLocationBtn.setText(getString(R.string.tools_gps_start));
                }
            }
        });

        selectModeRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String modeInformation = null;
                switch (checkedId) {
                    case R.id.radiobutton_gps_radio_hight:
                        tempMode = LocationClientOption.LocationMode.Hight_Accuracy;
                        modeInformation = getString(R.string.tools_gps_select_mode_hight_accuracy_desc);
                        break;
                    case R.id.radiobutton_gps_radio_low:
                        tempMode = LocationClientOption.LocationMode.Battery_Saving;
                        modeInformation = getString(R.string.tools_gps_select_mode_saving_battery_desc);
                        break;
                    case R.id.radiobutton_radio_gps_device:
                        tempMode = LocationClientOption.LocationMode.Device_Sensors;
                        modeInformation = getString(R.string.tools_gps_select_mode_device_sensor_desc);
                        break;
                    default:
                        break;
                }
                modeInfoTv.setText(modeInformation);
            }
        });

        selectCoordinatesRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radiobutton_gps_radio_gcj02:
                        tempcoor = "gcj02";
                        break;
                    case R.id.radiobutton_gps_radio_bd09ll:
                        tempcoor = "bd09ll";
                        break;
                    case R.id.radiobutton_gps_radio_bd09:
                        tempcoor = "bd09";
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gps, menu);
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
    protected void onStop() {
        // TODO Auto-generated method stub
        mLocationClient.stop();
        super.onStop();
    }

    private void initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(tempMode);//设置定位模式
        option.setCoorType(tempcoor);//返回的定位结果是百度经纬度，默认值gcj02
        int span=1000;
        try {
            span = Integer.valueOf(frequenceEdttxt.getText().toString());
        } catch (Exception e) {
            // TODO: handle exception
        }
        option.setScanSpan(span);//设置发起定位请求的间隔时间为5000ms
        option.setIsNeedAddress(geofencelogCheckBox.isChecked());
        mLocationClient.setLocOption(option);
    }
}
