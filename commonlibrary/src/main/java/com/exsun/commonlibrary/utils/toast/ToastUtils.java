package com.exsun.commonlibrary.utils.toast;

import android.content.Context;
import android.widget.Toast;

import com.exsun.commonlibrary.utils.app.AppUtils;


/**
 * 1.封装Toast
 * 2.解决多个Toast同时出现的问题
 *
 * @author MrKong
 * @date 18/1/5
 */
public class ToastUtils
{

    private Toast mToast;
    private Context mContext = AppUtils.getAppContext();
    
    /**********************
     * 非连续弹出的Toast
     **********************/
    
    public void showSingleToast(int resId)
    {
        getSingleToast(resId, Toast.LENGTH_SHORT).show();
    }

    public void showSingleToast(String text)
    {
        getSingleToast(text, Toast.LENGTH_SHORT).show();
    }

    public void showSingleLongToast(int resId)
    {
        getSingleToast(resId, Toast.LENGTH_LONG).show();
    }

    public void showSingleLongToast(String text)
    {
        getSingleToast(text, Toast.LENGTH_LONG).show();
    }
    
    /**********************
     * 连续弹出的Toast
     **********************/
    public void showToast(int resId)
    {
        getToast(resId, Toast.LENGTH_SHORT).show();
    }

    public void showToast(String text)
    {
        getToast(text, Toast.LENGTH_SHORT).show();
    }

    public void showLongToast(int resId)
    {
        getToast(resId, Toast.LENGTH_LONG).show();
    }

    public void showLongToast(String text)
    {
        getToast(text, Toast.LENGTH_LONG).show();
    }
    
    
    
    /**
     * 连续调用不会连续弹出只是替换文本
     *
     * @param resId
     * @param duration
     * @return
     */
    public Toast getSingleToast(int resId, int duration)
    {
        return getSingleToast(mContext.getResources().getText(resId).toString(), duration);
    }

    public Toast getSingleToast(String text, int duration)
    {
        if (mToast == null)
        {
            mToast = Toast.makeText(mContext, text, duration);
        } else
        {
            mToast.setText(text);
        }
        return mToast;
    }
    
    /**
     * 连续调用会连续弹出
     *
     * @param resId
     * @param duration
     * @return
     */
    public Toast getToast(int resId, int duration)
    {
        return getToast(mContext.getResources().getText(resId).toString(), duration);
    }

    public Toast getToast(String text, int duration)
    {
        return Toast.makeText(mContext, text, duration);
    }
}
