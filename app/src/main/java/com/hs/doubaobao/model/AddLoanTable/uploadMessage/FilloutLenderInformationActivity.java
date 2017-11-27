package com.hs.doubaobao.model.AddLoanTable.uploadMessage;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.hs.doubaobao.R;
import com.hs.doubaobao.base.AppBarActivity;
import com.hs.doubaobao.model.AddLoanTable.uploadMessage.fragment.AssetInformationFragment;
import com.hs.doubaobao.model.AddLoanTable.uploadMessage.fragment.BasicInformationFragment;
import com.hs.doubaobao.model.AddLoanTable.uploadMessage.fragment.FilloutLenderInfoAdapter;
import com.hs.doubaobao.model.AddLoanTable.uploadMessage.fragment.LiveInformationFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：zhanghaitao on 2017/11/23 17:30
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class FilloutLenderInformationActivity extends AppBarActivity {


    private TabLayout mTabLayout;
    private ViewPager mViewpager;
    private String[] mTabItemNameArray;
    private List<Fragment> fragments;
    private FilloutLenderInfoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("贷款人信息");
        isShowRightView(false);
        setContentView(R.layout.activity_fillout_lender_information);
        mTabLayout = (TabLayout) findViewById(R.id.main_tablayout);
        mViewpager = (ViewPager) findViewById(R.id.main_viewpager);
        mTabItemNameArray = getResources().getStringArray(R.array.fillout_tab_item);
        mTabLayout.addTab(mTabLayout.newTab().setText(mTabItemNameArray[0]));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTabItemNameArray[1]));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTabItemNameArray[2]));

        fragments = new ArrayList<>();
        fragments.add(new BasicInformationFragment());
        fragments.add(new LiveInformationFragment());
        fragments.add(new AssetInformationFragment());


        adapter = new FilloutLenderInfoAdapter(getSupportFragmentManager(), fragments, mTabItemNameArray);
        mViewpager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewpager);

        mTabLayout.getTabAt(0).getCustomView();
    }
}
