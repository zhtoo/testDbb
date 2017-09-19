package com.hs.doubaobao.http;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;

import com.hs.doubaobao.MyApplication;
import com.hs.doubaobao.base.BaseParams;
import com.hs.doubaobao.utils.MD5Util;
import com.hs.doubaobao.utils.log.LogWrap;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
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

    private static OKHttpWrap okHttpManager;
    //创建okHttpClient对象
    private static OkHttpClient okHttpClient;
    private final Handler handler;

    public static OKHttpWrap getOKHttpWrap() {

        if (okHttpManager == null) {

            synchronized (OKHttpWrap.class) {

                if (okHttpManager == null) {
                    okHttpManager = new OKHttpWrap();
                }
            }
        }
        return okHttpManager;
    }

    private OKHttpWrap() {
        okHttpClient = new OkHttpClient();
        okHttpClient.newBuilder().connectTimeout(10, TimeUnit.SECONDS);
        okHttpClient.newBuilder().readTimeout(10, TimeUnit.SECONDS);
        okHttpClient.newBuilder().writeTimeout(10, TimeUnit.SECONDS);

        //获取主线程的handler
        // handler = new Handler(Looper.getMainLooper());

        handler = MyApplication.getMainThreadHandler();
    }

    //编码格式
    private static final String CHARSET_NAME = "UTF-8";

    private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");//mdiatype 这个需要和服务端保持一致
    private static final MediaType MEDIA_OBJECT_STREAM = MediaType.parse("text/x-markdown; charset=utf-8");//mdiatype 这个需要和服务端保持一致

    /**
     * 添加公共参数
     * @param paramsMap
     * @param <T>
     * @return
     */
    private static <T> Map<String, T> getRequestMap(Map<String, T> paramsMap) {
        // 时间戳
        String ts = String.valueOf(System.currentTimeMillis() / 1000);
        Map<String, T> mParamsMap = new HashMap<>();
        mParamsMap.put("appkey",(T) BaseParams.APP_KEY);
        mParamsMap.put("signa", (T)getSigna(ts));
        mParamsMap.put("ts", (T)ts);
        mParamsMap.put("mobileType", (T)BaseParams.MOBILE_TYPE);
        mParamsMap.put("versionNumber",(T) getVersion());
        mParamsMap.putAll(paramsMap);
        return mParamsMap;
    }

    /**
     * 获取通用参数
     *
     * @param paramsMap
     * @return
     */
    private static String getStringParams(Map<String, String> paramsMap) {
        Map<String, String> mParamsMap = getRequestMap(paramsMap);
        //处理参数
        StringBuilder tempParams = new StringBuilder();
        try {
            int pos = 0;
            for (String key : mParamsMap.keySet()) {
                if (pos > 0) {
                    tempParams.append("&");
                }
                tempParams.append(String.format("%s=%s", key, URLEncoder.encode(mParamsMap.get(key), "utf-8")));
                pos++;
            }
            //生成参数
            LogWrap.e("请求参数", tempParams.toString().replaceAll("&","\n"));
            return tempParams.toString();
        } catch (Exception e) {
            LogWrap.e(TAG, e.toString());
            int pos = 0;
            for (String key : mParamsMap.keySet()) {
                if (pos > 0) {
                    tempParams.append("&");
                }
                tempParams.append(String.format("%s=%s", key, mParamsMap.get(key)));
                pos++;
            }
            LogWrap.e("请求参数", tempParams.toString().replaceAll("&","\n"));
            return tempParams.toString();
        }
    }

    /**
     * Post请求（同步加载数据）
     *
     * @param url
     * @param paramsMap
     * @return
     */
    public void requestPost(String url, Map<String, String> paramsMap, final CallBack callback) {
        //同步锁
        synchronized (MyApplication.getContext()) {
            //添加通用参数
            String params = getStringParams(paramsMap);
            //创建一个请求实体对象 RequestBody
            RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, params);
            //创建一个请求
            Request.Builder builder = new Request.Builder();
            //发起请求
            Request request = builder.url(url).post(body).build();
            //请求回来的响应
            requestClient(callback, request);
        }
    }

    /**
     * 上传文件
     *
     * @param url       接口地址
     * @param paramsMap 参数
     */
    public void upLoadFile(String url, HashMap<String, Object> paramsMap, final CallBack callback) {
        synchronized (MyApplication.getContext()) {
            // 时间戳
//            final String ts = String.valueOf(System.currentTimeMillis() / 1000);
//            Map<String, Object> mParamsMap = new HashMap<>();
//            mParamsMap.put("appkey", BaseParams.APP_KEY);
//            mParamsMap.put("signa", getSigna(ts));
//            mParamsMap.put("ts", ts);
//            mParamsMap.put("mobileType", BaseParams.MOBILE_TYPE);
//            mParamsMap.put("versionNumber", getVersion());
//            mParamsMap.putAll(paramsMap);
//            Log.e(TAG, mParamsMap.toString());
            Map<String, Object> mParamsMap = getRequestMap(paramsMap);
            LogWrap.e("请求参数", mParamsMap.toString().replaceAll(",","\n"));
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
            requestClient(callback, request);
        }
    }

    /**
     * 发起网络请求
     *
     * @param callback
     * @param request
     */
    private void requestClient(final CallBack callback, Request request) {
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                sendFailResultCallBack(call, e, callback);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Object object = null;
                try {
                    object = callback.parseNetworkResponse(response);
                    sendSuccessfulCallBack(object, callback);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    /**
     * 发送成功的结果
     *
     * @param object
     * @param callback
     */
    private void sendSuccessfulCallBack(final Object object, final CallBack callback) {
        if (callback == null) return;

        handler.post(new Runnable() {
            @Override
            public void run() {
                callback.onResponse(object);
                callback.onAfter();
            }
        });
    }
    /**
     * 发送失败结果
     *
     * @param call
     * @param e
     * @param callback
     */
    private void sendFailResultCallBack(final Call call, final IOException e, final CallBack callback) {
        if (callback == null) return;
        handler.post(new Runnable() {
            @Override
            public void run() {
                callback.onAfter();
                callback.onError(call, e);
            }
        });
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
}
