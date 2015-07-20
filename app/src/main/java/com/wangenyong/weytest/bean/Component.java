package com.wangenyong.weytest.bean;

/**
 * Created by wangenyong on 15/7/10.
 */
public class Component {
    public final static int BUTTON = 1000;
    public final static int EDITTEXT = 10001;
    public final static int PROGRESS = 10002;
    public final static int IMAGE = 10003;
    public final static int CHART = 10004;
    public final static int DIALOG = 10005;
    private int iconId;
    private String title;
    private int color;
    private int type;

    public Component(int iconId, String title, int color, int type) {
        this.iconId = iconId;
        this.title = title;
        this.color = color;
        this.type = type;
    }

    public int getIconId() {
        return iconId;
    }

    public String getTitle() {
        return title;
    }

    public int getColor() {
        return color;
    }

    public int getType() {
        return type;
    }
}
