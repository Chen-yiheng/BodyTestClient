package com.perry.happybirthday;

/**
 * Created by perry on 2018/2/4.
 */

public class Note {
    private int imageId;
    private String yourWord;
    private String myWord;

    public Note(int imageId) {
        this.imageId = imageId;
    }

    public Note(int imageId, String yourWord, String myWord) {
        this.imageId = imageId;
        this.yourWord = yourWord;
        this.myWord = myWord;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getYourWord() {
        return yourWord;
    }

    public void setYourWord(String yourWord) {
        this.yourWord = yourWord;
    }

    public String getMyWord() {
        return myWord;
    }

    public void setMyWord(String myWord) {
        this.myWord = myWord;
    }
}
