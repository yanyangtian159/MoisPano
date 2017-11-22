package com.deepai.moispano.model;

import android.support.annotation.NonNull;

import com.deepai.moispano.App;
import com.deepai.moispano.base.BaseWorkModel;
import com.deepai.moispano.data.entry.BannerBean;
import com.deepai.moispano.data.exception.ApiException;
import com.deepai.moispano.observer.CommonObserver;
import com.deepai.moispano.transformer.CommonTransformer;

import java.util.List;

/**
 * @author ZhaoZaigang
 * @Description
 * @date 2017/11/22  15:58
 */

public class BannerModel extends BaseWorkModel {


    public void getData(@NonNull final BannerModel.DataListener listener) {
        if (listener == null) {
            throw new RuntimeException("InfoHint不能为空");
        }
        httpService.requestBannerData()
                .compose(new CommonTransformer<List<BannerBean>>())
                .subscribe(new CommonObserver<List<BannerBean>>(App.getInstance()) {
                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull List<BannerBean> workListBean) {
                        List<BannerBean> list =workListBean;
                        listener.successInfo(list);
                    }

                    @Override
                    protected void onError(ApiException e) {
                        super.onError(e);
                        listener.failInfo(e.message);
                    }
                });
    }

    /**
     * 通过接口产生信息回调
     *
     * @param <T>
     */
    public interface DataListener<T> {
        void successInfo(T result);

        void failInfo(String result);
    }
}
