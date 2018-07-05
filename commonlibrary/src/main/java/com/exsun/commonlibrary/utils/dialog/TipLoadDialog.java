package com.exsun.commonlibrary.utils.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.exsun.commonlibrary.R;
import com.exsun.commonlibrary.utils.other.DimenUtils;



/**
 * @author xiaokun
 * @date 2017/12/14
 */

public class TipLoadDialog
{
    /**
     * 不显示icon状态
     */
    public static final int ICON_TYPE_NOTHING = 0;
    /**
     * Loading状态
     */
    public static final int ICON_TYPE_LOADING = 1;
    /**
     * Success状态
     */
    public static final int ICON_TYPE_SUCCESS = 2;
    /**
     * Failed状态
     */
    public static final int ICON_TYPE_FAIL = 3;
    /**
     * Info状态
     */
    public static final int ICON_TYPE_INFO = 4;

    /**
     * 默认Success icon
     */
    public int SUCCESS_ICON = R.mipmap.qmui_icon_notify_done;
    /**
     * 默认Failed icon
     */
    public int ERROR_ICON = R.mipmap.qmui_icon_notify_error;
    /**
     * 默认Info icon
     */
    public int INFO_ICON = R.mipmap.qmui_icon_notify_done;

    private static Handler sHandler = new Handler(Looper.getMainLooper());
    private MyDialog dialog;
    private final GraduallyTextView loadingText;
    private final ImageView icon;
    private final TextView text;
    private final ProgressBar progressBar;
    private final LinearLayout layout;
    private Context mContext;
    private int currentType;
    private int dismissTime = 1000;
    private View view;
    private DismissListener listener;

    public interface DismissListener
    {
        /**
         * 消失监听
         */
        void onDismissListener();
    }

    public TipLoadDialog setDismissListener(DismissListener dismissListener)
    {
        this.listener = dismissListener;
        return this;
    }
    
    /**
     * 加载框
     *
     * @param context
     */
    public TipLoadDialog(Context context)
    {
        this(context, "", ICON_TYPE_LOADING);
    }
    
    /**
     * 加载框
     *
     * @param context
     * @param info      加载框文字
     * @param type      加载框类型
     */
    public TipLoadDialog(Context context, String info, int type)
    {
        this.mContext = context;
        this.currentType = type;
        
        view = LayoutInflater.from(context).inflate(R.layout.tip_dialog_view, null);
        layout = (LinearLayout) view.findViewById(R.id.dialog_view);
        progressBar = (ProgressBar) view.findViewById(R.id.circular_ring);
        loadingText = (GraduallyTextView) view.findViewById(R.id.loading_text);
        icon = (ImageView) view.findViewById(R.id.tip_icon);
        text = (TextView) view.findViewById(R.id.tip_text);
       
        loadingText.setText(info);
        text.setText(info);

        if (type == ICON_TYPE_SUCCESS)
        {
            icon.setImageDrawable(ContextCompat.getDrawable(context, SUCCESS_ICON));
            icon.setVisibility(View.VISIBLE);
            text.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            loadingText.setVisibility(View.GONE);
        } else if (type == ICON_TYPE_FAIL)
        {
            icon.setImageDrawable(ContextCompat.getDrawable(context, ERROR_ICON));
            icon.setVisibility(View.VISIBLE);
            text.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            loadingText.setVisibility(View.GONE);
        } else if (type == ICON_TYPE_INFO)
        {
            icon.setImageDrawable(ContextCompat.getDrawable(context, INFO_ICON));
            icon.setVisibility(View.VISIBLE);
            text.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            loadingText.setVisibility(View.GONE);
        } else if (type == ICON_TYPE_LOADING)
        {
            icon.setVisibility(View.GONE);
            text.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            loadingText.setVisibility(View.VISIBLE);
        }
        
        setNoShadowTheme();
    }

    /**
     * 设置Success icon
     * 注意：如果要更改icon需要在setMsg方法之前调用此方法,否则无效
     *
     * @param resId
     * @return
     */
    public TipLoadDialog setSuccessIcon(int resId)
    {
        SUCCESS_ICON = resId;
        return this;
    }

    /**
     * 设置Failed icon
     * 注意：如果要更改icon需要在setMsg方法之前调用此方法
     *
     * @param resId
     * @return
     */
    public TipLoadDialog setErrorIcon(int resId)
    {
        ERROR_ICON = resId;
        return this;
    }

