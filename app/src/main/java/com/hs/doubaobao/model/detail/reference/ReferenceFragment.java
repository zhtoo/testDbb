package com.hs.doubaobao.model.detail.reference;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hs.doubaobao.R;
import com.hs.doubaobao.base.BaseFragment;
import com.hs.doubaobao.bean.ReferenceBean;
import com.hs.doubaobao.model.detail.DetailActivity;
import com.hs.doubaobao.utils.PullToRefresh;

import java.util.LinkedHashMap;
import java.util.Map;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;

/**
 * 作者：zhanghaitao on 2017/9/12 15:12
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class ReferenceFragment extends BaseFragment implements ReferenceContract.View, PullToRefresh.PullToRefreshListener {

    private TextView mName;
    private TextView mManager;
    private TextView mHouseLoantime;
    private TextView mHouseLoantimeValues;
    private TextView mHouseAddress;
    private TextView mHouseAddressValue;
    private TextView mHouseNatrue;
    private TextView mHouseNatrueValue;
    private TextView mCar;
    private TextView mCarValue;
    private TextView mFamily;
    private TextView mFamilyValue;
    private TextView mWorkNatrue;
    private TextView mWorkNatrueValue;
    private TextView mProprietor;
    private TextView mProprietorValue;
    private TextView mCredit;
    private TextView mCreditNumone;
    private TextView mCreditNumthree;
    private TextView mCreditNumper;
    private TextView mCreditNumtotal;
    private TextView mCreditFinalRation;
    private TextView mFinalRation;

    private ReferenceContract.Presenter presenter;
    private PtrClassicFrameLayout ptrFrame;
    private ViewGroup mContainer;
    private Map<String, String> map;

    @Override
    protected int setLayout() {
        return R.layout.fragment_reference;
    }

    @Override
    protected void initView(View view) {


        mContainer = (LinearLayout) view.findViewById(R.id.reference_container);
        ptrFrame = (PtrClassicFrameLayout) view.findViewById(R.id.reference_ptr);
        initPtrClassicFrameLayout();


        mName = (TextView) view.findViewById(R.id.reference_name);
        mManager = (TextView) view.findViewById(R.id.reference_manager);
        mHouseLoantime = (TextView) view.findViewById(R.id.reference_house_loantime);
        mHouseLoantimeValues = (TextView) view.findViewById(R.id.reference_house_loantime_values);
        mHouseAddress = (TextView) view.findViewById(R.id.reference_house_address);
        mHouseAddressValue = (TextView) view.findViewById(R.id.reference_house_address_value);
        mHouseNatrue = (TextView) view.findViewById(R.id.reference_house_natrue);
        mHouseNatrueValue = (TextView) view.findViewById(R.id.reference_house_natrue_value);
        mCar = (TextView) view.findViewById(R.id.reference_car);
        mCarValue = (TextView) view.findViewById(R.id.reference_car_value);
        mFamily = (TextView) view.findViewById(R.id.reference_family);
        mFamilyValue = (TextView) view.findViewById(R.id.reference_family_value);
        mWorkNatrue = (TextView) view.findViewById(R.id.reference_work_natrue);
        mWorkNatrueValue = (TextView) view.findViewById(R.id.reference_work_natrue_value);
        mProprietor = (TextView) view.findViewById(R.id.reference_proprietor);
        mProprietorValue = (TextView) view.findViewById(R.id.reference_proprietor_value);
        mCredit = (TextView) view.findViewById(R.id.reference_credit);
        mCreditNumone = (TextView) view.findViewById(R.id.reference_credit_numone);
        mCreditNumthree = (TextView) view.findViewById(R.id.reference_credit_numthree);
        mCreditNumper = (TextView) view.findViewById(R.id.reference_credit_numper);
        mCreditNumtotal = (TextView) view.findViewById(R.id.reference_credit_numtotal);
        mCreditFinalRation = (TextView) view.findViewById(R.id.reference_credit_final_ration);
        mFinalRation = (TextView) view.findViewById(R.id.reference_final_ration);

        DetailActivity activity = (DetailActivity) getActivity();
        String id = activity.id;

        //将Presenter和View进行绑定
        new ReferencePresener(this, getContext());
        //获取数据

        map = new LinkedHashMap<>();
        map.put("id", id);
        presenter.getData(map);
    }

    /**
     * 初始化上拉加载下拉刷新的布局
     * 注意：adapter的初始化在 PullToRefresh 之前
     */
    private void initPtrClassicFrameLayout() {
        //注意：adapter的初始化在 PullToRefresh 之前
        //创建PtrClassicFrameLayout的包装类对象
        PullToRefresh refresh = new PullToRefresh();
        //初始化PtrClassicFrameLayout
        refresh.initPTR(getContext(), ptrFrame);
        //设置监听
        refresh.setPullToRefreshListener(this);
    }

    @Override
    public void setData(ReferenceBean bean) {
        mContainer.setVisibility(View.VISIBLE);

        ReferenceBean.ResDataBean.CustomerRationBean ration = bean.getResData().getCustomerRation();

        mName.setText(checkString(ration.getCname()));
        mManager.setText(checkString(ration.getOperatorName()));
        mHouseLoantime.setText(checkString(ration.getBuildBorrowTimeString()));
        mHouseLoantimeValues.setText(checkString(ration.getFinalRationBbt()));

        if (TextUtils.isEmpty(ration.getFinalRationBa())) {
            mHouseAddress.setText(checkString(ration.getBuildAddrString()) + "( 市场均价：未填写)");
        } else {
            mHouseAddress.setText(checkString(ration.getBuildAddrString()) + "( 市场均价：" + ration.getFinalRationBa()
                    + ")");
        }

        mHouseAddressValue.setText("");

        mHouseNatrue.setText(checkString(ration.getBuildTypeString()));
        mHouseNatrueValue.setText(checkString(ration.getFinalRationBt()));
        mCar.setText(checkString(ration.getCarString()));
        mCarValue.setText(checkString(ration.getFinalRationCar()));
        mFamily.setText(checkString(ration.getFamilyString()));
        mFamilyValue.setText(checkString(ration.getFinalRationFamily()));
        mWorkNatrue.setText(checkString(ration.getWorkunitString()));
        mWorkNatrueValue.setText(checkString(ration.getFinalRationWorkunit()));
        mProprietor.setText(checkString(ration.getProprietorString()));
        mProprietorValue.setText(checkString(ration.getFinalRationProprietor()));
        mCredit.setText(checkString(ration.getCreditString()));
        mCreditNumone.setText(checkString(ration.getCreditNumOne()));
        mCreditNumthree.setText(checkString(ration.getCreditNumThree()));
        mCreditNumper.setText(checkString(ration.getCreditNumPer()));
        mCreditNumtotal.setText(checkString(ration.getCreditNumTotal()));
        mCreditFinalRation.setText(checkString(ration.getFinalRationCredit()));
        mFinalRation.setText(checkString(ration.getFinalRation()));

    }

    public String checkString(String value) {
        return TextUtils.isEmpty(value) ? "未填写" : value;
    }

    @Override
    public void setError(String text) {
        mContainer.setVisibility(View.GONE);
    }

    @Override
    public void setPresenter(ReferenceContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void pullToRefresh() {
        presenter.getData(map);
    }

    @Override
    public void pullToLoadMore() {
        presenter.getData(map);
    }
}
