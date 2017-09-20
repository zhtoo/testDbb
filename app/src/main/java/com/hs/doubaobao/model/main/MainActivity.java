package com.hs.doubaobao.model.main;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hs.doubaobao.R;
import com.hs.doubaobao.adapter.HomeAdapter;
import com.hs.doubaobao.base.BaseParams;
import com.hs.doubaobao.bean.HomeBean;
import com.hs.doubaobao.model.GeneralManager.GeneralManagerApprovalActivity;
import com.hs.doubaobao.model.detail.DetailActivity;
import com.hs.doubaobao.model.invalid.InvalidListActivity;
import com.hs.doubaobao.model.riskControl.RiskControlApprovalActivity;
import com.hs.doubaobao.view.MyRelativeLayout;
import com.hs.doubaobao.view.SlidingMenu;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 主界面
 * rectification by zht on 2017/9/11  16:51
 */
public class MainActivity extends Activity implements MainContract.View, HomeAdapter.onItemClickListener, View.OnClickListener {


    private static final String TAG = "MainActivity";
    private SlidingMenu sliding_menu;
    private LinearLayout ll_menu;
    private LinearLayout mSearch;
    public static boolean isShowing = false;
    private RecyclerView mRecyclerView;
    private MyRelativeLayout mSearchContainer;
    private LinearLayout mStatusBar;
    private MainContract.Presenter presenter;
    private TextView mName;
    private TextView mMenuName;
    private TextView mMenuRisk;
    private TextView mMenuManager;
    private TextView mMenuInvalid;
    private HomeBean.ResDataBean.MessageCountBean messageCount;
    private HomeBean.ResDataBean.PageDataListBean.PageBean pageBean;
    private List<Integer> roleIdList;
    private List<HomeBean.ResDataBean.PageDataListBean.ListBean> listBeen;
    private List<ListBean> mList = new ArrayList<>();
    private HomeAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        initView();

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        mName.setText(name);
        mMenuName.setText(name);
/**
 * userId	是	long	操作员id
 page	否	Int	当前页数
 rows	否	int	每页总数
 cusName	否	String	搜索客户姓名
 opeName	否	String	搜索客户经理名
 phone	否	String	搜索客户电话
 */
        new MainPresenter(this);
        Map<String, String> map = new LinkedHashMap<>();
        map.put("userId", BaseParams.USER_ID);
//        map.put("page", "6");
//        map.put("rows", "6");
//        map.put("cusName", "6");
//        map.put("opeName", "6");
//        map.put("phone", "6");

