package com.hs.doubaobao.model.detail.video;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.hs.doubaobao.R;

import java.util.HashMap;
import java.util.List;

/**
 * 作者：zhanghaitao on 2017/9/20 09:15
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class VideoAdapter extends RecyclerView.Adapter {

    private final Context context;
    private List<String> mList;

    public VideoAdapter(Context context, List<String> mList) {
        this.context = context;
        this.mList = mList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_video_fragment, parent, false));
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

        private ImageView videoImage, bgImage;
        private RelativeLayout videoContainer;
        private int scaleSize;

        public MyViewHolder(View itemView) {
            super(itemView);
            videoContainer = (RelativeLayout) itemView.findViewById(R.id.detail_video_container);
            videoImage = (ImageView) itemView.findViewById(R.id.detail_video_image);
            bgImage = (ImageView) itemView.findViewById(R.id.detail_video_bg);
            int screenWidth = getWidthPixels();
            int space = dp2px(4);
            scaleSize = (screenWidth - (4) * space) / 3;
            ViewGroup.LayoutParams layoutParams = videoContainer.getLayoutParams();
            layoutParams.width = scaleSize;
            layoutParams.height = scaleSize ;
        }

        public void setData(final int position) {

            Bitmap bitmap = getNetVideoBitmap(mList.get(position));
            if (bitmap != null) {
                videoImage.setImageBitmap(bitmap);
            } else {
                videoImage.setImageResource(R.mipmap.ic_launcher);
            }

            videoContainer.setOnClickListener(new View.OnClickListener() {
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
        void onItemClick(View view, int postion);
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

    /**
     * 服务器返回url，通过url去获取视频的第一帧
     * Android 原生给我们提供了一个MediaMetadataRetriever类
     * 提供了获取url视频第一帧的方法,返回Bitmap对象
     *
     * @param videoUrl
     * @return
     */
    public static Bitmap getNetVideoBitmap(String videoUrl) {
        Bitmap bitmap = null;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            //根据url获取缩略图
            retriever.setDataSource(videoUrl, new HashMap());
            //获得第一帧图片
            bitmap = retriever.getFrameAtTime(1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            retriever.release();
        }
        return bitmap;
    }

}