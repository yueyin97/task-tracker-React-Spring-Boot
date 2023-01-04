package com.yy.tasks.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddTaskRequest {

    @JsonProperty
    private String text;

    @JsonProperty
    private String day;

    @JsonProperty
    private boolean reminder;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public boolean isReminder() {
        return reminder;
    }

    public void setReminder(boolean reminder) {
        this.reminder = reminder;
    }
}
