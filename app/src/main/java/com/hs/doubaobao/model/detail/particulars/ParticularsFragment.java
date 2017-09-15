package com.hs.doubaobao.model.detail.particulars;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.hs.doubaobao.R;
import com.hs.doubaobao.base.BaseFragment;

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

public class ParticularsFragment extends BaseFragment {

    private RecyclerView mRecycler;

    @Override
    protected int setLayout() {
        return R.layout.fragment_particulars;
    }

    @Override
    protected void initView(View view) {
        mRecycler = (RecyclerView) view.findViewById(R.id.particulars_recycler);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayout.VERTICAL);

        mRecycler.setLayoutManager(llm);

        List<String> mTitles = new ArrayList<>();
        List<Map> mMap =new ArrayList<>();


        Map<String,String> map1 = new LinkedHashMap<>();
        map1.put("贷款类别","汇生带环");
        map1.put("line","line");
        map1.put("*申请贷款金额","20");

        mTitles.add("贷款类别");
        mMap.add(map1);

        Map<String,String> map2 = new LinkedHashMap<>();
        map2.put("淘宝网/支付宝账户","阿道夫却无法续请问安抚沁人肺腑我让师傅阿凡达阿恶趣味大所多放大VB发帖各大VGA的阿萨德阿打算的撒多撒地方热饭萨芬撒放弃打算翁染色费的达到期望大师傅玩儿法分期未发生烦人按时按时");
        map2.put("line","line");
        map2.put("车辆情况","阿道夫却无法续请问安抚沁人肺腑我让师傅阿凡达阿恶趣味大所多放大VB发帖各大VGA的阿萨德阿打算的撒多撒地方热饭萨芬撒放弃打算翁染色费的达到期望大师傅玩儿法分期未发生烦人按时按时");

        mTitles.add("客户详情调查表");
        mMap.add(map2);

        ParticularsAdapter adapter = new ParticularsAdapter(getContext() ,mTitles,mMap);
        mRecycler.setAdapter(adapter);
    }
}
