package com.wangenyong.weytest.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.wangenyong.mylibrary.views.PaperButton;
import com.wangenyong.weytest.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GpsActivity extends AppCompatActivity implements BDLocationListener {
    @Bind(R.id.toolbar_gps) Toolbar gpsToolbar;
    @Bind(R.id.radiogroup_gps_selectmode) RadioGroup selectModeRadioGroup;
    @Bind(R.id.radiogroup_gps_selectCoordinates) RadioGroup selectCoordinatesRadioGroup;
    @Bind(R.id.tv_gps_selectmode_explain) TextView modeInfoTv;
    @Bind(R.id.edittext_gps_frequence) EditText frequenceEdttxt;
    @Bind(R.id.checkbox_geofencelog) CheckBox geofencelogCheckBox;
    @Bind(R.id.tv_gps_location_result) TextView loactionResultTv;
    @Bind(R.id.btn_gps_addfence) PaperButton startLocationBtn;

    private LocationClient mLocationClient;
    private LocationClientOption.LocationMode tempMode = LocationClientOption.LocationMode.Hight_Accuracy;
    private String tempcoor="gcj02";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);

        ButterKnife.bind(this);
        setSupportActionBar(gpsToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.setStatusBarColor(getResources().getColor(R.color.dark_primary_color));
        }

        ((LocationApplication)getApplication()).setmMyLocationListener(this);
        mLocationClient = ((LocationApplication)getApplication()).mLocationClient;

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

    @Override
    public void onReceiveLocation(BDLocation location) {
        //Receive Location
        StringBuffer sb = new StringBuffer(256);
        sb.append("time : ");
        sb.append(location.getTime());
        sb.append("\nerror code : ");
        sb.append(location.getLocType());
        sb.append("\nlatitude : ");
        sb.append(location.getLatitude());
        sb.append("\nlontitude : ");
        sb.append(location.getLongitude());
        sb.append("\nradius : ");
        sb.append(location.getRadius());
        if (location.getLocType() == BDLocation.TypeGpsLocation){
            sb.append("\nspeed : ");
            sb.append(location.getSpeed());
            sb.append("\nsatellite : ");
            sb.append(location.getSatelliteNumber());
            sb.append("\ndirection : ");
            sb.append("\naddr : ");
            sb.append(location.getAddrStr());
            sb.append(location.getDirection());
        } else if (location.getLocType() == BDLocation.TypeNetWorkLocation){
            sb.append("\naddr : ");
            sb.append(location.getAddrStr());
            //运营商信息
            sb.append("\noperationers : ");
            sb.append(location.getOperators());
        }
        logMsg(sb.toString());
        Log.i("BaiduLocationApiDem", sb.toString());
    }

    /**
     * 显示请求字符串
     * @param str
     */
    public void logMsg(String str) {
        try {
            if (loactionResultTv != null)
                loactionResultTv.setText(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
