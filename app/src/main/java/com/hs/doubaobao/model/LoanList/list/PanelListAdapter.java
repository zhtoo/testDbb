package com.hs.doubaobao.model.LoanList.list;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hs.doubaobao.R;
import com.hs.doubaobao.utils.DensityUtil;
import com.hs.doubaobao.utils.log.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * 这里是关键类，要注意了
 */
public abstract class PanelListAdapter {

    private static final String TAG = "PanelListAdapter";

    private Context context;

    /**
     * 两个横向滑动layout
     */
    private MyHorizontalScrollView mhsv_row;//横向的头
    private MyHorizontalScrollView mhsv_content;//横向的内容

    /**
     * 整个页面的所有布局
     */
    private PanelListLayout pl_root;//外层的根布局
    private TextView tv_title;//左上角的title

    private LinearLayout ll_row;//上方的表头
    private ListView lv_column;//左边的表头第一个
    private ListView lv_content;//中间的内容部分

    private LinearLayout ll_contentItem;//中间的内容部分的子布局
    private SwipeRefreshLayout swipeRefreshLayout;//中间ListView外层的下拉刷新布局

    /**
     * 标题的宽和高,同时也是列表头的宽和列表头的高
     */

    private int titleHeight = DensityUtil.getPixels(R.dimen.y75);
    private int titleWidth = DensityUtil.getPixels(R.dimen.x206);//像素
    private int columnItemHeight;//左边列表的行高取决于内容列表的行高

    private String title = "";
    private int titleBackgroundResource;
    private List<String> columnDataList;
    private List<String> rowDataList;

    private String columnColor;//default color of column
    private int titleColor;//default color of title
    private String rowColor;//default color of title

    private Drawable rowDivider;
    private Drawable columnDivider;

    private boolean swipeRefreshEnable = false;//默认关闭下拉刷新

    private int initPosition = 0;//列表显示的初始值，默认第一条数据显示在最上面

    private BaseAdapter columnAdapter;
    private BaseAdapter columnTwoAdapter;
    private BaseAdapter contentAdapter;

    private SwipeRefreshLayout.OnRefreshListener onRefreshListener = new DefaultRefreshListener();

    /**
     * 两个监听器，分别控制水平和垂直方向上的同步滑动
     */
    private HorizontalScrollListener horizontalScrollListener = new HorizontalScrollListener();
    private VerticalScrollListener verticalScrollListener = new VerticalScrollListener();
    private int[] textWidths = {
            DensityUtil.getPixels(R.dimen.x257),
            DensityUtil.getPixels(R.dimen.x257),
            DensityUtil.getPixels(R.dimen.x257),
            DensityUtil.getPixels(R.dimen.x257),
            DensityUtil.getPixels(R.dimen.x257),
            DensityUtil.getPixels(R.dimen.x257),
            DensityUtil.getPixels(R.dimen.x257),
            DensityUtil.getPixels(R.dimen.x257),
            DensityUtil.getPixels(R.dimen.x257)};
    private int lenth;


    private int Showlength;

    int[] coordinate = new int[10];
    private int firstShowItem;
    private int lastShowItem;

    private List<ImageView> dotList = new ArrayList<>();


    /**
     * constructor
     *
     * @param lv_content 内容的ListView
     */
    public PanelListAdapter(Context context, PanelListLayout pl_root, ListView lv_content) {
        this.context = context;
        this.pl_root = pl_root;
        this.lv_content = lv_content;

        coordinate[0] = 0;

        for (int i = 0; i < textWidths.length; i++) {
            lenth += textWidths[i];
            coordinate[i + 1] = lenth;
            Logger.e(TAG, "" + lenth);
        }


    }

    //region APIs

    /**
     * 设置表的标题
     *
     * @param title title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 设置表标题的背景
     *
     * @param resourceId a drawable resource id
     */
   /* public void setTitleBackgroundResource(int resourceId) {
        this.titleBackgroundResource = resourceId;
    }
*/

