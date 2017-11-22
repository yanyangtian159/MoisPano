package com.deepai.moispano.contact;

import com.deepai.moispano.data.entry.BannerBean;
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
        /**
         * 设置数据
         *
         * @param bannerdataList
         */
        void setBannerData(List<BannerBean> bannerdataList);

/*
        void onLoadMore();*/
    }

    interface WorkListPresenter{
        /**
         * 获取数据
         */
        void getData();
        /**
         * 获取Banner数据
         */
        void getBannerData();
    }
}
