package com.hs.doubaobao.base;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.support.annotation.IntRange;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.hs.doubaobao.R;
import com.hs.doubaobao.utils.log.Logger;

import java.lang.reflect.Field;

/**
 * 作者：zhanghaitao on 2017/11/23 11:22
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class TransparentBarActivity extends AppCompatActivity {


    private final String TAG = getClass().getSimpleName();
    private LinearLayout mTransparentBar;
    private FrameLayout mContainer;
    private int statusHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.e(TAG, "当前线程的进程的ID：" + Process.myPid());
        initViews();
        initState();
    }

    /**
     * 初始化BaseActivity布局中的控件
     */
    private void initViews() {
        /**代码生成替代的状态栏*/
        LinearLayout layout = new LinearLayout(this);
        layout.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        layout.setOrientation(LinearLayout.VERTICAL);

        mTransparentBar = new LinearLayout(this);

        mTransparentBar.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        mTransparentBar.setOrientation(LinearLayout.VERTICAL);
        mTransparentBar.setBackgroundResource(R.drawable.ic_battery_bg);

        mContainer = new FrameLayout(this);
        mContainer.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        mContainer.setBackgroundColor(Color.WHITE);
        layout.addView(mTransparentBar);
        layout.addView(mContainer);

/**
 * android:layout_width="match_parent"
 android:layout_height="match_parent"
 android:background="#fff"
 */
        super.setContentView(layout);
        // super.setContentView(R.layout.activity_transparent_bar);
//        mTransparentBar = (LinearLayout) findViewById(R.id.transparent_bar);
//        mContainer = (FrameLayout) findViewById(R.id.layout_container);
    }

    /**
     * 动态的设置状态栏  实现沉浸式状态栏
     */
    private void initState() {
        //当系统版本为4.4或者4.4以上时可以使用沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            mTransparentBar.setVisibility(View.VISIBLE);
            //获取到状态栏的高度
            //通过反射的方式获取状态栏高度
            try {
                Class<?> c = Class.forName("com.android.internal.R$dimen");
                Object obj = c.newInstance();
                Field field = c.getField("status_bar_height");
                int x = Integer.parseInt(field.get(obj).toString());
                statusHeight = getResources().getDimensionPixelSize(x);
            } catch (Exception e) {
                e.printStackTrace();
                statusHeight = 0;
            }
            //动态的设置隐藏布局的高度
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mTransparentBar.getLayoutParams();
            params.height = statusHeight;
            mTransparentBar.setLayoutParams(params);
        }
    }


    /**
     * 重写setContentView的方法。
     * 从子类获取FrameLayout并调用父类removeAllViews()方法
     * 将子类的试图加载到布局中
     */
    @Override
    public void setContentView(int layoutResID) {
        mContainer.removeAllViews();
        View.inflate(this, layoutResID, mContainer);
        onContentChanged();
    }

    @Override
    public void setContentView(View view) {
        mContainer.removeAllViews();
        mContainer.addView(view);
        onContentChanged();
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        mContainer.removeAllViews();
        mContainer.addView(view, params);
        onContentChanged();
    }


    /**
     * 设置状态栏的背景
     */
    public void setBarBackground(int backgroundColor) {
        if (mTransparentBar != null) {
            mTransparentBar.setBackgroundResource(backgroundColor);
        }
    }

    /**
     * 设置状态栏背景的透明度
     */
    public void setBarAlpha(@IntRange(from=0,to=255) int alpha) {
        if (mTransparentBar != null) {
            mTransparentBar.getBackground().setAlpha(alpha);
        }
    }

    /**
     * 设置状态栏背景显示/隐藏
     */
    public void setBarVisible(boolean visible) {
        mTransparentBar.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    /**
     * 获取状态栏的高度
     */
    public int getBarHeight() {
        return statusHeight;
    }


}
