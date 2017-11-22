package com.deepai.moispano.data.rest;


import com.deepai.moispano.base.BaseHttpResult;
import com.deepai.moispano.data.entry.WorkListBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author ZhaoZaigang
 * @Description
 * @date 2017/9/15  15:25
 */

public interface WorkApiService {
    /**
     * 作品列表
     */
    @POST(RequestApiPath.WORK_QUERY_LIST)
    Observable<BaseHttpResult<WorkListBean>> requestSquareData(@Body Map<String, Object> paramas);
    /**
     * 我的作品列表
     */
    @POST(RequestApiPath.WORK_MY_LIST)
    Observable<BaseHttpResult<WorkListBean>> requestMySquareData(@Body Map<String, Object> paramas);
}
