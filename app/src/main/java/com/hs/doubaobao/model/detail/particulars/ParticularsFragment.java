package com.hs.doubaobao.model.detail.particulars;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.hs.doubaobao.R;
import com.hs.doubaobao.base.BaseFragment;

import java.util.ArrayList;
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

        ParticularsAdapter adapter = new ParticularsAdapter(getContext() ,mTitles,mMap);

        mRecycler.setAdapter(adapter);
    }
}
