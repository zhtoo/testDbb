package com.hs.doubaobao.model.Login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.hs.doubaobao.R;
import com.hs.doubaobao.base.AppBarActivity;


/**
 * Created by zhanghaitao on 2017/5/23.
 */

public class GuideActivity extends AppBarActivity {

    private Button passed;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        hideTitleBar();

        passed = (Button) findViewById(R.id.passed);
        handler.sendEmptyMessageDelayed(0,3000);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            skip2Login();
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }

    public void pass(View v) {
        handler.removeCallbacksAndMessages(null);
        skip2Login();
    }

    private void skip2Login() {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
