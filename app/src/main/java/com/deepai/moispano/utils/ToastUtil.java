package com.deepai.moispano.utils;


import android.content.Context;
import android.widget.Toast;

/**
 * 解决Toast重复出现多次，保持全局只有一个Toast实例
 */
public class ToastUtil {
    private static Context context = null;
    private static Toast toast = null;

    public static Toast getToast(Context context, String text) {
        if (ToastUtil.context == context) {
            toast.setText(text);
            toast.setDuration(Toast.LENGTH_SHORT);
        } else {
            ToastUtil.context = context;
            toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        }
        return toast;
    }

    public static void showToast(Context context, int textRes) {
        String text = context.getResources().getString(textRes);
        getToast(context, text).show();
    }

    public static void cancelToast() {
        if (toast != null) {
            toast.cancel();
        }
    }
}