    /**
     * 设置Info icon
     * 注意：如果要更改icon需要在setMsg方法之前调用此方法
     *
     * @param resId icon的资源路径
     * @return
     */
    public TipLoadDialog setInfoIcon(int resId)
    {
        INFO_ICON = resId;
        return this;
    }

    /**
     * 设置文本和样式
     *
     * @param info
     * @param type
     */
    public TipLoadDialog setTextAndType(String info, int type)
    {
        this.currentType = type;
        text.setText(info);
        loadingText.setText(info);
        if (type == ICON_TYPE_SUCCESS)
        {
            icon.setImageDrawable(ContextCompat.getDrawable(mContext, SUCCESS_ICON));
            icon.setVisibility(View.VISIBLE);
            text.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            loadingText.setVisibility(View.GONE);
        } else if (type == ICON_TYPE_FAIL)
        {
            icon.setImageDrawable(ContextCompat.getDrawable(mContext, ERROR_ICON));
            icon.setVisibility(View.VISIBLE);
            text.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            loadingText.setVisibility(View.GONE);
        } else if (type == ICON_TYPE_INFO)
        {
            icon.setImageDrawable(ContextCompat.getDrawable(mContext, INFO_ICON));
            icon.setVisibility(View.VISIBLE);
            text.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            loadingText.setVisibility(View.GONE);
        } else if (type == ICON_TYPE_LOADING)
        {
            icon.setVisibility(View.GONE);
            text.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            loadingText.setVisibility(View.VISIBLE);
        }
        if (type != ICON_TYPE_LOADING)
        {
            icon.post(new Runnable()
            {
                @Override
                public void run()
                {
                    /**
                     * 88dp = width - paddingLeft -paddingRight ;
                     * 1.icon的宽度<88dp,则设置msg的最大宽度为88dp
                     * 2.icon的宽度>88dp,则设置msg的最大宽度为icon宽度
                     */
                    int maxWidth = DimenUtils.dpToPxInt(88);
                    int width = icon.getWidth();
                    if (maxWidth > width)
                    {
                        text.setMaxWidth(maxWidth);
                    } else
                    {
                        text.setMaxWidth(width);
                    }
                }
            });
        }
        return this;
    }

    /**
     * 设置背景
     * <p>包括圆角和背景颜色</p>
     *
     * @param drawableId
     * @return
     */
    public TipLoadDialog setBackground(int drawableId)
    {
        layout.setBackgroundResource(drawableId);
        return this;
    }

    /**
     * 设置浮动层主题
     * <p>默认 无阴影</p>
     *
     * @return
     */
    public TipLoadDialog setNoShadowTheme()
    {
        if (dialog != null)
        {
            FrameLayout parent = (FrameLayout) layout.getParent();
            parent.removeAllViews();
        }
        dialog = new MyDialog(mContext, R.style.loading_dialog_no_shadow);
        initDialog();
        return this;
    }

    /**
     * 设置浮动层主题
     * <p>有阴影</p>
     *
     * @return
     */
    public TipLoadDialog setShadowTheme()
    {
        if (dialog != null)
        {
            FrameLayout parent = (FrameLayout) layout.getParent();
            parent.removeAllViews();
        }
        dialog = new MyDialog(mContext, R.style.loading_dialog_with_shadow);
        initDialog();
        return this;
    }

    /**
     * 自定义浮动层主题
     *
     * @param themeId 主题style id
     * @return
     */
    public TipLoadDialog setDialogTheme(int themeId)
    {
        if (dialog != null)
        {
            FrameLayout parent = (FrameLayout) layout.getParent();
            parent.removeAllViews();
        }
        dialog = new MyDialog(mContext, themeId);
        initDialog();
        return this;
    }
    
