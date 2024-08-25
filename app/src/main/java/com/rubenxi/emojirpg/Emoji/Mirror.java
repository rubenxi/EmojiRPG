package com.rubenxi.emojirpg.Emoji;

import android.graphics.Matrix;
import android.widget.TextView;

public class Mirror {
    public TextView textView;
    public Mirror(TextView textView){
    this.textView = textView;
    }

    public void mirrorHorizontal(){
        Matrix matrix = new Matrix();
        matrix.preScale(-1.0f, 1.0f);

        textView.setScaleX(-1.0f);
    }
    public void mirrorVertical(){
        Matrix matrix = new Matrix();
        matrix.preScale(1.0f, -1.0f);

        textView.setScaleY(-1.0f);
    }
    public void mirror(){
        Matrix matrix = new Matrix();
        matrix.preScale(1.0f, -1.0f);
        textView.setScaleX(-1.0f);
        textView.setScaleY(-1.0f);
    }
}
