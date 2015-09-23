package com.wangenyong.weytest.experiment;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by wangenyong on 15/9/23.
 */
public class Joke extends Book {
    private boolean fun;

    @JsonCreator
    public Joke(@JsonProperty("num")int num, @JsonProperty("fun")boolean fun) {
        super(num);
        this.fun = fun;
    }

    public boolean isFun() {
        return fun;
    }

    public void setFun(boolean fun) {
        this.fun = fun;
    }
}
