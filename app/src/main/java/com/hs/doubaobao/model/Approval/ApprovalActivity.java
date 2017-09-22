package com.hs.doubaobao.model.Approval;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

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
    private String showRightType;
    private String id;
    private TextView mOpinion;
    private TextView mQuota;
    private EditText mOpinionText;
    private Button mPass;
    private Button mSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approval);


        setTitleTextColor(R.color.textAggravating);
        setStatusBarBackground(R.drawable.ic_battery_bg);
        isShowRightView(false);
        initView();
        Intent intent = getIntent();
        showRightType = intent.getStringExtra("ShowRightType");
        id = intent.getStringExtra("ID");
        if (TextUtils.isEmpty(showRightType)) {
        } else if (showRightType.equals("RISK")) {
            setTitle(getString(R.string.risk_control));
            mOpinion.setText("风控意见");
            mQuota.setText("风控定额");

        } else if (showRightType.equals("MANAGER")) {
            setTitle(getString(R.string.general_manager));
            mOpinion.setText("总经理意见");
            mQuota.setText("总经理定额");
        }

        mClose.setOnClickListener(this);
        mSubmit.setOnClickListener(this);
        mNotPass.setOnClickListener(this);
        mPass.setOnClickListener(this);
        mSave.setOnClickListener(this);

    }

    private void initView() {
        mOpinion = (TextView) findViewById(R.id.approval_opinion);
        mQuota = (TextView) findViewById(R.id.approval_quota);
        mOpinionText = (EditText) findViewById(R.id.approval_opinion_text);
        mOpinionText = (EditText) findViewById(R.id.approval_quota_text);
        mPass = (Button) findViewById(R.id.approval_pass);
        mSave = (Button) findViewById(R.id.approval_save);
        mNotPass = (Button) findViewById(R.id.approval_not_pass);
        dialogView = (LinearLayout) findViewById(R.id.approval_not_pass_reason);
        mText = (EditText) findViewById(R.id.dailog_reason);
        mClose = (Button) findViewById(R.id.dialog_close);
        mSubmit = (Button) findViewById(R.id.dialog_submit);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        int id = v.getId();
        switch (id){
            case R.id.approval_pass:
                //TODO:通过联网提交
                break;
            case R.id.approval_save:
                //TODO:保存
                break;
            case R.id.approval_not_pass:
                dialogView.setVisibility(View.VISIBLE);
                mText.setFocusable(true);
                break;
            case R.id.dialog_submit:
                //TODO:不通过联网提交
                break;
            case R.id.dialog_close:
                dialogView.setVisibility(View.GONE);
                break;
        }
    }
}
