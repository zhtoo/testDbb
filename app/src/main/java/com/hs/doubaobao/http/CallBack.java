package com.hs.doubaobao.http;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by zhanghaitao
 * Date  2017/9/3.
 * Email 820159571@qq.com
 */
public abstract class CallBack<T> {

    public void onAfter(){

    }
    public abstract T parseNetworkResponse(Response response) throws Exception;
    public abstract void onError(Call call,Exception e);
    public abstract void onResponse(T response);

}
