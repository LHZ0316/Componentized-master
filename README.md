# Componentized

lib_base_common  基类库

lib_base_utils  工具类库

lib_thermal_repair 热更新库

lib_share_push 分享库

# 知识点
组件化，Mvp框架，retrofit2，rxJava2，rxLifecycle2
# 第三方SDK包括
友盟统计
shareSDK分享
bugly热修复
个推

#使用方法

config.gradle的配置（）

MVP架构的使用
1、新建 Contract ，关联view 与 presenter
2、在contract 中新建接口view继承IBaseView
3、在contract 中新建抽象类Presenter继承BasePresenter，并写抽象方法
4、新建类Presenter 继承 Contract中的抽象类Presenter，并实现其方法
5、新建类module ，创建网络请求方法
6、新建类 继承 BaseMvpActivity<V, P> 实现 view

注意：一个组件只能一个入口，library不能有入口

阿里巴巴ARouter的使用
1、类名上面添加注解@Route(path = "/test/activity")，至少两级
2、应用内跳转ARouter.getInstance().build("/test/activity").navigation();
3、携带参数跳转，链式携带
                ARouter.getInstance().build("/test/1")
                                .withString("name", "888")
                                        .withInt("age", 11)
                            .navigation();

封装的网络框架的使用

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

