package com.hs.doubaobao.model.Login;

import android.content.Context;

import com.hs.doubaobao.base.BaseParams;
import com.hs.doubaobao.bean.LoginBean;
import com.hs.doubaobao.http.JsonWrap;
import com.hs.doubaobao.http.OKHttpWrap;
import com.hs.doubaobao.http.requestCallBack;
import com.hs.doubaobao.utils.log.Logger;

import java.util.Map;

import okhttp3.Call;

/**
 * 作者：zhanghaitao on 2017/9/12 11:03
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class LoginPresener implements LoginContract.Presenter {

    private static final String TAG = "LoginPresener";
    LoginContract.View viewRoot;
    private LoginBean bean;
    private Context context;


    public LoginPresener(LoginContract.View viewRoot, Context context) {
        this.viewRoot = viewRoot;
        viewRoot.setPresenter(this);
        this.context = context;

    }

    @Override
    public void getData(final Map mapParameter) {
        OKHttpWrap.getOKHttpWrap(context).requestPost(BaseParams.LOGIN_URL, mapParameter, new requestCallBack() {
            @Override
            public void onError(Call call, Exception e) {
                viewRoot.setError(e.getLocalizedMessage());
            }

            @Override
            public void onResponse(String response) {
                Logger.e(TAG, response);
                bean = JsonWrap.getObject(response, LoginBean.class);
                //回到不能在子线程中
                if (bean != null) {
                    if (bean.getResCode() == 1) {
                        viewRoot.setData(bean);
                    } else {
                        viewRoot.setError("提示" + bean.getResMsg());
                    }
                } else {
                    viewRoot.setError("Json解析异常");
                }
            }
        });
    }
}
