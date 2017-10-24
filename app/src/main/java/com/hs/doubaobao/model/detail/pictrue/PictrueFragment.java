package com.hs.doubaobao.model.detail.pictrue;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hs.doubaobao.R;
import com.hs.doubaobao.base.BaseFragment;
import com.hs.doubaobao.bean.PictrueBean;
import com.hs.doubaobao.model.detail.DetailActivity;
import com.hs.doubaobao.utils.PullToRefresh;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 作者：zhanghaitao on 2017/9/12 15:11
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class PictrueFragment extends BaseFragment implements PictrueContract.View, PictrueAdapter.onItemClickListener, PullToRefresh.PullToRefreshListener {

    private PictrueContract.Presenter presenter;

    private RecyclerView mRecycler;
    private List<String> mList;
    private PictrueAdapter adapter;
    private Button mReload;
    private PtrClassicFrameLayout ptrFrame;
    private Map<String, String> map;

    @Override
    protected int setLayout() {
        return R.layout.fragment_pictrue;
    }

    @Override
    protected void initView(View view) {

        //无数据刷新
        ptrFrame = (PtrClassicFrameLayout) view.findViewById(R.id.pictrue_ptr);
        initPtrClassicFrameLayout();

        mRecycler = (RecyclerView) view.findViewById(R.id.pictrue_recycler);

        GridLayoutManager lm = new GridLayoutManager(getContext(), 4);
        lm.setOrientation(GridLayoutManager.VERTICAL);
        mRecycler.setLayoutManager(lm);

        mList = new ArrayList<>();

        adapter = new PictrueAdapter(getContext(), mList);
        mRecycler.setAdapter(adapter);
        adapter.setOnItemClickListener(this);

        DetailActivity activity = (DetailActivity) getActivity();
        String id = activity.id;

        //将Presenter和View进行绑定
        new PictruePresener(this, getContext());
        //获取数据
        map = new LinkedHashMap<>();
        map.put("id", id);
        presenter.getData(map);


    }


    /**
     * 初始化上拉加载下拉刷新的布局
     * 注意：adapter的初始化在 PullToRefresh 之前
     */
    private void initPtrClassicFrameLayout() {
        //注意：adapter的初始化在 PullToRefresh 之前
        //创建PtrClassicFrameLayout的包装类对象
        PullToRefresh refresh = new PullToRefresh();
        //初始化PtrClassicFrameLayout
        refresh.initPTR(getContext(), ptrFrame, PtrFrameLayout.Mode.REFRESH);
        //设置监听
        refresh.setPullToRefreshListener(this);
    }

    @Override
    public void onItemClick(View view, int position) {
        //imagesize是作为loading时的图片size
        ImagePagerActivity.ImageSize imageSize = new ImagePagerActivity.ImageSize(view.getMeasuredWidth(), view.getMeasuredHeight());
        ImagePagerActivity.startImagePagerActivity(getContext(), mList, position, imageSize);
    }

    @Override
    public void setData(PictrueBean bean) {
        if (bean == null || bean.getResData().getPicList() == null || bean.getResData().getPicList().size() == 0) {
            mRecycler.setVisibility(View.GONE);
        }else {
            List<PictrueBean.ResDataBean.PicListBean> picList = bean.getResData().getPicList();
            mList.clear();
            for (int i = 0; i < picList.size(); i++) {
                mList.add(picList.get(i).getPath());
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
    public void setPresenter(PictrueContract.Presenter presenter) {
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
