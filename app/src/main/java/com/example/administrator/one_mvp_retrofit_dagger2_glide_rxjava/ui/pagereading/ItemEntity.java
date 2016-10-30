package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.pagereading;

/**
 * Created by Shuwen on 2016/10/30.
 */

public class ItemEntity {
    private String mTitle;
    private String mContent;
    private String mText2;
    private String mText3;
    private String type;

    public ItemEntity(){

    }
    public ItemEntity(String pTitle, String pContent, String pText2, String pText3) {
        mTitle = pTitle;
        mContent = pContent;
        mText2 = pText2;
        mText3 = pText3;
    }



    public void setType(String type) {
        this.type = type;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }

    public void setmText2(String mText2) {
        this.mText2 = mText2;
    }

    public void setmText3(String mText3) {
        this.mText3 = mText3;
    }

    public String getType() {
        return type;
    }
    public String getTitle() {
        return mTitle;
    }
    public String getContent() {
        return mContent;
    }
    public String getmText2(){
        return mText2;
    }
    public String getmText3(){
        return mText3;
    }
}
