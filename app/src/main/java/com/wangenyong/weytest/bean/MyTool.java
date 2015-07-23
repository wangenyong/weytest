package com.wangenyong.weytest.bean;

/**
 * Created by wangenyong on 15/7/22.
 */
public class MyTool {
    public enum Types {QRCODE, PHOTO, DATE, SHAKE, MAP, GPS};
    private int image;
    private String title;
    private Types toolTypes;


    public MyTool(int image, String title, Types toolTypes) {
        this.image = image;
        this.title = title;
        this.toolTypes = toolTypes;
    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public Types getToolTypes() {
        return toolTypes;
    }
}
