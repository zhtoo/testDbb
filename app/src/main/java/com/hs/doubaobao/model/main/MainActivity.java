package com.hs.doubaobao.model.main;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hs.doubaobao.R;
import com.hs.doubaobao.adapter.HomeAdapter;
import com.hs.doubaobao.base.BaseParams;
import com.hs.doubaobao.bean.HomeBean;
import com.hs.doubaobao.bean.ListBean;
import com.hs.doubaobao.model.GeneralManager.GeneralManagerApprovalActivity;
import com.hs.doubaobao.model.Login.LoginActivity;
import com.hs.doubaobao.model.detail.DetailActivity;
import com.hs.doubaobao.model.invalid.InvalidListActivity;
import com.hs.doubaobao.model.riskControl.RiskControlApprovalActivity;
import com.hs.doubaobao.utils.PullToRefresh;
import com.hs.doubaobao.utils.SPHelp;
import com.hs.doubaobao.utils.log.Logger;
import com.hs.doubaobao.view.DotView;
import com.hs.doubaobao.view.MyRelativeLayout;
import com.hs.doubaobao.view.ResideLayout;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;

/**
 * 主界面
 * rectification by zht on 2017/9/11  16:51
 */
public class MainActivity extends Activity implements MainContract.View, HomeAdapter.onItemClickListener, View.OnClickListener, PullToRefresh.PullToRefreshListener{

    private static final String TAG = "MainActivity";
    //控件
    //private SlidingMenu sliding_menu;
    private LinearLayout ll_menu;
    private LinearLayout mSearch;
    private RecyclerView mRecyclerView;
    private MyRelativeLayout mSearchContainer;
    private LinearLayout mStatusBar;
    private TextView mName;
    private TextView mMenuName;
    private LinearLayout mMenuRisk;
    private LinearLayout mMenuManager;
    private LinearLayout mMenuInvalid;
    private EditText mSearchName;
    private EditText mSearchOpeName;
    private EditText mSearchPhone;
    private PtrClassicFrameLayout ptrFrame1;
    private DotView mMainDot;
    private DotView mMenuRiskDot;
    private DotView mMenuManagerDot;
    private LinearLayout mGray;
    private LinearLayout mMenu;
    private RelativeLayout mMenuLogo;
    private RelativeLayout mTitleBar;

    private MainContract.Presenter presenter;
    private HomeBean.ResDataBean.PageDataListBean.PageBean pageBean;
    private HomeBean.ResDataBean.MessageCountBean messageCount;
    private List<HomeBean.ResDataBean.PageDataListBean.ListBean> listBeen;

    private List<Integer> roleIdList;
    private List<ListBean> mList = new ArrayList<>();

    private HomeAdapter adapter;
    public static boolean isShowing = false;
    private PtrClassicFrameLayout ptrFrame;
    //存放请求参数
    private Map<String, Object> map;
    //分页
    private int page = 1;
    private int pages = 1;
    private ResideLayout mResideLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Logger.e(TAG, "当前线程的进程的ID：" + Process.myPid());

        initView();
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        mName.setText(name);
        mMenuName.setText(name);

