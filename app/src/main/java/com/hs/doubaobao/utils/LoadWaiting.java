package com.hs.doubaobao.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

import com.hs.doubaobao.R;


/**
 * Author: zhanghaitao
 * E-mail: 820159571@qq.com
 * Date: 2017/9/19 16:04
 * @Description:网络请求等待动画
 */
public class LoadWaiting extends Dialog {
    private static LoadWaiting loadWaiting = null;

    public LoadWaiting(Context context) {
        super(context);
    }

    public LoadWaiting(Context context, int theme) {
        super(context, theme);
    }

    public static LoadWaiting createDialog(Context context) {
        loadWaiting = new LoadWaiting(context, R.style.CustomDialog);
        loadWaiting.setContentView(R.layout.load_waiting);
        loadWaiting.getWindow().getAttributes().gravity = Gravity.CENTER;
        loadWaiting.setCanceledOnTouchOutside(false);
        return loadWaiting;
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        if (loadWaiting == null) {
            return;
        }
        ImageView imageView         = (ImageView) loadWaiting.findViewById(R.id.loadingImageView);
        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getBackground();
        animationDrawable.start();
    }

    /**
     * 设置标题
     */
    public LoadWaiting setTitle(String strTitle) {
        super.setTitle(strTitle);
        return loadWaiting;
    }

    /**
     * 提示内容
     */
    public LoadWaiting setMessage(String strMessage) {
        TextView tvMsg = (TextView) loadWaiting.findViewById(R.id.loadingMsg);
        if (tvMsg != null) {
            tvMsg.setText(strMessage);
        }
        return loadWaiting;
    }
}
