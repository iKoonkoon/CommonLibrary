package com.exsun.commonlibrary.utils.toast;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;

import com.exsun.commonlibrary.R;

/**
 * @author MrKong
 * @date 16/4/9
 */
public class DialogUtils
{

    public static AlertDialog.Builder dialogBuilder(Context context, String title, String msg)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (msg != null)
        {
            builder.setMessage(msg);
        }
        if (title != null)
        {
            builder.setTitle(title);
        }
        return builder;
    }

    public static AlertDialog.Builder dialogBuilder(Context context, String title, String msg, int i)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (msg != null)
        {
            builder.setMessage(Html.fromHtml(msg));
        }
        if (title != null)
        {
            builder.setTitle(title);
        }
        return builder;
    }

    /**
     * 自定义dialog view
     *
     * @param context
     * @param title
     * @param view
     * @return
     */
    public static AlertDialog.Builder dialogBuilder(Context context, int title, View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (view != null)
        {
            builder.setView(view);
        }
        if (title > 0)
        {
            builder.setTitle(title);
        }
        return builder;
    }

    public static AlertDialog.Builder dialogBuilder(Context context, int titleResId, int msgResId)
    {
        String title = titleResId > 0 ? context.getResources().getString(titleResId) : null;
        String msg = msgResId > 0 ? context.getResources().getString(msgResId) : null;
        return dialogBuilder(context, title, msg);
    }

    public static Dialog showTips(Context context, String title, String des)
    {
        return showTips(context, title, des, null, null);
    }

    public static Dialog showTips(Context context, int title, int des)
    {
        return showTips(context, context.getString(title), context.getString(des));
    }

    public static Dialog showTips(Context context, int title, int des, int btn, DialogInterface.OnDismissListener dismissListener)
    {
        return showTips(context, context.getString(title), context.getString(des), context.getString(btn), dismissListener);
    }

    public static Dialog showTips(Context context, String title, String des, String btn, DialogInterface.OnDismissListener dismissListener)
    {
        AlertDialog.Builder builder = dialogBuilder(context, title, des);
        builder.setCancelable(true);
        builder.setPositiveButton(btn, null);
        Dialog dialog = builder.show();
        dialog.setCanceledOnTouchOutside(true);
        dialog.setOnDismissListener(dismissListener);
        return dialog;
    }

    /**
     * 创建一个普通的dialog
     *
     * @param context
     * @param title
     * @param message
     * @param position
     * @param negative
     */
    public static void createNormalDialog(Context context, String title, String message, DialogInterface.OnClickListener position, DialogInterface.OnClickListener negative)
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(title).setMessage(message)
                .setPositiveButton(R.string.position, position)
                .setNegativeButton(R.string.negative, negative).show();
    }

    /**
     * 创建一个横向进度的dialog
     *
     * @param context
     * @param title
     * @param message
     * @return
     */
    public static ProgressDialog createDownLoadDialog(Context context, String title, String message)
    {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        if (!TextUtils.isEmpty(title))
        {
            progressDialog.setTitle(title);
        }
        if (!TextUtils.isEmpty(message))
        {
            progressDialog.setMessage(message);
        }
        return progressDialog;
    }
}
