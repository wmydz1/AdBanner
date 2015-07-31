package com.logoocc.adbanner.views;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by samchen on 7/31/15.
 */
public class MyViewPagerView extends ViewPager {
    public MyViewPagerView(Context context) {
        super(context);
    }

    public MyViewPagerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // 请求父类不要拦截自己的触摸事件
        requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(ev);
    }
}
