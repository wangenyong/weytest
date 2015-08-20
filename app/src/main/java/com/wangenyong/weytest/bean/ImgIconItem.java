package com.wangenyong.weytest.bean;

/**
 * Created by wangenyong on 15/8/7.
 */
public class ImgIconItem extends Item {
    private int imageId;
    private String packageName;

    public ImgIconItem(int imageId, String packageName, int count) {
        this.imageId = imageId;
        this.packageName = packageName;
        this.count = count;
    }

    public int getImageId() {
        return imageId;
    }

    public String getPackageName() {
        return packageName;
    }
}
