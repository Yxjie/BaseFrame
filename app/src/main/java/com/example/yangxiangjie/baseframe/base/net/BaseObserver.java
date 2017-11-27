package com.example.yangxiangjie.baseframe.base.net;

import android.content.Context;
import android.support.annotation.UiThread;
import android.support.annotation.WorkerThread;
import android.widget.Toast;

import com.example.yangxiangjie.baseframe.Config;
import com.example.yangxiangjie.baseframe.base.utils.ThreadHelper;
import com.socks.library.KLog;

import java.net.ConnectException;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by yangxiangjie on 2017/10/25.
 * 网络请求对应提示
 */

public abstract class BaseObserver<T> implements Observer<T> {

    private static final String TAG = BaseObserver.class.getSimpleName();
    private Context mContext;
    protected boolean showLoadingView = true;

    public BaseObserver(Context context) {
        mContext = context;
    }

    /**
     * 构造方法
     *
     * @param context     上下文
     * @param showLoading true：显示加载图片提示 false:隐藏加载图片提示
     */
    public BaseObserver(Context context, boolean showLoading) {
        this(context);
        showLoadingView = showLoading;
    }


    @Override
    @UiThread
    public void onSubscribe(@NonNull Disposable d) {
        // TODO:  显示加载progressbar
        if (showLoadingView) {
            Toast.makeText(mContext, "开始请求", Toast.LENGTH_SHORT).show();
        }

        if (Config.isDebug) {
            KLog.i(TAG, "开始请求");
        }
    }

    @Override
    @WorkerThread
    public void onError(@NonNull final Throwable e) {
        // TODO: 加载出错处理 子类根据业务需求修改
        if (e instanceof ConnectException) {
            //当前网络不可用
        }

        if (Config.isDebug) {
            KLog.i(TAG, e.getMessage());
        }
        ThreadHelper.getInstance().runOnUIThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    @WorkerThread
    public void onComplete() {
        // TODO:  加载完成处理
        if (Config.isDebug) {
            KLog.i(TAG, "加载完成！！！");
        }
        ThreadHelper.getInstance().runOnUIThread(new Runnable() {
            @Override
            public void run() {
                if (showLoadingView) {
                    Toast.makeText(mContext, "加载完成！！！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
