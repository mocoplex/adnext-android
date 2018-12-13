package com.mocoplex.demo.model;

/**
 * Created by lineplus on 18. 2. 12..
 */

public enum MainListItem {

    HEADER_01("Basic", true),
    HYBRID("Hybrid");

    private String value;
    private Boolean isHeader = false;

    MainListItem(String value) {
        this.value = value;
    }

    MainListItem(String value, boolean isHeader) {
        this.value = value;
        this.isHeader = isHeader;
    }

    public String getValue() {
        return value;
    }

    public boolean isHeader() {
        return isHeader;
    }
}
