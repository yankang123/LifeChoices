package com.example.lifechoices.MyProgress;

public class Item {
    public static int sum;
    public static int position=0;
    private int imgId;

    public Item(int imgId) {

        this.imgId = imgId;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}
