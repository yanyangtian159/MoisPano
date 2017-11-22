package com.deepai.moispano.contact;

import com.deepai.moispano.data.entry.BannerBean;

import java.util.List;

/**
 * @author ZhaoZaigang
 * @Description
 * @date 2017/11/22  15:54
 */

public interface BannerContact {
    interface BannerListView  {
        /**
         * 设置数据
         *
         * @param dataList
         */
        void setBannerData(List<BannerBean> dataList);

/*
        void onLoadMore();*/
    }

    interface BannerListPresenter{
        /**
         * 获取数据
         */
        void getData();
    }
}
