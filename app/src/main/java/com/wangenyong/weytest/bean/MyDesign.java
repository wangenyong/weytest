package com.wangenyong.weytest.bean;

/**
 * Created by wangenyong on 15/8/7.
 */
public class MyDesign {
    private int imageId;
    private String packageName;

    public MyDesign(int imageId, String packageName) {
        this.imageId = imageId;
        this.packageName = packageName;
    }

    public int getImageId() {
        return imageId;
    }

    public String getPackageName() {
        return packageName;
    }
}
