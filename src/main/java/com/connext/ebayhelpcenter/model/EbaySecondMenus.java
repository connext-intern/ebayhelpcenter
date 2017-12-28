package com.connext.ebayhelpcenter.model;

public class EbaySecondMenus {
    private int secondId;
    private int secondSerial;
    private String secondTitle;
    private String content;
    private String html;
    private int secondFirstId;

    @Override
    public String toString() {
        return "EbaySecondMenus{" +
                "secondId=" + secondId +
                ", secondSerial=" + secondSerial +
                ", secondTitle='" + secondTitle + '\'' +
                ", content='" + content + '\'' +
                ", html='" + html + '\'' +
                ", secondFirstId=" + secondFirstId +
                '}';
    }

    public int getSecondId() {
        return secondId;
    }

    public void setSecondId(int secondId) {
        this.secondId = secondId;
    }

    public int getSecondSerial() {
        return secondSerial;
    }

    public void setSecondSerial(int secondSerial) {
        this.secondSerial = secondSerial;
    }

    public String getSecondTitle() {
        return secondTitle;
    }

    public void setSecondTitle(String secondTitle) {
        this.secondTitle = secondTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public int getSecondFirstId() {
        return secondFirstId;
    }

    public void setSecondFirstId(int secondFirstId) {
        this.secondFirstId = secondFirstId;
    }
}
