package com.hs.doubaobao.model.AddLoanTable.uploadMessage.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Tablayout的FragmentPager适配器
 * 用于将Fragment加载到ViewPager中
 * 作者：zhanghaitao on 2017/8/15 09:48
 * 邮箱：820159571@qq.com
 */

public class FilloutLenderInfoAdapter extends FragmentPagerAdapter {

    /**fragment集合*/
    private List<Fragment> mFragmentList = null;
    /**标题集合*/
    private String[] titles;

    public FilloutLenderInfoAdapter(FragmentManager mFragmentManager,
                                    ArrayList<Fragment> fragmentList) {
        super(mFragmentManager);
        mFragmentList = fragmentList;
    }

    /**
     * titles是给TabLayout设置title用的
     *
     * @param mFragmentManager
     * @param fragmentList
     * @param titles
     */
    public FilloutLenderInfoAdapter(FragmentManager mFragmentManager,
                                    List<Fragment> fragmentList, String[] titles) {
        super(mFragmentManager);
        mFragmentList = fragmentList;
        this.titles = titles;
    }

    /**
     * 获取数量
     *
     * @return page的数量
     * @see android.support.v4.view.PagerAdapter#getCount()
     */
    @Override
    public int getCount() {
        return mFragmentList == null ? 0 : mFragmentList.size();
    }

    /**
     * 获取索引位置的Fragment.
     *
     * @param position the position
     * @return the item
     * @see android.support.v4.app.FragmentPagerAdapter#getItem(int)
     */
    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;
        if (position < mFragmentList.size()) {
            fragment = mFragmentList.get(position);
        } else {
            fragment = mFragmentList.get(0);
        }
        return fragment;
    }

    /**
     * 将标题设置到相对应的位置
     *
     * @param position the position
     * @return the page's title
     */
    @Override
    public CharSequence getPageTitle(int position) {
        if (titles != null && titles.length > 0)
            return titles[position];
        return null;
    }
}