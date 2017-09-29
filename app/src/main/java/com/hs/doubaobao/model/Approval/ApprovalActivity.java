package com.hs.doubaobao.model.Approval;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hs.doubaobao.R;
import com.hs.doubaobao.base.AppBarActivity;
import com.hs.doubaobao.bean.ApprovalBean;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 作者：zhanghaitao on 2017/9/15 15:50
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class ApprovalActivity extends AppBarActivity implements ApprovalContract.View {

    private ApprovalContract.Presenter presenter;
    private Map<String, Object> map;

    private Button mNotPass;
    private EditText mText;
    private Button mClose;
    private Button mSubmit;
    private LinearLayout dialogView;
    private String showRightType;
    private String id;
    private int type;
    private TextView mOpinion;
    private TextView mQuota;
    private EditText mOpinionText;
    private Button mPass;
    private Button mSave;
    private EditText mQuotaText;
    private String mApproveContent;
    private String mRiskControl;
    private String mManagerRation;
    private int mApproveStatus;
    private int mApprove = 0;

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

        mApproveStatus = intent.getIntExtra("ApproveStatus", 0);
        mApproveContent = intent.getStringExtra("Content");
        mRiskControl = intent.getStringExtra("riskControl");
        mManagerRation = intent.getStringExtra("managerRation");

        id = intent.getStringExtra("ID");
        if (TextUtils.isEmpty(showRightType)) {
        } else if (showRightType.equals("RISK")) {
            setTitle(getString(R.string.risk_control));
            mOpinion.setText("风控意见:");
            mQuota.setText("风控定额:");
            if (mApproveStatus == 1) {
                mOpinionText.setText(mApproveContent);
                mQuotaText.setText(mRiskControl);
            }
            type = 3;
        } else if (showRightType.equals("MANAGER")) {
            setTitle(getString(R.string.general_manager));
            mOpinion.setText("总经理意见:");
            mQuota.setText("总经理定额:");
            if (mApproveStatus == 1) {
                mOpinionText.setText(mApproveContent);
                mQuotaText.setText(mManagerRation);
            }
            type = 4;
        }

        mClose.setOnClickListener(this);
        mSubmit.setOnClickListener(this);
        mNotPass.setOnClickListener(this);
        mPass.setOnClickListener(this);
        mSave.setOnClickListener(this);

        //将Presenter和View进行绑定
        new ApprovalPresener(this, this);
        //获取数据

    }

    private void loadData(int status, String content, String riskControl, String remark) {
        map = new LinkedHashMap<>();
        map.put("id", id);//借款id
        map.put("type", type);//1：初审人员审批，2：家访审批，3：风控人员审批，:4：总经理审批
        map.put("status", status);//1：同意，2：不同意，3：保存
        if (!TextUtils.isEmpty(content)) {
            map.put("content", content);//通过审批内容
        }
        if (!TextUtils.isEmpty(riskControl)) {
            if (riskControl.startsWith(".")) {
                riskControl = "0" + riskControl;
            }
            map.put("riskControl", riskControl);//通过的定额
        }
        if (!TextUtils.isEmpty(remark)) {
            map.put("remark", remark);//不通过的理由
        }
        presenter.getData(map);
    }

    private void initView() {
        mOpinion = (TextView) findViewById(R.id.approval_opinion);
        mQuota = (TextView) findViewById(R.id.approval_quota);
        mOpinionText = (EditText) findViewById(R.id.approval_opinion_text);
        mQuotaText = (EditText) findViewById(R.id.approval_quota_text);
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
        String content = mOpinionText.getText().toString().trim();
        String riskControl = mQuotaText.getText().toString().trim();
        String remark = mText.getText().toString().trim();
        switch (id) {
            case R.id.approval_pass:
                //通过
                mApprove = 1;
                checkNull(1, content, riskControl);
                break;
            case R.id.approval_save:
                //保存
                // loadData(3,content,riskControl,"");
                mApprove = 3;
                checkNull(3, content, riskControl);
                break;
            case R.id.approval_not_pass:
                dialogView.setVisibility(View.VISIBLE);
                mText.setFocusable(true);
                break;
            case R.id.dialog_submit:
                //不通过
                if (TextUtils.isEmpty(remark)) {
                    Toast.makeText(this, "请填写不通过理由", Toast.LENGTH_SHORT).show();
                } else {
                    mApprove = 2;
                    loadData(2, "", "", remark);
                }
                break;
            case R.id.dialog_close:
                dialogView.setVisibility(View.GONE);
                break;
        }
    }

    void checkNull(int status, String content, String riskControl) {
        if (TextUtils.isEmpty(content)) {
            Toast.makeText(this, "请填写意见", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(riskControl)) {
            Toast.makeText(this, "金额", Toast.LENGTH_SHORT).show();
        } else {
            loadData(status, content, riskControl, "");
        }
    }

    @Override
    public void setData(ApprovalBean bean) {
        String[] aar = {"您没有任何操作", "审批成功", "审批成功", "保存成功"};
        Toast.makeText(this, aar[mApprove], Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void setError(String text) {
        String[] aar = {"您没有任何操作", "审批失败", "审批失败", "保存成功"};
        Toast.makeText(this, aar[mApprove] + "，请稍后再试", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(ApprovalContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
