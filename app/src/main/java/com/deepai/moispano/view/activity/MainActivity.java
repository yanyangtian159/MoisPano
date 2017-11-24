package com.deepai.moispano.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.ImageView;

import com.deepai.moispano.App;
import com.deepai.moispano.R;
import com.deepai.moispano.mvp.presenter.MainActivityPresenter;
import com.deepai.moispano.view.fragments.CamareFragment;
import com.deepai.moispano.view.fragments.MessageFragment;
import com.deepai.moispano.view.fragments.MineFragment;
import com.deepai.moispano.view.fragments.QuyingFragment;
import com.deepai.moispano.view.fragments.WorksFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    //主页tab切换“广场”及“我的”
    @BindView(R.id.tab_layout_main)
    TabLayout tabLayout;
    @BindView(R.id.iv_main_go_to_take)
    ImageView toTakePhoto;

    private QuyingFragment quyingFragment;
    private MessageFragment messageFragment;
    private CamareFragment camareFragment;
    private WorksFragment worksFragment;
    private MineFragment mineFragment;

    //当前选中页tab位置
    private int selectedTabPosition;

    protected MainActivityPresenter presenter;

    //全局变量
    private App app;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }
    /**
     * 进入本页
     *
     * @param context 上下文
     */
    public static void start(Context context, int selectTab) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("position", selectTab);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initview();
        initTabEvent();
        if (savedInstanceState == null) {
            showFragment(0);
        } else {
            restoreFragment();
            restoreTabStatus(savedInstanceState.getInt("selectedTabPosition", 0));
        }
        // 不同的意图， 显示不同的fragment
        if (getIntent() != null) {
            int position = getIntent().getIntExtra("position", 0);
            restoreTabStatus(position);
            showFragment(position);
        }
    }

//    @Override
//    public BasePresenter initPresenter() {
//        return null;
//    }

    //初始化控件
    private void  initview(){
        app = App.getInstance();
        presenter = new MainActivityPresenter();
    }
    @Override
    protected void onResume() {
        super.onResume();
        // 判断userId 是否为空，如果为空，说明token失效，就需要再次发请求获取token
        String token = app.getUserCache().getUUID();
        String userId = app.getUserCache().getUserId();

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int position = intent.getIntExtra("position", 0);
        restoreTabStatus(position);
        showFragment(position);
    }
    private void initTabEvent() {
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                selectedTabPosition = tab.getPosition();
                showFragment(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("selectedTabPosition", selectedTabPosition);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            restoreFragment();
            restoreTabStatus(savedInstanceState.getInt("selectedTabPosition", 0));
        }
    }
    private void restoreTabStatus(int position) {
        selectedTabPosition = position;
        TabLayout.Tab t = tabLayout.getTabAt(position);
        if (t != null) {
            t.select();
        }
    }
    private void restoreFragment() {
        FragmentManager manager = getSupportFragmentManager();
        quyingFragment = (QuyingFragment) manager.findFragmentByTag(QuyingFragment.TAG);
        mineFragment = (MineFragment) manager.findFragmentByTag(MineFragment.TAG);
        camareFragment = (CamareFragment) manager.findFragmentByTag(CamareFragment.TAG);
    }

    @Override
    public void showFragment(int position) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        switch (position) {
            case 0:
                if (quyingFragment == null) {
                    quyingFragment = new QuyingFragment();
                    transaction.add(R.id.container_main, quyingFragment, QuyingFragment.TAG);
                }
                transaction.show(quyingFragment);
                if (mineFragment != null && !mineFragment.isHidden())
                    transaction.hide(mineFragment);
                if (camareFragment != null && !camareFragment.isHidden())
                    transaction.hide(camareFragment);
                if (messageFragment != null && !messageFragment.isHidden())
                    transaction.hide(messageFragment);
                if (worksFragment != null && !worksFragment.isHidden())
                    transaction.hide(worksFragment);
                break;
            case 1:
                if (messageFragment == null) {
                    messageFragment = new MessageFragment();
                    transaction.add(R.id.container_main, messageFragment, MessageFragment.TAG);
                }
                transaction.show(messageFragment);
                if (mineFragment != null && !mineFragment.isHidden())
                    transaction.hide(mineFragment);
                if (camareFragment != null && !camareFragment.isHidden())
                    transaction.hide(camareFragment);
                if (quyingFragment != null && !quyingFragment.isHidden())
                    transaction.hide(quyingFragment);
                if (worksFragment != null && !worksFragment.isHidden())
                    transaction.hide(worksFragment);
                break;
            case 2:
               break;
            case 3:
                if (worksFragment == null) {
                    worksFragment = new WorksFragment();
                    transaction.add(R.id.container_main, worksFragment, WorksFragment.TAG);
                }
                transaction.show(worksFragment);
                if (mineFragment != null && !mineFragment.isHidden())
                    transaction.hide(mineFragment);
                if (camareFragment != null && !camareFragment.isHidden())
                    transaction.hide(camareFragment);
                if (quyingFragment != null && !quyingFragment.isHidden())
                    transaction.hide(quyingFragment);
                if (messageFragment != null && !messageFragment.isHidden())
                    transaction.hide(messageFragment);
                break;
            case 4:
                if (mineFragment == null) {
                    mineFragment = new MineFragment();
                    transaction.add(R.id.container_main, mineFragment, MineFragment.TAG);
                }
                transaction.show(mineFragment                                                                                         );
                if (messageFragment != null && !messageFragment.isHidden())
                    transaction.hide(messageFragment);
                if (camareFragment != null && !camareFragment.isHidden())
                    transaction.hide(camareFragment);
                if (quyingFragment != null && !quyingFragment.isHidden())
                    transaction.hide(quyingFragment);
                if (worksFragment != null && !worksFragment.isHidden())
                    transaction.hide(worksFragment);
                break;

        }
        transaction.commit();
    }

    // 主页面拍照按钮
    @OnClick(R.id.iv_main_go_to_take)
    void onTakePhoneClick() {
            toCamera();
    }
    public void toCamera() {

    }

    @Override
    public void showLoading(String msg) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String errorMsg) {

    }
}
