package com.deepai.moispano.mvp.model;

import com.deepai.moispano.Listener.LoadingListener;
import com.deepai.moispano.data.entry.BannerBean;
import com.deepai.moispano.data.entry.WorkBean;

import java.util.List;

/**
 * Created by lenovo on 2017/11/23.
 */

public interface IQuyingFragmentModel {

    public String geBannertPath();

    public void getBannerData(LoadingListener listener);

    public String gePath();

    public void getData(LoadingListener listener);
}
