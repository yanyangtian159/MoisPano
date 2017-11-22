package com.deepai.moispano.cache;

import android.content.Context;
import android.support.annotation.NonNull;

import com.deepai.moispano.utils.SPUtil;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author ZhaoZaigang
 * @Description 用户信息缓存
 * @date 2017/6/21  14:12
 */
@Singleton
public class UserCache {
    private static final String FILE_NAME = "user";
    private static final String K_UUID = "K_UUID";
    private static final String K_NICKNAME = "K_NICKNAME";
    private static final String K_PORTRAIT = "K_PORTRAIT";
    private static final String K_USERID = "K_USERID";
    private static final String K_RONG_TOKEN = "K_RONG_TOKEN";
    private static final String K_ACCOUNT = "K_ACCOUNT";
    private static final String K_SHOW_GUIDER_COUNT = "K_SHOW_GUIDER_COUNT";
    private static final String K_THIRD_LOGIN_PLAT = "K_THIRD_LOGIN_PLAT";
    private static final String K_PHONE_NUM = "K_PHONE_NUM";
    private static final String K_AVART = "K_AVART";

    private static final String MINE_BLE_SELECT = "mineBleSelect";

    public static final int SHOW_COUNT_MAX = 3;

    private Context context;

    @Inject
    public UserCache(Context context) {
        this.context = context;
    }
    /**
     * 用于登陆成功后初始化用户缓存，同时存入SP文件，避免后续使用重复读取文件
     */
    public void initByLogin(String uuid, String nickname, String portrait, String userId, String rongToken,String phoneNum,String avart) {
        setUUID(uuid);
        setNickname(nickname);
        setPortrait(portrait);
        setUserId(userId);
        setRongToken(rongToken);
        setPhoneNum(phoneNum);
        setkAvart(avart);
    }
    public void setUUID(@NonNull String uuid) {
        SPUtil.set(context, FILE_NAME, K_UUID, uuid);
    }

    public String getUUID() {
        return (String) SPUtil.get(context, FILE_NAME, K_UUID, "");
    }

    public void setNickname(@NonNull String nickname) {
        SPUtil.set(context, FILE_NAME, K_NICKNAME, nickname);
    }

    public String getNickname() {
        return (String) SPUtil.get(context, FILE_NAME, K_NICKNAME, "");
    }

    public void setPortrait(String portrait) {
        SPUtil.set(context, FILE_NAME, K_PORTRAIT, portrait);
    }

    public String getPortrait() {
        return (String) SPUtil.get(context, FILE_NAME, K_PORTRAIT, "");
    }

    public void setUserId(@NonNull String id) {
        SPUtil.set(context, FILE_NAME, K_USERID, id);
    }

    public String getUserId() {
        return (String) SPUtil.get(context, FILE_NAME, K_USERID, "");
    }

    public void setRongToken(@NonNull String rongToken) {
        SPUtil.set(context, FILE_NAME, K_RONG_TOKEN, rongToken);
    }

    public String getRongToken() {
        return (String) SPUtil.get(context, FILE_NAME, K_RONG_TOKEN, "");
    }

    public void setAccount(@NonNull String account) {
        SPUtil.set(context, FILE_NAME, K_ACCOUNT, account);
    }

    public String getAccount() {
        return (String) SPUtil.get(context, FILE_NAME, K_ACCOUNT, "");
    }

    public void setMineBleSelect(@NonNull boolean bleSelect) {
        SPUtil.set(context, FILE_NAME, MINE_BLE_SELECT, bleSelect);
    }

    public boolean getMineBleSelect() {
        return (boolean) SPUtil.get(context, FILE_NAME, MINE_BLE_SELECT, true);
    }

    public void showGuiderCountIncrease() {
        int count = getShowGuiderCount();
        if(count < SHOW_COUNT_MAX) {
            count++;
            SPUtil.set(context, FILE_NAME, K_SHOW_GUIDER_COUNT, count);
//            Log.d("gcount", "increase");
//            Log.d("gcount", getShowGuiderCount() + "");
        }
    }

    public void setShowGuiderCountMax() {
        SPUtil.set(context, FILE_NAME, K_SHOW_GUIDER_COUNT, SHOW_COUNT_MAX);
//        Log.d("gcount", "set max");
//        Log.d("gcount", getShowGuiderCount() + "");
    }

    public void setShowGuiderCount(int count) {
        SPUtil.set(context, FILE_NAME, K_SHOW_GUIDER_COUNT, 0);
    }

    public int getShowGuiderCount() {
        return (int)SPUtil.get(context, FILE_NAME, K_SHOW_GUIDER_COUNT, 0);
    }

    public void setPhoneNum(@NonNull String phoneNum) {
        SPUtil.set(context, FILE_NAME, K_PHONE_NUM, phoneNum);
    }

    public String getPhoneNum() {
        return (String) SPUtil.get(context, FILE_NAME, K_PHONE_NUM, "");
    }

    public  String getkAvart() {
        return (String) SPUtil.get(context, FILE_NAME, K_AVART, "");
    }
    public void setkAvart(@NonNull String avart) {
        SPUtil.set(context, FILE_NAME, K_AVART, avart);
    }
    public void setThirdLoginPlat(String platName) {
        SPUtil.set(context, FILE_NAME, K_THIRD_LOGIN_PLAT, platName);
    }

    public String getThirdLoginPlat() {
        return (String) SPUtil.get(context, FILE_NAME, K_THIRD_LOGIN_PLAT, "");
    }
    public void clear() {
        setUUID("");
        setNickname("");
        setPortrait("");
        setUserId("");
        setRongToken("");
        setAccount("");
        setPhoneNum("");
        setMineBleSelect(true);
    }
}
