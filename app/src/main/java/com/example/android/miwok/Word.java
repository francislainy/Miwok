package com.example.android.miwok;

/**
 * Created by Francislainy on 24/12/2016.
 */

public class Word {

    private static final int NO_IMAGE_PROVIDED = -1;

    private String mMiwokTranslation;
    private String mDefaultTranslation;
    private int mImageResourceID = NO_IMAGE_PROVIDED;

    public Word(String miwokTranslation, String defaultTranslation) {
        this.mMiwokTranslation = miwokTranslation;
        this.mDefaultTranslation = defaultTranslation;
    }

    public Word(String miwokTranslation, String defaultTranslation, int imageResourceID) {
        this.mMiwokTranslation = miwokTranslation;
        this.mDefaultTranslation = defaultTranslation;
        this.mImageResourceID = imageResourceID;
    }

    public String getmMiwokTranslation() {
        return mMiwokTranslation;
    }

    public void setmMiwokTranslation(String mMiwokTranslation) {
        this.mMiwokTranslation = mMiwokTranslation;
    }

    public String getmDefaultTranslation() {
        return mDefaultTranslation;
    }

    public void setmDefaultTranslation(String mDefaultTranslation) {
        this.mDefaultTranslation = mDefaultTranslation;
    }

    public int getmImageResourceID() {
        return mImageResourceID;
    }

    public void setmImageResourceID(int mImageResourceID) {
        this.mImageResourceID = mImageResourceID;
    }

    public boolean hasImage() {
        if(mImageResourceID != -1)
            return true;
        else
            return false;
    }

}
