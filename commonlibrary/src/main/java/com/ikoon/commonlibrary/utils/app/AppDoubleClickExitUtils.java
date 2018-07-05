package com.ikoon.commonlibrary.utils.app;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import android.widget.Toast;

import com.ikoon.commonlibrary.R;


/**
 * Created by MrKong on 18/4/20.
 */

public class AppDoubleClickExitUtils
{
    private final Activity mActivity;

    private boolean isOnKeyBacking;
    private Handler mHandler;
    private Toast mBackToast;

    public AppDoubleClickExitUtils(Activity activity)
    {
        mActivity = activity;
        mHandler = new Handler(Looper.getMainLooper());
    }

    /**
     * Activity onKeyDown事件
     */
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode != KeyEvent.KEYCODE_BACK)
            {
                return false;
            }
        if (isOnKeyBacking)
            {
                mHandler.removeCallbacks(onBackTimeRunnable);
                if (mBackToast != null)
                    {
                        mBackToast.cancel();
                    }
                AppManager.getAppManager().AppExit(mActivity, true);
                return true;
            } else
            {
                isOnKeyBacking = true;
                if (mBackToast == null)
                    {
                        mBackToast = Toast.makeText(mActivity, R.string.text_double_click, Toast.LENGTH_SHORT);
                    }
                mBackToast.show();
                mHandler.postDelayed(onBackTimeRunnable, 2000);
                return true;
            }
    }

    private Runnable onBackTimeRunnable = new Runnable()
    {

        @Override
        public void run()
        {
            isOnKeyBacking = false;
            if (mBackToast != null)
                {
                    mBackToast.cancel();
                }
        }
    };
}