package com.hs.doubaobao.model.LoanList.list;

import android.content.Context;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 这里是修改条目内容的地方。修改可横向滑动的条目
 */
public class MyPanelListAdapter extends PanelListAdapter {

    private Context context;
    private ListView lv_content;
    private int contentResourceId;
    private List<Map<String, String>> contentList = new ArrayList<>();

    /**
     * constructor
     *
     * @param context 上下文
     * @param pl_root 根布局（PanelListLayout）
     * @param lv_content content部分的布局（ListView）
     * @param contentResourceId content 部分的 item 布局
     * @param contentList content 部分的数据
     */
    public MyPanelListAdapter(Context context, PanelListLayout pl_root, ListView lv_content,
                              int contentResourceId, List<Map<String,String>> contentList) {
        super(context, pl_root, lv_content);
        this.context = context;
        this.lv_content = lv_content;
        this.contentResourceId = contentResourceId;
        this.contentList = contentList;
    }

    /**
     * 给该方法添加实现，返回Content部分的适配器
     *
     * @return adapter of content
     */
    @Override
    protected BaseAdapter getContentAdapter() {
        return new ContentAdapter(context,contentResourceId,contentList,lv_content);
    }

    /**
     * 给该方法添加实现，返回content部分的数据个数
     *
     * @return size of content data
     */
    @Override
    protected int getCount() {
        return contentList.size();
    }


}
