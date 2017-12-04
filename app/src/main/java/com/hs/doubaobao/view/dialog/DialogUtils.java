package com.hs.doubaobao.view.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import me.leefeng.citypicker.CityPicker;
import me.leefeng.citypicker.CityPickerListener;


/**
 * 对话框工具类
 * Created by zhanghaitao on 2017/5/26.
 */

public class DialogUtils {

    private Context context;
    private List<String> mData = new ArrayList<>();
    private boolean hasAmount;

    private TextView textView;


    public DialogUtils(){}

    public DialogUtils(Context context, TextView textView) {
        this.context = context;
        this.textView = textView;
    }

    public DialogUtils(Context context, TextView textView, List<String> mData) {
        this.context = context;
        this.textView = textView;
        this.mData = mData;
    }

    public DialogUtils(Context context, TextView textView, List<String> mData, boolean hasAmount) {
        this.context = context;
        this.textView = textView;
        this.mData = mData;
        this.hasAmount = hasAmount;
    }


    /**
     * 纯文本展示
     */
    public void showTextDialog() {
        final CommonDialog textDailog = new CommonDialog(context, mData);
        textDailog.setOnClickListener(new CommonDialog.OnClickListener() {
            @Override
            public void onClick(String text) {
                if (!"取消".equals(text)) {
                    textView.setText(text);
                }
            }
        });
        textDailog.show();
    }


    /**
     * 带有编辑框的对话框
     */
    public void showEditDialog() {

        TextEditDialog editDailog = new TextEditDialog(context, mData, hasAmount);
        editDailog.setOnClickListener(new TextEditDialog.OnClickListener() {
            @Override
            public void onClick(String text) {
                if (!"取消".equals(text)) {
                    textView.setText(text);
                }
            }
        });
        editDailog.show();

    }


    /**
     * 展示日期选择的对话框
     */
    public void showSelectDateDialog() {
        SelectDateDialog mSelectDateDialog = new SelectDateDialog(context);
        mSelectDateDialog.setOnClickListener(new SelectDateDialog.OnClickListener() {
            @Override
            public boolean onSure(int mYear, int mMonth, int mDay, long time) {
                //将日期转换为自己需要的格式
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                //日期回显，在这里保存数据
                textView.setText(dateFormat.format(time));
                return false;
            }

            @Override
            public boolean onCancel() {
                return false;
            }
        });
        //设置默认日期
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        String[] nowDate = str.split("-");

        mSelectDateDialog.show(Integer.parseInt(nowDate[0]),
                Integer.parseInt(nowDate[1])-1,
                Integer.parseInt(nowDate[2]));
    }


    /**
     * 展示城市选择的对话框
     */
    public void showSelectCityDialog() {
        CityPicker cityPicker = new CityPicker((Activity) context, new CityPickerListener() {
            @Override
            public void getCity(String name) {
                textView.setText(name);
            }
        });
        cityPicker.show();

    }

    public void showAlertDialog(Context context,String Message,String Title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(Message);
        builder.setTitle(Title);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();

    }
}
