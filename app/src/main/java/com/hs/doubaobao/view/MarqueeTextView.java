package com.hs.doubaobao.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 作者：zhanghaitao on 2017/8/2 17:26
 * 邮箱：820159571@qq.com
 *
 * @des 跑马灯效果
 * <p>
 * XML布局
 * <com.xxx.xxxx.xxxx.MarqueeTextView
 * android:layout_width="wrap_content"
 * android:layout_height="wrap_content"
 * android:maxWidth="@dimen/x450"
 * android:lines="1"
 * android:ellipsize="marquee"
 * android:text="C先生一房dsfsdfdsfasdfsdfafawfasfasf子抵押"/>
 *
 *
 * <com.hs.doubaobao.view.MarqueeTextView
 * android:layout_width="wrap_content"
 * android:layout_height="wrap_content"
 * android:maxWidth="@dimen/x450"
 * android:lines="1"
 * android:ellipsize="marquee"
 * android:text="C先生一房dsfsdfdsfasdfsdfafawfasfasf子抵押"/>
 */

public class MarqueeTextView extends TextView {

    public MarqueeTextView(Context context) {
        super(context);
    }

    public MarqueeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MarqueeTextView(Context context, AttributeSet attrs,
                           int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean isFocused() {
        return true;

    }

}
