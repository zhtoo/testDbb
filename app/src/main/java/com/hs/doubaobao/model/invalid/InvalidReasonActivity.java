package com.hs.doubaobao.model.invalid;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.hs.doubaobao.MyApplication;
import com.hs.doubaobao.R;
import com.hs.doubaobao.base.AppBarActivity;
import com.hs.doubaobao.bean.InvalidReasonBean;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 作者：zhanghaitao on 2017/9/11 17:44
 * 邮箱：820159571@qq.com
 *
 * @describe:无效原因查看
 */

public class InvalidReasonActivity extends AppBarActivity implements InvalidReasonContract.View {


    private TextView mName;
    private TextView mManager;
    private TextView mWriter;
    private TextView mTime;
    private TextView mReason;

    private InvalidReasonContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invalid_reason);

        setTitle(getString(R.string.invalid_reason));
        setTitleTextColor(R.color.textAggravating);
        isShowRightView(false);
        setStatusBarBackground(R.drawable.ic_battery_bg);

        Intent intent = getIntent();
        String invalidId = intent.getStringExtra("invalidId");

        initView();

        new InvalidReasonPresener(this,this);
        Map<String, String> map = new LinkedHashMap<>();
        map.put("id", invalidId);
        presenter.getData(map);
    }

    private void initView() {
        mName = (TextView) findViewById(R.id.invalid_name);
        mManager = (TextView) findViewById(R.id.invalid_manager);
        mWriter = (TextView) findViewById(R.id.invalid_writer);
        mTime = (TextView) findViewById(R.id.invalid_time);
        mReason = (TextView) findViewById(R.id.invalid_reason);
    }

    @Override
    public void setData(InvalidReasonBean bean) {
        InvalidReasonBean.ResDataBean.DisableDetailBean detail = bean.getResData().getDisableDetail();

        mName.setText(detail.getCname());
        mManager.setText(detail.getOperatorName());
        mWriter.setText(detail.getAuditor());
        mTime.setText(detail.getAuditorTime());
        mReason.setText(detail.getRemark());
    }

    @Override
    public void setError(String text) {
        Toast.makeText(MyApplication.getContext(), "网络不给力", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(InvalidReasonContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
