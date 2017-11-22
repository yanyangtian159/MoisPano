package com.deepai.moispano.data.entry;

/**
 * @author ZhaoZaigang
 * @Description 用户实体
 * @date 2017/9/15  14:54
 */

public class UserBean {
    /**
     * userId : 65410022-f857-4503-a4c4-8e53b7242886
     * cellPhone : null
     * createTime : 1499679340827
     * lastLoginTime : 1499746751843
     * userName : 风帆
     * sex : 0
     * avatar : http://q.qlogo.cn/qqapp/101412298/A92DD76BC7B55D17DC416289A0024EEC/100
     * praiseCount : 1
     * commentCount : 0
     * workCount : 0
     * status : true
     * platForm : 1
     */

    private String userId;
    private Object cellPhone;
    private long createTime;
    private long lastLoginTime;
    private String userName;
    private int sex;
    private String avatar;
    private int praiseCount;
    private int commentCount;
    private int workCount;
    private int status;
    private int platForm;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Object getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(Object cellPhone) {
        this.cellPhone = cellPhone;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(int praiseCount) {
        this.praiseCount = praiseCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getWorkCount() {
        return workCount;
    }

    public void setWorkCount(int workCount) {
        this.workCount = workCount;
    }

    public int isStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPlatForm() {
        return platForm;
    }

    public void setPlatForm(int platForm) {
        this.platForm = platForm;
    }
}
