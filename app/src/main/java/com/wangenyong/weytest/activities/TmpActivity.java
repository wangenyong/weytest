package com.wangenyong.weytest.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ImageView;

import com.wangenyong.mylibrary.drawables.CircleImageDrawable;
import com.wangenyong.weytest.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class TmpActivity extends AppCompatActivity {
    @InjectView(R.id.toolbar_tmp) Toolbar tmpToolbar;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tmp);

        ButterKnife.inject(this);
        setSupportActionBar(tmpToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.setStatusBarColor(getResources().getColor(R.color.dark_primary_color));
        }

        ImageView imageView = (ImageView) findViewById(R.id.img_tmp);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img_guilunmei);
        imageView.setImageDrawable(new CircleImageDrawable(bitmap));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tmp, menu);
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
}
