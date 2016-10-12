package com.example.zwq.mygiftapp.view;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewConfigurationCompat;
import android.support.v4.widget.SlidingPaneLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/**
 * Created by zhuwenqiang on 2016/9/30.
 */
public class MySlidingPane extends SlidingPaneLayout {

    float edgeSlop;
    float evX;
    float evY;
    public MySlidingPane(Context context) {
        super(context);
    }

    public MySlidingPane(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MySlidingPane(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        ViewConfiguration config = ViewConfiguration.get(context);
        edgeSlop = config.getScaledEdgeSlop();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        switch (MotionEventCompat.getActionMasked(ev)){
            case MotionEvent.ACTION_DOWN:
                evX = ev.getX();
                evY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float x = ev.getX();
                float y = ev.getY();
                if (evX>edgeSlop&&!isOpen()&&canScroll(this,false,Math.round(x-evX),Math.round(x),Math.round(y))){
                    MotionEvent event = MotionEvent.obtain(ev);
                    event.setAction(MotionEvent.ACTION_CANCEL);
                    return super.onInterceptTouchEvent(event);
                }
                break;

        }
        return super.onInterceptTouchEvent(ev);
    }
}
