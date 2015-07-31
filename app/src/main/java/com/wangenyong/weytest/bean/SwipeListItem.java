package com.wangenyong.weytest.bean;

/**
 * Created by wangenyong on 15/7/31.
 */
public class SwipeListItem {
    private int imgId;
    private String title;
    private String intro;

    public SwipeListItem(int imgId, String title, String intro) {
        this.imgId = imgId;
        this.title = title;
        this.intro = intro;
    }

    public SwipeListItem(int imgId) {
        this.imgId = imgId;
    }

    public int getImgId() {
        return imgId;
    }

    public String getTitle() {
        return title;
    }

    public String getIntro() {
        return intro;
    }
}
