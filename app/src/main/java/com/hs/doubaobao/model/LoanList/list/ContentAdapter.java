package com.hs.doubaobao.model.LoanList.list;


import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.hs.doubaobao.R;
import com.hs.doubaobao.utils.DensityUtil;

import java.util.List;
import java.util.Map;


/**
 * 作者：zhanghaitao on 2017/11/16 15:40
 * 邮箱：820159571@qq.com
 *
 * @describe:content部分的adapter
 */
public class ContentAdapter extends ArrayAdapter {


    private Context context;
    private ListView lv_content;
    private List<Map<String, String>> contentList;
    private int resourceId;
    private int itemHeight = 80;//单位dp
    private int[] textWidths = {
            DensityUtil.getPixels(R.dimen.x222),
            DensityUtil.getPixels(R.dimen.x298),
            DensityUtil.getPixels(R.dimen.x168),
            DensityUtil.getPixels(R.dimen.x207),
            DensityUtil.getPixels(R.dimen.x145),
            DensityUtil.getPixels(R.dimen.x292),
            DensityUtil.getPixels(R.dimen.x228),
            DensityUtil.getPixels(R.dimen.x325),
            DensityUtil.getPixels(R.dimen.x195)};
    ;

    ContentAdapter(Context context, int resourceId, List<Map<String, String>> contentList, ListView lv_content) {
        super(context, resourceId);
        this.context = context;
        this.lv_content = lv_content;
        this.contentList = contentList;
        this.resourceId = resourceId;
    }

    @Override
    public int getCount() {
        return contentList.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Map<String, String> data = contentList.get(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(resourceId, parent, false);
            viewHolder.linearLayout = (LinearLayout) convertView.findViewById(R.id.linear_root);


            for (int i = 0; i < data.size(); i++) {
                TextView item = new TextView(context);

                item.setWidth(textWidths[i]);//设置宽度
                item.setSingleLine(true);
                item.setGravity(Gravity.CENTER_VERTICAL);
                item.setTextSize(15);
                //item.setTextColor(context.getResources().getColor(R.color.color_323232));
                //item.setPadding(5, 5, 5, 5);
                viewHolder.linearLayout.addView(item);
            }
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        for (int i = 0; i < data.size(); i++) {
            ((TextView) viewHolder.linearLayout.getChildAt(i)).setText(data.get(i + 1 + ""));
        }

        if (lv_content.isItemChecked(position)) {
            convertView.setBackgroundColor(context.getResources().getColor(R.color.textGray));
        } else {
            convertView.setBackgroundColor(context.getResources().getColor(R.color.white));
        }

        if (position % 2 == 0) {
            convertView.setBackgroundColor(context.getResources().getColor(R.color.item1));
        } else {
            convertView.setBackgroundColor(Color.WHITE);
        }

        return convertView;
    }

    private class ViewHolder {
        LinearLayout linearLayout;
    }
}