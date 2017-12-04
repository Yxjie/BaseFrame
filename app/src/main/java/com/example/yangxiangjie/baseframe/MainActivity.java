package com.example.yangxiangjie.baseframe;

import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yangxiangjie.baseframe.base.net.BaseObserver;
import com.example.yangxiangjie.baseframe.base.net.RetrofitHelper;
import com.example.yangxiangjie.baseframe.base.preference.KDPref;
import com.example.yangxiangjie.baseframe.base.utils.ThreadHelper;
import com.example.yangxiangjie.baseframe.base.view.PhoneView;
import com.example.yangxiangjie.baseframe.testbean.NewsDetailService;

import io.reactivex.Observable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final long ID = 9612092;

    private PhoneView mPhoneView;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.txt);
        findViewById(R.id.btn_thread).setOnClickListener(this);
        findViewById(R.id.btn_net).setOnClickListener(this);

        boolean b = KDPref.getBoolean(this, KDPref.TAG_TEST_BOOL);
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (R.id.btn_thread == id) {
            testThread();
        } else if (R.id.btn_net == id) {
            testNet();
        }
    }

    /**
     * 测试Net请求库
     */
    private void testNet() {
        Observable<NewsDetailService.NewsDetail> detailObservable = RetrofitHelper.getInstance().create(NewsDetailService.class).getNewsDetails("api/4/news/"+ID);
        RetrofitHelper.getInstance().toSubscribe(detailObservable, new BaseObserver<NewsDetailService.NewsDetail>(this, false) {
            @Override
            public void onNext(NewsDetailService.NewsDetail newsDetail) {
                Log.d(TAG, newsDetail.toString());
                mTextView.setText(newsDetail.toString());
            }
        });


//        detailObservable.subscribeOn(Schedulers.io())
//                .subscribeOn(AndroidSchedulers.mainThread())
//                .subscribe(new BaseObserver<NewsDetailService.NewsDetail>(this, false) {
//                    @Override
//                    public void onNext(@NonNull NewsDetailService.NewsDetail newsDetail) {
//                        Log.d(TAG, newsDetail.toString());
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        super.onError(e);
//                    }
//                });

//                .subscribe(new BaseObserver<NewsDetailService.NewsDetail>(this) {
//                    @Override
//                    public void onNext(@NonNull NewsDetailService.NewsDetail newsDetail) {
//                        Log.d(TAG, newsDetail.toString());
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        super.onError(e);
//                    }
//                });

//                .subscribe(new Observer<NewsDetailService.NewsDetail>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(@NonNull NewsDetailService.NewsDetail newsDetail) {
//                        Log.d(TAG, newsDetail.toString());
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        Log.d(TAG, e.getMessage());
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });

//        NewsDetailService service = RetrofitHelper.getInstance().create(NewsDetailService.class);
//        Call<NewsDetailService.NewsDetail> detail = service.getNewsDetail(ID);
//        detail.enqueue(new Callback<NewsDetailService.NewsDetail>() {
//            @Override
//            public void onResponse(Call<NewsDetailService.NewsDetail> call, Response<NewsDetailService.NewsDetail> response) {
//                NewsDetailService.NewsDetail newsDetail = response.body();
//                Log.d(TAG, response.body().toString());
//                Log.d(TAG, newsDetail.toString());
//            }
//
//            @Override
//            public void onFailure(Call<NewsDetailService.NewsDetail> call, Throwable t) {
//                Log.d(TAG, t.getMessage());
//            }
//        });
    }


    /**
     * 测试线程
     */
    private void testThread() {
        ThreadHelper.getInstance().runOnWorkThread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "Looper.getMainLooper()==Looper.myLooper():" + (Looper.getMainLooper() == Looper.myLooper()));
                ThreadHelper.getInstance().runOnUIThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "功能正常", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }




}
