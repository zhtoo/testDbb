package com.hs.doubaobao.model.AddLoanTable.uploadMessage;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.hs.doubaobao.R;
import com.hs.doubaobao.base.AppBarActivity;
import com.hs.doubaobao.listener.EditListener;
import com.hs.doubaobao.model.AddLoanTable.uploadMessage.fragment.AssetInformationFragment;
import com.hs.doubaobao.model.AddLoanTable.uploadMessage.fragment.BasicInformationFragment;
import com.hs.doubaobao.model.AddLoanTable.uploadMessage.fragment.FilloutLenderInfoAdapter;
import com.hs.doubaobao.model.AddLoanTable.uploadMessage.fragment.LiveInformationFragment;
import com.hs.doubaobao.view.ArcProgressView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：zhanghaitao on 2017/11/23 17:30
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class FilloutLenderInformationActivity extends AppBarActivity {

    @BindView(R.id.fillout_basic_info_progress)
    public  ArcProgressView mBasicProgress;
    @BindView(R.id.fillout_live_info_progress)
    public  ArcProgressView mLiveProgress;
    @BindView(R.id.fillout_asset_info_progress)
    public ArcProgressView mAssetProgress;
    @BindView(R.id.fillout_lender_info_save)
    Button filloutSave;
    @BindView(R.id.fillout_basic_info_tab)
    FrameLayout mBasicInfoTab;
    @BindView(R.id.fillout_live_info_tab)
    FrameLayout mtLiveInfoTab;
    @BindView(R.id.fillout_asset_info_tab)
    FrameLayout mAssetInfoTab;
    @BindView(R.id.lender_info_tablayout)
    TabLayout mTabLayout;
    @BindView(R.id.lender_info_viewpager)
    ViewPager mViewpager;

    private String[] mTabItemNameArray;//标题集合
    private List<Fragment> fragments;
    //viewpager的监听器
    private FilloutLenderInfoAdapter adapter;
    //监听器
    public EditListener editBasicListener;
    public EditListener editLiveListener;
    public EditListener editAssetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("贷款人信息");
        isShowRightView(false);
        setContentView(R.layout.activity_fillout_lender_information);
        ButterKnife.bind(this);
        initListener();
        intData();
    }

    /**
     * 初始化监听
     */
    private void initListener() {
        editBasicListener = new EditListener(){
            @Override
            public void toChangedProgress(int isAdd) {
                changeProgress(mBasicProgress,isAdd);
            }
        };
        editLiveListener = new EditListener(){
            @Override
            public void toChangedProgress(int isAdd) {
                changeProgress(mLiveProgress,isAdd);
            }
        };
        editAssetListener = new EditListener(){
            @Override
            public void toChangedProgress(int isAdd) {
                changeProgress(mAssetProgress,isAdd);
            }
        };
    }

    /**
     * 根据传入的数值将与之对应的的ArcProgressView的状态进行改变
     * @param progressView
     * @param isAdd
     */
    public void changeProgress(ArcProgressView progressView,int isAdd) {
        if(isAdd !=0){
            float progress = progressView.getmProgress();
            progressView.setmProgress(progress + isAdd);
        }
    }

    /**
     * 初始化数据（不需要联网，联网操作交给fragment来实现）
     */
    private void intData() {
        mTabItemNameArray = getResources().getStringArray(R.array.fillout_tab_item);

        fragments = new ArrayList<>();
        fragments.add(new BasicInformationFragment());
        fragments.add(new LiveInformationFragment());
        fragments.add(new AssetInformationFragment());

        adapter = new FilloutLenderInfoAdapter(getSupportFragmentManager(), fragments, mTabItemNameArray);
        mViewpager.setAdapter(adapter);
        mViewpager.setOffscreenPageLimit(fragments.size());
        mTabLayout.setupWithViewPager(mViewpager);

        mBasicProgress.setmProgressMax(6);
        mLiveProgress.setmProgressMax(3);
        mAssetProgress.setmProgressMax(5);
    }

    /**
     * 保存按钮
     */
    @OnClick(R.id.fillout_lender_info_save)
    public void onViewClicked() {
        int currentItem = mViewpager.getCurrentItem();

        switch (currentItem){
            case 0:
                Toast.makeText(this, mTabItemNameArray[currentItem], Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(this, mTabItemNameArray[currentItem], Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(this, mTabItemNameArray[currentItem], Toast.LENGTH_SHORT).show();
                break;
        }

    }

    /**
     * 顶部图标和ViewPager绑定
     * @param view
     */
    @OnClick({R.id.fillout_basic_info_tab, R.id.fillout_live_info_tab, R.id.fillout_asset_info_tab})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fillout_basic_info_tab:
                mViewpager.setCurrentItem(0);
                break;
            case R.id.fillout_live_info_tab:
                mViewpager.setCurrentItem(1);
                break;
            case R.id.fillout_asset_info_tab:
                mViewpager.setCurrentItem(2);
                break;
        }
    }
}
