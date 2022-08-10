package com.example.lifechoices.Adapter;

public class Guide {
    private int imgId;
    private String content;
    private int count;

    public Guide(int imgId, String content, int count) {
        this.imgId = imgId;
        this.content = content;
        this.count = count;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
