package com.hs.doubaobao.model.detail.reference;

import android.view.View;
import android.widget.TextView;

import com.hs.doubaobao.R;
import com.hs.doubaobao.base.BaseFragment;
import com.hs.doubaobao.bean.ReferenceBean;
import com.hs.doubaobao.model.detail.DetailActivity;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 作者：zhanghaitao on 2017/9/12 15:12
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class ReferenceFragment extends BaseFragment implements ReferenceContract.View {

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

    @Override
    protected int setLayout() {
        return R.layout.fragment_reference;
    }

    @Override
    protected void initView(View view) {
       mName =(TextView)view.findViewById(R.id.reference_name);
       mManager =(TextView)view.findViewById(R.id.reference_manager);
       mHouseLoantime =(TextView)view.findViewById(R.id.reference_house_loantime);
       mHouseLoantimeValues =(TextView)view.findViewById(R.id.reference_house_loantime_values);
       mHouseAddress =(TextView)view.findViewById(R.id.reference_house_address);
       mHouseAddressValue =(TextView)view.findViewById(R.id.reference_house_address_value);
       mHouseNatrue =(TextView)view.findViewById(R.id.reference_house_natrue);
       mHouseNatrueValue =(TextView)view.findViewById(R.id.reference_house_natrue_value);
       mCar =(TextView)view.findViewById(R.id.reference_car);
       mCarValue =(TextView)view.findViewById(R.id.reference_car_value);
       mFamily =(TextView)view.findViewById(R.id.reference_family);
       mFamilyValue =(TextView)view.findViewById(R.id.reference_family_value);
       mWorkNatrue =(TextView)view.findViewById(R.id.reference_work_natrue);
       mWorkNatrueValue =(TextView)view.findViewById(R.id.reference_work_natrue_value);
       mProprietor =(TextView)view.findViewById(R.id.reference_proprietor);
       mProprietorValue =(TextView)view.findViewById(R.id.reference_proprietor_value);
       mCredit =(TextView)view.findViewById(R.id.reference_credit);
       mCreditNumone =(TextView)view.findViewById(R.id.reference_credit_numone);
       mCreditNumthree =(TextView)view.findViewById(R.id.reference_credit_numthree);
       mCreditNumper =(TextView)view.findViewById(R.id.reference_credit_numper);
       mCreditNumtotal =(TextView)view.findViewById(R.id.reference_credit_numtotal);
       mCreditFinalRation =(TextView)view.findViewById(R.id.reference_credit_final_ration);
       mFinalRation =(TextView)view.findViewById(R.id.reference_final_ration);


        DetailActivity activity = (DetailActivity) getActivity();
        String id = activity.id;

        //将Presenter和View进行绑定
        new ReferencePresener(this,getContext());
        //获取数据

        Map<String, String> map = new LinkedHashMap<>();
        map.put("id", id);
        presenter.getData(map);
    }

    @Override
    public void setData(ReferenceBean bean) {
        ReferenceBean.ResDataBean.CustomerRationBean ration = bean.getResData().getCustomerRation();

        mName.setText(ration.getCname());
        mManager.setText(ration.getOperatorName());
        mHouseLoantime.setText(ration.getBuildBorrowTimeString());
        mHouseLoantimeValues.setText(ration.getFinalRationBbt());
        mHouseAddress.setText(ration.getBuildAddrString());
        mHouseAddressValue.setText("");
        mHouseNatrue.setText(ration.getBuildTypeString());
        mHouseNatrueValue.setText(ration.getFinalRationBt());
        mCar.setText(ration.getCarString());
        mCarValue.setText(ration.getFinalRationCar());
        mFamily.setText(ration.getFamilyString());
        mFamilyValue.setText(ration.getFinalRationFamily());
        mWorkNatrue.setText(ration.getWorkunitString());
        mWorkNatrueValue.setText(ration.getFinalRationWorkunit());
        mProprietor.setText(ration.getProprietorString());
        mProprietorValue.setText(ration.getFinalRationProprietor());
        mCredit.setText(ration.getCreditString());
        mCreditNumone.setText(ration.getCreditNumOne());
        mCreditNumthree.setText(ration.getCreditNumThree());
        mCreditNumper.setText(ration.getCreditNumPer());
        mCreditNumtotal.setText(ration.getCreditNumTotal());
        mCreditFinalRation.setText(ration.getFinalRationCredit());
        mFinalRation.setText(ration.getFinalRation());

    }

    @Override
    public void setError(String text) {

    }

    @Override
    public void setPresenter(ReferenceContract.Presenter presenter) {
        this.presenter = presenter;
    }



}
