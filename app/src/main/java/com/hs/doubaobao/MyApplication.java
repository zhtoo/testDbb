package com.hs.doubaobao;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Process;

import com.hs.doubaobao.utils.SPHelp;
import com.hs.doubaobao.utils.log.Logger;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * 全局单例
 * Created by zhanghaitao on 2017/5/18.
 */
public class MyApplication extends Application {

    private final static String TAG = "MyApplication";

    private static Context mContext;
    private static Handler mMainThreadHandler;
    private static int mMainThreadId;
    private Map<String, String> mMemProtocolCacheMap = new HashMap<>();

    @Override
    public void onCreate() {//程序的入口方法
        //上下文
        mContext = getApplicationContext();
        //主线程的Handler
        mMainThreadHandler = new Handler();
        //主线程的线程id
        mMainThreadId = android.os.Process.myTid();
        // 抓取异常LOG，保存在本地
     //   CrashHandler.getInstance().init(this);

        //极光推送start
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);


        if(!SPHelp.getData("IsSetAlias").equals("true")){
            //设置别名
            setAlias();
        }


        //极光推送end




        // long elapsedCpuTime = Process.getElapsedCpuTime();
        // int myPid = Process.myPid();//获取该进程的ID
        // int mMainThreadId = Process.myTid();//获取该线程的ID
        // int myUid = Process.myUid();//获取该进程的用户ID
        // boolean isSupportsProcesses = Process.supportsProcesses();//判断该进程是否支持多进程

        // Process.getThreadPriority(mMainThreadId);//获取指定ID的线程的优先级。

//        Logger.e(TAG, "当前线程的优先级：" + Process.getThreadPriority(mMainThreadId));
//        Logger.e(TAG, "当前线程的线程的ID：" + Process.myTid());
//        Logger.e(TAG, "当前进程的ID：" + Process.myPid());
//        Logger.e(TAG, "当前进程的用户ID：" + Process.myUid());
//        Logger.e(TAG, Process.supportsProcesses() ? "支持多进程" : "不支持多进程");
//        Logger.e(TAG, "当前系统中数据文件夹环境变量：" + android.os.Environment.getDataDirectory());
//        Logger.e(TAG, "当前系统中下载缓存文件环境变量：" + android.os.Environment.getDownloadCacheDirectory());
//        Logger.e(TAG, "当前系统中外部存储文件环境变量：" + android.os.Environment.getExternalStorageDirectory());
//        Logger.e(TAG, "当前系统中根文件环境变量：" + android.os.Environment.getRootDirectory());

