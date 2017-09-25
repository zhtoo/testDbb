package com.hs.doubaobao.model.detail.video;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.hs.doubaobao.R;
import com.hs.doubaobao.base.BaseFragment;
import com.hs.doubaobao.bean.ApprovalBean;
import com.hs.doubaobao.model.detail.DetailActivity;
import com.hs.doubaobao.utils.log.LogWrap;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 作者：zhanghaitao on 2017/9/12 15:12
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class VideoFragment extends BaseFragment implements VideoContract.View, VideoAdapter.onItemClickListener {


    private VideoContract.Presenter presenter;

    private ArrayList<String> mList;
    private RecyclerView mRecycler;

    @Override
    protected int setLayout() {
        return R.layout.fragment_video;
    }

    @Override
    protected void initView(View view) {
        mRecycler = (RecyclerView) view.findViewById(R.id.video_recycler);

        GridLayoutManager lm = new GridLayoutManager(getContext(), 3);
        lm.setOrientation(GridLayoutManager.VERTICAL);
        mRecycler.setLayoutManager(lm);

        mList = new ArrayList<String>();

        String URL = "http://192.168.1.244:8080/test/";
        mList.add(URL + "VID_20170906_112401.mp4");
        mList.add(URL + "TestMovie.mp4");
        mList.add("http://inchangshan1.oss-cn-hangzhou.aliyuncs.com/m3u8SegementIn72/学问是什么~2017.6.30 00_00_00-00_15_00.mp4");
        mList.add("http://inchangshan1.oss-cn-hangzhou.aliyuncs.com/m3u8SegementIn72/19、《史记》的形成~2017.6.30 00_00_00-00_15_00.mp4");
        mList.add("http://inchangshan1.oss-cn-hangzhou.aliyuncs.com/m3u8SegementIn72/2C66015B7F0CAF7A62D6283E318BBB79.mp4");
        mList.add("http://inchangshan1.oss-cn-hangzhou.aliyuncs.com/m3u8SegementIn72/BF48015B99B15C709892362459760B82.mp4");
        mList.add("http://inchangshan1.oss-cn-hangzhou.aliyuncs.com/m3u8SegementIn72/12、儒家经典的诞生~2017.6.30 00_00_00-00_15_00.mp4");
        mList.add("http://inchangshan1.oss-cn-hangzhou.aliyuncs.com/m3u8SegementIn72/0E1D015B8B3205B7B471E7B76B9ED862.mp4");
        mList.add("http://inchangshan1.oss-cn-hangzhou.aliyuncs.com/m3u8SegementIn72/450C015B93284D684AA9084F4FD2021C.mp4");
        mList.add("http://inchangshan1.oss-cn-hangzhou.aliyuncs.com/m3u8SegementIn72/6518013E2FCD3E842C4A8128DC500372.mp4");
        mList.add("http://inchangshan1.oss-cn-hangzhou.aliyuncs.com/m3u8SegementIn72/3BC7015D0D6A0943A3E87E2AA73D3DFC.mp4");
        mList.add("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4");
        VideoAdapter adapter = new VideoAdapter(getContext(), mList);
        mRecycler.setAdapter(adapter);
        adapter.setOnItemClickListener(this);


        DetailActivity activity = (DetailActivity) getActivity();
        String id = activity.id;

        //将Presenter和View进行绑定
        new VideoPresener(this,getContext());
        //获取数据

        Map<String,String> map = new LinkedHashMap<>();
        map.put("id",id);
        presenter.getData(map);

    }

    @Override
    public void onItemClick(View view, int postion) {
        Uri uri = Uri.parse(mList.get(postion));
        //调用系统自带的播放器
        Intent intent = new Intent(Intent.ACTION_VIEW);
        LogWrap.v("URI--------", uri.toString());
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
    public void setData(ApprovalBean bean) {

    }

    @Override
    public void setError(String text) {

    }

    @Override
    public void setPresenter(VideoContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
