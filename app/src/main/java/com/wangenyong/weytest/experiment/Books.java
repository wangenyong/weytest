package com.wangenyong.weytest.experiment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangenyong on 15/9/23.
 */
public class Books {
    private List<Book> bookList;

    public Books(List<Book> bookList) {
        this.bookList = bookList;
    }

    public Books() {
        this.bookList = new ArrayList<Book>();
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
