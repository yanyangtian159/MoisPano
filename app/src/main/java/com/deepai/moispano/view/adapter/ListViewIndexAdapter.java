package com.deepai.moispano.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.signature.StringSignature;
import com.deepai.moispano.App;
import com.deepai.moispano.BuildConfig;
import com.deepai.moispano.R;
import com.deepai.moispano.data.entry.WorkBean;
import com.deepai.moispano.utils.LogUtil;

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
    private  int imageHeight;

    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NOMAL = 1;
    private static View mHeaderView;
    public View getmHeaderView() {
        return mHeaderView;
    }
    public void setmHeaderView(View mHeaderView) {
        this.mHeaderView = mHeaderView;
        notifyItemInserted(0);//插入下标0位置
    }

    public ListViewIndexAdapter(List<WorkBean> list, @NonNull Context context, OnWorkClickListener listener,int imageWidth,int imageHeight) {
        this.list = list;
        this.context = context;
        this.listener = listener;
        this.imageWidth = imageWidth;
        this.imageHeight = imageWidth;
    }
    @Override
    public int getItemViewType(int position) {
        if(mHeaderView == null){
            return TYPE_NOMAL;
        }
        if(position == 0){
            return TYPE_HEADER;
        }
        return TYPE_NOMAL;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mHeaderView != null && viewType == TYPE_HEADER){
            View header = LayoutInflater.from(context).inflate(R.layout.rv_header_banner, parent,false);
            StaggeredGridLayoutManager.LayoutParams params =
                    (StaggeredGridLayoutManager.LayoutParams) header.getLayoutParams();
            params.setFullSpan(true);//最为重要的一个方法，占满全屏,以下同理
            mHeaderView.setLayoutParams(params);
            return new VHWork(mHeaderView,null);
        }
        View view = LayoutInflater.from(context).inflate(R.layout.common_quying_item, parent, false);
        return new VHWork(view, listener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_HEADER) {
            return;
        }
        if (holder instanceof VHWork) {
            final int pos = getRealPosition((VHWork) holder);
            ((VHWork) holder).setView(context, list.get(pos));
        }
    }
    /**
     * 添加头部布局后的位置
     * headerView 不为空则 position - 1
     * @param holder
     * @return
     */
    private int getRealPosition(VHWork holder) {
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position : position - 1;
    }

    @Override
    public int getItemCount() {
        return mHeaderView == null ? list.size() : list.size() + 1;
//        return list != null ? list.size() : 0;
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
            if(itemView == mHeaderView){ return; }
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
            LogUtil.i("======================="+mainImgUrl);

            pictureWork.getHeight();
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mImageContent.getLayoutParams();
            layoutParams.width = imageWidth;
            float scale = (imageWidth+0f)/pictureWork.getWidth();
            layoutParams.height = (int)(pictureWork.getHeight()*scale);
            if (layoutParams.height<150){
                layoutParams.height=layoutParams.height+150;
            }
            mImageContent.setLayoutParams(layoutParams);
            mImageContent.setScaleType(ImageView.ScaleType.CENTER_CROP);
            // 展示图片
            Glide.with(context)
                    .load(mainImgUrl)
                    .error(R.mipmap.works_null)
                    .crossFade()
                    .override(layoutParams.width,layoutParams.height)
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
           mTextViewedNum.setVisibility(View.GONE);
            // 图片类型
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
