package com.deepai.moispano.data.entry;

/**
 * @author ZhaoZaigang
 * @Description
 * @date 2017/9/27  17:00
 */

public class BannerBean {

    /**
     * bannerId : a92e758b-b4c7-418e-9164-aaa5560094cc
     * path : banner/banner.html
     * title : 测试 添加banner
     * description : ceshi --
     * sort : 1
     * h5Path :
     * createTime : 1504077342276
     * lookCount : 0
     */

    private String bannerId;
    private String path;
    private String title;
    private String description;
    private int sort;
    private String h5Path;
    private long createTime;
    private int lookCount;

    public String getBannerId() {
        return bannerId;
    }

    public void setBannerId(String bannerId) {
        this.bannerId = bannerId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getH5Path() {
        return h5Path;
    }

    public void setH5Path(String h5Path) {
        this.h5Path = h5Path;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getLookCount() {
        return lookCount;
    }

    public void setLookCount(int lookCount) {
        this.lookCount = lookCount;
    }
}
