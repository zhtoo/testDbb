package com.hs.doubaobao.model.invalid;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.hs.doubaobao.R;
import com.hs.doubaobao.base.AppBarActivity;
import com.hs.doubaobao.view.MyRelativeLayout;

import java.util.LinkedHashMap;

/**
 * 作者：zhanghaitao on 2017/9/11 17:44
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class InvalidListActivity extends AppBarActivity implements InvalidListContract.View, InvalidListAdapter.onItemClickListener {

    private InvalidListContract.Presenter presenter;
    private RecyclerView mRecycler;
    private MyRelativeLayout mSearchContainer;
    private LinearLayout mSearch;
    private boolean isShowing = false;
    private Button reset;
    private Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invalid_list);

        setTitle(getString(R.string.invalid_list));
        setTitleTextColor(R.color.textAggravating);
        setRightStatus(R.drawable.ic_invalid_search_selector);
        setStatusBarBackground(R.drawable.ic_battery_bg);

        initView();

        new InvalidListPresenter(this);
        presenter.getData(new LinkedHashMap());

    }

    private void initView() {
        mRecycler = (RecyclerView) findViewById(R.id.invalid_recycler_view);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayout.VERTICAL);
        mRecycler.setLayoutManager(llm);
        InvalidListAdapter adapter = new InvalidListAdapter(this);
        adapter.setOnItemClickListener(this);
        mRecycler.setAdapter(adapter);

        mSearch = (LinearLayout) findViewById(R.id.invalid_search);
        mSearchContainer = (MyRelativeLayout) findViewById(R.id.invalid_search_container);
        mSearchContainer.setVisibility(View.GONE);

        reset = (Button) findViewById(R.id.search_reset);
        start = (Button) findViewById(R.id.search_start);

        //设置状态栏的背景
        mSearch.setOnClickListener(this);
        mSearchContainer.setOnClickListener(this);
        reset.setOnClickListener(this);
        start.setOnClickListener(this);
    }

    @Override
    public void onItemClick(int postion) {
        Intent intent = new Intent(this, InvalidReasonActivity.class);
        startActivity(intent);
       // Alert  dialog
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
                Toast.makeText(this, "无效列表的重置", Toast.LENGTH_SHORT).show();
                break;
            case R.id.search_start:
                Toast.makeText(this, "无效列表的完成", Toast.LENGTH_SHORT).show();
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
    public void setData(String text) {

    }

    @Override
    public void setError(String text) {

    }

    @Override
    public void setPresenter(InvalidListContract.Presenter presenter) {
        this.presenter = presenter;
    }


    /**
     * 隐藏软键盘
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


}
