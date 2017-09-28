package com.hs.doubaobao.utils;

import android.content.Context;
import android.view.View;

import in.srain.cube.views.ptr.PtrClassicDefaultFooter;
import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler2;

/**
 * Created by zhanghaitao on 2017/7/14.
 * 下拉刷新，上拉加载的包装类
 */

public class PullToRefresh {

    public <T> void initPTR(Context context, final PtrClassicFrameLayout ptrFrame) {
        //默认经典头布局
        PtrClassicDefaultHeader defaultHeader = new PtrClassicDefaultHeader(context);
        //给Ptr添加头布局
        ptrFrame.setHeaderView(defaultHeader);
        //使头布局的状态和刷新状态同步
        ptrFrame.addPtrUIHandler(defaultHeader);
        //默认经典脚布局
        PtrClassicDefaultFooter defaultFooter = new PtrClassicDefaultFooter(context);
        //给Ptr添加脚布局
        ptrFrame.setFooterView(defaultFooter);
        //使脚布局的状态和刷新状态同步
        ptrFrame.addPtrUIHandler(defaultFooter);

        ptrFrame.setPtrHandler(new PtrHandler2() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                //Toast.makeText(LoanActivity.this, "下拉刷新", Toast.LENGTH_SHORT).show();
                if (listener != null) {
                    listener.pullToRefresh();
                    ptrFrame.refreshComplete();
                }
            }

            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler2.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onLoadBegin(PtrFrameLayout frame) {
                //Toast.makeText(LoanActivity.this, "上拉加载", Toast.LENGTH_SHORT).show();
                if (listener != null) {
                    listener.pullToLoadMore();
                    ptrFrame.refreshComplete();
                }
            }

            public boolean checkCanDoLoad(PtrFrameLayout frame, View content, View footer) {
                return PtrDefaultHandler2.checkContentCanBePulledUp(frame, content, footer);
            }
        });
    }

    private PullToRefreshListener listener;

    public void setPullToRefreshListener(PullToRefreshListener listener) {
        this.listener = listener;
    }

    public interface PullToRefreshListener {
        void pullToRefresh();
        void pullToLoadMore();
    }

}