        new MainPresenter(this, this);
        map = new LinkedHashMap<>();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mList.clear();
        loadData("", "", "");
    }

    @Override
    protected void onPause() {
        super.onPause();
       // sliding_menu.startScroll(0);
    }

    /**
     * 联网访问数据
     *
     * @param name
     * @param opeName
     * @param phone
     */
    private void loadData(String name, String opeName, String phone) {
        map.put("userId", BaseParams.USER_ID);
        map.put("page", page + "");
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

    /**
     * 初始化视图
     */
    private void initView() {
        mResideLayout = (ResideLayout) findViewById(R.id.resideLayout);
        ll_menu = (LinearLayout) findViewById(R.id.ll_menu);
        mMenu = (LinearLayout) findViewById(R.id.menu);
        //全局的灰色背景
        mGray = (LinearLayout) findViewById(R.id.main_gray_bg);
        //状态栏
        mStatusBar = (LinearLayout) findViewById(R.id.main_status_bar);
        //搜索栏
        mSearch = (LinearLayout) findViewById(R.id.main_search);

        //菜单的背景图标
        mMenuLogo = (RelativeLayout) findViewById(R.id.menu_logo_bg);
        //标题栏
        mTitleBar = (RelativeLayout) findViewById(R.id.main_title_bar);

        mSearchContainer = (MyRelativeLayout) findViewById(R.id.main_search_container);

        mRecyclerView = (RecyclerView) findViewById(R.id.main_recycler_view);
        //刷选栏的三个条件
        mSearchName = (EditText) findViewById(R.id.search_name);
        mSearchOpeName = (EditText) findViewById(R.id.search_opename);
        mSearchPhone = (EditText) findViewById(R.id.search_phone);
        //用户名称
        mName = (TextView) findViewById(R.id.home_person_name);
        mMenuName = (TextView) findViewById(R.id.menu_person_name);
        //菜单的三个条目(根据权限控制显示和隐藏)
        mMenuRisk = (LinearLayout) findViewById(R.id.menu_risk);
        mMenuManager = (LinearLayout) findViewById(R.id.menu_manager);
        mMenuInvalid = (LinearLayout) findViewById(R.id.menu_invalid);
        //消息小红点
        mMainDot = (DotView) findViewById(R.id.main_dot);
        mMenuRiskDot = (DotView) findViewById(R.id.menu_risk_dot);
        mMenuManagerDot = (DotView) findViewById(R.id.menu_manager_dot);
        //下拉刷新，上拉加载
        ptrFrame = (PtrClassicFrameLayout) findViewById(R.id.main_ptr);
        ptrFrame1 = (PtrClassicFrameLayout) findViewById(R.id.main_ptr1);

        mSearchContainer.setVisibility(View.GONE);
        initState();
        //设置状态栏的背景
        mStatusBar.setBackgroundResource(R.drawable.ic_battery_bg);
        initRecyclerView();
        mSearch.setOnClickListener(this);
    //    sliding_menu.setMenuShowListener(this);
        mName.setOnClickListener(this);

        //ViewGroup.LayoutParams layoutParams = mMenuLogo.getLayoutParams();
        //layoutParams.width = sliding_menu.getWidthPixels() * 59 / 72;
        //layoutParams.height = sliding_menu.getWidthPixels() * 42 / 72;
        //mMenuLogo.setLayoutParams(layoutParams);
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
        adapter = new HomeAdapter(this, mList, 0);
        adapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(adapter);
        initPtrClassicFrameLayout();
    }

    /**
     * 初始化上拉加载下拉刷新的布局
     * 注意：adapter的初始化在 PullToRefresh 之前
     */
    private void initPtrClassicFrameLayout() {
        //创建PtrClassicFrameLayout的包装类对象
        PullToRefresh refresh = new PullToRefresh();
        //初始化PtrClassicFrameLayout
        refresh.initPTR(this, ptrFrame);
        refresh.initPTR(this, ptrFrame1);
        //设置监听
        refresh.setPullToRefreshListener(this);
    }

    /**
     * 主界面上的菜单按钮被单击了
     */
    public void onMenuToggleClick(View v) {
        //if (v.getId() == R.id.home_person_name) {
//        hideInput(v);
//        if (isShowing) {
//            isShowing = false;
//            mSearch.setVisibility(View.GONE);
//            mSearchContainer.setVisibility(View.GONE);
//        }
//        sliding_menu.toggle();
        //}
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
     * 主界面的灰色部分被点击
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
//设置缩放的相对位置
        mSearch.setPivotX(getResources().getDimension(R.dimen.x477));//相对于控件的位子
        mSearch.setPivotY(0);

        ObjectAnimator anim = ObjectAnimator//
                .ofFloat(mSearch, "main_search", start, end)//设置变化目标值
                .setDuration(500);//设置动画时间
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

//        int width = mSearch.getWidth();
//        int height = mSearch.getHeight();
//
//        ObjectAnimator anim = new ObjectAnimator();
//        PropertyValuesHolder a1 = PropertyValuesHolder.ofFloat("alpha", start, end);//透明度
//        PropertyValuesHolder a2 = PropertyValuesHolder.ofFloat("translationX", start == 0 ? 0 : -width, start == 0 ? width : 0);
//        PropertyValuesHolder a3 = PropertyValuesHolder.ofFloat("translationY", start == 0 ? 0 : -height, start == 0 ? height : 0);
//        anim.ofPropertyValuesHolder(mSearch, a1, a2, a3).setDuration(300).start();
/**
 * PropertyValuesHolder a1 = PropertyValuesHolder.ofFloat("alpha", 0f, 1f);
 PropertyValuesHolder a2 = PropertyValuesHolder.ofFloat("translationY", 0, viewWidth);
 ......
 ObjectAnimator.ofPropertyValuesHolder(view, a1, a2, ......).setDuration(1000).start();
 */
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
                int width = mSearch.getWidth();
                int height = mSearch.getHeight();
                Logger.e(TAG, "width---->" + width);
                Logger.e(TAG, "height---->" + height);
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
        if (!TextUtils.isEmpty(name)
                || !TextUtils.isEmpty(opeName) ||
                !TextUtils.isEmpty(phone)) {
            loadData(name, opeName, phone);
        }
    }

    /**
     * 重置搜索
     */
    public void resetSearch(View v) {
        mSearchName.setText("");
        mSearchOpeName.setText("");
        mSearchPhone.setText("");
        mMenu.getWidth();
    }

    /**
     * 退出当前账号
     */
    public void onExit(View v) {
        Dialog alertDialog = new AlertDialog.Builder(this).
                setMessage("您确定退出当前账号吗？").
                setIcon(R.drawable.ic_launcher).
                setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SPHelp.setData("name", "");
                        SPHelp.setData("password", "");
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }).
                setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                    }
                }).create();
        alertDialog.show();
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

        int size = roleIdList.size();

        String vlues = "";
        //将所有的权限拼接成字符串
        for (int key : roleIdList) {
            vlues += key;
        }

        if(TextUtils.isEmpty(vlues)){
            mMenuRisk.setVisibility(View.GONE);
            mMenuManager.setVisibility(View.GONE);
        }else
        if (vlues.contains("9")) {
            mMenuRisk.setVisibility(View.VISIBLE);
            mMenuManager.setVisibility(View.VISIBLE);
        } else {
            if (vlues.contains("7")) {
                mMenuRisk.setVisibility(View.VISIBLE);
            } else {
                mMenuRisk.setVisibility(View.GONE);
            }
            if (vlues.contains("8")) {
                mMenuManager.setVisibility(View.VISIBLE);
            } else {
                mMenuManager.setVisibility(View.GONE);
            }
        }

        //角色的消息
        messageCount = bean.getResData().getMessageCount();
        int messageRole7 = messageCount.getMessageRole7();
        int messageRole8 = messageCount.getMessageRole8();

        mMainDot.setVisibility((messageRole7 == 1 || messageRole8 == 1) ? View.VISIBLE : View.GONE);
        mMenuRiskDot.setVisibility(messageRole7 == 1 ? View.VISIBLE : View.GONE);
        mMenuManagerDot.setVisibility(messageRole8 == 1 ? View.VISIBLE : View.GONE);

        //分页
        pageBean = bean.getResData().getPageDataList().getPage();
        pages = pageBean.getPages();
        //每页总数
        pageBean.getPernum();
        //list内容
        listBeen = bean.getResData().getPageDataList().getList();
        //mList.clear();
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
                mBean.setId(listBeen.get(i).getId());
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

    /**
     * 数据错误
     *
     * @param text
     */
    @Override
    public void setError(String text) {
        Toast.makeText(this, "网络不给力", Toast.LENGTH_SHORT).show();
        ptrFrame.setVisibility(View.GONE);
        ptrFrame1.setVisibility(View.VISIBLE);
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.home_person_name) {
            hideInput(v);
            mResideLayout.openPane();
            if (isShowing) {
                isShowing = false;
                mSearch.setVisibility(View.GONE);
                mSearchContainer.setVisibility(View.GONE);
            }
           // sliding_menu.toggle();
        }
    }

    @Override
    public void onItemClick(int postion) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("ID", mList.get(postion).getId() + "");
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

    /**
     * 当菜单显示的时候  onGrayClick
     *
     * @param show
     */
   /* @Override
    public void onMenuShow(boolean show) {
        mGray.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    public void onGrayClick(View v) {
        sliding_menu.toggle();
    }
*/

    //--------------使用onKeyDown()完成双击退出程序--------------

    //记录用户首次点击返回键的时间
    private long firstTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - firstTime > 2000) {
                Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                firstTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        if (mResideLayout.isOpen()) {
            mResideLayout.closePane();
        } else {
            super.onBackPressed();
        }
    }

}
