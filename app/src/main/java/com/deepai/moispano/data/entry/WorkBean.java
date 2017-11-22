package com.deepai.moispano.data.entry;

/**
 * @author ZhaoZaigang
 * @Description 作品实体
 * @date 2017/9/15  14:53
 */

public class WorkBean {

    /**
     * workId : 351365fc-66e9-4be6-96f3-6c0b7d395c06
     * userId : 65410022-f857-4503-a4c4-8e53b7242886
     * name : null
     * createTime : 1499680106780
     * path : 64c40a9d-ad2e-4f30-b426-8ebe0786596c.jpg
     * thumbnail : thumbnails/64c40a9d-ad2e-4f30-b426-8ebe0786596c.jpg
     * say : 这个人很懒，什么也没说。
     * isPraise : 0
     * type : 2
     * address : 北京市-西城区
     * status : true
     * pass : true
     * publicStatus : 2
     * isContribute : true
     * width : 1080
     * height : 1920
     * user : {"userId":"65410022-f857-4503-a4c4-8e53b7242886","cellPhone":null,"createTime":1499679340827,"lastLoginTime":1499746751843,"userName":"风帆","sex":0,"avatar":"http://q.qlogo.cn/qqapp/101412298/A92DD76BC7B55D17DC416289A0024EEC/100","praiseCount":1,"commentCount":0,"workCount":0,"status":true,"platForm":1}
     * praiseCount : 1
     * commentCount : 0
     * lookCount : 15
     */

    private String workId;
    private String userId;
    private Object name;
    private long createTime;
    private String path;
    private String thumbnail;
    private String say;
    private int isPraise;
    private String type;
    private String address;
    private int status;
    private boolean pass;
    private int publicStatus;
    private boolean isContribute;
    private int width;
    private int height;
    private UserBean user;
    private int praiseCount;
    private int commentCount;
    private int lookCount;

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getSay() {
        return say;
    }

    public void setSay(String say) {
        this.say = say;
    }

    public int getIsPraise() {
        return isPraise;
    }

    public void setIsPraise(int isPraise) {
        this.isPraise = isPraise;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int isStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isPass() {
        return pass;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }

    public int getPublicStatus() {
        return publicStatus;
    }

    public void setPublicStatus(int publicStatus) {
        this.publicStatus = publicStatus;
    }

    public boolean isIsContribute() {
        return isContribute;
    }

    public void setIsContribute(boolean isContribute) {
        this.isContribute = isContribute;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
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

    public int getLookCount() {
        return lookCount;
    }

    public void setLookCount(int lookCount) {
        this.lookCount = lookCount;
    }
}
