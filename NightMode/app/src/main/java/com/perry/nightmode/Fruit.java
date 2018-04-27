package com.perry.nightmode;

/**
 * Created by Administrator on 2017/9/22 0022.
 */

public class Fruit {
    private int imageId;
    private String name;
    private String introduce;

    public Fruit(int imageId, String name, String introduce) {
        this.imageId = imageId;
        this.name = name;
        this.introduce = introduce;
    }

    public Fruit() {
        
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
    
    
}
