package com.ikoon.commonlibrary.base;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.ikoon.commonlibrary.utils.app.AppManager;
import com.ikoon.commonlibrary.utils.other.StatusBarUtil;
import com.ikoon.commonlibrary.utils.toast.ToastUtils;


/**
 * @author MrKong
 * @date 2017/9/11
 */

public abstract class BaseActivity extends AppCompatActivity
{
    public ToastUtils toastUtils;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        doBeforeSetContentView();
        setContentView(getLayoutId());
        doAfterSetContentView();
    }
    
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
    }
    
    /**
     * 获取布局文件
     *
     * @return
     */
    protected abstract int getLayoutId();
    
    /**
     * 初始化presenter
     *
     */
    protected abstract void initPresenter();
    
    /**
     * 初始化data
     *
     * @param bundle
     */
    public abstract void initData(Bundle bundle);
    
    /**
     * 初始化view
     *
     */
    protected abstract void initView();
    
    /**
     * 初始化listener
     *
     */
    protected abstract void initEvent();
    
    /**
     * 业务操作
     *
     * @param context
     */
    public abstract void doBusiness(Context context);
    
    /**
     * setContentView之前配置
     */
    public void doBeforeSetContentView()
    {
        // 设置无标题
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        // 设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // 栈中管理Activity
        AppManager.getAppManager().addActivity(this);
        // 获取Toast对象
        toastUtils = new ToastUtils();
    }
    
    /**
     * setContentView之后配置
     */
    private void doAfterSetContentView()
    {
        // 设置状态栏
        setStatusBar();
    }
    
    /**
     * 设置状态栏
     *
     * Override 的时候 要去掉 super.setStatusBar()
     */
    public void setStatusBar()
    {
        StatusBarUtil.setTranslucent(this, StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA);
    }
    
    /**
     * 通过Class跳转界面
     * 不含Bundle
     *
     * @param cls
     */
    public void startActivity(Class<?> cls)
    {
        startActivity(cls, null);
    }
    
    /**
     * 通过Class跳转界面
     * 含有Bundle
     *
     * @param cls    跳转到的页面
     * @param bundle 包含有传递的参数
     */
    public void startActivity(Class<?> cls, Bundle bundle)
    {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null)
        {
            intent.putExtras(bundle);
        }
        ActivityCompat.startActivity(this, intent, null);
    }
    
    /**
     * 通过Class跳转界面 有回调
     * 不含Bundle
     *
     * @param cls
     * @param requestCode
     */
    public void startActivityForResult(Class<?> cls, int requestCode)
    {
        startActivityForResult(cls, null, requestCode);
    }
    
    /**
     * 通过Class跳转界面 有回调
     * 含有Bundle
     *
     * @param cls
     * @param bundle
     * @param requestCode
     */
    public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode)
    {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null)
        {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }
    
}
