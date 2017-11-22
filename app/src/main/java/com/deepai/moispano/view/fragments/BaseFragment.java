package com.deepai.moispano.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.deepai.moispano.App;
import com.deepai.moispano.R;
import com.deepai.moispano.base.BasePresenter;
import com.deepai.moispano.mvp.IView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author ZhaoZaigang
 * @Description
 * @date 2017/9/11  11:49
 */

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements IView, View.OnClickListener{

    private static final String TAG = "BaseFragment";
    private Unbinder unbinder;

    protected P presenter;
    private boolean isViewCreate = false;//view是否创建
    private boolean isViewVisible = false;//view是否可见
    public Context context;
    private boolean isFirst = true;//是否第一次加载


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = initPresenter();
        initCommonData();
    }
    public abstract P initPresenter();

    private void initCommonData() {
        if (presenter != null) {
            presenter.attachView(this);
        }
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewCreate = true;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isViewVisible = isVisibleToUser;
        if (isVisibleToUser && isViewCreate) {
            visibleToUser();
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        if (isViewVisible) {
            visibleToUser();
        }
    }
    /**
     * 懒加载
     * 让用户可见
     * 第一次加载
     */
    protected void firstLoad() {

    }

    /**
     * 懒加载
     * 让用户可见
     */
    protected void visibleToUser() {
        if (isFirst) {
            firstLoad();
            isFirst = false;
        }
    }


    @Override
    public void onDestroyView() {
        if (presenter != null) {
            presenter.detachView();
        }
        isViewCreate = false;
        super.onDestroyView();
    }

    /**
     * 获取布局id
     */
    public abstract int getLayoutId();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    public boolean isLogin() {
        return !TextUtils.isEmpty(App.getInstance().getUserCache().getUUID());
    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
    protected View getDefaultEmptyView() {
        return LayoutInflater.from(getActivity()).inflate(R.layout.common_empty_view, null);
    }

}
