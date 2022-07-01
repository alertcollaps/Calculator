package com.example.calculator;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.AnimationUtils;

import static com.example.calculator.MainActivity.flipper;
import static com.example.calculator.MainActivity.fromPosition;

public class Button1 extends android.widget.Button {
    public Button1(Context context) {
        super(context);
    }

    public Button1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Button1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Button1(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        MainActivity.mn.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}
