package com.deepai.moispano.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.deepai.moispano.BuildConfig;
import com.deepai.moispano.R;
import com.deepai.moispano.contact.WorkListContact;
import com.deepai.moispano.data.entry.BannerBean;
import com.deepai.moispano.data.entry.WorkBean;
import com.deepai.moispano.presenter.WorkListPresenter;
import com.deepai.moispano.utils.LogUtil;
import com.deepai.moispano.utils.ToastUtil;
import com.deepai.moispano.view.adapter.ListViewIndexAdapter;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


/**
 * @author ZhaoZaigang
 * @Description 趣影页
 * @date 2017/9/11  11:42
 */

public class QuyingFragment extends BaseFragment<WorkListPresenter> implements WorkListContact.WorkListView,OnItemClickListener{

    public static final String TAG = "QuyingFragment";

    private int countPage = 1;
    private String workType = "";

    private List<WorkBean> list = new ArrayList<>();
    private ListViewIndexAdapter adapter;

    @BindView(R.id.quying_switch)
    TextView tvSwitch;
    @BindView(R.id.quying_search)
    ImageView search;
    @BindView(R.id.switch_iv)
    ImageView ivSwitch;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout mSrlRefresh;
    @BindView(R.id.quying_recycler_list)
    RecyclerView mListView;

    @BindView(R.id.un_login_area)
    LinearLayout llTips; // 提示界面布局
    @BindView(R.id.tv_no_caontacts)
    TextView tvTips; // 没有内容的提示信息
    @BindView(R.id.ll_no_login_btn_area)
    LinearLayout llBtns; // 注册 登录按钮 父布局
    @BindView(R.id.btn_register)
    Button btnRegister; // 注册按钮
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.ll_reloading)
    LinearLayout llReloading; // 网络异常
    @BindView(R.id.btn_reloading)
    TextView btnReloading; // 重新加载

    private Disposable longinSub, switchSub;
    private Disposable registerSub;
    private Disposable reloadingSub;
    private WorkListPresenter workListPresenter;
    ConvenientBanner mBanner;
    List<BannerBean> networkImage = new ArrayList<>();
    @Override
    public WorkListPresenter initPresenter() {
        workListPresenter = new WorkListPresenter();
        return workListPresenter;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_quying;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initTips();
        initListView();
        restart();
    }

    /**
     * 初始化 提示界面
     */
    private void initTips() {
        llTips.setVisibility(View.GONE);
        longinSub = RxView.clicks(btnLogin)
                .throttleFirst(500, TimeUnit.MICROSECONDS)
                .subscribe(
                        new Consumer<Object>() {
                            @Override
                            public void accept(@NonNull Object o) throws Exception {
//                                Intent intent = new Intent(getActivity(), RegPwdActivity.class);
//                                intent.putExtra("WAY", "REGISTER");
//                                startActivity(intent);
                            }
                        }
                );


        registerSub = RxView.clicks(btnRegister)
                .throttleFirst(500, TimeUnit.MICROSECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
//                                Intent intent = new Intent(getActivity(), RegPwdActivity.class);
//                                intent.putExtra("WAY", "REGISTER");
//                                startActivity(intent);
                    }
                });

        reloadingSub = RxView.clicks(btnReloading)
                .throttleFirst(500, TimeUnit.MICROSECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
//                                Intent intent = new Intent(getActivity(), RegPwdActivity.class);
//                                intent.putExtra("WAY", "REGISTER");
//                                startActivity(intent);
                    }
                });
        ivSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showPopWindow();
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getActivity(), SearchActivity.class));
            }
        });
    }

    public void restart() {
        showTips();
        countPage = 1;
        workType = "";
        getData();
    }

    /**
     * 暴露出去切换title选择器的时候调用
     */
    public void reloadData() {
        // 切换的时候  会闪一下 暂时在这里加上三行代码
        llTips.setVisibility(View.GONE);
        list.clear();
        adapter.notifyDataSetChanged();
        countPage = 1;
        getData();
    }

    private void getData() {
        mListView.setVisibility(View.VISIBLE);
        workListPresenter.setParams(countPage, workType, "");
        showLoading("");
        presenter.getData();
    }

    private void initListView() {
        View header = LayoutInflater.from(getContext()).inflate(R.layout.rv_header_banner, null);
        mBanner = (ConvenientBanner) header.findViewById(R.id.banner);
        //设置高度是屏幕1/4
        mBanner.setLayoutParams(new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, getActivity().getWindowManager().getDefaultDisplay().getHeight()/3));
        presenter.getBannerData();
