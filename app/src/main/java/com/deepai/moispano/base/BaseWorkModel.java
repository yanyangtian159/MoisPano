package com.deepai.moispano.base;

import com.deepai.moispano.data.rest.Http;
import com.deepai.moispano.data.rest.WorkApiService;
import com.deepai.moispano.mvp.IModel;

/**
 * @author ZhaoZaigang
 * @Description WorkModel基类
 * @date 2017/10/25  17:08
 */

public class BaseWorkModel implements IModel {
    protected static WorkApiService httpService;
    // 初始化HttpService
    static {
        httpService = Http.getWorkService();
    }
}
