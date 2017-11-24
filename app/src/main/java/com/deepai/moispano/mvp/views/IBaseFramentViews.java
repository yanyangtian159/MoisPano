package com.deepai.moispano.mvp.views;

/**
 * Created by lenovo on 2017/11/23.
 */

public interface IBaseFramentViews {
    void showLoading(String msg);

    void hideLoading();

    void showError(String errorMsg);
}
