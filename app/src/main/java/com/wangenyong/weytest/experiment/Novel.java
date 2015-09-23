package com.wangenyong.weytest.experiment;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by wangenyong on 15/9/23.
 */
public class Novel extends Book {
    private String description;

    @JsonCreator
    public Novel(@JsonProperty("num")int num, @JsonProperty("description")String description) {
        super(num);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
