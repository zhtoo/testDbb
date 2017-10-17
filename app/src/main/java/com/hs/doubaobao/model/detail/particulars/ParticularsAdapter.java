package com.hs.doubaobao.model.detail.particulars;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hs.doubaobao.R;

import java.util.List;
import java.util.Map;

/**
 * 作者：zhanghaitao on 2017/9/14 16:43
 * 邮箱：820159571@qq.com
 *
 * @describe: 详情界面的适配器，动态添加每一个条目
 */

public class ParticularsAdapter extends RecyclerView.Adapter {

    private Context context;
    //存放标题
    private List<String> mTitles;
    //存放内容
    private List<Map> mMap;

    public ParticularsAdapter(Context context, List<String> mTitles, List<Map> mMap) {
        this.context = context;
        this.mTitles = mTitles;
        this.mMap = mMap;
    }


    //创建ViewHolder
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_detail_particulars, parent, false);
        return new MyViewHolder(view);
    }

    //对ViewHolder里面的控件进行赋值
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder viewHolder = (MyViewHolder) holder;
        //设置不循环利用视图
        viewHolder.setIsRecyclable(false);
        viewHolder.setData(position);
    }

    //条目的数量
    @Override
    public int getItemCount() {
        return mTitles != null ? mTitles.size() : 0;
    }

    //视图处理器
    private class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mTitle;
        LinearLayout mContainer;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.item_title);
            mContainer = (LinearLayout) itemView.findViewById(R.id.item_container);
        }

        //设置数据
        public void setData(final int position) {
            mTitle.setText(mTitles.get(position));
            Map<String, String> itemMap = mMap.get(position);
            if (itemMap.size() == 0) {
                mContainer.addView(setNewItem("空", "暂无数据"));
            } else {
                for (String key : itemMap.keySet()) {
                    String container = itemMap.get(key);

//                    if (key.equals("现住房居住时间") || key.equals("现单位工龄") || key.equals("建筑年限")) {
//                        container += "年";
//                    } else if (key.equals("现住房面积") || key.equals("自有房产面积") || key.equals("房屋建筑面积")) {
//                        container += "m²";
//                    } else if (key.equals("所在楼层") || key.equals("总楼层")) {
//                        container += "层";
//                    } else if (key.equals("月均工资收入")) {
//                        container += "元";
//                    } else if (key.equals("部门人数")) {
//                        container += "人";
//                    } else if (key.equals("所在部门")) {
//                        container += "部";
//                    } else if (key.equals("*申请借款金额")) {
//                        container += "万元";
//                    }

                    if (TextUtils.isEmpty(itemMap.get(key))) {
                        container = "未填写";
                    }
                    mContainer.addView(setNewItem(key, container));
                }
            }
        }

        /**
         * 代码生成每一个条目中信息的视图
         *
         * @param name
         * @param container
         * @return
         */
        private LinearLayout setNewItem(String name, String container) {

            if(name.contains("姓名")){
                name = "姓名";
            }
            if(name.contains("关系")){
                name = "关系";
            }
            if(name.contains("手机号码")){
                name = "手机号码";
            }
            if(name.contains("是否知晓贷款")){
                name = "是否知晓贷款";
            }

            LinearLayout mLayout = new LinearLayout(context);
            mLayout.setOrientation(LinearLayout.HORIZONTAL);
            //空数据仅仅显示内容
            if ("空".equals(name)||name.contains("空")) {
                TextView text = new TextView(context);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,//mText的宽度
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.setMargins(dp2px(context, 16), 0, 0, 0);
                text.setLayoutParams(lp);
                text.setPadding(0, 0, dp2px(context, 5),0);
                text.setText(container);
                text.setTextSize(12);
                text.setTextColor(context.getResources().getColor(R.color.textGray));

                mLayout.setGravity(Gravity.CENTER);
                mLayout.addView(text);
                return mLayout;
            }

            //画分割线
            if ("line".equals(container) || "line".equals(name)) {
                View line = new View(context);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,//mText的宽度
                        dp2px(context, 1));
                lp.setMargins(dp2px(context, 16), 0, 0, 0);
                line.setLayoutParams(lp);
                line.setBackgroundResource(R.color.lineBackground);

                mLayout.setGravity(Gravity.RIGHT);
                mLayout.addView(line);
                return mLayout;
            }

            //设置条目的padding
            mLayout.setPadding(dp2px(context, 3), dp2px(context, 3), dp2px(context, 3), dp2px(context, 3));
            //设置名称
            TextView mText = new TextView(context);
            mText.setText(name + ":");
            mText.setTextSize(12);
            mText.setTextColor(context.getResources().getColor(R.color.textGray));
            // 为子View设置布局参数
            if (name.length() < 10) {
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        dp2px(context, 130), //mText的宽度
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                mText.setLayoutParams(lp);
            }
            mText.setPadding(dp2px(context, 15), 0, dp2px(context, 3), 0);
            //设置内容
            TextView mContainer = new TextView(context);
            mContainer.setText(container);
            mContainer.setTextSize(12);
            mContainer.setTextColor(context.getResources().getColor(R.color.textAggravating));
            mContainer.setPadding(0, 0, dp2px(context, 5), 0);
            mLayout.addView(mText);
            mLayout.addView(mContainer);
            return mLayout;
        }

        /**
         * 尺寸转换
         *
         * @param context
         * @param dp
         * @return
         */
        public int dp2px(Context context, int dp) {
            float scale = context.getResources().getDisplayMetrics().density;
            return (int) ((float) dp * scale + 0.5F);
        }

    }

}
