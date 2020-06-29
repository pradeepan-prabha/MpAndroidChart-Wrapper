package com.imake.mpandroidchartwrappersample.util;


public class PropsPoJo {
    String[] xValues;
    float[] yValues;
    String yLabelName;
    float[] ySizeValue;

    float[] shadowH;
    float[] shadowL;
    float[] open;
    float[] close;

    public PropsPoJo(float[] yValues, float[] shadowH, float[] shadowL, float[] open, float[] close) {
        this.yValues = yValues;
        this.shadowH = shadowH;
        this.shadowL = shadowL;
        this.open = open;
        this.close = close;
    }

    public float[] getShadowH() {
        return shadowH;
    }

    public void setShadowH(float[] shadowH) {
        this.shadowH = shadowH;
    }

    public float[] getShadowL() {
        return shadowL;
    }

    public void setShadowL(float[] shadowL) {
        this.shadowL = shadowL;
    }

    public float[] getOpen() {
        return open;
    }

    public void setOpen(float[] open) {
        this.open = open;
    }

    public float[] getClose() {
        return close;
    }

    public void setClose(float[] close) {
        this.close = close;
    }

    public PropsPoJo(String[] xValues, float[] yValues) {
        this.xValues = xValues;
        this.yValues = yValues;
    }

    public PropsPoJo(float[] yValues, float[] ySizeValue) {
        this.yValues = yValues;
        this.ySizeValue = ySizeValue;
    }

    public PropsPoJo(float[] yValues, String yLabelName) {
        this.yValues = yValues;
        this.yLabelName = yLabelName;
    }

    public String[] getxValues() {
        return xValues;
    }

    public void setxValues(String[] xValues) {
        this.xValues = xValues;
    }

    public float[] getyValues() {
        return yValues;
    }

    public void setyValues(float[] yValues) {
        this.yValues = yValues;
    }

    public String getyLabelName() {
        return yLabelName;
    }

    public void setyLabelName(String yLabelName) {
        this.yLabelName = yLabelName;
    }

    public float[] getySizeValue() {
        return ySizeValue;
    }

    public void setySizeValue(float[] ySizeValue) {
        this.ySizeValue = ySizeValue;
    }

}
