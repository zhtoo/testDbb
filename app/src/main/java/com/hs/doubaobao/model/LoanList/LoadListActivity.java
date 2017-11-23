package com.hs.doubaobao.model.LoanList;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.hs.doubaobao.R;
import com.hs.doubaobao.bean.HomeBean;
import com.hs.doubaobao.model.LoanList.list.MyPanelListAdapter;
import com.hs.doubaobao.model.LoanList.list.PanelListLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者：zhanghaitao on 2017/11/21 16:32
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class LoadListActivity extends AppCompatActivity implements LoadListContract.View {


    private LoadListContract.Presenter presenter;
    private RecyclerView recyclerView;
    private ListView idLvContent;
    private PanelListLayout idPlRoot;
    private MyPanelListAdapter adapter;

    private List<Map<String, String>> contentList = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_load_list);

        //将Presenter和View进行绑定
        new LoadListPresener(this, this);
        //presenter.getData(new LinkedHashMap());

        idLvContent = (ListView) findViewById(R.id.id_lv_content);
        idPlRoot = (PanelListLayout) findViewById(R.id.id_pl_root);

        initContentDataList();
        initColumnDataList();
        adapter = new MyPanelListAdapter(this, idPlRoot, idLvContent, R.layout.item_content, contentList);
        adapter.setInitPosition(0);
        adapter.setRowDataList(getRowDataList());
        adapter.setTitle("客户姓名");
        adapter.setColumnDataList(columnDataList);
        adapter.setSwipeRefreshEnabled(false);

        idPlRoot.setAdapter(adapter);

        adapter.getColumnListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(LoadListActivity.this, "点击条目" + (position + 1), Toast.LENGTH_SHORT).show();
            }
        });
        adapter.getContentListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(LoadListActivity.this, "内容点击条目" + (position + 1), Toast.LENGTH_SHORT).show();
            }
        });

    }


    private List<String> columnDataList = new ArrayList<>();

    /**
     * 初始化姓名
     */
    private void initColumnDataList() {


        for (int i = 1; i < 21; i++) {
            Map<String, String> data = new HashMap<>();


            columnDataList.add("李四" + i);

        }

    }


    /**
     * 初始化content数据
     */
    private void initContentDataList() {
        for (int i = 1; i < 21; i++) {
            Map<String, String> data = new HashMap<>();
            data.put("1", "贷款金额"+i);
            data.put("2", "贷款状态"+i);
            data.put("3", "申请期限"+i);
            data.put("4", "贷款类别"+i);
            data.put("5", "放款金额"+i);
            data.put("6", "贷款用途"+i);
            data.put("7", "电话号码"+i);
            data.put("8", "门店名称"+i);
            data.put("9", "贷款时间"+i);
            contentList.add(data);
        }
    }


    /**
     * 生成一份横向表头的内容，固定数据
     *
     * @return List<String>
     */
    private List<String> getRowDataList() {
        List<String> rowDataList = new ArrayList<>();
        rowDataList.add("贷款金额");
        rowDataList.add("状态");
        rowDataList.add("申请期限");
        rowDataList.add("贷款类别");
        rowDataList.add("放款金额");
        rowDataList.add("贷款用途");
        rowDataList.add("电话号码");
        rowDataList.add("门店名称");
        rowDataList.add("贷款时间");
        return rowDataList;
    }


    @Override
    public void setData(HomeBean bean) {

    }

    @Override
    public void setError(String text) {

    }

    @Override
    public void setPresenter(LoadListContract.Presenter presenter) {
        this.presenter = presenter;
    }
}