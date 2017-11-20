package com.hs.doubaobao.model.ApplyLoad.RecyclerView;

import com.zht.expandablerecyclerview.model.Parent;

import java.util.List;

/**
 * 作者：zhanghaitao on 2017/11/20 17:22
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class ParentItem implements Parent<ChildItem> {

    private String customName;
    private String applyDate;


    private List<ChildItem> mChildItems;

    public ParentItem(String name, String date, List<ChildItem> mChildItems) {
        customName = name;
        applyDate = date;
        this.mChildItems = mChildItems;
    }

    @Override
    public List<ChildItem> getChildList() {
        return mChildItems;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }


    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }


}
