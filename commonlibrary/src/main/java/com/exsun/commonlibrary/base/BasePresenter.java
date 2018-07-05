package com.exsun.commonlibrary.base;

import android.content.Context;

import com.exsun.commonlibrary.frame.rx.RxManager;

/**
 * @author MrKong
 * @date 2017/11/21
 */

public abstract class BasePresenter<M, V>
{
    public M mModel;
    public V mView;
    public RxManager mRxManager = new RxManager();
    public Context mContext;
    
    public void attachVM(V v, M m)
    {
        this.mModel = m;
        this.mView = v;
    }
    
    public void setVM(V v, M m)
    {
        this.mModel = m;
        this.mView = v;
    }
    
    public void detachVM()
    {
        mRxManager.clear();
        mView = null;
        mModel = null;
    }
}