        int threadPriorityBackground = Process.THREAD_PRIORITY_BACKGROUND;
        /**
         * 常用语句：
         android.os.Process
         //获取当前进程的方法
         android.os.Process.getElapsedCpuTime()：获取消耗的时间。
         android.os.Process.myPid()：获取该进程的ID。
         android.os.Process.myTid()：获取该线程的ID。
         android.os.Process.myUid()：获取该进程的用户ID。
         android.os.Process.supportsProcesses：判断该进程是否支持多进程。

         // 获取/设置线程优先级
         getThreadPriority(int tid)：
         setThreadPriority(int priority)：设置当前线程的优先级。priority：-20 --->19 ，高优先级 ---> 低优先级。
         setThreadPriority(int tid,int priority)：设置指定ID的线程的优先级。

        //  优先级：
        //  android.os.Process.THREAD_PRIORITY_AUDIO //标准音乐播放使用的线程优先级   -16
        //  android.os.Process.THREAD_PRIORITY_BACKGROUND //标准后台程序      10
        //  android.os.Process.THREAD_PRIORITY_DEFAULT// 默认应用的优先级    0
        //  android.os.Process.THREAD_PRIORITY_DISPLAY//标准显示系统优先级，主要是改善UI的刷新     -4
        //  android.os.Process.THREAD_PRIORITY_FOREGROUND //标准前台线程优先级             -2
        //  android.os.Process.THREAD_PRIORITY_LESS_FAVORABLE //低于favorable               1
        //  android.os.Process.THREAD_PRIORITY_LOWEST //有效的线程最低的优先级             19
        //  android.os.Process.THREAD_PRIORITY_MORE_FAVORABLE //高于favorable              -1
        //  android.os.Process.THREAD_PRIORITY_URGENT_AUDIO //标准较重要音频播放优先级       -19
        //  android.os.Process.THREAD_PRIORITY_URGENT_DISPLAY //标准较重要显示优先级（输入事件也适用）。-8

         //管理进程
         //killProcess(int pid)：杀死指定的进程。
         //sendSignal(int pid,int singal)：向指定的进程发送信号。

         android.os.Environment
         //获取系统环境变量
         //getDataDirectory()：获取当前系统中数据文件夹环境变量。
         //getDownloadCacheDirectory()：获取当前系统中下载缓存文件环境变量。
         //getExternalStorageDirectory()：获取当前系统中外部存储文件环境变量。
         //getRootDirectory()：获取当前系统中根文件环境变量。
         */
        super.onCreate();
    }

    //极光推送设置别名start
    private void setAlias() {
        String tag= "tag2";
        // 调用 Handler 来异步设置别名
        mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS, tag));
    }

    private final TagAliasCallback mAliasCallback = new TagAliasCallback() {
        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs ;
            switch (code) {
                case 0:
                    logs = "Set tag and alias success";
                    Logger.i(TAG, logs);
                    // 建议这里往 SharePreference 里写一个成功设置的状态。成功设置一次后，以后不必再次设置了。
                    SPHelp.setData("IsSetAlias","true");
                    break;
                case 6002:
                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
                    Logger.i(TAG, logs);
                    // 延迟 60 秒来调用 Handler 设置别名
                    mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_ALIAS, alias), 1000 * 60);
                    break;
                default:
                    logs = "Failed with errorCode = " + code;
                    Logger.e(TAG, logs);
            }

        }
    };
    private static final int MSG_SET_ALIAS = 1001;

    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_SET_ALIAS:
                    Logger.d(TAG, "Set alias in handler.");
                    // 调用 JPush 接口来设置别名。
                    Set<String>  tags = new HashSet<>();
                    tags.add("tag2");
                    JPushInterface.setAliasAndTags(getContext(),
                            (String) msg.obj,
                            tags,
                            mAliasCallback);
                    break;
                default:
                    Logger.i(TAG, "Unhandled msg - " + msg.what);
            }
        }
    };
    //极光推送设置别名end





    /**
     * 获取MEM协议缓存集合
     *
     * @return:Map集合
     */
    public Map<String, String> getMemProtocolCacheMap() {
        return mMemProtocolCacheMap;
    }

    /**
     * 得到上下文
     *
     * @return
     */
    public static Context getContext() {
        return mContext;
    }

    /**
     * 得到主线程里面的创建的一个hanlder
     *
     * @return
     */
    public static Handler getMainThreadHandler() {
        return mMainThreadHandler;
    }

    /**
     * 得到主线程的线程id
     *
     * @return
     */
    public static int getMainThreadId() {
        return mMainThreadId;
    }

    /**
     * 获取资源文件中颜色的int值
     *
     * @return
     */
    public static int getcolor(int id) {
        int color = getContext().getResources().getColor(id);
        return color;
    }


    /**
     * 存放Activity的对象
     */
    private static Map<String, Activity> destoryMap = new HashMap<>();

    /**
     * 添加到销毁队列
     *
     * @param activity 要销毁的activity
     */

    public static void addDestoryActivity(Activity activity, String activityName) {
        destoryMap.put(activityName, activity);
    }

    /**
     * 销毁指定Activity
     */
    public static void destoryActivity(String activityName) {
        Set<String> keySet = destoryMap.keySet();
        for (String key : keySet) {
            destoryMap.get(key).finish();
        }
    }

    /**
     * 将Activity添加到集合中
     *
     * @param activity
     * @param activityName
     */
    public static void addActivity(Activity activity, String activityName) {
        destoryMap.put(activityName, activity);
    }

    /**
     * 获取到某个Activity
     *
     * @param activityName
     * @return
     */
    public static Activity getActivity(String activityName) {
        Set<String> keySet = destoryMap.keySet();
        for (String key : keySet) {
            return destoryMap.get(key);
        }
        return null;
    }

}

