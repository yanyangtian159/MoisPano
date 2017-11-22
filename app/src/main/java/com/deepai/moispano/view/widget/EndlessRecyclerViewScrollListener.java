package com.deepai.moispano.view.widget;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * @author ZhaoZaigang
 * @Description 滑不停的RecyclerView
 * @date 2017/6/20  18:57
 */

public abstract class EndlessRecyclerViewScrollListener extends RecyclerView.OnScrollListener{
    // The minimum amount of items to have below your current scroll position
    // before loading more.
    //
    private int visibleThreshold = 5;
    // The current offset index of data you have loaded
    // 已经加载过的页数
    private int currentPage = 1;
    // The total number of items in the dataset after the last load
    // 最后一次加载后的数据集数量
    private int previousTotalItemCount = 0;
    // True if we are still waiting for the last set of data to load.
    // 如果数据集中还有数据没有显示则为true
    private boolean loading = true;
    // Sets the starting page index
    // 起始页数
    private int startingPageIndex = 1;

    private LinearLayoutManager mLinearLayoutManager;
    private String TAG = "endless";

    public EndlessRecyclerViewScrollListener(LinearLayoutManager layoutManager) {
        this.mLinearLayoutManager = layoutManager;
    }

    /**
     * TODO：2016/3/30 标记
     * 此处提供此方法是为了解决某些情况下，加载更多触发失败的问题，具体原因还没找到。但是此方法会强制
     * 刷新成第一页，不适用刷新时只增加新数据的情况
     */
    public void init() {
        currentPage = 1;
        previousTotalItemCount = 0;
        loading = true;
        startingPageIndex = 1;
    }

    // This happens many times a second during a scroll, so be wary of the code you place here.
    // We are given a few useful parameters to help us work out if we need to load some more data,
    // but first we check if we are waiting for the previous load to finish.
    // 这个方法在一次滑动过程中会调用多次，所以重写此方法要十分小心。
    // 我们已经设定了一些参数来确认是否需要加载更多数据，
    // 但是首先需要检查上一次加载过程是否已经完成。
    @Override
    public void onScrolled(RecyclerView view, int dx, int dy) {
        if(dy < 0) return;
        int firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();
        int visibleItemCount = view.getChildCount();
        int totalItemCount = mLinearLayoutManager.getItemCount();

        // If the total item count is zero and the previous isn't, assume the
        // list is invalidated and should be reset back to initial state
        // 如果数据集实际总数为0但是当前记录的总数不为0，意味着需要重置状态
        if (totalItemCount < previousTotalItemCount) {
            this.currentPage = this.startingPageIndex;
            /*this.previousTotalItemCount = totalItemCount;
            if (totalItemCount == 0) {
                this.loading = true;
            }*/
            // 原来的代码是上面所注释的部分，但是会和下拉刷新产生冲突：下拉后totalItemCount变少触发此段语句，将
            // totalItemCount和previousTotalItemCount的值置为相同，如果此时loading为true则无法更改为false
            // 导致无法触发onLoadMore，因此做如下修改，待实际使用中检验。
            this.previousTotalItemCount = 0;
            this.loading = true;
        }
        // If it’s still loading, we check to see if the dataset count has
        // changed, if so we conclude it has finished loading and update the current page
        // number and total item count.
        // 如果还是在一次加载的过程中，我们需要检测数据集的数量是否已改变，如果改变了可断定本次加载已结束并更新当前页码和总数
        if (loading && (totalItemCount > previousTotalItemCount)) {
            loading = false;
            previousTotalItemCount = totalItemCount;
        }

        // If it isn’t currently loading, we check to see if we have breached
        // the visibleThreshold and need to reload more data.
        // If we do need to reload some more data, we execute onLoadMore to fetch the data.
        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
            currentPage++;
            onLoadMore(currentPage, totalItemCount);
            loading = true;
        }
    }

    // Defines the process for actually loading more data based on page
    public abstract void onLoadMore(int page, int totalItemsCount);
}