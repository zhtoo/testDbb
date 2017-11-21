package com.hs.doubaobao.model.ApplyLoad.RecyclerView;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hs.doubaobao.R;
import com.zht.expandablerecyclerview.ChildViewHolder;

/**
 * 作者：zhanghaitao on 2017/11/21 16:19
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class ChildItemViewHolder extends ChildViewHolder {

    private final String TAG = getClass().getSimpleName();

    private final TextView mLoadUse, mLoanCategories, mLoanAmount, mTelphone, mApplicationPeriod, mStoreName;
    private final Button mParticulars;

    public ChildItemViewHolder(@NonNull View itemView) {
        super(itemView);
        mLoadUse = (TextView) itemView.findViewById(R.id.item_load_use);
        mLoanCategories = (TextView) itemView.findViewById(R.id.item_loan_categories);
        mLoanAmount = (TextView) itemView.findViewById(R.id.item_loan_amount);
        mTelphone = (TextView) itemView.findViewById(R.id.item_telphone);
        mApplicationPeriod = (TextView) itemView.findViewById(R.id.item_application_period);
        mStoreName = (TextView) itemView.findViewById(R.id.item_store_name);
        mParticulars = (Button) itemView.findViewById(R.id.item_particulars);
    }

    /**
     * 绑定数据
     *
     * @param childItem
     */
    public void bind(ChildItem childItem, final int parentPosition, final int childPosition) {
        mLoadUse.setText(childItem.getLoansUse());
        mLoanCategories.setText(childItem.getLoanCategories());
        mLoanAmount.setText(childItem.getLoanAmount());
        mTelphone.setText(childItem.getTelephone());
        mApplicationPeriod.setText(childItem.getApplicationPeriod());
        mStoreName.setText(childItem.getStoreName());
        mParticulars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.onItemClick(parentPosition, childPosition);
                }
            }
        });
    }


    public void setListener(ClickListener listener) {
        this.listener = listener;
    }

    private ClickListener listener;

    interface ClickListener {
        void onItemClick(int parentPosition, int childPosition);
    }

}