package com.example.zwq.mygiftapp.view;

import android.content.Context;
import android.support.v7.widget.SearchView;
import android.util.AttributeSet;

/**
 * Created by zhuwenqiang on 2016/10/7.
 */
public class MySearchView extends SearchView {
    @Override
    public void setSubmitButtonEnabled(boolean enabled) {
        super.setSubmitButtonEnabled(false);
    }

    public MySearchView(Context context) {
        super(context);
    }

    public MySearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MySearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
