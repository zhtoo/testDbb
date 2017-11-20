package com.hs.doubaobao.model.ApplyLoad.RecyclerView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.zht.expandablerecyclerview.ExpandableRecyclerAdapter;

import java.util.List;

/**
 * 作者：zhanghaitao on 2017/11/20 17:25
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class ApplyLoadAdapter extends ExpandableRecyclerAdapter<ParentItem, ChildItem, ParentItemViewHolder, ChildItemViewHolder> {

    private LayoutInflater mInflater;
    private List<ParentItem> mParentList;



    public ApplyLoadAdapter(Context context, @NonNull List<ParentItem> parentList) {
        super(parentList);
        mParentList = parentList;
        mInflater = LayoutInflater.from(context);
    }

    /**
     * 在这里决定父类的视图
     * @param viewGroup
     * @param viewType
     * @return
     */
    @UiThread
    @NonNull
    @Override
    public ParentItemViewHolder onCreateParentViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return null;
    }

    /**
     * 在这里决定孩子的视图
     * @param viewGroup
     * @param viewType
     * @return
     */
    @UiThread
    @NonNull
    @Override
    public ChildItemViewHolder onCreateChildViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return null;
    }


    @UiThread
    @Override
    public void onBindParentViewHolder(@NonNull ParentItemViewHolder parentItemViewHolder, int parentPosition, @NonNull ParentItem parentItem) {
        parentItemViewHolder.bind(parentItem);
    }


    @UiThread
    @Override
    public void onBindChildViewHolder(@NonNull ChildItemViewHolder childItemViewHolder, int parentPosition, int childPosition, @NonNull ChildItem childItem) {
        childItemViewHolder.bind(childItem);
    }
}
