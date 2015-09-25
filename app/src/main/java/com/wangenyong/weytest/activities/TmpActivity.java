package com.wangenyong.weytest.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orhanobut.logger.Logger;
import com.wangenyong.mylibrary.drawables.CircleImageDrawable;
import com.wangenyong.weytest.R;
import com.wangenyong.weytest.experiment.Book;
import com.wangenyong.weytest.experiment.Books;
import com.wangenyong.weytest.experiment.Joke;
import com.wangenyong.weytest.experiment.Novel;

import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class TmpActivity extends AppCompatActivity {
    @InjectView(R.id.toolbar_tmp) Toolbar tmpToolbar;
    @InjectView(R.id.tv_tmp) TextView tmpTv;
    
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

        tmpTv.setError("Wrong!");

        ObjectMapper mapper = new ObjectMapper();

        Books c = new Books();
        c.getBookList().add(new Novel(3243, "woman fight"));
        c.getBookList().add(new Joke(55, false));

        String s = null;
        try {
            s = mapper.writeValueAsString(c);
        }
        catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Logger.d(s);
        Logger.json(s);

        Books c2 = null;
        try {
            c2 = mapper.readValue(s, Books.class);
        }
        catch (JsonParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (c2 != null) {
            System.out.println("----- Items List -----");

            for (Book mi : c2.getBookList()) {
                Log.d("DSWEY", "Type = " + mi.getClass() + ", num = " + mi.getNum());
            }
        }
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
