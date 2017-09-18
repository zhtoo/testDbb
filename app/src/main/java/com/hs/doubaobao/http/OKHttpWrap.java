package com.hs.doubaobao.http;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import com.google.gson.Gson;
import com.hs.doubaobao.MyApplication;
import com.hs.doubaobao.base.BaseParams;
import com.hs.doubaobao.utils.MD5Util;
import com.hs.doubaobao.utils.log.LogWrap;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by zhanghaitao on 2017/6/15.
 */

public class OKHttpWrap {

    private static final String TAG = "OKHttpWrap";
    //编码格式
    private static final String CHARSET_NAME = "UTF-8";

    private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");//mdiatype 这个需要和服务端保持一致
    private static final MediaType MEDIA_OBJECT_STREAM = MediaType.parse("text/x-markdown; charset=utf-8");//mdiatype 这个需要和服务端保持一致

    /**
     * Post请求（同步加载数据）
     * @param url
     * @param paramsMap
     * @return
     */
    public static String requestPost(String url, Map<String, String> paramsMap) {
        //同步锁
        synchronized (MyApplication.getContext()) {
            String result = "网络不给力";
            try {
                //添加通用参数
                String params = getStringParams(paramsMap);
                //创建okHttpClient对象
                OkHttpClient mOkHttpClient = new OkHttpClient();
                //创建一个请求实体对象 RequestBody
                RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, params);
                //创建一个请求
                Request.Builder builder = new Request.Builder();
                //发起请求
                final Request request = builder.url(url).post(body).build();
                //请求回来的响应
                Response response = mOkHttpClient.newCall(request).execute();
                if (response.isSuccessful()) {
                    // okhttp3
                    // 报java.lang.IllegalStateException: closed,
                    // 原因为OkHttp请求回调中
                    // response.body().string()只能有效调用一次
                    result = response.body().string();
                    LogWrap.e(TAG, result);
                    return result;
                } else {
                    LogWrap.e(TAG, "Unexpected code " + response);
                    return "{\"resCode\":8,\"resData\":{},\"resMsg\":\"" + response + "\"}";
                }

            } catch (Exception e) {
                LogWrap.e(TAG, e.toString());
                return  "{\"resCode\":8,\"resData\":{},\"resMsg\":\"" + e.toString() + "\"}";
            }
        }
    }

    /**
     * 获取通用参数
     * @param paramsMap
     * @return
     * @throws UnsupportedEncodingException
     */
    private static String getStringParams(Map<String, String> paramsMap) throws UnsupportedEncodingException {
        // 时间戳
        final String ts = String.valueOf(System.currentTimeMillis() / 1000);
        Map<String, String> mParamsMap = new HashMap<>();
        mParamsMap.put("appkey", BaseParams.APP_KEY);
        mParamsMap.put("signa", getSigna(ts));
        mParamsMap.put("ts", ts);
        mParamsMap.put("mobileType", BaseParams.MOBILE_TYPE);
        mParamsMap.put("versionNumber", getVersion());
        mParamsMap.putAll(paramsMap);
        //处理参数
        StringBuilder tempParams = new StringBuilder();
        int pos = 0;
        for (String key : mParamsMap.keySet()) {
            if (pos > 0) {
                tempParams.append("&");
            }
            tempParams.append(String.format("%s=%s", key, URLEncoder.encode(mParamsMap.get(key), "utf-8")));
            pos++;
        }
        //生成参数
        Log.e("我是参数", tempParams.toString());
        return tempParams.toString();
    }

    /**
     * 一般接口调用-signa签名生成规则
     *
     * @param ts 时间戳
     */
    private static String getSigna(String ts) {
        // appsecrt拼接ts的字符串后进行MD5（32）
        String signa = MD5Util.encode(BaseParams.APP_SECRET + ts, CHARSET_NAME);
        // 得到的MD5串拼接appkey再次MD5，所得结果转大写
        signa = MD5Util.encode(signa + BaseParams.APP_KEY, CHARSET_NAME).toUpperCase();
        return signa;
    }

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public static String getVersion() {
        try {
            PackageManager manager = MyApplication.getContext().getPackageManager();
            PackageInfo info = manager.getPackageInfo(MyApplication.getContext().getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "can not find version name";
        }
    }

    /**
     * 统一为请求添加头信息
     *
     * @return
     */
    private Request.Builder addHeaders() {
        Request.Builder builder = new Request.Builder()
                .addHeader("Connection", "keep-alive")
                .addHeader("platform", "2")
                .addHeader("phoneModel", Build.MODEL)
                .addHeader("systemVersion", Build.VERSION.RELEASE)
                .addHeader("appVersion", "1.0.1");
        return builder;
    }

    /**
     * 上传文件
     *
     * @param url       接口地址
     * @param paramsMap 参数
     */
    public static String upLoadFile(String url, HashMap<String, Object> paramsMap) {
        String result = "网络不给力";
        synchronized (MyApplication.getContext()) {
            try {
                //初始化OkHttpClient
                OkHttpClient mOkHttpClient = new OkHttpClient().newBuilder()
                        .connectTimeout(10, TimeUnit.SECONDS)//设置超时时间
                        .readTimeout(10, TimeUnit.SECONDS)//设置读取超时时间
                        .writeTimeout(10, TimeUnit.SECONDS)//设置写入超时时间
                        .build();
                // 时间戳
                final String ts = String.valueOf(System.currentTimeMillis() / 1000);
                Map<String, Object> mParamsMap = new HashMap<>();
                mParamsMap.put("appkey", BaseParams.APP_KEY);
                mParamsMap.put("signa", getSigna(ts));
                mParamsMap.put("ts", ts);
                mParamsMap.put("mobileType", BaseParams.MOBILE_TYPE);
                mParamsMap.put("versionNumber", getVersion());
                mParamsMap.putAll(paramsMap);
                Log.e(TAG, mParamsMap.toString());
                MultipartBody.Builder builder = new MultipartBody.Builder();
                //设置类型
                builder.setType(MultipartBody.FORM);
                //追加参数
                for (String key : mParamsMap.keySet()) {
                    Object object = mParamsMap.get(key);
                    if (!(object instanceof File)) {
                        //添加请求参数
                        builder.addFormDataPart(key, object.toString());
                    } else {
                        //添加文件
                        File file = (File) object;
                        builder.addFormDataPart(key, file.getName(), RequestBody.create(MEDIA_OBJECT_STREAM, file));
                    }
                }
                //创建RequestBody
                RequestBody body = builder.build();
                //创建Request
                final Request request = new Request.Builder().url(url).post(body).build();
                //单独设置参数 比如读取超时时间
                Response response = mOkHttpClient.newCall(request).execute();
                if (response.isSuccessful()) {
                    result = response.body().string();
                    Log.e(TAG, result);
                    return result;
                } else {
                    Log.e(TAG, "Unexpected code " + response);
                    return "Unexpected code " + response;
                }
            } catch (Exception e) {
                Log.e(TAG, e.toString());
                return e.toString();
            }
        }
    }

    /**
     * 使用Gson进行解析对象
     * @param jsonString
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T getObject(String jsonString, Class<T> cls) {
        T t = null;
        try {
            Gson gson = new Gson();
            t = gson.fromJson(jsonString, cls);
        } catch (Exception e) {

            Log.e("Json解析", e.toString());
        }
        return t;
    }

    /**
     * 使用Gson进行对象转Json
     * @param trim
     * @param <T>
     * @return
     */
    public static <T> String getString(T trim) {
        T t = (T)trim;
        String tojson ="";
        try {
            Gson gson = new Gson();
            tojson=gson.toJson(t);
            LogWrap.e("Json解析",tojson);
            return tojson;
        } catch (Exception e) {
            //tojson = BaseParams.TO_JAON_FAIL;
            LogWrap.e("Json解析",e.toString());
            return tojson;
        }
    }

}
