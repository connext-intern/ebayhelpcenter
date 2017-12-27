package com.connext.ebayhelpcenter.model;

import java.util.List;

public class EbayFirstMenus {
    private int firstId;
    private int firstSerial;
    private String firstTitle;
    private List<EbaySecondMenus> secondMenuses;

    public int getFirstId() {
        return firstId;
    }

    public void setFirstId(int firstId) {
        this.firstId = firstId;
    }

    public int getFirstSerial() {
        return firstSerial;
    }

    public void setFirstSerial(int firstSerial) {
        this.firstSerial = firstSerial;
    }

    public String getFirstTitle() {
        return firstTitle;
    }

    public void setFirstTitle(String firstTitle) {
        this.firstTitle = firstTitle;
    }

    public List<EbaySecondMenus> getSecondMenuses() {
        return secondMenuses;
    }

    public void setSecondMenuses(List<EbaySecondMenus> secondMenuses) {
        this.secondMenuses = secondMenuses;
    }
}
