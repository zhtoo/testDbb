package com.hs.doubaobao.view.BottomDialog;

import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hs.doubaobao.R;

/**
 * Created by zht on 17/11/29.
 * <p>
 * BaseBottomDialog的实现类
 * 使用方式：
 * <p>
 * SelectBottomDialog dialog = new SelectBottomDialog();
 * dialog.show(getSupportFragmentManager());
 */

public class SelectBottomDialog extends BaseBottomDialog implements View.OnClickListener {

    private LinearLayout container;
    private TextView cancel;

    private Context context;
    private String[] itemStrings ;
    private int[] viewID;//条目的id


    @Override
    public int getLayoutRes() {
        return R.layout.dialog_bottom_select;
    }


    @Override
    public void addItemView(View v) {
        container = (LinearLayout) v.findViewById(R.id.dialog_bottom_container);
        container.removeAllViews();
        for (int i = 0; i < itemStrings.length; i++) {
            TextView mText = new TextView(context);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                mText.setId(View.generateViewId());
                viewID[i] = mText.getId();
            }
            //布局参数
            LinearLayout.LayoutParams textlp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,//mText的宽度
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            //设置属性
            mText.setLayoutParams(textlp);
            mText.setGravity(Gravity.CENTER);
            mText.setPadding(30, 30, 30, 30);
            mText.setTextSize(18);
            mText.setTextColor(context.getResources().getColor(R.color.dialogText));
            mText.setText(this.itemStrings[i]);
            container.addView(mText, LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);

            //设置监听
            mText.setOnClickListener(this);
            if(i != itemStrings.length-1){
                View line= new View(context);
                //布局参数
                LinearLayout.LayoutParams linelp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        (int) getResources().getDimension(R.dimen.y1));
                //设置属性
                line.setLayoutParams(linelp);
                line.setBackgroundColor(getResources().getColor(R.color.lineBackground));
                container.addView(line);
            }

        }
        cancel = (TextView) v.findViewById(R.id.dialog_bottom_cancel);
    }

    @Override
    public void bindView(View v) {
        //TODO:绑定视图
        cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        String selecetText;
        if(id!=R.id.dialog_bottom_cancel){
            for (int i = 0; i < itemStrings.length; i++) {
                if (id == viewID[i]) {
                    selecetText = itemStrings[i];
                    if(listener!=null){
                        listener.onClick(selecetText);
                    }
                }
            }
        }
        this.dismiss();
    }

    public void setItemStrings(Context context,String[] itemStrings) {
        this.context = context;
        this.itemStrings = itemStrings;
        viewID = new int[itemStrings.length];
    }

    /**
     * 选择对话框回调
     *
     * @param listener
     */
    public void setOnClickListener(onItemClickListener listener) {
        this.listener = listener;
    }

    private onItemClickListener listener;
    /**
     * 选择对话框回调接口，调用者实现
     */
    public interface onItemClickListener {
        void onClick(String text);
    }




}
