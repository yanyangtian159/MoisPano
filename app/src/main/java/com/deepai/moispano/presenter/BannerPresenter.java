package com.deepai.moispano.presenter;

import com.deepai.moispano.App;
import com.deepai.moispano.base.BasePresenter;
import com.deepai.moispano.cache.UserCache;
import com.deepai.moispano.contact.BannerContact;
import com.deepai.moispano.data.entry.BannerBean;
import com.deepai.moispano.model.BannerModel;
import com.deepai.moispano.mvp.IModel;
import com.deepai.moispano.view.fragments.QuyingFragment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * @author ZhaoZaigang
 * @Description
 * @date 2017/11/22  16:30
 */

public class BannerPresenter  extends BasePresenter<QuyingFragment> implements BannerContact.BannerListPresenter{
    private UserCache mUserCache;

    private Map<String,Object> params;
    @Inject
    public BannerPresenter(){
        App app = App.getInstance();
        mUserCache = app.getUserCache();
    }
    @Override
    public void getData() {
        ((BannerModel)getiModelMap().get("getBannerList")).getData(new BannerModel.DataListener<List<BannerBean>>(){

            @Override
            public void successInfo(List<BannerBean> result) {
                getIView().hideLoading();
                getIView().setBannerData(result);
            }

            @Override
            public void failInfo(String result) {
                getIView().hideLoading();
                getIView().showError(result);
            }
        });
    }

    @Override
    public HashMap<String, IModel> getiModelMap() {
        return loadModelMap(new BannerModel());
    }

    @Override
    public HashMap<String, IModel> loadModelMap(IModel... models) {
        HashMap<String, IModel> map = new HashMap<>();
        map.put("getBannerList", models[0]);
        return map;
    }
}
