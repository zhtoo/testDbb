package com.hs.doubaobao.model.detail.pictrue;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.hs.doubaobao.R;
import com.hs.doubaobao.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：zhanghaitao on 2017/9/12 15:11
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class PictrueFragment extends BaseFragment implements PictrueAdapter.onItemClickListener {

    private RecyclerView mRecycler;
    private List<String> mList;

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

        String URL = "http://192.168.1.244:8080/pictrue/";
        mList.add(URL + "testPictrue0.png");
        mList.add(URL + "testPictrue1.png");
        mList.add(URL + "testPictrue2.png");
        mList.add(URL + "testPictrue3.png");
        mList.add(URL + "testPictrue4.png");
        mList.add(URL + "testPictrue5.png");
        mList.add(URL + "testPictrue6.png");
        mList.add(URL + "testPictrue7.png");
        mList.add(URL + "testPictrue8.png");
        mList.add(URL + "testPictrue9.png");
        mList.add(URL + "testPictrue10.png");
        mList.add(URL + "testPictrue11.png");
        mList.add(URL + "testPictrue12.png");
        mList.add(URL + "testPictrue13.png");
        mList.add(URL + "testPictrue14.png");
        mList.add(URL + "testPictrue15.png");
        mList.add(URL + "testPictrue16.png");
        mList.add(URL + "testPictrue17.png");
        mList.add(URL + "testPictrue18.png");
        mList.add(URL + "testPictrue19.png");
        mList.add(URL + "testPictrue20.png");
        mList.add(URL + "testPictrue21.png");
        PictrueAdapter adapter = new PictrueAdapter(getContext(), mList);
        mRecycler.setAdapter(adapter);

        adapter.setOnItemClickListener(this);


    }

    @Override
    public void onItemClick(View view, int position) {
        //imagesize是作为loading时的图片size
        ImagePagerActivity.ImageSize imageSize = new ImagePagerActivity.ImageSize(view.getMeasuredWidth(), view.getMeasuredHeight());
        ImagePagerActivity.startImagePagerActivity(getContext(), mList, position, imageSize);
    }
}
