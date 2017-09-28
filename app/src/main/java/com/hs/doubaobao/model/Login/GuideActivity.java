package com.hs.doubaobao.model.Login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;

import com.hs.doubaobao.R;
import com.hs.doubaobao.base.AppBarActivity;

/**
 * Created by zhanghaitao on 2017/5/23.
 * 向导界面
 */

public class GuideActivity extends AppBarActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        hideTitleBar();

        handler.sendEmptyMessageDelayed(0,3000);
    }

    /**
     * 处理跳转
     */
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            skip2Login();
            super.handleMessage(msg);
        }
    };

    /**
     * 当Activity销毁的时候
     */
    @Override
    protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }

    /**
     * 点击跳过
     * @param v
     */
    public void pass(View v) {
        handler.removeCallbacksAndMessages(null);
        skip2Login();
    }

    /**
     * 跳转到登录界面
     */
    private void skip2Login() {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
