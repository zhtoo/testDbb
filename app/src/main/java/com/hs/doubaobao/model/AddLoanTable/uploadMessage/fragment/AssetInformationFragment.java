package com.hs.doubaobao.model.AddLoanTable.uploadMessage.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.hs.doubaobao.R;
import com.hs.doubaobao.model.AddLoanTable.uploadMessage.FilloutLenderInformationActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * 作者：zhanghaitao on 2017/8/17 09:11
 * 邮箱：820159571@qq.com
 */

public class AssetInformationFragment extends Fragment {


    @BindView(R.id.lender_own_house_address)
    TextView mOwnHouseAddress;
    @BindView(R.id.lender_own_address_edit)
    EditText mOwnAddressEdit;
    @BindView(R.id.lender_own_street)
    TextView mOwnStreet;
    @BindView(R.id.lender_own_street_edit)
    EditText mOwnStreetEdit;
    @BindView(R.id.lender_own_house_area)
    TextView mOwnHouseArea;
    @BindView(R.id.lender_own_house_area_edit)
    EditText mOwnHouseAreaEdit;
    @BindView(R.id.lender_own_house_property)
    TextView mOwnHouseProperty;
    @BindView(R.id.lender_own_house_property_edit)
    EditText mOwnHousePropertyEdit;
    @BindView(R.id.lender_monthly_income)
    TextView mMonthlyIncome;
    @BindView(R.id.lender_monthly_income_edit)
    EditText mMonthlyIncomeEdit;
    Unbinder unbinder;
    private FilloutLenderInformationActivity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fillout_asset_info, null, false);
        unbinder = ButterKnife.bind(this, view);

        activity = (FilloutLenderInformationActivity) getActivity();

        initListener();
        return view;


    }


    private void initListener() {
        mOwnStreetEdit.addTextChangedListener(activity.editAssetListener);
        mOwnHouseAreaEdit.addTextChangedListener(activity.editAssetListener);
        mMonthlyIncomeEdit.addTextChangedListener(activity.editAssetListener);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.lender_monthly_income)
    public void onViewClicked() {
    }
}
