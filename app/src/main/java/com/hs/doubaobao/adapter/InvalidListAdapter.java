package com.hs.doubaobao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hs.doubaobao.R;

/**
 * 作者：zhanghaitao on 2017/9/8 14:36
 * 邮箱：820159571@qq.com
 *
 * @describe: 主界面列表的适配器
 */

public class InvalidListAdapter extends RecyclerView.Adapter {


    private final Context context;
    private String[] aar = {"java", "c#", "c++", "phyon", "JS", "html", "java", "c#", "c++", "phyon", "JS", "html"};

    public InvalidListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_invalid_list, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder viewHolder = (MyViewHolder) holder;
        viewHolder.setData(position);
    }

    @Override
    public int getItemCount() {
        return aar.length;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private  TextView text;
        private  LinearLayout item;

        public MyViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.item_main_text);
            item = (LinearLayout) itemView.findViewById(R.id.item_all);
        }

        public void setData(final int position) {
            text.setText(aar[position]);
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
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
