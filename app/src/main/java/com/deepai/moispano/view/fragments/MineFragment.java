package com.deepai.moispano.view.fragments;


import android.view.View;

import com.deepai.moispano.R;
import com.deepai.moispano.base.BasePresenter;

/**
 * @author ZhaoZaigang
 * @Description 我的页
 * @date 2017/9/11  11:43
 */

public class MineFragment extends BaseFragment{

    public static final String TAG = "MineFragment";

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void showLoading(String msg) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String errorMsg) {

    }

    @Override
    public void onClick(View view) {

    }
}
