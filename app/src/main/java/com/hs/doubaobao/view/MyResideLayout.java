package com.hs.doubaobao.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 作者：zhanghaitao on 2017/11/10 14:11
 * 邮箱：820159571@qq.com
 *
 * @describe:继承ResideLayout，处理ViewPager处于第一页向右滑动时打开菜单栏。
 */

public class MyResideLayout extends ResideLayout {
    public MyResideLayout(Context context) {
        super(context);
    }

    public MyResideLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyResideLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private ViewPager viewPager;

    public ViewPager getViewPager() {
        return viewPager;
    }

    //处理前必须调用此方法初始化冲突ViewPager
    public void setViewPager(ViewPager viewPager) {
        if (viewPager == null) {
            throw new IllegalArgumentException("viewPager can not be null");
        }
        this.viewPager = viewPager;
    }

    private int startX;
    private int startY;
    private boolean flag = true;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (viewPager != null) {
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    startX = (int) ev.getX();
                    startY = (int) ev.getY();
                    // 使父控件不处理任何触摸事件
                    viewPager.requestDisallowInterceptTouchEvent(true);
                    flag = true;
                    break;
                case MotionEvent.ACTION_MOVE:
                    int dX = (int) (ev.getX() - startX);
                    int dY = (int) (ev.getY() - startY);
                    if (Math.abs(dX) > Math.abs(dY)) {//左右滑动
                        if (viewPager.getCurrentItem() == 0 && (ev.getX() - startX) > 0) {
                            flag = false;
                            viewPager.requestDisallowInterceptTouchEvent(false);//将事件交由父控件处理
                        } else {
                            flag = true;
                            viewPager.requestDisallowInterceptTouchEvent(true);//将事件拦截
                        }
                    }
                case MotionEvent.ACTION_UP:
                    break;
            }
        }
        return super.dispatchTouchEvent(ev);
    }
}
