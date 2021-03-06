package com.zht.http.builder;

import com.zht.http.OkHttpUtils;
import com.zht.http.request.OtherRequest;
import com.zht.http.request.RequestCall;

/**
 * Created by zhy on 16/3/2.
 */
public class HeadBuilder extends GetBuilder
{
    @Override
    public RequestCall build()
    {
        return new OtherRequest(null, null, OkHttpUtils.METHOD.HEAD, url, tag, params, headers,id).build();
    }
}
