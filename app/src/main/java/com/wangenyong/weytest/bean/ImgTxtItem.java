package com.wangenyong.weytest.bean;

/**
 * Created by wangenyong on 15/8/19.
 */
public class ImgTxtItem extends Item {
    private int image;
    private String title;
    private String link;

    public ImgTxtItem(int image, String link, String title, int count) {
        this(image, link, title);
        this.count = count;
    }

    public ImgTxtItem(int image, String link, String title) {
        this.image = image;
        this.link = link;
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public String getLink() {
        return link;
    }

    public String getTitle() {
        return title;
    }
}
