package com.hs.doubaobao.model.detail.pictrue;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.hs.doubaobao.R;

import java.util.List;

/**
 * 作者：zhanghaitao on 2017/9/20 09:15
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class PictrueAdapter extends RecyclerView.Adapter {

    private final Context context;
    private List<String> mList;

    public PictrueAdapter(Context context, List<String> mList) {
        this.context = context;
        this.mList = mList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_pictrue_fragment, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder viewHolder = (MyViewHolder) holder;
        viewHolder.setData(position);
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView gridImage;
        private int scaleSize;

        public MyViewHolder(View itemView) {
            super(itemView);
            gridImage = (ImageView) itemView.findViewById(R.id.iv_grid);
            int screenWidth = getWidthPixels();
            int space = dp2px(4);
            scaleSize = (screenWidth - (4 + 1) * space) / 4;
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) gridImage.getLayoutParams();
            params.width = scaleSize;
            params.height = scaleSize;

        }

        public void setData(final int position) {
            Glide.with(context)
                    .load(mList.get(position))
                    /*.diskCacheStrategy(DiskCacheStrategy.NONE)
                    .thumbnail(0.3f)
                    .dontAnimate()*/
                    .error(R.drawable.ic_error_pictrue_remind)
                    .into(gridImage);
            gridImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                       listener.onItemClick(v,position);
                    }
                }
            });
        }
    }

    public static onItemClickListener listener;

    public void setOnItemClickListener(onItemClickListener listener) {
        this.listener = listener;
    }

    public interface onItemClickListener {
        void onItemClick(View view,int postion);
    }

    /**
     * 获取屏幕像素
     *
     * @return
     */
    public int getWidthPixels() {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        Configuration cf = context.getResources().getConfiguration();
        int ori = cf.orientation;
        if (ori == Configuration.ORIENTATION_LANDSCAPE) {// 横屏
            return displayMetrics.heightPixels;
        } else if (ori == Configuration.ORIENTATION_PORTRAIT) {// 竖屏
            return displayMetrics.widthPixels;
        }
        return 0;
    }

    public int dp2px(float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}