package com.deepai.moispano.view.activity;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;

import com.deepai.moispano.BuildConfig;
import com.deepai.moispano.R;
import com.deepai.moispano.mvp.views.IMainActivityViews;
import com.deepai.moispano.utils.ActivityManager;
import com.deepai.moispano.utils.LogUtil;
import com.deepai.moispano.view.widget.TitleView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author ZhaoZaigang
 * @Description
 * @date 2017/9/11  16:39
 */

public abstract class BaseActivity extends AppCompatActivity implements IMainActivityViews{
    protected LogUtil log;
    private InputMethodManager imm;
    protected TitleView titleView;

    private Unbinder unbinder;

    public Context context;

    public abstract int getLayoutId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
        titleView = (TitleView) findViewById(R.id.titleView);
        if(titleView != null) {
            titleView.setOnChildClickListener(new TitleView.SimpleOnChildClickListener(this));
        }
        log = new LogUtil(BuildConfig.DEBUG);
        context = this;
        ActivityManager.getAppInstance().addActivity(this);//将当前activity添加进入管理
        initCommonData();
    }
    private void initCommonData() {
//        if (presenter != null) {
//            presenter.attachView(this);
//        }
    }
    /**
     * 在子类中初始化对应的presenter
     *
     * @return 相应的presenter
     */
//    public abstract P initPresenter();


    protected boolean requestPermission(Context context, String[] permissions, int reqCode) {
        boolean flag = false;
        if (Build.VERSION.SDK_INT >= 23) {
            for (String permission : permissions) {
                int checkPermission = ContextCompat.checkSelfPermission(context, permission);
                if (checkPermission != PackageManager.PERMISSION_GRANTED) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                requestPermissions(permissions, reqCode);
            }
        } else {
            flag = !cameraIsCanUse();
        }
        return flag;
    }

    /**
     * 返回true 表示可以使用  返回false表示不可以使用
     */
    public boolean cameraIsCanUse() {
        boolean isCanUse = true;
        Camera mCamera = null;
        try {
            mCamera = Camera.open();
        } catch (Exception e) {
            isCanUse = false;
        }

        if (mCamera != null) {
            try {
                mCamera.release();
            } catch (Exception e) {
                e.printStackTrace();
                return isCanUse;
            }
        }
        return isCanUse;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    protected boolean survive() {
        return !isDestroyed() && !isFinishing();
    }

    public Dialog getLoadingDialog(Context context, int titleId,
                                   boolean canCancel) {
        return getLoadingDialog(context, context.getString(titleId), canCancel);
    }


    public Dialog getLoadingDialog(Context context, String title,
                                   boolean canCancel) {
        ProgressDialog dialog = new ProgressDialog(context);
        dialog.setCancelable(canCancel);
        dialog.setMessage(title);
        return dialog;
    }

    // 改变输入法 软键盘状态
    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    protected void changeImmState() {

        if (imm == null) {
            imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        }
        if (imm.isActive()) {
            if (getCurrentFocus() != null && getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        }
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        ActivityManager.getAppInstance().removeActivity(this);//将当前activity移除管理栈
//        if (presenter != null) {
//            presenter.detachView();//在presenter中解绑释放view
//            presenter = null;
//        }
        super.onDestroy();
    }


}
