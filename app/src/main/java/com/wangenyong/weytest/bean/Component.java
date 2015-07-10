package com.wangenyong.weytest.bean;

/**
 * Created by wangenyong on 15/7/10.
 */
public class Component {
    private int iconId;
    private String title;
    private int color;

    public Component(int iconId, String title, int color) {
        this.iconId = iconId;
        this.title = title;
        this.color = color;
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
}
