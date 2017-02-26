package com.sha1607.smallmovie.utils;

import android.content.Context;

/**
 * ***********************************************************
 * author: alex
 * time: 16/9/26 下午4:04
 * name: DiaplayMetrics的简单使用
 * desc: dp2px sp2px px2dp px2sp
 * step: px=dp*density    px=sp*scaleDensity
 * *************************************************************
 */
public class DisplayUtils {

    /**
     * dp 2 px
     *
     * @param context
     * @param dpValue
     * @return 加上0.5主要为了四舍五入
     * <p/>
     * 1.1 int 1   1.9 int 1       1.9+0.5=2.4 int 2
     * 1.6 +0.5=2.1 2
     */
    public static int dp2px(Context context, float dpValue) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * density + 0.5);
    }

    /**
     * px2dp
     *
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dp(Context context, float pxValue) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / density + 0.5);
    }

    /**
     * sp2px
     *
     * @param context
     * @param spValue
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * scaledDensity + 0.5);
    }

    /**
     * px2sp
     */
    public static int px2sp(Context context, float pxValue) {
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / scaledDensity + 0.5);
    }
}
