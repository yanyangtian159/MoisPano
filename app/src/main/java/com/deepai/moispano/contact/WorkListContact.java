package com.deepai.moispano.contact;

import com.deepai.moispano.data.entry.WorkBean;

import java.util.List;

/**
 * @author ZhaoZaigang
 * @Description
 * @date 2017/9/21  19:03
 */

public interface WorkListContact {
    interface WorkListView  {
        /**
         * 设置数据
         *
         * @param dataList
         */
        void setData(List<WorkBean> dataList);

/*
        void onLoadMore();*/
    }

    interface WorkListPresenter{
        /**
         * 获取数据
         */
        void getData();
    }
}
