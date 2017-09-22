package com.hs.doubaobao.model.riskControl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.hs.doubaobao.R;
import com.hs.doubaobao.adapter.RiskAdapter;
import com.hs.doubaobao.base.AppBarActivity;
import com.hs.doubaobao.bean.HomeBean;
import com.hs.doubaobao.model.detail.DetailActivity;
import com.hs.doubaobao.model.main.ListBean;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者：zhanghaitao on 2017/9/11 17:42
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public  class RiskControlApprovalActivity extends AppBarActivity implements RiskApprovalContract.View, RiskAdapter.onItemClickListener {

    private RiskApprovalContract.Presenter presenter;
    private RecyclerView mRecyclerView;
    private RiskAdapter adapter;
    private List<ListBean> mList = new ArrayList<>();
    private HomeBean.ResDataBean.PageDataListBean.PageBean pageBean;
    private List<HomeBean.ResDataBean.PageDataListBean.ListBean> listBeen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risk_control_approval);
        setTitle(getString(R.string.risk_control));
        setTitleTextColor(R.color.textAggravating);
        isShowRightView(false);
        setStatusBarBackground(R.drawable.ic_battery_bg);

        mRecyclerView =(RecyclerView) findViewById(R.id.risk_recycler_view);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayout.VERTICAL);

        mRecyclerView.setLayoutManager(llm);

        adapter = new RiskAdapter(this, mList,1);

        adapter.setOnItemClickListener(this);

        mRecyclerView.setAdapter(adapter);

        new RiskApprovalPresenter(this);
        Map<String,String> map = new LinkedHashMap<>();
//        map.put("amount","3");
//        map.put("amount","3");
//        map.put("amount","3");
        map.put("page","1");
        map.put("rows","10");
        presenter.getData(map);

    }

    @Override
    public void setData(HomeBean bean) {

        //分页
        pageBean = bean.getResData().getPageDataList().getPage();
        //list内容
        listBeen = bean.getResData().getPageDataList().getList();
        mList.clear();
        if (listBeen != null && listBeen.size() > 0) {
            for (int i = 0; i < listBeen.size(); i++) {
                ListBean mBean = new ListBean();
                mBean.setName(listBeen.get(i).getCusName());
                mBean.setTime(listBeen.get(i).getApplydate());
                mBean.setPurpose(listBeen.get(i).getPurpose());
                mBean.setLoanAmount(listBeen.get(i).getAccount());
                mBean.setCustomPhone(listBeen.get(i).getMobilephone());
                mBean.setCustomManager(listBeen.get(i).getOperName());
                mBean.setLoanPeriods(listBeen.get(i).getPeriod());
                mBean.setStatus(listBeen.get(i).getStatus());
                mList.add(mBean);
            }
        }else{
            //TODO:空视图
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setError(String text) {

    }

    @Override
    public void setPresenter(RiskApprovalContract.Presenter presenter) {
            this.presenter = presenter;
    }


    @Override
    public void onItemClick(int postion) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("ID",listBeen.get(postion).getId()+"");
        intent.putExtra("ShowRightType","RISK");
        startActivity(intent);
    }

}
