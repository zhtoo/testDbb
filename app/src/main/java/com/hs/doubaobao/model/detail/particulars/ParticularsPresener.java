package com.hs.doubaobao.model.detail.particulars;

import android.content.Context;

import com.hs.doubaobao.base.BaseParams;
import com.hs.doubaobao.bean.ParticularBean;
import com.hs.doubaobao.http.JsonWrap;
import com.hs.doubaobao.http.OKHttpWrap;
import com.hs.doubaobao.http.requestCallBack;
import com.hs.doubaobao.utils.log.LogWrap;

import java.util.Map;

import okhttp3.Call;

/**
 * 作者：zhanghaitao on 2017/9/12 11:03
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class ParticularsPresener implements ParticularsContract.Presenter {

    private static final String TAG ="ParticularsPresener" ;
    ParticularsContract.View viewRoot;



    private Context context;

    public ParticularsPresener(ParticularsContract.View viewRoot, Context context) {
        this.viewRoot = viewRoot;
        this.context = context;
        viewRoot.setPresenter(this);
    }

    @Override
    public void getData(Map mapParameter) {

        OKHttpWrap.getOKHttpWrap(context)
                .requestPost(BaseParams.PARTICULARS_URL, mapParameter, new requestCallBack() {

                    private ParticularBean bean;

                    @Override
                    public void onError(Call call, Exception e) {
                        viewRoot.setError(e.getLocalizedMessage());
                    }
                    @Override
                    public void onResponse(String response) {
                        LogWrap.e(TAG,response);
                        bean = JsonWrap.getObject(response, ParticularBean.class);
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
