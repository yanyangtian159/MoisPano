package com.deepai.moispano.view.fragments;


import android.view.View;

import com.deepai.moispano.R;

/**
 * @author ZhaoZaigang
 * @Description 消息页
 * @date 2017/9/11  11:42
 */

public class MessageFragment extends BaseFragment {

    public static final String TAG = "MessageFragment";

//    @Override
//    public BasePresenter initPresenter() {
//        return null;
//    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_message;
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
