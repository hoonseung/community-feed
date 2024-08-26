package com.backend.post.domain.cotent;

import com.backend.post.domain.common.DateTimeInfo;

public abstract class Content {

    protected String contentText;
    protected final DateTimeInfo dataTimeInfo;

    protected Content(String contentText) {
        checkText(contentText);
        this.contentText = contentText;
        this.dataTimeInfo = new DateTimeInfo();
    }

    public void updateContent(String updateContent) {
        checkText(updateContent);
        this.contentText = updateContent;
        this.dataTimeInfo.updateDateTime();
    }


    public String getContentText() {
        return contentText;
    }

    public DateTimeInfo getDataTimeInfo() {
        return dataTimeInfo;
    }

    protected abstract void checkText(String contentText);
}
