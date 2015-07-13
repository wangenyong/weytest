package com.wangenyong.weytest.bean;

import android.view.View;

/**
 * Created by wangenyong on 15/7/13.
 */
public class MyView {
    private String title;
    private View view;

    public MyView(String title, View view) {
        this.title = title;
        this.view = view;
    }

    public String getTitle() {
        return title;
    }

    public View getView() {
        return view;
    }
}
