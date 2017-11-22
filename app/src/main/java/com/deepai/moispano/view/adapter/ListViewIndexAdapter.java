package com.deepai.moispano.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.signature.StringSignature;
import com.deepai.moispano.App;
import com.deepai.moispano.BuildConfig;
import com.deepai.moispano.R;
import com.deepai.moispano.data.entry.WorkBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * @author ZhaoZaigang
 * @Description
 * @date 2017/9/27  14:55
 */

public class ListViewIndexAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<WorkBean> list;
    private final OnWorkClickListener listener;
    private static Map<Integer, Integer> imageHeightMap = new HashMap<Integer, Integer>();
    private static int imageWidth;

    public ListViewIndexAdapter(List<WorkBean> list, @NonNull Context context, OnWorkClickListener listener) {
        this.list = list;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.common_quying_item, parent, false);
        return new VHWork(view, listener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof VHWork) {
            ((VHWork) holder).setView(context, list.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    private static class VHWork extends RecyclerView.ViewHolder implements View.OnClickListener {
        // 头像
        ImageView mImageHead;
        // 昵称
        TextView mTextName;
        // 展示图片
        ImageView mImageContent;

        // 查看数
        TextView mTextViewedNum;

        // 收藏
        ImageView mImageType;


        public VHWork(View itemView, OnWorkClickListener listener) {
            super(itemView);
            mImageHead = itemView.findViewById(R.id.common_quying_head);
            mTextName = itemView.findViewById(R.id.common_quying_username);
            mImageContent = itemView.findViewById(R.id.common_quying_img);
            mTextViewedNum = itemView.findViewById(R.id.common_quying_see_num);
            mImageType = itemView.findViewById(R.id.common_quying_type);

            mImageHead.setOnClickListener(this);
            mTextName.setOnClickListener(this);
            mImageContent.setOnClickListener(this);
            mTextViewedNum.setOnClickListener(this);
            this.listener = listener;
        }

        public void setView(Context context, WorkBean workBean) {
            this.pictureWork = workBean;
            this.position = getAdapterPosition();
            // 作品url
            String mainImgUrl = pictureWork.getThumbnail();
            if (null != mainImgUrl && !mainImgUrl.startsWith("http")) {
                mainImgUrl = BuildConfig.API_HOST + mainImgUrl;
            }
            // 展示图片
            Glide.with(context)
                    .load(mainImgUrl)
                    .error(R.mipmap.works_null)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(mImageContent);
            // 头像url
            String headImgUrl = pictureWork.getUser().getAvatar();
            if (null != headImgUrl && !headImgUrl.startsWith("http")) {
                headImgUrl = BuildConfig.API_HOST + headImgUrl;
            }
            // 头像图片
            Glide.with(context)
                    .load(headImgUrl)
                    .error(R.mipmap.mine_head_default)
                    .bitmapTransform(new CropCircleTransformation(context))
                    .signature(new StringSignature(App.getInstance().getUserCache().getkAvart()))
                    .into(mImageHead);
            // 昵称
            mTextName.setText(pictureWork.getSay());
            // 查看数
            String commentNum = pictureWork.getLookCount() + "";
            if (TextUtils.isEmpty(commentNum))
                commentNum = "0";
            mTextViewedNum.setText("浏览 " + commentNum + " 次");
            // 是否已经点赞
            if ("2".equals(pictureWork.getType())) { // 未点赞
                mImageType.setImageResource(R.mipmap.quying_pano);
            } else if ("1".equals(pictureWork.getType())) {
                mImageType.setImageResource(R.mipmap.quying_photo);
            } else if ("3".equals(pictureWork.getType())) {
                mImageType.setImageResource(R.mipmap.quying_video);
            }
        }

        OnWorkClickListener listener;

        WorkBean pictureWork;

        int position;

        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.common_quying_img:
                    if (listener != null)
                        listener.onWorkClick(pictureWork, position);
                    break;
            }
        }
    }

    public interface OnWorkClickListener {
        void onWorkClick(WorkBean pictureWork, int position);

    }
}
