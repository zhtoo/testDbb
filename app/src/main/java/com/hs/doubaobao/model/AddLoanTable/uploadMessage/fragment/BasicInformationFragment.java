package com.hs.doubaobao.model.AddLoanTable.uploadMessage.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.hs.doubaobao.R;
import com.hs.doubaobao.model.AddLoanTable.uploadMessage.FilloutLenderInformationActivity;
import com.hs.doubaobao.view.BottomDialog.SelectBottomDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * 作者：zhanghaitao on 2017/8/17 09:11
 * 邮箱：820159571@qq.com
 */

public class BasicInformationFragment extends Fragment {


    @BindView(R.id.lender_name)
    TextView mName;
    @BindView(R.id.lender_name_edit)
    EditText mNameEdit;
    @BindView(R.id.lender_id_card)
    TextView mIdCard;
    @BindView(R.id.lender_id_card_edit)
    EditText mIdCardEdit;
    @BindView(R.id.lender_sex)
    TextView mSex;
    @BindView(R.id.lender_sex_male)
    RadioButton mSexMale;
    @BindView(R.id.lender_sex_female)
    RadioButton mSexFemale;
    @BindView(R.id.lender_sex_group)
    RadioGroup mSexGroup;
    @BindView(R.id.lender_marital_status)
    TextView mMaritalStatus;
    @BindView(R.id.lender_marital_status_edit)
    LinearLayout mMaritalStatusEdit;
    @BindView(R.id.lender_domicile)
    TextView mDomicile;
    @BindView(R.id.lender_domicile_edit)
    EditText mDomicileEdit;
    Unbinder unbinder;
    @BindView(R.id.lender_marital_status_text)
    TextView mMaritalStatusText;
    @BindView(R.id.lender_phone)
    TextView mPhone;
    @BindView(R.id.lender_phone_edit)
    EditText mPhoneEdit;
    private FilloutLenderInformationActivity activity;
    private boolean maleChecked;
    private boolean femaleChecked;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fillout_basic_info, null, false);
        unbinder = ButterKnife.bind(this, view);

        activity = (FilloutLenderInformationActivity) getActivity();
        //mSexMale.setChecked(true);
        intitState();
        initListener();
        return view;
    }

    /**
     * 初始化状态
     */
    private void intitState() {

        maleChecked = mSexMale.isChecked();
        femaleChecked = mSexFemale.isChecked();
        if (maleChecked || femaleChecked) {
            activity.changeProgress(activity.mBasicProgress, 1);
        }


    }

    private void initListener() {
        mNameEdit.addTextChangedListener(activity.editBasicListener);
        mIdCardEdit.addTextChangedListener(activity.editBasicListener);
        mDomicileEdit.addTextChangedListener(activity.editBasicListener);
        mPhoneEdit.addTextChangedListener(activity.editBasicListener);
        mSexGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //如果在初始化之前都没有被选择
                if (!maleChecked && !femaleChecked) {
                    switch (checkedId) {
                        case R.id.lender_sex_male:
                            activity.changeProgress(activity.mBasicProgress, 1);
                            break;
                        case R.id.lender_sex_female:
                            activity.changeProgress(activity.mBasicProgress, 1);
                            break;
                    }
                }
                maleChecked = mSexMale.isChecked();
                femaleChecked = mSexFemale.isChecked();
            }
        });

        mMaritalStatusEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });


    }

    private void showDialog() {
        SelectBottomDialog dialog = new SelectBottomDialog();
        dialog.setItemStrings(getContext(), new String[]{"未婚", "已婚", "离异"});
        dialog.show(activity.getSupportFragmentManager());
        dialog.setOnClickListener(new SelectBottomDialog.onItemClickListener() {
            @Override
            public void onClick(String text) {
                String oldText = mMaritalStatusText.getText().toString().trim();
                if(oldText.equals("请选择")&&!TextUtils.isEmpty(text)){
                    activity.changeProgress(activity.mBasicProgress, 1);
                }
                mMaritalStatusText.setText(text);
            }
        });


    }


    @OnClick({R.id.lender_name, R.id.lender_name_edit, R.id.lender_id_card, R.id.lender_id_card_edit, R.id.lender_sex, R.id.lender_sex_male, R.id.lender_sex_female, R.id.lender_sex_group, R.id.lender_marital_status, R.id.lender_marital_status_edit, R.id.lender_domicile, R.id.lender_domicile_edit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lender_name:
                break;
            case R.id.lender_name_edit:
                break;
            case R.id.lender_id_card:
                break;
            case R.id.lender_id_card_edit:
                break;
            case R.id.lender_sex:
                break;
            case R.id.lender_sex_male:
                break;
            case R.id.lender_sex_female:
                break;
            case R.id.lender_sex_group:
                break;
            case R.id.lender_marital_status:
                break;
            case R.id.lender_marital_status_edit:
                break;
            case R.id.lender_domicile:
                break;
            case R.id.lender_domicile_edit:
                break;
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
