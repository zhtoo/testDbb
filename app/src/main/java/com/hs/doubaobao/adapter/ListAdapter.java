package com.hs.doubaobao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hs.doubaobao.MyApplication;
import com.hs.doubaobao.R;
import com.hs.doubaobao.bean.ListBean;

import java.util.List;

/**
 * 作者：zhanghaitao on 2017/9/8 14:36
 * 邮箱：820159571@qq.com
 *
 * @describe: 主界面列表的适配器
 */

public class ListAdapter extends RecyclerView.Adapter {

    private final Context context;
    private List<ListBean> mList;
    private int type = 0;

    public ListAdapter(Context context, List<ListBean> mList) {
        this.context = context;
        this.mList = mList;
    }

    public ListAdapter(Context context, List<ListBean> mList, int type) {
        this.context = context;
        this.mList = mList;
        this.type = type;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mian, parent, false);
        return new MyListViewHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyListViewHolder viewHolder = (MyListViewHolder) holder;
        viewHolder.setData(position);
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    public class MyListViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout item;
        //客户姓名
        public TextView name;
        //借款时间
        public TextView time;
        //借款用途
        public TextView purpose;
        //借款金额
        public TextView loanAmount;
        //客户电话
        public TextView customPhone;
        //借款期数
        public TextView loanPeriods;
        //客户经理
        public TextView customManager;
        //审批状态
        public TextView status;

        public TextView approval;
        public ImageView main;


        public MyListViewHolder(View itemView) {
            super(itemView);
            item = (LinearLayout) itemView.findViewById(R.id.item_all);
            name = (TextView) itemView.findViewById(R.id.list_name);
            time = (TextView) itemView.findViewById(R.id.list_time);
            purpose = (TextView) itemView.findViewById(R.id.list_purpose);
            loanAmount = (TextView) itemView.findViewById(R.id.list_load_amount);
            customPhone = (TextView) itemView.findViewById(R.id.list_custom_phone);
            loanPeriods = (TextView) itemView.findViewById(R.id.list_loan_periods);
            customManager = (TextView) itemView.findViewById(R.id.list_custom_manager);
            status = (TextView) itemView.findViewById(R.id.list_status);
            approval = (TextView) itemView.findViewById(R.id.list_approval);
            main = (ImageView) itemView.findViewById(R.id.list_main);
        }

        public void setData(final int position) {
            ListBean listBean = mList.get(position);

            if (type == 4) {
                time.setBackgroundResource(R.drawable.bg_invalid_time);
                main.setImageResource(R.drawable.ic_invalid_arrow_selector);
                int grayColor = MyApplication.getcolor(R.color.textGray);
                name.setTextColor(grayColor);
                purpose.setTextColor(grayColor);
                loanAmount.setTextColor(grayColor);
                customPhone.setTextColor(grayColor);
                loanPeriods.setTextColor(grayColor);
                customManager.setTextColor(grayColor);
                status.setTextColor(grayColor);
            }
            if (type == 0 || type == 4) {
                main.setVisibility(View.VISIBLE);
                approval.setVisibility(View.GONE);
            }

            if (type == 1) {
                main.setVisibility(View.GONE);
                approval.setVisibility(View.VISIBLE);
            }
            name.setText(listBean.getName());
            time.setText(listBean.getTime());
            purpose.setText(listBean.getPurpose());
            loanAmount.setText(((int) listBean.getLoanAmount() / 10000) + "万元");
            customPhone.setText(listBean.getCustomPhone());
            loanPeriods.setText(listBean.getLoanPeriods() + "期");
            customManager.setText(listBean.getCustomManager());
            status.setText(listBean.getStatus());

            if (1 == listBean.getShowType()) {
                // TODO: 2017/9/19  风控视图
            } else if (2 == listBean.getShowType()) {
                // TODO: 2017/9/19  总经理视图
            }

            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(/**/View v) {
                    if (listener != null) {
                        listener.onItemClick(position);
                    }
                }
            });
        }
    }

    public static onItemClickListener listener;

    public void setOnItemClickListener(onItemClickListener listener) {
        this.listener = listener;
    }

    public interface onItemClickListener {
        void onItemClick(int postion);
    }

}