//        mListView.setStaggeredGridLayout(2);
        //获取屏幕宽度
        DisplayMetrics outMetrics = new DisplayMetrics();
        WindowManager manager= getActivity().getWindowManager();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        int width = outMetrics.widthPixels/2;
        int imageHeight = getActivity().getWindowManager().getDefaultDisplay().getHeight()/3;
//        mListView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mListView.setLayoutManager(layoutManager);

        adapter = new ListViewIndexAdapter(list, getContext(), new ListViewIndexAdapter.OnWorkClickListener() {
            @Override
            public void onWorkClick(WorkBean pictureWork, int position) {

            }
        },width,imageHeight);
        adapter.setmHeaderView(mBanner);
        mListView.setAdapter(adapter);
        mSrlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reloadData();
            }
        });
        mListView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                layoutManager.invalidateSpanAssignments();
                
            }
        });

    }

    @Override
    public void setData(List<WorkBean> dataList) {
        if (countPage==1)
            list.clear();
        list.addAll(dataList);
        showTips();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading(String msg) {
        mSrlRefresh.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        mSrlRefresh.setRefreshing(false);
    }

    @Override
    public void showError(String errorMsg) {
        showTips();
        if (errorMsg.contains("连接异常，请检查网络")) {
            mListView.setVisibility(View.GONE);
            llTips.setVisibility(View.GONE);
            llReloading.setVisibility(View.VISIBLE);
        } else {
            ToastUtil.getToast(getContext(), errorMsg).show();
        }
    }

    /**
     * 数据为空或未登录
     */
    private void showTips() {
        llReloading.setVisibility(View.GONE);
        if (list.size() == 0) {
            llTips.setVisibility(View.VISIBLE);
            tvTips.setText(R.string.no_attention_work);
            llBtns.setVisibility(View.GONE);
        } else {
            llTips.setVisibility(View.GONE);
            llBtns.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (longinSub != null && !longinSub.isDisposed()) {
            longinSub.dispose();
        }

        if (registerSub != null && !registerSub.isDisposed()) {
            registerSub.dispose();
        }

        if (reloadingSub != null && !reloadingSub.isDisposed()) {
            reloadingSub.dispose();
        }
    }

    @Override
    public void onClick(View view) {

    }
    private void initBanner(){
        mBanner.setPages(new CBViewHolderCreator<NetWorkImageHolderView>() {
            @Override
            public NetWorkImageHolderView createHolder() {
                return new NetWorkImageHolderView();
            }
        }, networkImage)
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
                .setPageIndicator(new int[] { R.drawable.indicator_gray, R.drawable.indicator_red })
                .setOnItemClickListener(this)
                .setScrollDuration(1800);
        mBanner.startTurning(1800);
    }

    @Override
    public void setBannerData(List<BannerBean> dataList) {

        networkImage = dataList;
        initBanner();
    }

    @Override
    public void onItemClick(int position) {

    }

    public class NetWorkImageHolderView implements Holder<BannerBean> {

        private ImageView imageView;

        @Override
        public View createView(Context context) {
            View view = LayoutInflater.from(context).inflate(R.layout.rv_header_img, null);
            imageView = (ImageView) view.findViewById(R.id.iv_head);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return view;
        }

        @Override
        public void UpdateUI(Context context, int position, BannerBean data) {
            //Glide.with(context).load(data.getImgUrl()).into(imageView);
            // 作品url
            String bannerImgUrl = data.getPath();
            if (null != bannerImgUrl && !bannerImgUrl.startsWith("http")) {
                bannerImgUrl = BuildConfig.API_HOST + bannerImgUrl;
            }
            LogUtil.i("==========bannner======"+bannerImgUrl);
            Glide.with(context).load(bannerImgUrl).placeholder(R.mipmap.works_null).into(imageView);
        }
    }
}
