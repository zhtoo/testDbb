package com.hs.doubaobao.view.dialog;

import android.content.Context;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hs.doubaobao.R;
import com.hs.doubaobao.utils.DensityUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by AndroidStudio on 2017/5/25.
 */

public class TextEditDialog extends BaseDialog implements View.OnClickListener {

    private View dialogView;
    private OnClickListener onClickListener;

    private List<String> mData = new ArrayList<>();
    private int[] viewID;
    private boolean hasAmount;
    private TextView mHint;
    private EditText mEditText;
    private TextView mHasAmount;
    private TextView mCancel;
    private TextView mEnsure;

    /**
     * 创建一个选择对话框的对象
     *
     * @param mContext
     */
    public TextEditDialog(Context mContext, List<String> mData, Boolean hasAmount) {
        this.context = mContext;
        this.mData = mData;
        this.hasAmount = hasAmount;
        if (mData == null) {
            mData = new ArrayList<String>();
            mData.add("空数据");
        }
        viewID = new int[mData.size() - 1];
        create();
    }

    /**
     * 创建一个选择对话框的对象
     *
     * @param mContext
     */
    public TextEditDialog(Context mContext, OnClickListener listener) {
        this.context = mContext;
        onClickListener = listener;
        create();
    }

    /**
     * 创建选择对话框
     */
    private void create() {
        if (dialog != null) {
            return;
        }
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        dialogView = layoutInflater.inflate(R.layout.dialog_text_edit, null);
        LinearLayout mContainer = (LinearLayout) dialogView.findViewById(R.id.dailog_container);

        if (mData.size() > 1) {
            for (int i = 0; i < mData.size() - 1; i++) {
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
                mText.setText(mData.get(i));
                mContainer.addView(mText, 716,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                if (!"取消".equals(mData.get(i))) {
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
        mHint = (TextView) dialogView.findViewById(R.id.dailog_hint);

        LinearLayout mEdit = (LinearLayout) dialogView.findViewById(R.id.dailog_edit);
        mEdit.setOnClickListener(this);


        mEditText = (EditText) dialogView.findViewById(R.id.dailog_edittext);
        mHasAmount = (TextView) dialogView.findViewById(R.id.dailog_has_amount);
        mCancel = (TextView) dialogView.findViewById(R.id.dailog_bt_cancel);
        mEnsure = (TextView) dialogView.findViewById(R.id.dailog_bt_ensure);
        mCancel.setOnClickListener(this);
        mEnsure.setOnClickListener(this);
        mHint.setText(mData.get(mData.size() - 1));
        if (hasAmount) {
            mEditText.setLines(1);
            mHasAmount.setVisibility(View.VISIBLE);
        } else {
            mHasAmount.setVisibility(View.GONE);
        }


        mEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                /*判断是否是“enter”键*/
                if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {

                    if (hasAmount) {

                        Pattern pattern = Pattern.compile("[0-9]*");
                        Matcher isNum = pattern.matcher(mEditText.getText().toString().trim());
                        if (!isNum.matches()) {
                            Toast.makeText(context, "金额只能输入数字", Toast.LENGTH_SHORT).show();
                            mEditText.setText("0.00");
                            return false;
                        }
                    }
                    /*隐藏软键盘*/
                    InputMethodManager imm = (InputMethodManager) v
                            .getContext().getSystemService(
                                    Context.INPUT_METHOD_SERVICE);
                    if (imm.isActive()) {
                        imm.hideSoftInputFromWindow(
                                v.getApplicationWindowToken(), 0);
                    }
                    String text = mHint.getText().toString().trim();
                    if (hasAmount) {
                        text += mEditText.getText().toString().trim() +
                                mHasAmount.getText().toString().trim();
                    } else {
                        text += mEditText.getText().toString().trim();
                    }
                    onClickListener.onClick(text);
                    dialog.dismiss();

                    return true;
                }
                return false;
            }
        });

        dialog = new AlertDialog.Builder(context).setView(dialogView).create();
        dialog.setCanceledOnTouchOutside(false);
    }

    /**
     * 显示选择对话框
     */
    public void show() {
        if (dialog == null || dialog.isShowing()) {
            return;
        }
        dialog.show();


    }

    /**
     * 选择对话框回调
     *
     * @param listener
     */
    public void setOnClickListener(OnClickListener listener) {
        onClickListener = listener;
    }

    @Override
    public void onClick(View view) {
        String text = "";//要返回的数据

        int viewId  = view.getId();

        for (int j = 0; j < mData.size() - 1; j++) {
            if (viewId == viewID[j]) {
                text = mData.get(j);
            }
        }

        String amount = mEditText.getText().toString().trim();
        if (viewId == R.id.dailog_edit||viewId == R.id.dailog_bt_ensure) {
            Pattern pattern = Pattern.compile("[0-9]*");
            Matcher isNum = pattern.matcher(amount);
            if (!isNum.matches()) {
                Toast.makeText(context, "金额只能输入数字", Toast.LENGTH_SHORT).show();
                amount = "\t\t0.00";
            }
            if (TextUtils.isEmpty(amount)) {
                amount = "\t\t0.00";
            }
            text = mHint.getText().toString().trim() +" "+amount;
            text += hasAmount ? mHasAmount.getText().toString().trim() : "";
        }else if(viewId == R.id.dailog_bt_cancel){
            amount = "\t\t0.00";
            text = mHint.getText().toString().trim() +" "+amount;
            text += hasAmount ? mHasAmount.getText().toString().trim() : "";
        }
        onClickListener.onClick(text);
        dialog.dismiss();
    }

    /**
     * 选择对话框回调接口，调用者实现
     */
    public interface OnClickListener {
        void onClick(String text);
    }


}