    /**
     * 设置表头的宽度
     *
     * @param titleWidth title width
     */
    public void setTitleWidth(int titleWidth) {
        this.titleWidth = titleWidth;
    }

    /**
     * 设置表头的高度
     *
     * @param titleHeight title height
     */
    public void setTitleHeight(int titleHeight) {
        this.titleHeight = titleHeight;
    }

    /**
     * 设置横向表头的标题（！！必须调用！！）
     *
     * @param rowDataList data list of row layout, must be a List<String>
     */
    public void setRowDataList(List<String> rowDataList) {
        this.rowDataList = rowDataList;
    }

    /**
     * 设置纵向表头的内容
     *
     * @param columnDataList data list of column layout, must be a List<String>. if you don`t call
     *                       this method, the default column list will be used
     */
    public void setColumnDataList(List<String> columnDataList) {
        this.columnDataList = columnDataList;
    }

    /**
     * 横向表头的分割线
     */
  /*  public void setRowDivider(Drawable rowDivider) {
        this.rowDivider = rowDivider;
    }*/

    /**
     * 纵向表头的分割线
     */
  /*  public void setColumnDivider(Drawable columnDivider) {
        this.columnDivider = columnDivider;
    }*/

    /**
     * 设置纵向表头的背景色
     *
     * @param columnColor background color of column
     */
 /*   public void setColumnColor(String columnColor) {
        this.columnColor = columnColor;
    }*/

    /**
     * 设置标题的背景色
     *
     * @param titleColor background color of title
     */
  /*  public void setTitleColor(int titleColor) {
        this.titleColor = titleColor;
    }
*/
    /**
     * 设置横向表头的背景色
     *
     * @param rowColor background color of row
     */
  /*  public void setRowColor(String rowColor) {
        this.rowColor = rowColor;
    }*/

    /**
     * 设置纵向表头的适配器
     *
     * @param columnAdapter adapter of column ListView
     */
    public void setColumnAdapter(BaseAdapter columnAdapter) {
        this.columnAdapter = columnAdapter;
    }

    /**
     * 设置content的初始position
     * <p>
     * 比如你想进入这个Activity的时候让第300条数据显示在屏幕上（前提是该数据存在）
     * 那么在这里传入299即可
     *
     * @param initPosition position
     */
    public void setInitPosition(int initPosition) {
        this.initPosition = initPosition;
    }

    /**
     * 返回中间内容部分的ListView
     *
     * @return listView of content
     */
    public ListView getContentListView() {
        return lv_content;
    }

    /**
     * 返回左边表头的ListView
     *
     * @return listView of column(left)
     */
    public ListView getColumnListView() {
        return lv_column;
    }

    /**
     * 返回上访表头的最外层布局
     *
     * @return a CheckableLinearLayout
     */
    public LinearLayout getRowLayout() {
        return ll_row;
    }

    /**
     * 设置是否开启下拉刷新（默认关闭）
     *
     * @param bool pass true to enable pullToRefresh
     */
    public void setSwipeRefreshEnabled(boolean bool) {
        swipeRefreshEnable = bool;
    }

