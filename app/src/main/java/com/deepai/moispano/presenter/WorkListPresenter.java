package com.deepai.moispano.presenter;


import com.deepai.moispano.App;
import com.deepai.moispano.base.BasePresenter;
import com.deepai.moispano.cache.UserCache;
import com.deepai.moispano.contact.WorkListContact;
import com.deepai.moispano.data.entry.WorkBean;
import com.deepai.moispano.model.WorkListModel;
import com.deepai.moispano.mvp.IModel;
import com.deepai.moispano.view.fragments.QuyingFragment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;


/**
 * @author ZhaoZaigang
 * @Description
 * @date 2017/9/15  16:06
 */

public class WorkListPresenter extends BasePresenter<QuyingFragment> implements WorkListContact.WorkListPresenter{

    private  UserCache mUserCache;

    private  Map<String,Object> params;

    @Inject
    public WorkListPresenter() {
        App app = App.getInstance();
        mUserCache = app.getUserCache();
    }

    public void setParams(final int page,String workType,String say) {

        if (params == null)
            params = new HashMap<>();
        else
            params.clear();
        params.put("pageSize", 20);
        params.put("currentPage", page);
        params.put("workType", workType);
        params.put("say", say);
        params.put("userId", mUserCache.getUserId());
    }

    @Override
    public void getData() {
        ((WorkListModel)getiModelMap().get("getWorkList")).getData(params, new WorkListModel.DataListener<List<WorkBean>>() {
            @Override
            public void successInfo(List<WorkBean> result) {
                getIView().hideLoading();
                getIView().setData(result);
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
        return loadModelMap(new WorkListModel());
    }

    @Override
    public HashMap<String, IModel> loadModelMap(IModel... models) {
        HashMap<String, IModel> map = new HashMap<>();
        map.put("getWorkList", models[0]);
        return map;
    }
}
