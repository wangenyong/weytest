package com.wangenyong.weytest.activities;

import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.os.Vibrator;

import com.baidu.location.BDLocationListener;
import com.baidu.location.GeofenceClient;
import com.baidu.location.LocationClient;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.wangenyong.weytest.BuildConfig;

/**
 * Created by wangenyong on 15/7/28.
 */
public class LocationApplication extends Application {
    public LocationClient mLocationClient;
    public GeofenceClient mGeofenceClient;
    public BDLocationListener mMyLocationListener;

    public Vibrator mVibrator;
    private RefWatcher refWatcher;

    public static RefWatcher getRefWatcher(Context context) {
        LocationApplication application = (LocationApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        refWatcher = LeakCanary.install(this);

        Logger.init("DSWEY")                  // default PRETTYLOGGER or use just init()
                //.setMethodCount(3)            // default 2
                //.hideThreadInfo()             // default shown
                .setLogLevel(BuildConfig.DEBUG ? LogLevel.FULL : LogLevel.NONE);   // default LogLevel.FULL
                //.setMethodOffset(2);          // default 0

        mLocationClient = new LocationClient(this.getApplicationContext());
        mGeofenceClient = new GeofenceClient(getApplicationContext());
        mVibrator =(Vibrator)getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);

    }


    public void setmMyLocationListener(BDLocationListener bdLocationListener) {
        this.mMyLocationListener = bdLocationListener;
        mLocationClient.registerLocationListener(mMyLocationListener);
    }

    /**
     * 实现实位回调监听

    public class MyLocationListener implements BDLocationListener {

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


    }
    */

    /**
     * 显示请求字符串
     * @param str

    public void logMsg(String str) {
        try {
            if (mLocationResult != null)
                mLocationResult.setText(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    */
}
