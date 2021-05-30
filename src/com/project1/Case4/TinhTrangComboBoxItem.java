package com.project1.Case4;

public class TinhTrangComboBoxItem {
    private String key;
    private int value;

    public TinhTrangComboBoxItem(String key, int value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return key;
    }
}