    /**
     * 这里有点蛋疼，因为控件是在initAdapter中赋值的，但是这里要用
     * 所以如果开发者在setAdapter之前调用了该方法，则必须对控件进行赋值
     * 但如果赋值了，还得判断开发者是否设置了初始位置，因为控件默认开启，如果初始位置不为0，则控件启用
     * 这样会造成在中间阶段下拉会触发监听，因此对initPosition再进行一次判断
     * 当用户发生了滑动操作，控件的状态会被随即改变
     *
     * @param listener
     */
    public void setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener listener) {
        this.onRefreshListener = listener;
        if (swipeRefreshLayout == null) {
            swipeRefreshLayout = new SwipeRefreshLayout(context);
            if (initPosition != 0) {
                swipeRefreshLayout.setEnabled(false);
            }
        }
        swipeRefreshLayout.setOnRefreshListener(listener);
        Log.d(TAG, "setOnRefreshListener: " + onRefreshListener.toString());
    }

    public SwipeRefreshLayout getSwipeRefreshLayout() {
        return swipeRefreshLayout;
    }

    //endregion

    protected abstract BaseAdapter getContentAdapter();

    /**
     * 初始化总Adapter，加载数据到视图
     */
    void initAdapter() {
        contentAdapter = getContentAdapter();
        reorganizeViewGroup();

        mhsv_row.setOnHorizontalScrollListener(horizontalScrollListener);
        mhsv_content.setOnHorizontalScrollListener(horizontalScrollListener);

        lv_content.setOnScrollListener(verticalScrollListener);
        lv_column.setOnScrollListener(verticalScrollListener);

    }

    // must be override
    protected abstract int getCount();

    /**
     * 核心代码：
     * 整理重组整个表的布局
     * <p>
     * 主要包含4个部分
     * 1. title     标题
     * 2. row       行
     * 3. column    列
     * 4. content   内容
     */
    private void reorganizeViewGroup() {

        lv_content.setAdapter(contentAdapter);
        lv_content.setVerticalScrollBarEnabled(true);

        // clear root viewGroup
        pl_root.removeView(lv_content);

        /**dot*/
        LinearLayout layout = new LinearLayout(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                DensityUtil.getPixels(R.dimen.x20));
        layout.setLayoutParams(params);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setGravity(Gravity.CENTER);
        layout.setBackgroundResource(R.color.white);
        layout.setId(View.generateViewId());
        RelativeLayout.LayoutParams lp_layout =
                new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        DensityUtil.getPixels(R.dimen.x40));
        for (int i = 0; i < textWidths.length + 1; i++) {
            ImageView dot = new ImageView(context);
            dot.setBackgroundResource(R.drawable.ic_dot_orange);
            LinearLayout.LayoutParams dotParams = new LinearLayout.LayoutParams(
                    DensityUtil.getPixels(R.dimen.x12),
                    DensityUtil.getPixels(R.dimen.x12));
            dotParams.setMargins(0, 0, DensityUtil.getPixels(R.dimen.x10), 0);
            dot.setLayoutParams(dotParams);
            dotList.add(dot);
            layout.addView(dot);
        }


        pl_root.addView(layout, lp_layout);


        // 1. title (TextView --> PanelListLayout)
        /**标题*/
        tv_title = new TextView(context);
        tv_title.setText(title);
        Paint titlePaint = tv_title.getPaint();
        titlePaint.setColor(context.getResources().getColor(R.color.Orange));
        titlePaint.setTextSize(DensityUtil.getPixels(R.dimen.x28));

        tv_title.setGravity(Gravity.CENTER_VERTICAL);
        tv_title.setBackgroundColor(context.getResources().getColor(R.color.white));
        tv_title.setTextColor(context.getResources().getColor(R.color.Orange));
        tv_title.setId(View.generateViewId());//设置一个随机id，这样可以保证不冲突
        tv_title.setPadding(DensityUtil.getPixels(R.dimen.x24), 0, 0, 0);
        RelativeLayout.LayoutParams lp_tv_title = new RelativeLayout.LayoutParams(
                titleWidth, titleHeight);
        lp_tv_title.addRule(RelativeLayout.BELOW, layout.getId());
        pl_root.addView(tv_title, lp_tv_title);


        // 2. row（LinearLayout --> MyHorizontalScrollView --> PanelListLayout）
        /**可以滑动的标题条目*/
        ll_row = new LinearLayout(context);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        ll_row.setLayoutParams(lp);
        mhsv_row = new MyHorizontalScrollView(context);
        mhsv_row.setBackgroundColor(Color.WHITE);
        mhsv_row.setHorizontalScrollBarEnabled(false);
        mhsv_row.setOverScrollMode(View.OVER_SCROLL_NEVER);//去除滑动到边缘时出现的阴影
        mhsv_row.addView(ll_row);//暂时先不给ll_row添加子view，等布局画出来了再添加
        mhsv_row.setId(View.generateViewId());
        RelativeLayout.LayoutParams lp_mhsv_row = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, titleHeight);
        /**让行标签相对于标题*/
        lp_mhsv_row.addRule(RelativeLayout.BELOW, layout.getId());
        lp_mhsv_row.addRule(RelativeLayout.RIGHT_OF, tv_title.getId());

        pl_root.addView(mhsv_row, lp_mhsv_row);

        /*line*/
        LinearLayout view = new LinearLayout(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                DensityUtil.getPixels(R.dimen.x2));
        view.setLayoutParams(layoutParams);
        view.setBackgroundResource(R.color.Orange);
        view.setId(View.generateViewId());
        RelativeLayout.LayoutParams lp_line =
                new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        DensityUtil.getPixels(R.dimen.x2));
        lp_line.addRule(RelativeLayout.BELOW, tv_title.getId());
        lp_line.addRule(RelativeLayout.BELOW, mhsv_row.getId());
        pl_root.addView(view, lp_line);

        // 3. column （ListView --> PanelListLayout）
        /**让行标签相对于标题*/
        lv_column = new ListView(context);
        lv_column.setDividerHeight(0);

        lv_column.setId(View.generateViewId());
        lv_column.setVerticalScrollBarEnabled(false);//去掉滚动条
        final RelativeLayout.LayoutParams lp_lv_column =
                new RelativeLayout.LayoutParams(titleWidth, ViewGroup.LayoutParams.MATCH_PARENT);
        lp_lv_column.addRule(RelativeLayout.BELOW, view.getId());
        pl_root.addView(lv_column, lp_lv_column);

        // 4. content (ListView --> MyHorizontalScrollView --> SwipeRefreshLayout --> PanelListLayout)
        /**让行标签相对于标题*/
        mhsv_content = new MyHorizontalScrollView(context);
        mhsv_content.addView(lv_content);//因为 lv_content 在 xml 文件中已经设置了 layout 为 match_parent，所以这里add时不需要再加 LayoutParameter 对象
        mhsv_content.setOverScrollMode(View.OVER_SCROLL_NEVER);//去除滑动到边缘时出现的阴影
        mhsv_content.setHorizontalScrollBarEnabled(false);
        RelativeLayout.LayoutParams lp_mhsv_content = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        if (swipeRefreshLayout == null) {
            swipeRefreshLayout = new SwipeRefreshLayout(context);
        }
        swipeRefreshLayout.addView(mhsv_content, lp_mhsv_content);
        swipeRefreshLayout.setOnRefreshListener(onRefreshListener);
        Log.d(TAG, "reorganizeViewGroup: " + onRefreshListener.toString());
        RelativeLayout.LayoutParams lp_srl = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        /**让可以上下左右滑动的视图对应第一个条目*/
        lp_srl.addRule(RelativeLayout.RIGHT_OF, lv_column.getId());
        lp_srl.addRule(RelativeLayout.BELOW, view.getId());
        pl_root.addView(swipeRefreshLayout, lp_srl);
        if (initPosition == 0) {
            swipeRefreshLayout.setEnabled(swipeRefreshEnable);
        }

        // 发一个消息出去。当布局渲染完成之后会执行消息内容，此时
        pl_root.post(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "post--lv_content = " + lv_content.toString());
