package com.deepai.moispano.view.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ScrollView;

import com.deepai.moispano.R;


/**
 * @author ZhaoZaigang
 * @Description 封装下拉刷新和Endless加载更多的自定义View，使用SwipeRefreshLayout包裹RecyclerView实现。
 * @date 2017/6/20  18:56
 */

public class SwipeEndlessRecycler extends FrameLayout implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout srl;
    private RecyclerView rv;
    private SwipeRefreshLayout srlEmpty;
    private ScrollView svEmpty;
    private SwipeEndlessRecyclerListener listener;
    private EndlessRecyclerViewScrollListener endlessRecyclerViewScrollListener;

    public SwipeEndlessRecycler(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_swipe_endless_recycler, this);
        srl = (SwipeRefreshLayout) findViewById(R.id.srl);
        rv = (RecyclerView) findViewById(R.id.rv_swipe_refresh);
        srlEmpty = (SwipeRefreshLayout) findViewById(R.id.srl_empty);
        svEmpty = (ScrollView) findViewById(R.id.sv_swipe_refresh_empty);
    }

    public void setSwipeEndlessRecyclerListener(@NonNull SwipeEndlessRecyclerListener listener) {
        this.listener = listener;
        listener.onInitSwipeRefreshLayout(srl, srlEmpty);
        listener.onInitRecyclerView(rv);
        srl.setOnRefreshListener(this);
        srlEmpty.setOnRefreshListener(this);
    }

    public void setLayoutManager(@NonNull LinearLayoutManager layoutManager) {
        rv.setLayoutManager(layoutManager);
        endlessRecyclerViewScrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                // 添加!srl.isRefreshing()条件，使下拉刷新过程中不会加载更多
                if (listener != null && !srl.isRefreshing())
                    listener.onLoadMore(page, totalItemsCount);
            }
        };
        rv.addOnScrollListener(endlessRecyclerViewScrollListener);
    }

    public void setEmptyView(View emptyView) {
        svEmpty.addView(emptyView);
    }

    public void showEmptyView() {
        srlEmpty.setVisibility(VISIBLE);
        srl.setVisibility(GONE);
    }

    public void hideEmptyView() {
        srlEmpty.setVisibility(GONE);
        srl.setVisibility(VISIBLE);
    }

    @Override
    public void onRefresh() {
        endlessRecyclerViewScrollListener.init();
        if (listener != null) listener.onRefresh();
    }

    public void setRefreshing(boolean refreshing) {
        srl.setRefreshing(refreshing);
        srlEmpty.setRefreshing(refreshing);
    }

    public void scrollToPosition(int position) {
        rv.scrollToPosition(position);
    }

    public int getItemPosition(View view) {
        return rv.getChildAdapterPosition(view);
    }

    public SwipeRefreshLayout getSrl() {
        return srl;
    }

    public interface SwipeEndlessRecyclerListener {

        void onInitSwipeRefreshLayout(SwipeRefreshLayout srl, SwipeRefreshLayout srlEmpty);

        void onInitRecyclerView(RecyclerView rv);

        void onRefresh();

        void onLoadMore(int page, int totalItemsCount);
    }

}