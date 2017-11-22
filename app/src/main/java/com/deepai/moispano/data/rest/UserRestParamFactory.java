package com.deepai.moispano.data.rest;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.deepai.moispano.cache.UserCache;
import com.deepai.moispano.utils.LogUtil;
import com.deepai.moispano.utils.VerifyUtil;

import java.util.Map;

/**
 * @author ZhaoZaigang
 * @Description 用户模块rest参数工厂
 * @date 2017/6/26  18:05
 */

public class UserRestParamFactory {
    private UserCache userCache;

    public UserRestParamFactory(UserCache userCache) {
        this.userCache = userCache;
    }

    public void assembleLoginParam(@NonNull Map<String, String> params,
                                   @NonNull String account,
                                   @NonNull String pwd
                                   ) {
        // 魅族手机上会出现  定位地址为null的情况
//        address = TextUtils.isEmpty(address) ? "" : address;

        params.put("cellphone", account);
        params.put("passwordMd5", VerifyUtil.md5(pwd));
//        params.put("lon", lon + "");
//        params.put("lan", lan + "");
//        params.put("location", address);
    }

    public Map<String, String> assembleSendVerifyParam(@NonNull Map<String, String> params,
                                                       @NonNull String phone) {
        params.put("loginName", phone);
        params.put("codeType", "0");
        return params;
    }

    public Map<String, String> assembleCheckVerifyParam(@NonNull Map<String, String> params,
                                                        @NonNull String phone,
                                                        @NonNull String verify) {
        params.put("loginName", phone);
        params.put("verifyCode", verify);
        return params;
    }

    public Map<String, String> assembleRegisterParam(@NonNull Map<String, String> params,
                                                     int userType,
                                                     @NonNull String account,
                                                     @NonNull String pwd) {
        params.put("loginName", account);
        params.put("password", VerifyUtil.md5(pwd));
        params.put("userType", userType + "");
        return params;
    }

    public Map<String, String> assembleResetPwdParam(@NonNull Map<String, String> params,
                                                     @NonNull String account,
                                                     @NonNull String pwd) {
        params.put("loginName", account);
        params.put("password", VerifyUtil.md5(pwd));
        return params;
    }

    public void processLoginParamOther(@NonNull Map<String, String> params,
                                       @NonNull Message message,
                                       @NonNull double lon,
                                       @NonNull double lat,
                                       @NonNull String address) {
        // 获取Bundle信息
        Bundle bundle = message.getData();
//        String platName = bundle.getString("platName");
        String userId = bundle.getString("userId");
        String userName = bundle.getString("userName");
        String userIcon = bundle.getString("userIcon");
        String regType = bundle.getString("regType");
        // 封装请求参数
        params.put("thirduid", userId);
        params.put("regType", regType);
        params.put("name", userName);
        params.put("headimgurl", userIcon);
        params.put("lon", lon + "");
        params.put("lan", lat + "");
        // 魅族手机上会出现  定位地址为null的情况
        address = TextUtils.isEmpty(address) ? "" : address;
        params.put("location", address);
    }

    public void composeContactsListParam(Map<String, String> params, String token, String friendName, String userId) {
        if (!TextUtils.isEmpty(token) ) params.put("token", token);
        if (!TextUtils.isEmpty(friendName) ) params.put("name", friendName);
        if (userId != null) params.put("userId", userId);

    }

    public void composeContactsGroupListParam(Map<String, String> params, String token, String userId, int page) {
        if (token != null) params.put("token", token);
        if (userId != null) params.put("userId", userId);
        params.put("page", page + "");
        params.put("rows", 10 + "");
    }
    public void processLoginParamOther(@NonNull Map<String, Object> params,
                                       @NonNull Message message) {
        // 获取Bundle信息
        Bundle bundle = message.getData();
//        String platName = bundle.getString("platName");
        String userId = bundle.getString("userId");
        String userName = bundle.getString("userName");
        String userIcon = bundle.getString("userIcon");
        int regType = Integer.parseInt(bundle.getString("regType"));
        LogUtil.i("=================="+userId+"===="+userName+"===="+userIcon+"====="+regType);
        // 封装请求参数
        params.put("thirdId", userId);
        params.put("platform", regType);
        params.put("name", userName);
        params.put("avatar", userIcon);
    }

}
