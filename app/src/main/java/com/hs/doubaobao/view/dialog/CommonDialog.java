package com.hs.doubaobao.view.dialog;

import android.content.Context;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hs.doubaobao.R;
import com.hs.doubaobao.utils.DensityUtil;

import java.util.List;

/**
 * Created by zhanghaitao on 2017/5/25.
 * 自定义对话框的使用：
 * 1、构造
 * 2、设置监听
 * 3、在监听中处理坚挺返回的数据
 * 4、show()
 */

public class CommonDialog extends BaseDialog implements View.OnClickListener {


    private View dialogView;
    private List<String> mDate;
    private int[] viewID;
    private LinearLayout mContainer;

    /**
     * 创建一个性别选择对话框
     *
     * @param mContext
     */
    public CommonDialog(Context mContext, List<String> mDate) {
        this.context = mContext;
        this.mDate = mDate;
        if (mDate != null) {
            viewID = new int[mDate.size()];
        }
        create();
    }

    /**
     * 创建一个日期选择对话框，带有监听
     *
     * @param mContext
     */
    public CommonDialog(Context mContext, OnClickListener listener) {
        this.context = mContext;
        onClickListener = listener;
        create();
    }

    /**
     * 创建选择性别对话框
     */
    private void create() {

        if (dialog != null) {
            return;
        }
        //将自定义的布局放到dialogView中
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        dialogView = layoutInflater.inflate(R.layout.dialog_select_common, null);
        mContainer = (LinearLayout) dialogView.findViewById(R.id.select_container);

        /**
         * 初始化View，设置监听
         */
        if (mDate != null) {
            for (int i = 0; i < mDate.size(); i++) {
                TextView mText = new TextView(context);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    mText.setId(View.generateViewId());
                    viewID[i] = mText.getId();
                }
                LinearLayout.LayoutParams textlp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,//mText的宽度
                        LinearLayout.LayoutParams.WRAP_CONTENT);

                mText.setLayoutParams(textlp);
                mText.setGravity(Gravity.CENTER);
                mText.setPadding(30, 30, 30, 30);
                mText.setTextSize(18);
                mText.setTextColor(context.getResources().getColor(R.color.dialogText));
                mText.setText(mDate.get(i));
                mContainer.addView(mText, 716,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                if(!"取消".equals(mDate.get(i))){
                    View line = new View(context);
                    LinearLayout.LayoutParams linelp = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,//line的宽度
                            DensityUtil.dp2px(context, 1));

                    line.setLayoutParams(linelp);
                    line.setBackgroundResource(R.color.lineBackground);
                    mContainer.addView(line);
                }
                mText.setOnClickListener(this);
            }
        }


        dialog = new AlertDialog.Builder(context).setView(dialogView).create();


    }

    /**
     * 显示选择对话框（使用的时候只需要调用此方法）
     */
    public void show() {
        if (dialog == null || dialog.isShowing()) {
            return;
        }
        dialog.show();
    }


    @Override
    public void onClick(View v) {
        String air = "";//要返回的数据

        for (int j = 0; j < mDate.size(); j++) {
            if (v.getId() == viewID[j]) {
                air = mDate.get(j);
            }
        }
        onClickListener.onClick(air);
        dialog.dismiss();
    }

    /**
     * 选择对话框回调
     *
     * @param listener
     */
    public void setOnClickListener(OnClickListener listener) {
        onClickListener = listener;
    }

    private OnClickListener onClickListener;
    /**
     * 选择对话框回调接口，调用者实现
     */
    public interface OnClickListener {
        void onClick(String text);
    }


}
