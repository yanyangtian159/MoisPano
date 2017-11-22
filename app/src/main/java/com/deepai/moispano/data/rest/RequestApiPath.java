package com.deepai.moispano.data.rest;

/**
 * @author ZhaoZaigang
 * @Description 请求地址
 * @date 2017/9/13  17:33
 */

public interface RequestApiPath {


    /**
     * 更新app
     */
    String UPDATE_APP_VERSION = "userLoginAndRegister/checkforupdates.action";

    /**
     * 用户注册
     */
    String USER_REGIST="api/user/register";
    /**
     * 用户登录
     */
    String USER_LOGIN="api/user/login";
    /**
     * 用户第三方登录
     */
    String USER_OTHER_LOGIN="api/user/thirdLogin";
    /**
     * 用户注销
     */
    String USER_LOGOUT="api/user/logout";
    /**
     * 用户详细信息
     */
    String USER_PROFILE="api/user/profile";
    /**
     * 更新用户名
     */
    String USER_UPDATANAME="api/user/update_name";
    /**
     * 更新用户密码
     */
    String USER_UPDATAPW="api/user/update_pw";
    /**
     * 重置用户密码
     */
    String USER_RESETPW="api/user/reset_pw";
    /**
     * 更新用户头像
     */
    String USER_UPDATAAVATAR="api/user/upload_avatar";
    /**
     * 用户是否存在
     */
    String USER_EXISTED="api/user/existed/";
    /**
     * 用户提交反馈
     */
    String USER_FEEDBACK="api/user/feedback";
    /**
     * 用户头像访问byID
     */
    String USER_AVATAR_ID="api/avatar/id";
    /**
     * 用户头像访问byPhone
     */
    String USER_AVATAR_PHONE="api/avatar/phone";


    /**
     * 短信验证码获取
     */
    String SMS_SEND="api/sms/send";
    /**
     * 短信验证码验证
     */
    String SMS_VERIFY="api/sms/verify";
    /**
     * 作品发布
     */
    String WORK_PUBLISH="api/work/publish";
    /**
     * 作品详情
     */
    String WORK_DETAIL="api/work/id/";
    /**
     * 作品删除
     */
    String WORK_REMOVE="api/work/remove";
    /**
     * 作品举报
     */
    String WORK_REPORT="api/user/addAccusation";

    /**
     * 作品列表
     */
    String WORK_QUERY_LIST="api/work/findWorks";
    /**
     * 我的作品列表
     */
    String WORK_MY_LIST="api/work/findMyWorks";
    /**
     * Banner列表
     */
    String WORK_BANNER="api/sys/findBanners";
    /**
     * Banner列表ByID
     */
    String WORK_BANNER_BY_ID="api/sys/findTopicalByBannerId";

    /**
     * 作品点赞
     */
    String USER_LIKE_WORK="api/user/like_work";
    /**
     * 取消作品点赞
     */
    String USER_UNLIKE_WORK="api/user/un_like_work";
    /**
     * 作品收藏
     */
    String USER_CONNECTED_WORK="api/user/collect_work";
    /**
     * 取消作品收藏
     */
    String USER_UNCONNECTED_WORK="api/user/un_collect_work";
    /**
     * 作品查看次数
     */
    String USER_LOOK_WORK="api/work/addLookCount";
}
