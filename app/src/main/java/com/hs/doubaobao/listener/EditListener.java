package com.hs.doubaobao.listener;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

/**
 * 作者：zhanghaitao on 2017/11/28 16:08
 * 邮箱：820159571@qq.com
 *
 * @describe:EditText输入改变的监听
 */

public abstract class EditListener implements TextWatcher {

    private int isBeforeEmpty = 1;//记录EditText改变前里面的是否为空
    private int isAfterEmpty = 1;//记录EditText改变后里面的是否为空

    /**
     * @param s     输入框中改变前的字符串信息
     * @param start 输入框中改变前的字符串的起始位置
     * @param count 输入框中改变前后的字符串改变数量一般为0
     * @param after 输入框中改变后的字符串与起始位置的偏移量
     */
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        isBeforeEmpty = TextUtils.isEmpty(s.toString())? 1:0;
    }

    /**
     * @param s      输入框中改变后的字符串信息
     * @param start  输入框中改变后的字符串的起始位置
     * @param before 输入框中改变前的字符串的位置 默认为0
     * @param count  输入框中改变后的一共输入字符串的数量
     */
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    /**
     * 这里才是我们需要的监听
     *
     * @param s 输入结束呈现在输入框中的信息
     */
    @Override
    public void afterTextChanged(Editable s) {
        isAfterEmpty = TextUtils.isEmpty(s.toString())?1:0;
        toChangedProgress(isBeforeEmpty - isAfterEmpty);
    }


    public abstract void toChangedProgress(int isAdd);// 0  1  -1


}
