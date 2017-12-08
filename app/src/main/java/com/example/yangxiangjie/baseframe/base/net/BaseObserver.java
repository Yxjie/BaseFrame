package com.example.yangxiangjie.baseframe.base.net;

import android.content.Context;
import android.net.ParseException;
import android.support.annotation.UiThread;
import android.support.annotation.WorkerThread;
import android.widget.Toast;

import com.example.yangxiangjie.baseframe.Config;
import com.example.yangxiangjie.baseframe.base.net.bean.BaseHttpResponse;
import com.example.yangxiangjie.baseframe.base.utils.ThreadHelper;
import com.google.gson.JsonParseException;
import com.socks.library.KLog;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

import static com.example.yangxiangjie.baseframe.base.net.BaseObserver.ExceptionReason.CONNECT_ERROR;
import static com.example.yangxiangjie.baseframe.base.net.BaseObserver.ExceptionReason.CONNECT_TIMEOUT;
import static com.example.yangxiangjie.baseframe.base.net.BaseObserver.ExceptionReason.PARSE_ERROR;
import static com.example.yangxiangjie.baseframe.base.net.BaseObserver.ExceptionReason.UNKNOWN_ERROR;

/**
 * Created by yangxiangjie on 2017/10/25.
 * 网络请求对应提示
 */

public abstract class BaseObserver<T extends BaseHttpResponse> implements Observer<T> {

    private static final String TAG = BaseObserver.class.getSimpleName();

    /**
     * 服务器数据请求成功返回码
     */
    private static final int RESPONSE_SUCCESS_CODE = 0;

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
        if (showLoadingView) {
            Toast.makeText(mContext, "开始请求", Toast.LENGTH_SHORT).show();
        }

        if (Config.isDebug) {
            KLog.i(TAG, "开始请求");
        }
    }


    @Override
    public void onNext(T t) {
        if (RESPONSE_SUCCESS_CODE == t.getCode()) {
            onSuccess(t);
        } else {
            onFailed(t);
        }
    }


    public abstract void onSuccess(T t);

    public abstract void onFailed(T t);

    @Override
    @WorkerThread
    public void onError(@NonNull final Throwable e) {
        // TODO: 加载出错处理 子类根据业务需求修改
        if (Config.isDebug) {
            KLog.i(TAG, e.getMessage());
        }
        ThreadHelper.getInstance().runOnUIThread(new Runnable() {
            @Override
            public void run() {
                if (e instanceof HttpException) {     //   HTTP错误
                    onException(ExceptionReason.BAD_NETWORK);
                } else if (e instanceof ConnectException
                        || e instanceof UnknownHostException) {   //   连接错误
                    onException(CONNECT_ERROR);
                } else if (e instanceof InterruptedIOException) {   //  连接超时
                    onException(CONNECT_TIMEOUT);
                } else if (e instanceof JsonParseException
                        || e instanceof JSONException
                        || e instanceof ParseException) {   //  解析错误
                    onException(PARSE_ERROR);
                } else {
                    onException(UNKNOWN_ERROR);
                }

            }
        });
    }

    @Override
    @WorkerThread
    public void onComplete() {
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


    /**
     * 请求异常
     *
     * @param reason
     */
    public void onException(ExceptionReason reason) {
        switch (reason) {
            case CONNECT_ERROR:
                Toast.makeText(mContext, "连接错误", Toast.LENGTH_SHORT).show();
                break;

            case CONNECT_TIMEOUT:
                Toast.makeText(mContext, "连接超时", Toast.LENGTH_SHORT).show();
                break;

            case BAD_NETWORK:
                Toast.makeText(mContext, "网络问题", Toast.LENGTH_SHORT).show();
                break;

            case PARSE_ERROR:
                Toast.makeText(mContext, "解析数据失败", Toast.LENGTH_SHORT).show();
                break;

            case UNKNOWN_ERROR:
            default:
                Toast.makeText(mContext, "未知错误", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * 请求网络失败原因
     */
    public enum ExceptionReason {
        /**
         * 解析数据失败
         */
        PARSE_ERROR,
        /**
         * 网络问题
         */
        BAD_NETWORK,
        /**
         * 连接错误
         */
        CONNECT_ERROR,
        /**
         * 连接超时
         */
        CONNECT_TIMEOUT,
        /**
         * 未知错误
         */
        UNKNOWN_ERROR,
    }
}
