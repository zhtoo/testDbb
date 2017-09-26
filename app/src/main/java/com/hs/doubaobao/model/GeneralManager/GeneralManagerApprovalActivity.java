package com.hs.doubaobao.model.GeneralManager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.hs.doubaobao.R;
import com.hs.doubaobao.adapter.ManagerAdapter;
import com.hs.doubaobao.base.AppBarActivity;
import com.hs.doubaobao.bean.HomeBean;
import com.hs.doubaobao.adapter.ListAdapter;
import com.hs.doubaobao.model.detail.DetailActivity;
import com.hs.doubaobao.bean.ListBean;
import com.hs.doubaobao.utils.PullToRefresh;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;

/**
 * 作者：zhanghaitao on 2017/9/11 17:43
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class GeneralManagerApprovalActivity extends AppBarActivity implements GMAContract.View, ManagerAdapter.onItemClickListener, ListAdapter.onItemClickListener, PullToRefresh.PullToRefreshListener {

    private GMAContract.Presenter presenter;
    private RecyclerView mRecyclerView;
    private ManagerAdapter adapter;
    private List<ListBean> mList = new ArrayList<>();
    private HomeBean.ResDataBean.PageDataListBean.PageBean pageBean;
    private List<HomeBean.ResDataBean.PageDataListBean.ListBean> listBeen;
    private PtrClassicFrameLayout ptrFrame;
    private int page = 1;
    private int pages = 1;
    private Map<String, Object> map;
    private PtrClassicFrameLayout ptrFrame1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_general_manager_approval);
        setTitle(getString(R.string.general_manager));
        setTitleTextColor(R.color.textAggravating);
        isShowRightView(false);
        setStatusBarBackground(R.drawable.ic_battery_bg);

        mRecyclerView = (RecyclerView) findViewById(R.id.manager_recycler_view);
        ptrFrame = (PtrClassicFrameLayout) findViewById(R.id.manager_ptr);
        ptrFrame1 = (PtrClassicFrameLayout) findViewById(R.id.manager_ptr1);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayout.VERTICAL);
        mRecyclerView.setLayoutManager(llm);
        adapter = new ManagerAdapter(this, mList, 1);
        adapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(adapter);


        initPtrClassicFrameLayout();

        //将Presenter和View进行绑定
        new GMAPresener(this, this);
        //获取数据
        map = new LinkedHashMap<>();
        loadData();

    }

    private void loadData() {
        map.put("page", page);
        map.put("rows", "10");
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
        refresh.initPTR(this, ptrFrame, adapter);
        //设置监听
        refresh.setPullToRefreshListener(this);
    }

    @Override
    public void setData(HomeBean bean) {
        //分页
        pageBean = bean.getResData().getPageDataList().getPage();
        //list内容
        listBeen = bean.getResData().getPageDataList().getList();

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
                mBean.setApproveStatus(listBeen.get(i).getApproveStatus());
                mList.add(mBean);
            }
            ptrFrame.setVisibility(View.VISIBLE);
            ptrFrame1.setVisibility(View.GONE);
        } else {
            ptrFrame.setVisibility(View.GONE);
            ptrFrame1.setVisibility(View.VISIBLE);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setError(String text) {
        ptrFrame.setVisibility(View.GONE);
        ptrFrame1.setVisibility(View.VISIBLE);
    }

    @Override
    public void setPresenter(GMAContract.Presenter presenter) {
        this.presenter = presenter;
    }


    @Override
    public void onItemClick(int postion) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("ID", listBeen.get(postion).getId() + "");
        intent.putExtra("ShowRightType", "MANAGER");
        intent.putExtra("ApproveStatus",listBeen.get(postion).getApproveStatus());
        if(listBeen.get(postion).getApproveStatus() == 1){
            intent.putExtra("Content",listBeen.get(postion).getContent());
            intent.putExtra("managerRation",((int)listBeen.get(postion).getManagerRation())+"");
        }
        startActivity(intent);
    }

    @Override
    public void pullToRefresh() {
        mList.clear();
        loadData();
    }

    @Override
    public void pullToLoadMore() {
        if (page >= pages) {
            Toast.makeText(this, "没有更多数据", Toast.LENGTH_SHORT).show();
        } else {
            page += 1;
            loadData();
        }
    }
}
