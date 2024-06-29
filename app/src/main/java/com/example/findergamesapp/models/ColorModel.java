package com.example.findergamesapp.models;

import android.graphics.Color;

public class ColorModel {
    String colorName;
    int colorCode;

    public ColorModel(String colorName, int colorCode) {
        this.colorName = colorName;
        this.colorCode = colorCode;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public int getColorCode() {
        return colorCode;
    }

    public void setColorCode(int colorCode) {
        this.colorCode = colorCode;
    }
}
