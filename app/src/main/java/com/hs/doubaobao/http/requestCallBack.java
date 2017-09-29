package com.hs.doubaobao.http;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by zhanghaitao on 2017/6/15.
 * Email 820159571@qq.com
 * @DES：request回调的类
 */
public abstract class requestCallBack extends CallBack<String> {

    @Override
    public String parseNetworkResponse(Response response) throws IOException {
        return  response.body().string();
    }


}
