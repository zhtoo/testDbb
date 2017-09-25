package com.hs.doubaobao.model.riskControl;

import android.content.Context;

import com.hs.doubaobao.base.BaseParams;
import com.hs.doubaobao.bean.HomeBean;
import com.hs.doubaobao.http.JsonWrap;
import com.hs.doubaobao.http.OKHttpWrap;
import com.hs.doubaobao.http.requestCallBack;
import com.hs.doubaobao.utils.log.LogWrap;

import java.util.Map;

import okhttp3.Call;

/**
 * 作者：zhanghaitao on 2017/9/12 13:55
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class RiskApprovalPresenter implements RiskApprovalContract.Presenter {

    private static final String TAG ="RiskApprovalPresenter" ;
    RiskApprovalContract.View viewRoot;
    private HomeBean bean;


    private Context context;

    public RiskApprovalPresenter(RiskApprovalContract.View viewRoot, Context context) {
        this.viewRoot = viewRoot;
        this.context = context;
        viewRoot.setPresenter(this);
    }

    @Override
    public void getData(Map mapParameter) {

        OKHttpWrap.getOKHttpWrap(context)
                .requestPost(BaseParams.RISK_URL, mapParameter, new requestCallBack() {
                    @Override
                    public void onError(Call call, Exception e) {
                        viewRoot.setError(e.getLocalizedMessage());
                    }
                    @Override
                    public void onResponse(String response) {
                        LogWrap.e(TAG,response);
                        bean = JsonWrap.getObject(response, HomeBean.class);
                        //回到不能在子线程中
                        if(bean !=null){
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