        presenter.getData(map);

    }

    /**
     * 初始化视图
     */
    private void initView() {
        sliding_menu = (SlidingMenu) findViewById(R.id.sliding_menu);
        ll_menu = (LinearLayout) findViewById(R.id.ll_menu);
        mStatusBar = (LinearLayout) findViewById(R.id.main_status_bar);
        mSearch = (LinearLayout) findViewById(R.id.main_search);

        mSearchContainer = (MyRelativeLayout) findViewById(R.id.main_search_container);

        mRecyclerView = (RecyclerView) findViewById(R.id.main_recycler_view);

        mName = (TextView) findViewById(R.id.home_person_name);

        mMenuName = (TextView) findViewById(R.id.menu_person_name);

        mMenuRisk = (TextView) findViewById(R.id.menu_risk);
        mMenuManager = (TextView) findViewById(R.id.menu_manager);
        mMenuInvalid = (TextView) findViewById(R.id.menu_invalid);

        mSearchContainer.setVisibility(View.GONE);
        initState();
        //设置状态栏的背景
        mStatusBar.setBackgroundResource(R.drawable.ic_battery_bg);
        initRecyclerView();
        mSearch.setOnClickListener(this);
    }

    /**
     * 动态的设置状态栏  实现沉浸式状态栏
     */
    private void initState() {
        //当系统版本为4.4或者4.4以上时可以使用沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            mStatusBar.setVisibility(View.VISIBLE);
            //获取到状态栏的高度
            int statusHeight;
            //通过反射的方式获取状态栏高度
            try {
                Class<?> c = Class.forName("com.android.internal.R$dimen");
                Object obj = c.newInstance();
                Field field = c.getField("status_bar_height");
                int x = Integer.parseInt(field.get(obj).toString());
                statusHeight = getResources().getDimensionPixelSize(x);
            } catch (Exception e) {
                e.printStackTrace();
                statusHeight = 0;
            }
            //动态的设置隐藏布局的高度
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mStatusBar.getLayoutParams();
            params.height = statusHeight;
            mStatusBar.setLayoutParams(params);
        }
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayout.VERTICAL);

        mRecyclerView.setLayoutManager(llm);

        adapter = new HomeAdapter(this,mList,0);

        adapter.setOnItemClickListener(this);

        mRecyclerView.setAdapter(adapter);

    }

    /**
     * 主界面上的菜单按钮被单击了
     */
    public void onMenuToggleClick(View v) {
        if (v.getId() == R.id.home_person_name) {
            hideInput(v);
            if (isShowing) {
                isShowing = false;
                mSearch.setVisibility(View.GONE);
                mSearchContainer.setVisibility(View.GONE);
            }
            sliding_menu.toggle();
        }
    }

    /**
     * 主界面的筛选按钮被点击
     */
    public void onMenuSearchClick(final View view) {
        hideInput(view);
        if (isShowing) {
            isShowing = false;
            ShowSearchAnimator(1f, 0f);
        } else {
            isShowing = true;
            mSearchContainer.setVisibility(View.VISIBLE);
            mSearch.setVisibility(View.VISIBLE);
            ShowSearchAnimator(0f, 1f);
        }
    }

    /**
     * 主界面的筛选按钮被点击
     */
    public void onMenuSearchContainerClick(final View view) {
        hideInput(view);
        if (isShowing) {
            isShowing = false;
            ShowSearchAnimator(1f, 0f);
        }
    }

    /**
     * 隐藏软键盘
     *
     * @param view
     */
    private void hideInput(View view) {
        //获取输入模式的管理对象
        InputMethodManager imm = (InputMethodManager) MainActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            //imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            //关闭软键盘
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
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

    /**
     * 菜单列表中的某个菜单项被单击了
     */
    public void onMenuItemClick(int type) {
        Class[] classes = {RiskControlApprovalActivity.class,
                GeneralManagerApprovalActivity.class,
                InvalidListActivity.class};
        if (type != -1) {
            Intent intent = new Intent(this, classes[type]);
            startActivity(intent);
        }
    }


    public void onRiskClick(View v) {
        onMenuItemClick(0);
    }

    public void onManagerClick(View v) {
        onMenuItemClick(1);
    }

    public void onInvalidClick(View v) {
        onMenuItemClick(2);
    }

    /**
     * 开始搜索
     */
    public void startSearch(View v) {
        Toast.makeText(this, "完成", Toast.LENGTH_SHORT).show();
    }

    /**
     * 重置搜索
     */
    public void resetSearch(View v) {
        Toast.makeText(this, "重置", Toast.LENGTH_SHORT).show();
    }


    /**
     * 退出
     */
    public void onExit(View v) {
        Toast.makeText(this, "退出", Toast.LENGTH_SHORT).show();
    }


    /**
     * 请求回来的数据处理
     *
     * @param bean
     */
    @Override
    public void setData(HomeBean bean) {
        //角色权限
        roleIdList = bean.getResData().getRoleIdList();
        //角色的消息
        messageCount = bean.getResData().getMessageCount();
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

    /**
     * 数据错误
     *
     * @param text
     */
    @Override
    public void setError(String text) {
       // LogWrap.e(TAG, text);
        Toast.makeText(this, "网络不给力", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.presenter = presenter;
    }



    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(int postion) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("ID",listBeen.get(postion).getId()+"");
        startActivity(intent);
    }

}
