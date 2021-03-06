package com.hs.doubaobao.model.detail.video;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.hs.doubaobao.R;
import com.hs.doubaobao.base.BaseFragment;
import com.hs.doubaobao.bean.VideoBean;
import com.hs.doubaobao.model.detail.DetailActivity;
import com.hs.doubaobao.utils.PullToRefresh;
import com.hs.doubaobao.utils.log.Logger;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 作者：zhanghaitao on 2017/9/12 15:12
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class VideoFragment extends BaseFragment implements VideoContract.View, VideoAdapter.onItemClickListener, PullToRefresh.PullToRefreshListener {

    private VideoContract.Presenter presenter;

    private List<String> mList;
    private RecyclerView mRecycler;
    private VideoAdapter adapter;
    private List<VideoBean.ResDataBean.PicListBean> picList;
    private PtrClassicFrameLayout ptrFrame;
    private Map<String, String> map;

    @Override
    protected int setLayout() {
        return R.layout.fragment_video;
    }

    @Override
    protected void initView(View view) {


        //无数据刷新
        ptrFrame = (PtrClassicFrameLayout) view.findViewById(R.id.video_ptr);
        initPtrClassicFrameLayout();

        mRecycler = (RecyclerView) view.findViewById(R.id.video_recycler);

        GridLayoutManager lm = new GridLayoutManager(getContext(), 3);
        lm.setOrientation(GridLayoutManager.VERTICAL);
        mRecycler.setLayoutManager(lm);

        mList = new ArrayList<>();

        adapter = new VideoAdapter(getContext(), mList);
        mRecycler.setAdapter(adapter);
        adapter.setOnItemClickListener(this);


        DetailActivity activity = (DetailActivity) getActivity();
        String id = activity.id;

        //将Presenter和View进行绑定
        new VideoPresener(this,getContext());
        //获取数据

        map = new LinkedHashMap<>();
        map.put("id",id);
        presenter.getData(map);

    }

    /**
     * 初始化上拉加载下拉刷新的布局
     * 注意：adapter的初始化在 PullToRefresh 之前
     */
    private void initPtrClassicFrameLayout() {
        //创建PtrClassicFrameLayout的包装类对象
        PullToRefresh refresh = new PullToRefresh();
        //初始化PtrClassicFrameLayout
        refresh.initPTR(getContext(), ptrFrame, PtrFrameLayout.Mode.REFRESH);
        //设置监听
        refresh.setPullToRefreshListener(this);
    }

    @Override
    public void onItemClick(View view, int postion) {
        Uri uri = Uri.parse(picList.get(postion).getPath());
        //调用系统自带的播放器
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Logger.v("URI--------", uri.toString());
        intent.setDataAndType(uri, "video/*");
        try{
            //调用系统的播放器
            startActivity(intent);
        }catch (Exception e){
            Toast.makeText(getContext(), "没有默认的播放器", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    public void setData(VideoBean bean ) {
        if (bean == null || bean.getResData().getPicList() == null || bean.getResData().getPicList().size() == 0) {
            mRecycler.setVisibility(View.GONE);
        }else {
            picList = bean.getResData().getPicList();
            for (int i = 0; i < picList.size(); i++) {
                String name = picList.get(i).getName();
                mList.add(name);
            }
            mRecycler.setVisibility(View.VISIBLE);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setError(String text) {
        mRecycler.setVisibility(View.GONE);
        Toast.makeText(getContext(), "访问数据失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(VideoContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void pullToRefresh() {
        presenter.getData(map);
    }

    @Override
    public void pullToLoadMore() {
        presenter.getData(map);
    }
}
