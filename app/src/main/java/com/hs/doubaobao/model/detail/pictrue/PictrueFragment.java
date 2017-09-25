package com.hs.doubaobao.model.detail.pictrue;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
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

//        GridLayoutManager lm = new GridLayoutManager(getContext(), 4);
//        lm.setOrientation(GridLayoutManager.VERTICAL);
//        mRecycler.setLayoutManager(lm);

        StaggeredGridLayoutManager lm = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
        mRecycler.setLayoutManager(lm);

        mList = new ArrayList<>();

//        String URL = "http://192.168.1.244:8080/pictrue/";
//        mList.add(URL + "testPictrue0.png");
//        mList.add(URL + "testPictrue1.png");
//        mList.add(URL + "testPictrue2.png");
//        mList.add(URL + "testPictrue3.png");
//        mList.add(URL + "testPictrue4.png");
//        mList.add(URL + "testPictrue5.png");
//        mList.add(URL + "testPictrue6.png");
//        mList.add(URL + "testPictrue7.png");
//        mList.add(URL + "testPictrue8.png");
//        mList.add(URL + "testPictrue9.png");
//        mList.add(URL + "testPictrue10.png");
//        mList.add(URL + "testPictrue11.png");
//        mList.add(URL + "testPictrue12.png");
//        mList.add(URL + "testPictrue13.png");
//        mList.add(URL + "testPictrue14.png");
//        mList.add(URL + "testPictrue15.png");
//        mList.add(URL + "testPictrue16.png");
//        mList.add(URL + "testPictrue17.png");
//        mList.add(URL + "testPictrue18.png");
//        mList.add(URL + "testPictrue19.png");
//        mList.add(URL + "testPictrue20.png");
//        mList.add(URL + "testPictrue21.png");
        adapter = new PictrueAdapter(getContext(), mList);
        mRecycler.setAdapter(adapter);
        adapter.setOnItemClickListener(this);


        DetailActivity activity = (DetailActivity) getActivity();
        String id = activity.id;

        //将Presenter和View进行绑定
        new PictruePresener(this,getContext());
        //获取数据

        Map<String,String> map = new LinkedHashMap<>();
        map.put("id",id);
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
        List<PictrueBean.ResDataBean.PicListBean> picList = bean.getResData().getPicList();
        mList.clear();
        for(int i = 0 ; i <picList.size();i++){
            mList.add(picList.get(i).getPath());
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
