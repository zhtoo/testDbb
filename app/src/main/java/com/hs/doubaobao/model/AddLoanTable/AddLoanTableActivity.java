package com.hs.doubaobao.model.AddLoanTable;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hs.doubaobao.R;
import com.hs.doubaobao.base.AppBarActivity;
import com.hs.doubaobao.model.AddLoanTable.uploadMessage.FilloutLenderInformationActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：zhanghaitao on 2017/11/23 15:38
 * 邮箱：820159571@qq.com
 *
 * @describe:资料填写的入口界面+状态提交的界面
 */

public class AddLoanTableActivity extends AppBarActivity {


    @BindView(R.id.add_lender_information)
    TextView addLenderInformation;
    @BindView(R.id.add_the_loans)
    TextView addTheLoans;
    @BindView(R.id.add_contact_information)
    TextView addContactInformation;
    @BindView(R.id.add_investigation_and_opinion)
    TextView addInvestigationAndOpinion;
    @BindView(R.id.add_lender_information_optional)
    TextView addLenderInformationOptional;
    @BindView(R.id.add_mutual_lender_information)
    TextView addMutualLenderInformation;
    @BindView(R.id.add_car_information)
    TextView addCarInformation;
    @BindView(R.id.add_internet_information)
    TextView addInternetInformation;
    @BindView(R.id.add_upload_picture)
    TextView addUploadPicture;
    @BindView(R.id.add_upload_vidoe)
    TextView addUploadVidoe;
    @BindView(R.id.add_submit)
    Button mSubmit;


    private String customId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_loan_table);
        ButterKnife.bind(this);
        setTitle("填写资料");
        isShowRightView(false);


    }

    private int[] ids = {R.id.add_the_loans, R.id.add_contact_information,
            R.id.add_investigation_and_opinion, R.id.add_lender_information_optional,
            R.id.add_mutual_lender_information, R.id.add_car_information,
            R.id.add_internet_information, R.id.add_upload_picture, R.id.add_upload_vidoe};

    @OnClick({R.id.add_lender_information, R.id.add_the_loans,
            R.id.add_contact_information, R.id.add_investigation_and_opinion,
            R.id.add_lender_information_optional, R.id.add_mutual_lender_information,
            R.id.add_car_information, R.id.add_internet_information,
            R.id.add_upload_picture, R.id.add_upload_vidoe, R.id.add_submit})
    public void onViewClicked(View view) {

        int viewId = view.getId();


        for (int i = 0; i < ids.length; i++) {
            if (viewId == ids[i]) {
                if (TextUtils.isEmpty(customId)) {
                    //TODO:提示不能跳转界面
                    Toast.makeText(this, "请先保存贷款人信息", Toast.LENGTH_SHORT).show();
                } else {
                    //TODO:跳转界面
                }
            }
        }
        switch (viewId) {
            case R.id.add_lender_information:
                //TODO：直接跳转贷款人信息填写界面
                Intent intent = new Intent(this, FilloutLenderInformationActivity.class);
                startActivity(intent);

                break;
            case R.id.add_submit:
                //TODO：联网申请改变客户的申请状态
                break;
        }
    }


}
