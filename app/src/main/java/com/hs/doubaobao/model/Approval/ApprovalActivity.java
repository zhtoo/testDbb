package com.hs.doubaobao.model.Approval;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.hs.doubaobao.R;
import com.hs.doubaobao.base.AppBarActivity;

/**
 * 作者：zhanghaitao on 2017/9/15 15:50
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class ApprovalActivity extends AppBarActivity {


    private Button mNotPass;
    private EditText mText;
    private Button mClose;
    private Button mSubmit;
    private LinearLayout dialogView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approval);

        setTitle(getString(R.string.invalid_reason));
        setTitleTextColor(R.color.textAggravating);
        isShowRightView(false);
        setStatusBarBackground(R.drawable.ic_battery_bg);


        dialogView = (LinearLayout) findViewById(R.id.approval_not_pass_reason);
        mText = (EditText) findViewById(R.id.dailog_reason);
        mClose = (Button) findViewById(R.id.dialog_close);
        mSubmit = (Button) findViewById(R.id.dialog_submit);

        mClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogView.setVisibility(View.GONE);
            }
        });
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        mNotPass = (Button) findViewById(R.id.approval_not_pass);
        mNotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogView.setVisibility(View.VISIBLE);
            }
        });

    }


}
