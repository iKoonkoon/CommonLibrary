package com.ikoon.commonlibrary.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ikoon.commonlibrary.utils.toast.ToastUtils;


/**
 * @author MrKong
 * @date 2018/1/8
 */

public abstract class BaseFragment extends Fragment
{
    private static final String TAG = "BaseFragment";
    public static final String STATE_SAVE_IS_HIDDEN = "state_save_is_hidden";
    
    /**
     * 当前Activity渲染的视图View
     */
    protected View mContentView;
    protected BaseActivity mActivity;
    public ToastUtils toastUtils;
    
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        if (savedInstanceState != null)
        {
            boolean isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (isSupportHidden)
            {
                ft.hide(this);
            } else
            {
                ft.show(this);
            }
            ft.commit();
        }
        Log.d(TAG, "onCreate: ");
    }
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        setRetainInstance(true);
        
        if (mContentView == null)
        {
            mContentView = inflater.inflate(getLayoutId(), null);
        }
        
        Log.d(TAG, "onCreateView: ");
        
        return mContentView;
    }
    
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        
        Bundle bundle = getArguments();
        initData(bundle);
        initView(savedInstanceState, mContentView);
        initEvent();
        
        Log.d(TAG, "onViewCreated: ");
    }
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        mActivity = (BaseActivity) getActivity();
        toastUtils = new ToastUtils();
        
        doBusiness(mActivity);
        
        Log.d(TAG, "onActivityCreated: ");
    }
    
    @Override
    public void onDestroyView()
    {
        if (mContentView != null)
        {
            ((ViewGroup) mContentView.getParent()).removeView(mContentView);
        }
        super.onDestroyView();
        Log.d(TAG, "onDestroyView: ");
    }
    
    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
    
    /**
     * 获取布局文件
     *
     * @return
     */
    protected abstract int getLayoutId();
    
    /**
     * 初始化presenter层
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
     * @param savedInstanceState
     * @param view
     */
    public abstract void initView(Bundle savedInstanceState, final View view);
    
    /**
     * 初始化listener
     */
    protected abstract void initEvent();
    
    /**
     * 业务操作
     *
     * @param context
     */
    public abstract void doBusiness(Context context);
    
    
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
        intent.setClass(mActivity, cls);
        if (bundle != null)
        {
            intent.putExtras(bundle);
        }
        ActivityCompat.startActivity(mActivity, intent, null);
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
        intent.setClass(mActivity, cls);
        if (bundle != null)
        {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }
    
    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        if (outState != null)
        {
            outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
        }
    }
    
}