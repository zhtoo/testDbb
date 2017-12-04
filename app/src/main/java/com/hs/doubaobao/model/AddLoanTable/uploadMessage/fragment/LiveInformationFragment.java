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
import butterknife.Unbinder;


/**
 * 作者：zhanghaitao on 2017/8/17 09:11
 * 邮箱：820159571@qq.com
 */

public class LiveInformationFragment extends Fragment {


    @BindView(R.id.lender_live_address)
    TextView mLiveAddress;
    @BindView(R.id.lender_live_address_edit)
    EditText mLiveAddressEdit;
    @BindView(R.id.lender_live_street)
    TextView mLiveStreet;
    @BindView(R.id.lender_live_street_edit)
    EditText mLiveStreetEdit;
    @BindView(R.id.lender_support_number)
    TextView mSupportNumber;
    @BindView(R.id.lender_support_number_edit)
    EditText mSupportNumberEdit;
    Unbinder unbinder;
    private FilloutLenderInformationActivity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fillout_live_info, null, false);
        unbinder = ButterKnife.bind(this, view);

        activity = (FilloutLenderInformationActivity) getActivity();

        initListener();
        return view;


    }


    private void initListener() {
        mLiveAddressEdit.addTextChangedListener(activity.editLiveListener);
        mLiveStreetEdit.addTextChangedListener(activity.editLiveListener);
        mSupportNumberEdit.addTextChangedListener(activity.editLiveListener);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
