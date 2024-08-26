package com.backend.post.domain.common;

import java.time.LocalDateTime;

public class DateTimeInfo {

    private boolean isModified;
    private LocalDateTime dateTime;


    public DateTimeInfo() {
        this.isModified = false;
        this.dateTime = LocalDateTime.now();
    }


    public void updateDateTime() {
        this.dateTime = LocalDateTime.now();
        isModified = true;
    }


    public boolean isModified() {
        return isModified;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
