package com.deepai.moispano.mvp;

public interface IView {
    void showLoading(String msg);

    void hideLoading();

    void showError(String errorMsg);
}
