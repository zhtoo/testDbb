package com.hs.doubaobao.model.invalid;

import android.os.Bundle;

import com.hs.doubaobao.R;
import com.hs.doubaobao.base.AppBarActivity;

/**
 * 作者：zhanghaitao on 2017/9/11 17:44
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class InvalidReasonActivity extends AppBarActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invalid_reason);

        setTitle(getString(R.string.invalid_reason));
        setTitleTextColor(R.color.textAggravating);
        isShowRightView(false);
        setStatusBarBackground(R.drawable.ic_battery_bg);

    }
}
