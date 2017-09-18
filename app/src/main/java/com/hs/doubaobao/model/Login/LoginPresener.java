package com.hs.doubaobao.model.Login;

import com.hs.doubaobao.base.BaseParams;
import com.hs.doubaobao.bean.LoginBean;
import com.hs.doubaobao.http.OKHttpWrap;
import com.hs.doubaobao.threadpool.ThreadPoolProxyFactory;
import com.hs.doubaobao.utils.log.LogWrap;

import java.util.Map;

/**
 * 作者：zhanghaitao on 2017/9/12 11:03
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class LoginPresener implements LoginContract.Presenter {

    private static final String TAG ="LoginPresener" ;
    LoginContract.View viewRoot;

    public LoginPresener(LoginContract.View viewRoot) {
        this.viewRoot = viewRoot;
        viewRoot.setPresenter(this);
    }

    @Override
    public void getData(final Map mapParameter) {

        //利用线程池管理类开启线程
        ThreadPoolProxyFactory.getNormalThreadPoolProxy().submit(new Runnable() {
            @Override
            public void run() {
                String result = OKHttpWrap.requestPost(BaseParams.LOGIN_URL, mapParameter);
                LogWrap.d(TAG,result);
                LoginBean bean = OKHttpWrap.getObject(result, LoginBean.class);
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
