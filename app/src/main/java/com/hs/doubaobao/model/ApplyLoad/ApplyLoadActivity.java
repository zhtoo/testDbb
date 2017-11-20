package com.hs.doubaobao.model.ApplyLoad;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.hs.doubaobao.R;
import com.hs.doubaobao.bean.HomeBean;

/**
 * 作者：zhanghaitao on 2017/11/20 15:15
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class ApplyLoadActivity extends AppCompatActivity implements ApplyLoadContract.View {


    private ApplyLoadContract.Presenter presenter;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_apply_load);

        recyclerView = (RecyclerView) findViewById(R.id.applyload_recyclerview);

        //将Presenter和View进行绑定
        new ApplyLoadPresener(this, this);
        //presenter.getData(new LinkedHashMap());
    }






    @Override
    public void setData(HomeBean bean) {

    }

    @Override
    public void setError(String text) {

    }

    @Override
    public void setPresenter(ApplyLoadContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
