package com.deepai.moispano.view.widget;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.deepai.moispano.R;


/**
 * @author ZhaoZaigang
 * @Description 头布局
 * @date 2017/9/11  11:43
 */
public class TitleView extends RelativeLayout implements View.OnClickListener {

    public TextView tvLeft;
    public TextView tvName;
    public TextView tvRight;
    public ImageView ivRight;

    private OnChildClickListener onChildClickListener;
    private CustomChildCallback customChildCallback;

    public TitleView(Context context) {
        super(context);
    }

    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public TitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TitleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        initContainer(context);
        initWidget();
        initAttrs(context, attrs);
    }

    private void initContainer(Context context) {
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                (int) (context.getResources().getDimension(R.dimen.title_height))));
        setBackgroundResource(R.color.colorPrimary);
        LayoutInflater.from(context).inflate(R.layout.view_title, this);
    }

    private void initWidget() {
        tvLeft = (TextView) findViewById(R.id.tv_title_v_left);
        tvName = (TextView) findViewById(R.id.tv_title_v_name);
        tvRight = (TextView) findViewById(R.id.tv_title_v_right);
        ivRight = (ImageView) findViewById(R.id.iv_title_v_right);
        tvLeft.setOnClickListener(this);
        tvName.setOnClickListener(this);
        tvRight.setOnClickListener(this);
        ivRight.setOnClickListener(this);
        if (customChildCallback != null) {
            customChildCallback.customChild(tvLeft, tvName, tvRight);
        }
    }


    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleView);

        String titleNameText = typedArray.getString(R.styleable.TitleView_titleNameText);
        if (!TextUtils.isEmpty(titleNameText)) {
            tvName.setText(titleNameText);
        }

        String leftText = typedArray.getString(R.styleable.TitleView_leftText);
        if (!TextUtils.isEmpty(leftText)) {
            tvLeft.setText(leftText);
        }

        String rightText = typedArray.getString(R.styleable.TitleView_rightText);
        if (!TextUtils.isEmpty(rightText)) {
            tvRight.setText(rightText);
        }

        Drawable drawableLeft = typedArray.getDrawable(R.styleable.TitleView_drawableLeft);
        if (drawableLeft != null) {
            tvLeft.setCompoundDrawablesWithIntrinsicBounds(drawableLeft, null, null, null);
        }

        Drawable drawableRight = typedArray.getDrawable(R.styleable.TitleView_drawableRight);
        if (drawableRight != null) {
            tvRight.setCompoundDrawablesWithIntrinsicBounds(drawableRight, null, null, null);
        }

        typedArray.recycle();
    }

    @Override
    public void onClick(View v) {
        if (onChildClickListener != null) {
            int id = v.getId();
            if (id == R.id.tv_title_v_left) {
                onChildClickListener.onLeftClick();
            } else if (id == R.id.tv_title_v_name) {
                onChildClickListener.onNameClick();
            } else if (id == R.id.tv_title_v_right) {
                onChildClickListener.onRightClick();
            } else if (id == R.id.iv_title_v_right) {
                onChildClickListener.onRightClick();
            }
        }
    }

    /**
     * set left button text
     *
     * @param textRes null means ""
     */
    public TitleView setLeftText(Integer textRes) {
        if (textRes != null) {
            tvLeft.setText(textRes);
        } else {
            tvLeft.setText(null);
        }
        return this;
    }

    /**
     * set title name
     *
     * @param textRes null means ""
     */
    public TitleView setTitleNameText(Integer textRes) {
        if (textRes != null) {
            tvName.setText(textRes);
        } else {
            tvName.setText(null);
        }
        return this;
    }

    public TitleView setTitleNameText(String text) {
        tvName.setText(text);
        return this;
    }

    /**
     * set right button text
     *
     * @param textRes null means ""
     */
    public TitleView setRightText(Integer textRes) {
        if (textRes != null) {
            tvRight.setText(textRes);
        } else {
            tvRight.setText(null);
        }
        return this;
    }

    public void setOnChildClickListener(OnChildClickListener onChildClickListener) {
        this.onChildClickListener = onChildClickListener;
    }

    public interface OnChildClickListener {
        void onLeftClick();

        void onNameClick();

        void onRightClick();
    }

    public static class SimpleOnChildClickListener implements OnChildClickListener {

        private Activity activity;

        public SimpleOnChildClickListener(@NonNull Activity activity) {
            this.activity = activity;
        }

        @Override
        public void onLeftClick() {
            activity.finish();
        }

        @Override
        public void onNameClick() {

        }

        @Override
        public void onRightClick() {

        }
    }

    public void setCustomChildCallback(CustomChildCallback customChildCallback) {
        this.customChildCallback = customChildCallback;
    }

    public interface CustomChildCallback {
        void customChild(TextView tvLeft, TextView tvTitleName, TextView tvRight);
    }
}
