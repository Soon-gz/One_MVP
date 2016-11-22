package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils;

/**
 * Created by Administrator on 2016/11/22.
 */

public class Block {
    int iColor;
    float  fRate;
    String sText;
    public Block(int iColor, float fRate) {
        this.iColor = iColor;
        this.fRate = fRate;
    }

    public Block(int iColor, float fRate, String sText) {
        this.iColor = iColor;
        this.fRate = fRate;
        this.sText = sText;
    }

    String rateToString(float rate){
        return String.format("%.0f%%",rate*100);
    }


    @Override
    public String toString() {
        if(sText == null || sText.length() == 0){
            return rateToString(fRate);
        }
        return sText;
    }
}
