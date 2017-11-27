package com.example.yangxiangjie.baseframe.base.utils;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;


/**
 * 线程工具类
 */

public class ThreadHelper {

    private static Handler mUIHandler, mWorkHandler;
    private static HandlerThread mWorkThread;
    private static ThreadHelper sInstance;

    /**
     * 构造方法
     */
    private ThreadHelper() {
        mWorkThread = new HandlerThread("work thread");
        mWorkThread.start();
        Looper workLooper = mWorkThread.getLooper();
        mWorkHandler = new Handler(workLooper);
    }

    /**
     * 获取当前类的引用对象
     *
     * @return ThreadHelper
     */
    public static ThreadHelper getInstance() {
        if (sInstance == null) {
            synchronized (ThreadHelper.class) {
                if (sInstance == null) {
                    sInstance = new ThreadHelper();
                }
            }
        }
        return sInstance;
    }

    /**
     * 判断是否为UI线程
     *
     * @return true：UI线程；false：工作线程
     */
    private boolean isUIThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

    /**
     * 获取UI Handler
     *
     * @return
     */
    private Handler getUIHandler() {
        if (mUIHandler == null) {
            mUIHandler = new Handler(Looper.getMainLooper());
        }
        return mUIHandler;
    }

    /**
     * UI线程中运行代码
     *
     * @param runnable Runnable
     */
    public void runOnUIThread(Runnable runnable) {
        if (isUIThread()) {
            runnable.run();
        } else {
            getUIHandler().post(runnable);
        }
    }

    /**
     * 工作线程中运行代码
     *
     * @param runnable Runnable
     */
    public void runOnWorkThread(Runnable runnable) {
        if (isUIThread()) {
            mWorkHandler.post(runnable);
        } else {
            runnable.run();
        }
    }

    /**
     * 取消工作【推出应用调用此方法】
     */
    public void cancel() {
        if (mWorkThread != null) {
            mWorkThread.quit();
            mWorkThread = null;
        }


        if (mUIHandler != null) {
            mUIHandler.removeCallbacksAndMessages(null);
            mUIHandler = null;
        }

        if (mWorkHandler != null) {
            mWorkHandler.removeCallbacksAndMessages(null);
            mWorkHandler = null;
        }
    }

}
