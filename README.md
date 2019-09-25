# MvpFrame

lib_base_common  基类库
* MVP基类Activity
* MVP基类Fragment
* 统一的网络请求
* 统一的eventBus
* 统一的数据库

* 要学的 MVP 、 MVVM 、jetPack 、flutter 、kotlin 、小程序
* 我好难啊~~~

lib_base_utils  工具类库
* 统一的状态栏
* 统一的标题栏
* 统一的加载框
* 统一的空布局
* 统一的弹框
* 统一的屏幕适配、权限适配、版本适配
* 统一的版本升级
* 统一的拍照剪裁
* 统一的多渠道打包
* 统一的崩溃收集
* 统一的音视频播放

lib_thermal_repair 热更新库

lib_share_push 分享库

# 知识点
* 组件化，Mvp框架，retrofit2，rxJava2，rxLifecycle2

* Material Design 常用控件

* androidStudio 3.5 与 androidX

模块化 与 组件化

* SwipeRefreshLayout
* Toolbar
* TabLayout
* DrawerLayout
* NavigationView
* BottomNavigationView
* SwitchCompat
* FloatingActionButton
* SnackBar
* CoordinatorLayout
* CollapsingToolbarLayout
* AppBarLayout
* TextInputLayout
* NestedScrollView
* BottomSheet
* Chip、ChipGroups、ChipDrawable


# 第三方SDK
友盟-统计，shareSDK-分享，Bugly-热修复，个推-消息推送

# 使用方法
config.gradle的配置

MVP架构的使用
* 新建 Contract ，关联view 与 presenter
* 在contract 中新建接口view继承IBaseView
* 在contract 中新建抽象类Presenter继承BasePresenter，并写抽象方法
* 新建类Presenter 继承 Contract中的抽象类Presenter，并实现其方法
* 新建类module ，创建网络请求方法
* 新建类 继承 BaseMvpActivity<V, P> 实现 view
    注意：一个组件只能一个入口，library不能有入口

阿里巴巴ARouter的使用
* 类名上面添加注解@Route(path = "/test/activity")，至少两级
* 应用内跳转ARouter.getInstance().build("/test/activity").navigation();
* 携带参数跳转，链式携带
                ARouter.getInstance().build("/test/1")
                                .withString("name", "888")
                                        .withInt("age", 11)
                            .navigation();

封装的网络框架的使用
* import retrofit2.http.Body;
* import retrofit2.http.Field;
* import retrofit2.http.FormUrlEncoded;
* import retrofit2.http.Path;
* import retrofit2.http.FieldMap;
* import retrofit2.http.Query;

eventbus的使用

BaseRecyclerViewAdapterHelper的使用

SmartRefreshLayout的使用

bugly热修复的使用

handler的使用

    /**
     * 匿名内部类
     */
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            time--;
            //            txt.setText(time + "s");
            if (time == 0) {
                startActivity(new Intent(LaunchActivity.this, MainActivity.class));
                finish();
                handler.removeMessages(0);
            }
            handler.sendEmptyMessageDelayed(0, 1000);
        }
    };

    /**
     * 静态内部类 + 弱引用
     */
    private static class myHandler extends Handler {
        private final WeakReference<LaunchActivity> mActivity;

        public myHandler(LaunchActivity activity) {
            mActivity = new WeakReference<LaunchActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            LaunchActivity activity = mActivity.get();
            if (activity != null) {
                activity.time--;
            //            txt.setText(time + "s");
                if (activity.time == 0) {
                    activity.startActivity(new Intent(activity, MainActivity.class));
                    activity.finish();
                    activity.myHandler.removeMessages(0);
                }
                activity.myHandler.sendEmptyMessageDelayed(0, 1000);
            }
        }

问题 一个activity对应多个fragment，沉浸式状态栏问题
 * 这种问题产生的原因：
 * 当第一个Fragment添加到Activity中的时候，Activity寻找出有fitsSystemWindows的子布局
 * 为其预留出状态栏的空间，其实就是设置一个padding，而其他Fragment添加到Activity中的时候，
 * 因为状态栏空间的适配已经被消费过一次了，Activity并不会再次去添加这个padding。
 * 因此我们需要自定义一个FrameLayout，重写它的状态栏空间适配的时机和它的适配事件的分发。

问题 解决组件化开发使用butterKnife
 * id  R替换成R2
 * 点击事件   switch (view.getId()) {} 替换成   if (i == R.id.mb_button1) {}else if(i == R.id.mb_button2){}

问题 如过在Android P(9.0)上依然使用明文传输，则有下面三种解决方案
 * 将APP改用https请求；
 * 将targetSdkVersion降到27以下；
 * 在res下新建一个xml目录，然后创建一个名为：network_security_config.xml文件
    <?xml version="1.0" encoding="utf-8"?>
    <network-security-config>
        <base-config cleartextTrafficPermitted="true" />
    </network-security-config>
 * 在APP的AndroidManifest.xml文件下的application标签内增加下面一条属性
   <application
   ...
    android:networkSecurityConfig="@xml/network_security_config"
   ...
   />