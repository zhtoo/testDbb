package com.hs.doubaobao.model.ApplyLoad;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.hs.doubaobao.R;
import com.hs.doubaobao.base.TransparentBarActivity;
import com.hs.doubaobao.bean.HomeBean;
import com.hs.doubaobao.model.AddLoanTable.AddLoanTableActivity;
import com.hs.doubaobao.model.ApplyLoad.RecyclerView.ApplyLoadAdapter;
import com.hs.doubaobao.model.ApplyLoad.RecyclerView.ChildItem;
import com.hs.doubaobao.model.ApplyLoad.RecyclerView.ParentItem;
import com.zht.expandablerecyclerview.ExpandableRecyclerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 作者：zhanghaitao on 2017/11/20 15:15
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class ApplyLoadActivity extends TransparentBarActivity implements ApplyLoadContract.View {


    private ApplyLoadContract.Presenter presenter;
    private RecyclerView recyclerView;
    private RelativeLayout test;
    private EditText editText;
    private ImageView image;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_apply_load);

        recyclerView = (RecyclerView) findViewById(R.id.applyload_recyclerview);
       /* test = (RelativeLayout) findViewById(R.id.apply_list_test);
        editText = (EditText) findViewById(R.id.apply_list_edit);
        image = (ImageView) findViewById(R.id.apply_list_search);*/
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

/*
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setVisibility(View.VISIBLE);
                ShowSearchAnimator(0f, 1f);
            }
        });*/

        setBarAlpha(100);
        initData();

        //将Presenter和View进行绑定
        new ApplyLoadPresener(this, this);
        //presenter.getData(new LinkedHashMap());
    }

    /**
     * 初始化数据（temp）
     */
    private void initData() {
        final List<ParentItem> parentItems = new ArrayList<>();

        for (int i = 1; i < 21; i++) {
            ChildItem child = new ChildItem();
            child.setLoansUse("买下皇城" + i);
            child.setLoanCategories("网汇贷" + i);
            child.setTelephone("135171945" + i);
            child.setApplicationPeriod("期数" + i);
            child.setStoreName("合肥分部" + i);
            child.setLoanAmount(i + "万元");
            parentItems.add(new ParentItem("张亚鹏" + i, "2017-11-" + i, Arrays.asList(child)));
        }
        ApplyLoadAdapter adapter = new ApplyLoadAdapter(this, parentItems);
        recyclerView.setAdapter(adapter);
        /**父类条目展开的监听*/
        adapter.setExpandCollapseListener(new ExpandableRecyclerAdapter.ExpandCollapseListener() {
            @Override
            public void onParentExpanded(int parentPosition) {
                Toast.makeText(ApplyLoadActivity.this, "parentPosition:" + parentPosition,
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onParentCollapsed(int parentPosition) {
                Toast.makeText(ApplyLoadActivity.this, "parentPosition:" + parentPosition,
                        Toast.LENGTH_SHORT).show();
            }
        });
        /**详情界面按钮的监听*/
        adapter.setChildItemClickListener(new ApplyLoadAdapter.ChildItemClickListener() {
            @Override
            public void onChildItemClick(int parentPosition, int childPosition) {
                Toast.makeText(ApplyLoadActivity.this, "parentPosition:" + parentPosition +
                                "---childPosition:" + childPosition,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }


   /* private void ShowSearchAnimator(float start, float end) {
        //设置缩放的相对位置
        test.setPivotX(editText.getWidth());//相对于控件的位子
        test.setPivotY(0);

        ObjectAnimator anim = ObjectAnimator//
                .ofFloat(test, "apply_load", start, end)//设置变化目标值
                .setDuration(500);//设置动画时间
        anim.start();
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float cVal = (Float) animation.getAnimatedValue();
               // test.setAlpha(cVal);
                test.setScaleX(cVal);
               // editText.setScaleY(cVal);
            }
        });

    }*/

    /**
     * 跳转到创建新表界面
     * @param view
     */
    public void onAddNewTable(View view) {
        Intent intent = new Intent(this,AddLoanTableActivity.class);
        startActivity(intent);
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

    public void onAddNewable(View view) {
    }
}