    /**
     * 初始化Dialog
     *
     */
    private void initDialog()
    {
        // 默认 设置返回键取消加载框
        dialog.setCancelable(true);
        // 默认 设置点击框外取消加载框
        dialog.setCanceledOnTouchOutside(false);
        // 默认 设置ContentView
        dialog.setContentView(layout, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
    }
    
    /**
     * 设置text字体大小
     *
     * @param sp
     * @return
     */
    public TipLoadDialog setMsgSize(int sp)
    {
        text.setTextSize(TypedValue.COMPLEX_UNIT_SP, sp);
        return this;
    }
    
    /**
     * 设置text字体颜色
     *
     * @param resColorId
     * @return
     */
    public TipLoadDialog setMsgColor(int resColorId)
    {
        text.setTextColor(resColorId);
        return this;
    }


    /**
     * 设置progressbar color
     * <p>注意：只是在api 21以上才有用</p>
     *
     * @param color
     * @return
     */
    public TipLoadDialog setProgressbarColor(int color)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            progressBar.setIndeterminateTintMode(PorterDuff.Mode.SRC_ATOP);
            progressBar.setIndeterminateTintList(ColorStateList.valueOf(color));
        }
        return this;
    }
    
    /**
     * 设置提示框加载文字大小
     *
     * @param sp
     * @return
     */
    public TipLoadDialog setLoadingTextSize(int sp)
    {
        loadingText.setTextSize(TypedValue.COMPLEX_UNIT_SP, sp);
        return this;
    }
    
    /**
     * 设置提示框加载文字颜色
     *
     * @param color
     * @return
     */
    public TipLoadDialog setLoadingTextColor(int color)
    {
        loadingText.setTextColor(color);
        return this;
    }

    /**
     * 设置提示框加载一次文字的动画时间
     *
     * @param duration
     * @return
     */
    public TipLoadDialog setLoadingTime(int duration)
    {
        loadingText.setDuration(duration);
        return this;
    }

    /**
     * 设置提示框显示时间
     *
     * @param duration 毫秒
     * @return
     */
    public TipLoadDialog setTipTime(int duration)
    {
        this.dismissTime = duration;
        return this;
    }

    /**
     * 配置是否能返回键取消加载框
     *
     * @param flag
     */
    public void setCancelable(boolean flag)
    {
        dialog.setCancelable(flag);
    }

    /**
     * 配置是否能点击框外取消加载框
     *
     * @param cancel
     */
    public void setCanceledOnTouchOutside(boolean cancel)
    {
        dialog.setCanceledOnTouchOutside(cancel);
    }
    
    /**
     * 判断是否显示
     *
     * @return
     */
    public boolean isShowing()
    {
        return dialog.isShowing();
    }

    /**
     * 显示
     * <p>默认提示框(非加载框)1s延迟后消失</p>
     *
     */
    public void show()
    {
        dialog.show();
        if (loadingText.getVisibility() == View.VISIBLE)
        {
            loadingText.startLoading();
        }
        //移除所有的message和callback,
        // 防止返回键dismiss后,callback没移除
        sHandler.removeCallbacksAndMessages(null);
        if (this.currentType != ICON_TYPE_LOADING)
        {
            sHandler.postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    dismiss();
                }
            }, dismissTime);
        }
    }
    
    /**
     * 显示
     *
     * @param duration 自定义时间
     */
    public void show(int duration)
    {
        this.dismissTime = duration;
        dialog.show();
        if (loadingText.getVisibility() == View.VISIBLE)
        {
            loadingText.startLoading();
        }
        //移除所有的message和callback,
        // 防止返回键dismiss后,callback没移除
        sHandler.removeCallbacksAndMessages(null);
        if (this.currentType != ICON_TYPE_LOADING)
        {
            sHandler.postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    dismiss();
                }
            }, dismissTime);
        }
    }

    /**
     * 消失
     *
     */
    public void dismiss()
    {
        dialog.dismiss();
        if (loadingText.getVisibility() == View.VISIBLE)
        {
            loadingText.stopLoading();
        }
        if (listener != null)
        {
            listener.onDismissListener();
        }
    }

    class MyDialog extends Dialog
    {

        public MyDialog(@NonNull Context context, @StyleRes int themeResId)
        {
            super(context, themeResId);
        }

        @Override
        public boolean onKeyDown(int keyCode, @NonNull KeyEvent event)
        {
            if (keyCode == KeyEvent.KEYCODE_BACK)
            {
                //拦截back键
                dialog.dismiss();
                if (loadingText.getVisibility() == View.VISIBLE)
                {
                    loadingText.stopLoading();
                }
            }
            return super.onKeyDown(keyCode, event);
        }
    }

}
