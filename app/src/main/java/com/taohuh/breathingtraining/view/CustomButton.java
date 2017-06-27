package com.taohuh.breathingtraining.view;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by taohuh on 3/29/2016.
 */
public class CustomButton extends AppCompatButton {
    public CustomButton(Context context) {
        super(context);
        initTypeFace();
    }

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initTypeFace();

    }

    public CustomButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initTypeFace();
    }

    private void initTypeFace(){
        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/Bangna.ttf");
        setTypeface(typeface);
    }
}
