package com.hs.doubaobao.model.LoanList.list;


import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.hs.doubaobao.R;

import java.util.List;


/**
 * 作者：zhanghaitao on 2017/11/16 15:45
 * 邮箱：820159571@qq.com
 *
 * @describe:只能垂直滑动的ListView的Adapter
 */
public class ColumnAdapter extends ArrayAdapter {

    private Context context;
    private int resourceId;
    private List<String> columnDataList;

    public  ColumnAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
        this.context = context;
        resourceId = resource;
        columnDataList = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            final LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.first_column, null);
            holder = new ViewHolder();
            holder.text = (TextView) convertView.findViewById(R.id.custom_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.text.setText(columnDataList.get(position));

        if (position%2==0){
            holder.text.setBackgroundColor(context.getResources().getColor(R.color.item1));
        }else{
            holder.text.setBackgroundColor(Color.WHITE);
        }

        return convertView;
    }

    class ViewHolder {
        TextView text;
    }

}