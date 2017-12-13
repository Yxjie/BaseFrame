## BaseFrame简单介绍
### 该框架包含以下内容：
* net 网络模块
* preference 模块
* 常用工具类 utils 模块
* 自定义View模块

#### 一.net网络模块简单说明以及使用
1.BaseHttpResponse<T> 服务器返回数据封装；<br>
2.BaseObserver Observer 四个回调方法简单处理；<br>
3.HttpHelper OKHttp简单封装[非Retrofit]；<br>
4.RetrofitHelper:Rxjava+Retrofit简单分装;<br>
 使用方法如下：
 ```
  Observable<NewsDetailService.NewsDetail> detailObservable = RetrofitHelper.getInstance().create(NewsDetailService.class).getNewsDetails("api/4/news/"+ID);
         RetrofitHelper.getInstance().toSubscribe(detailObservable, new BaseObserver<NewsDetailService.NewsDetail>(this, false) {
             @Override
             public void onNext(NewsDetailService.NewsDetail newsDetail) {
                 Log.d(TAG, newsDetail.toString());
                 mTextView.setText(newsDetail.toString());
             }
         });

 ```
 
#### 二.preference模块说明以及使用
KDPre工具类【使用方法如下】：<br/>
a.首先在KDPre里面定义SharePreference静态Key：<br>
@Pref 为自定义注解：type 为返回属性类型 有Long, Int, Bool, String, Float五种类型，对应默认值设置方法为：intValue，longValue，floatValue，boolValue，StringValue

```
 @Pref(type = Pref.Type.Bool, boolValue = true)
 public static final String TAG_TEST_BOOL = "TAG_TEST_BOOL";
```
注：属性名与属性值大小写要要一致<br/>
   
b.使用方法：
```
boolean b = KDPref.getBoolean(this, KDPref.TAG_TEST_BOOL);
```

#### 三.utils模块
1.CloseUtil:关闭常用IO数据流的方法;
2.InputUtil:系统输入法关闭展示的工具类;
3.MethodUtil:将类属性转化成Retrofit @QueryMap @FieldMap可用数据;
4.NetWorkUtils:网络状态工具类;
5.PhoneNumUtil:手机好中间4位显示*工具类;
6.PreferencesUtils:SharePreference工具类;
7.TextWatcherUtil:根据输入框输入状态判断登录注册按钮是否可点击工具类【速贷超人项目】;
8.ThreadHelper:线程工具类，具体用法如下:
```
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
```
{runOnWorkThread:切换工作线程；runOnUIThread：切换至UI线程}
#### 四.自定义View模块
该模块自定义View有带有删除按钮的手机输入框，密码输入框以及验证码输入框，主要用于速贷超人项目项目；

#### 五.自定义Dialog【速贷超人弹窗样式】
 使用方法：
 ```
  BaseDialogFragment fragment = BaseDialogFragment.newInstance();
         fragment.setMessage("hello yxjie").setOkBtn("OK !!!", new BaseDialogFragment.DialogClickEvent() {
             @Override
             public void onClick(View view) {
                 Toast.makeText(view.getContext(), "U Click ok", Toast.LENGTH_SHORT).show();
             }
         }).setCancelBtn("Cancel ", null);
         fragment.show(getSupportFragmentManager(), "dialog");
 ```





