package com.hs.doubaobao.model.detail.pictrue;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.hs.doubaobao.R;
import com.hs.doubaobao.base.BaseFragment;
import com.hs.doubaobao.bean.PictrueBean;
import com.hs.doubaobao.model.detail.DetailActivity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者：zhanghaitao on 2017/9/12 15:11
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class PictrueFragment extends BaseFragment implements PictrueContract.View, PictrueAdapter.onItemClickListener {

    private PictrueContract.Presenter presenter;

    private RecyclerView mRecycler;
    private List<String> mList;
    private PictrueAdapter adapter;

    @Override
    protected int setLayout() {
        return R.layout.fragment_pictrue;
    }

    @Override
    protected void initView(View view) {

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
        Map<String, String> map = new LinkedHashMap<>();
        map.put("id", id);
        presenter.getData(map);


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
        Toast.makeText(getContext(), "访问数据失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(PictrueContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
