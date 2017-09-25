package com.hs.doubaobao.model.invalid;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.hs.doubaobao.R;
import com.hs.doubaobao.adapter.InvalidAdapter;
import com.hs.doubaobao.base.AppBarActivity;
import com.hs.doubaobao.base.BaseParams;
import com.hs.doubaobao.bean.HomeBean;
import com.hs.doubaobao.model.main.ListBean;
import com.hs.doubaobao.utils.PullToRefresh;
import com.hs.doubaobao.view.MyRelativeLayout;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;

/**
 * 作者：zhanghaitao on 2017/9/11 17:44
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class InvalidListActivity extends AppBarActivity implements InvalidListContract.View, InvalidAdapter.onItemClickListener, PullToRefresh.PullToRefreshListener {

    private InvalidListContract.Presenter presenter;
    private RecyclerView mRecyclerView;
    private MyRelativeLayout mSearchContainer;
    private LinearLayout mSearch;
    private boolean isShowing = false;
    private Button reset;
    private Button start;
    private InvalidAdapter adapter;
    private List<ListBean> mList = new ArrayList<>();
    private HomeBean.ResDataBean.PageDataListBean.PageBean pageBean;
    private List<HomeBean.ResDataBean.PageDataListBean.ListBean> listBeen;

    private PtrClassicFrameLayout ptrFrame;
    private Map<String, Object> map;
    private int page = 1;
    private int pages = 1;
    private EditText mSearchName;
    private EditText mSearchOpeName;
    private EditText mSearchPhone;
    private PtrClassicFrameLayout ptrFrame1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invalid_list);

        setTitle(getString(R.string.invalid_list));
        setTitleTextColor(R.color.textAggravating);
        setRightStatus(R.drawable.ic_invalid_search_selector);
        setStatusBarBackground(R.drawable.ic_battery_bg);

        initView();

        new InvalidListPresenter(this,this);

        map = new LinkedHashMap<>();
        loadData("", "", "");
    }

    private void loadData(String name, String opeName, String phone) {
        map.put("userId", BaseParams.USER_ID);
        map.put("page", page);
        map.put("rows", "10");
        if (!TextUtils.isEmpty(name)) {
            map.put("cusName", name);
        } else {
            map.put("cusName", "");
        }
        if (!TextUtils.isEmpty(opeName)) {
            map.put("opeName", opeName);
        } else {
            map.put("opeName", "");
        }
        if (!TextUtils.isEmpty(phone)) {
            map.put("phone", phone);
        } else {
            map.put("phone", "");
        }
        presenter.getData(map);
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.invalid_recycler_view);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayout.VERTICAL);
        mRecyclerView.setLayoutManager(llm);

        adapter = new InvalidAdapter(this, mList, 4);
        adapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(adapter);

        mSearch = (LinearLayout) findViewById(R.id.invalid_search);
        mSearchContainer = (MyRelativeLayout) findViewById(R.id.invalid_search_container);
        mSearchContainer.setVisibility(View.GONE);

        reset = (Button) findViewById(R.id.search_reset);
        start = (Button) findViewById(R.id.search_start);

        ptrFrame = (PtrClassicFrameLayout) findViewById(R.id.invalid_ptr);
        ptrFrame1 = (PtrClassicFrameLayout) findViewById(R.id.invalid_ptr1);
        mSearchName = (EditText) findViewById(R.id.search_name);
        mSearchOpeName = (EditText) findViewById(R.id.search_opename);
        mSearchPhone = (EditText) findViewById(R.id.search_phone);
        initPtrClassicFrameLayout();

        //设置状态栏的背景
        mSearch.setOnClickListener(this);
        mSearchContainer.setOnClickListener(this);
        reset.setOnClickListener(this);
        start.setOnClickListener(this);
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
        refresh.initPTR(this, ptrFrame1, adapter);
        //设置监听
        refresh.setPullToRefreshListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        int viewID = v.getId();
        switch (viewID) {
            case R.id.invalid_search_container:
                hideInput(v);
                if (isShowing) {
                    isShowing = false;
                    ShowSearchAnimator(1f, 0f);
                }
                break;
            case R.id.search_reset:
                mSearchName.setText("");
                mSearchOpeName.setText("");
                mSearchPhone.setText("");
                //Toast.makeText(this, "无效列表的重置", Toast.LENGTH_SHORT).show();
                break;
            case R.id.search_start:
                hideInput(v);
                if (isShowing) {
                    isShowing = false;
                    ShowSearchAnimator(1f, 0f);
                }
                mList.clear();
                page = 1;
                String name = mSearchName.getText().toString().trim();
                String opeName = mSearchOpeName.getText().toString().trim();
                String phone = mSearchPhone.getText().toString().trim();
                loadData(name, opeName, phone);
               // Toast.makeText(this, "无效列表的完成", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onRightForward(View forwardView) {
        hideInput(forwardView);
        if (isShowing) {
            isShowing = false;
            ShowSearchAnimator(1f, 0f);
        } else {
            mSearchName.setText("");
            mSearchOpeName.setText("");
            mSearchPhone.setText("");
            isShowing = true;
            mSearchContainer.setVisibility(View.VISIBLE);
            mSearch.setVisibility(View.VISIBLE);
            ShowSearchAnimator(0f, 1f);
        }
    }

    /**
     * 搜索菜单显示和隐藏的动画
     *
     * @param start
     * @param end
     */
    private void ShowSearchAnimator(float start, float end) {
        ObjectAnimator anim = ObjectAnimator//
                .ofFloat(mSearch, "zht", start, end)//
                .setDuration(300);//
        anim.start();
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float cVal = (Float) animation.getAnimatedValue();
                mSearch.setAlpha(cVal);
                mSearch.setScaleX(cVal);
                mSearch.setScaleY(cVal);
            }
        });

        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (!isShowing) {
                    mSearch.setVisibility(View.GONE);
                    mSearchContainer.setVisibility(View.GONE);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
    }

    @Override
    public void setData(HomeBean bean) {
        //分页
        pageBean = bean.getResData().getPageDataList().getPage();
        //list内容
        listBeen = bean.getResData().getPageDataList().getList();
        pages = pageBean.getPages();

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
            ptrFrame1.setVisibility(View.GONE);
            ptrFrame.setVisibility(View.VISIBLE);
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
    public void setPresenter(InvalidListContract.Presenter presenter) {
        this.presenter = presenter;
    }

    /**
     * 隐藏软键盘
     *
     * @param view
     */
    private void hideInput(View view) {
        //获取输入模式的管理对象
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            //imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            //关闭软键盘
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onItemClick(int postion) {
        Intent intent = new Intent(this, InvalidReasonActivity.class);
        intent.putExtra("invalidId", "" + listBeen.get(postion).getId());
        startActivity(intent);

    }

    /**
     * 下拉刷新
     */
    @Override
    public void pullToRefresh() {
        //清空数据
        mList.clear();
        page = 1;
        loadData("", "", "");
    }

    /**
     * 上拉刷新
     */
    @Override
    public void pullToLoadMore() {
        if (page >= pages) {
            Toast.makeText(this, "没有更多数据", Toast.LENGTH_SHORT).show();
        } else {
            page += 1;
            loadData("", "", "");
        }
    }
}