//                ll_contentItem = (LinearLayout) lv_content.getChildAt(lv_content.getFirstVisiblePosition());//获得content的第一个可见item
                ll_contentItem = (LinearLayout) lv_content.getChildAt(0);//获得content的第一个可见item
                initColumnLayout();
                initColumnTwoLayout();
                initRowLayout();
                // 当ListView绘制完成后设置初始位置，否则ll_contentItem会报空指针
                lv_content.setSelection(initPosition);
                lv_column.setSelection(initPosition);

            }
        });
    }

    private void initColumnLayout() {
        columnItemHeight = ll_contentItem.getHeight();
        lv_column.setAdapter(getColumnAdapter());
        if (columnDivider != null) {
            lv_column.setDivider(columnDivider);
        }
    }

    private void initColumnTwoLayout() {
        columnItemHeight = ll_contentItem.getHeight();

        if (columnDivider != null) {
            lv_column.setDivider(columnDivider);
        }
    }

    /**
     * 初始化横向表头的布局，必须在所有的布局都载入完之后才能调用
     */
    private void initRowLayout() {

        if (rowDataList == null) {
            Log.e("PanelList", "custom Row data list is strongly recommended! Call setRowDataList(List<String> rowDataList) in your panel adapter");
        }
        int rowCount = ll_contentItem.getChildCount();

        List<String> rowDataList1 = getRowDataList(rowCount);

        //在这里获取可以滑动的View在什么位置
        /**初始化小圆球*/
        Showlength = (int) (DensityUtil.getWidthPixels(context) - titleWidth);
        firstShowItem = 0;
        lastShowItem = 0;
        for (int i = 0; i < coordinate.length - 1; i++) {
            if (Showlength == coordinate[i]) {
                lastShowItem = i - 1;
                break;
            } else if (Showlength > coordinate[i] + 10 && Showlength < coordinate[i + 1]) {
                lastShowItem = i;
                break;
            }
        }
        changeDotBackground();


        //分隔线的设置，如果content的item设置了分割线，那row使用相同的分割线，除非单独给row设置了分割线
        if (rowDivider == null) {
            ll_row.setDividerDrawable(ll_contentItem.getDividerDrawable());
        } else {
            ll_row.setDividerDrawable(rowDivider);
        }

        // 获得row一共有多少个item，然后使用循环往里面添加对应个数个TextView（简单粗暴）
        for (int i = 0; i < rowCount; i++) {
            View contentItem = ll_contentItem.getChildAt(i);// 获得item的item，以便获取宽度
            TextView rowItem = new TextView(context);
            rowItem.setText(rowDataList1.get(i));//设置文字
            Paint rowPaint = rowItem.getPaint();
            rowPaint.setFakeBoldText(false);
            rowPaint.setTextSize(DensityUtil.getPixels(R.dimen.x28));
            rowItem.setTextColor(context.getResources().getColor(R.color.Orange));
           // rowItem.setWidth(textWidths[i]);//设置宽度
            rowItem.setWidth(contentItem.getWidth());//设置宽度
            rowItem.setHeight(titleHeight);//设置高度
            rowItem.setGravity(Gravity.CENTER_VERTICAL);
            TextView line = new TextView(context);
            ll_row.addView(rowItem);
            if (i < rowCount - 1) {
                line.setBackgroundColor(Color.WHITE);
                line.setWidth(0);
                line.setHeight(titleHeight);
                ll_row.addView(line);
            }
        }
    }

    /**
     * 根据显示的条目改变小圆点的状态
     */
    private void changeDotBackground() {
        for (int i = 0; i < dotList.size() - 1; i++) {
            if (firstShowItem <= i && i <= lastShowItem) {
                dotList.get(i + 1).setBackgroundResource(R.drawable.ic_dot_orange);
            } else {
                dotList.get(i + 1).setBackgroundResource(R.drawable.ic_dot_gray);
            }
        }
    }

    /**
     * 返回横向表头的内容列表
     * <p>
     * 如果设置了自定义的表头内容，则直接返回引用
     * 如果用户没设置，则根据传进来的count数生成一个默认表头
     */
    private List<String> getRowDataList(int count) {
        if (rowDataList == null) {
            List<String> defaultRowDataList = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                String s = "Row" + i;
                defaultRowDataList.add(s);
            }
            return defaultRowDataList;
        } else {
            return rowDataList;
        }
    }

    /**
     * 返回纵向表头的数据列表
     *
     * @return data list of column ListView
     */
    private List<String> getColumnDataList() {
        if (columnDataList == null) {
            columnDataList = new ArrayList<>();
            for (int i = 1; i <= getCount(); i++) {
                columnDataList.add(String.valueOf(i));
            }
        }
        return columnDataList;
    }

    /**
     * 返回纵向表头的适配器
     *
     * @return adapter of column ListView
     */
    private BaseAdapter getColumnAdapter() {
        if (columnAdapter == null) {
            /**在这里设置姓名+图片*/
            columnAdapter = new ColumnAdapter(context, android.R.layout.simple_list_item_1, getColumnDataList());
        }
        return columnAdapter;
    }

    /**
     * HorizontalScrollView的滑动监听（水平方向同步控制）
     * 在这里处理横向滑动
     */
    private class HorizontalScrollListener implements MyHorizontalScrollView.OnHorizontalScrollListener {
        @Override
        public void onHorizontalScrolled(MyHorizontalScrollView view, int l, int t, int oldl, int oldt) {
            if (view == mhsv_content) {
                mhsv_row.scrollTo(l, t);
            } else {
                mhsv_content.scrollTo(l, t);
            }
            changShowItremPosition(l);
            changeDotBackground();
        }
    }

    /**
     * 动态获取展示条目的位置
     * @param l
     */
    private void changShowItremPosition(int l) {
        int StartCoordinate = l;
        int LastCoordinate = l + Showlength;
        for (int i = 0; i < coordinate.length; i++) {
            if (StartCoordinate > coordinate[i]-20 && StartCoordinate < coordinate[i + 1]) {
                firstShowItem = i;
                break;
            }
        }
        for (int i = 0; i < coordinate.length - 1; i++) {
            if (LastCoordinate > coordinate[i] && LastCoordinate < coordinate[i + 1]) {
                lastShowItem = i;
                break;
            }
        }
    }

    /**
     * 两个ListView的滑动监听（垂直方向同步控制）
     */
    private class VerticalScrollListener implements AbsListView.OnScrollListener {

        int scrollState;

        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            this.scrollState = scrollState;

            // 滑动事件冲突，曲线救国：如果ListView的首条item的position != 0，则将下拉刷新禁用
            if (swipeRefreshEnable) {
                if (view.getFirstVisiblePosition() != 0 && swipeRefreshLayout.isEnabled()) {
                    swipeRefreshLayout.setEnabled(false);
                }

                if (view.getFirstVisiblePosition() == 0) {
                    swipeRefreshLayout.setEnabled(true);
                }
            }
        }

        private boolean isLastTimes = false;

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            //判断滑动是否终止，以停止自动对齐，否则该方法会一直被调用，影响性能
            if (scrollState == SCROLL_STATE_IDLE) {
                if (isLastTimes) {
                    return;
                }
                isLastTimes = true;
            } else {
                isLastTimes = false;
            }
            View subView = view.getChildAt(0);
            if (subView != null && view == lv_content) {
                int top = subView.getTop();
                int position = view.getFirstVisiblePosition();
                lv_column.setSelectionFromTop(position, top);
            } else if (subView != null && view == lv_column) {
                int top = subView.getTop();
                int position = view.getFirstVisiblePosition();
                lv_content.setSelectionFromTop(position, top);
                lv_column.setSelectionFromTop(position, top);
            }

        }
    }


    private class DefaultRefreshListener implements SwipeRefreshLayout.OnRefreshListener {
        @Override
        public void onRefresh() {
            Toast.makeText(context, "请调用PanelListAdapter的setOnRefreshListener()并传入你的Listener", Toast.LENGTH_SHORT).show();
            if (swipeRefreshLayout.isRefreshing()) {

                swipeRefreshLayout.setRefreshing(false);
            }
        }
    }
}