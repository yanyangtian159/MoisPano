package com.deepai.moispano.model;

import android.support.annotation.NonNull;

import com.deepai.moispano.App;
import com.deepai.moispano.base.BaseWorkModel;
import com.deepai.moispano.data.entry.WorkBean;
import com.deepai.moispano.data.entry.WorkListBean;
import com.deepai.moispano.data.exception.ApiException;
import com.deepai.moispano.observer.CommonObserver;
import com.deepai.moispano.transformer.CommonTransformer;

import java.util.List;
import java.util.Map;

/**
 * @author ZhaoZaigang
 * @Description
 * @date 2017/10/25  17:57
 */

public class WorkListModel extends BaseWorkModel {


    public void getData(@NonNull Map<String, Object> paramas, @NonNull final DataListener listener){
        if (listener == null) {
            throw new RuntimeException("InfoHint不能为空");
        }
        httpService.requestSquareData(paramas)
                .compose(new CommonTransformer<WorkListBean>())
                .subscribe(new CommonObserver<WorkListBean>(App.getInstance()) {
                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull WorkListBean workListBean) {
                        List<WorkBean> list = workListBean.getWorks();
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
