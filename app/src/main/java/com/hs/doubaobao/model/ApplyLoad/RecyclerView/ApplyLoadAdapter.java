package com.hs.doubaobao.model.ApplyLoad.RecyclerView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hs.doubaobao.R;
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
        View view = mInflater.inflate(R.layout.item_applyload_parent, viewGroup, false);
        return new ParentItemViewHolder(view);
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
        View view = mInflater.inflate(R.layout.item_applyload_child, viewGroup, false);
        ChildItemViewHolder viewHolder = new ChildItemViewHolder(view);
        viewHolder.setListener(new ChildItemViewHolder.ClickListener() {
            @Override
            public void onItemClick(int parentPosition, int childPosition) {
                if(listener!=null){
                    listener.onChildItemClick(parentPosition,childPosition);
                }
            }
        });
        return viewHolder;
    }


    @UiThread
    @Override
    public void onBindParentViewHolder(@NonNull ParentItemViewHolder parentItemViewHolder, int parentPosition, @NonNull ParentItem parentItem) {
        parentItemViewHolder.bind(parentItem,parentPosition);
    }


    @UiThread
    @Override
    public void onBindChildViewHolder(@NonNull ChildItemViewHolder childItemViewHolder, int parentPosition, int childPosition, @NonNull ChildItem childItem) {
        childItemViewHolder.bind(childItem,parentPosition,childPosition);
    }




    /*@Override
    public int getParentViewType(int parentPosition) {
        if (mParentList.get(parentPosition).isVegetarian()) {
            return PARENT_VEGETARIAN;
        } else {
            return PARENT_NORMAL;
        }
    }

    @Override
    public int getChildViewType(int parentPosition, int childPosition) {
        ChildItem child = mParentList.get(parentPosition).getIngredient(childPosition);
        if (child.isVegetarian()) {
            return CHILD_VEGETARIAN;
        } else {
            return CHILD_NORMAL;
        }
    }

    @Override
    public boolean isParentViewType(int viewType) {
        return viewType == PARENT_VEGETARIAN || viewType == PARENT_NORMAL;
    }*/

    public void setChildItemClickListener(ChildItemClickListener childItemClickListener) {
        this.listener = childItemClickListener;
    }

    private ChildItemClickListener listener;
    public interface  ChildItemClickListener{
        void onChildItemClick(int parentPosition, int childPosition);
    }


}
