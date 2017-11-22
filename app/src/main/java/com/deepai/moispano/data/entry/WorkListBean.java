package com.deepai.moispano.data.entry;

import java.util.List;

/**
 * @author ZhaoZaigang
 * @Description 作品列表
 * @date 2017/9/15  14:57
 */

public class WorkListBean {


    /**
     * works : [{"workId":"351365fc-66e9-4be6-96f3-6c0b7d395c06","userId":"65410022-f857-4503-a4c4-8e53b7242886","name":null,"createTime":1499680106780,"path":"64c40a9d-ad2e-4f30-b426-8ebe0786596c.jpg","thumbnail":"thumbnails/64c40a9d-ad2e-4f30-b426-8ebe0786596c.jpg","say":"这个人很懒，什么也没说。","isPraise":0,"type":"2","address":"北京市-西城区","status":true,"pass":true,"publicStatus":2,"isContribute":true,"width":1080,"height":1920,"user":{"userId":"65410022-f857-4503-a4c4-8e53b7242886","cellPhone":null,"createTime":1499679340827,"lastLoginTime":1499746751843,"userName":"风帆","sex":0,"avatar":"http://q.qlogo.cn/qqapp/101412298/A92DD76BC7B55D17DC416289A0024EEC/100","praiseCount":1,"commentCount":0,"workCount":0,"status":true,"platForm":1},"praiseCount":1,"commentCount":0,"lookCount":15},{"workId":"37c3d7bc-6e00-4c9e-99bd-add8a8dd678e","userId":"65410022-f857-4503-a4c4-8e53b7242886","name":null,"createTime":1499680078993,"path":"d9b62808-b395-4101-96c7-1d240eec55b1.jpg","thumbnail":"thumbnails/d9b62808-b395-4101-96c7-1d240eec55b1.jpg","say":"这个人很懒，什么也没说。","isPraise":0,"type":"2","address":"北京市-西城区","status":true,"pass":true,"publicStatus":2,"isContribute":true,"width":1080,"height":1920,"user":{"userId":"65410022-f857-4503-a4c4-8e53b7242886","cellPhone":null,"createTime":1499679340827,"lastLoginTime":1499746751843,"userName":"风帆","sex":0,"avatar":"http://q.qlogo.cn/qqapp/101412298/A92DD76BC7B55D17DC416289A0024EEC/100","praiseCount":1,"commentCount":0,"workCount":0,"status":true,"platForm":1},"praiseCount":0,"commentCount":0,"lookCount":1},{"workId":"44bf8a93-7658-489a-a40c-cc902f3c0a7c","userId":"65410022-f857-4503-a4c4-8e53b7242886","name":null,"createTime":1499680057713,"path":"95c9490d-1112-4260-b8bd-381b44a151de.jpg","thumbnail":"thumbnails/95c9490d-1112-4260-b8bd-381b44a151de.jpg","say":"这个人很懒，什么也没说。","isPraise":0,"type":"2","address":"北京市-西城区","status":true,"pass":true,"publicStatus":2,"isContribute":true,"width":720,"height":540,"user":{"userId":"65410022-f857-4503-a4c4-8e53b7242886","cellPhone":null,"createTime":1499679340827,"lastLoginTime":1499746751843,"userName":"风帆","sex":0,"avatar":"http://q.qlogo.cn/qqapp/101412298/A92DD76BC7B55D17DC416289A0024EEC/100","praiseCount":1,"commentCount":0,"workCount":0,"status":true,"platForm":1},"praiseCount":0,"commentCount":0,"lookCount":0}]
     * counts : 21
     * pageSize : 3
     * pageArrays : [1,2,3,4,5,6,7]
     */

    private int counts;
    private int pageSize;
    private List<WorkBean> works;
    private List<Integer> pageArrays;

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<WorkBean> getWorks() {
        return works;
    }

    public void setWorks(List<WorkBean> works) {
        this.works = works;
    }

    public List<Integer> getPageArrays() {
        return pageArrays;
    }

    public void setPageArrays(List<Integer> pageArrays) {
        this.pageArrays = pageArrays;
    }
}
