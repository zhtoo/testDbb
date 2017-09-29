package com.hs.doubaobao.model.main;

import android.content.Context;

import com.hs.doubaobao.base.BaseParams;
import com.hs.doubaobao.bean.HomeBean;
import com.hs.doubaobao.http.JsonWrap;
import com.hs.doubaobao.http.OKHttpWrap;
import com.hs.doubaobao.http.requestCallBack;
import com.hs.doubaobao.utils.log.Logger;

import java.util.Map;

import okhttp3.Call;

/**
 * 作者：zhanghaitao on 2017/9/12 14:12
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class MainPresenter implements MainContract.Presenter {

    private static final String TAG = "MainPresenter";
    MainContract.View viewRoot;
    private HomeBean bean;
    private Context context;

    public MainPresenter(MainContract.View viewRoot, Context context) {
        this.viewRoot = viewRoot;
        this.context = context;
        viewRoot.setPresenter(this);
    }

    @Override
    public void getData(final Map mapParameter) {
        OKHttpWrap.getOKHttpWrap(context)
                .requestPost(BaseParams.HOME_URL, mapParameter, new requestCallBack() {
            @Override
            public void onError(Call call, Exception e) {
                viewRoot.setError(e.getLocalizedMessage());
            }
            @Override
            public void onResponse(String response) {
                Logger.e(TAG,response);
                bean = JsonWrap.getObject(response, HomeBean.class);
                //回到不能在子线程中
                if(bean!=null){
                    if(bean.getResCode() == 1){
                        viewRoot.setData(bean);
                    }else {
                        viewRoot.setError(bean.getResMsg());
                    }
                }else {
                    viewRoot.setError("Json解析异常");
                }
            }
        });
    }
}
