package com.port.ocean.shipping.consignor.activity;
/**
 * Created by 超悟空 on 2015/6/23.
 */

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.port.ocean.shipping.consignor.R;
import com.port.ocean.shipping.consignor.function.DataListManager;
import com.port.ocean.shipping.consignor.function.LoadUserInfo;
import com.port.ocean.shipping.consignor.util.StaticValue;

import org.mobile.library.common.function.AutoLogin;
import org.mobile.library.database.city.CityDatabase;
import org.mobile.library.global.ApplicationStaticValue;
import org.mobile.library.global.GlobalApplication;

/**
 * 启动页
 *
 * @author 超悟空
 * @version 1.0 2015/6/23
 * @since 1.0
 */
public class SplashActivity extends Activity {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "SplashActivity.";

    /**
     * 本地广播管理器
     */
    private LocalBroadcastManager localBroadcastManager = null;

    /**
     * 数据加载结果的广播接收者
     */
    private LoadingReceiver loadingReceiver = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        // 首次运行复制数据库
        copyDatabase();
        // 检测并加载数据
        checkLoadData();
    }

    /**
     * 检测并加载数据
     */
    private void checkLoadData() {
        // 注册广播接收者
        registerReceivers();

        // 配置系统参数
        systemConfig();

        // 自动登录
        autoLogin();

        // 加载基础数据
        loadData();
    }

    /**
     * 配置系统参数
     */
    private void systemConfig() {
        GlobalApplication.getApplicationAttribute().setAppCode(StaticValue.APP_CODE);
        GlobalApplication.getApplicationAttribute().setAppToken(StaticValue.APP_TOKEN);
    }

    /**
     * 加载基础数据
     */
    private void loadData() {
        DataListManager.create(StaticValue.DataListTag.CARGO_TYPE_LIST);
        DataListManager.create(StaticValue.DataListTag.VEHICLE_TYPE_LIST);
    }

    /**
     * 首次运行复制数据库
     */
    private void copyDatabase() {
        CityDatabase.copyDatabase(this);
    }

    /**
     * 自动登录
     */
    private void autoLogin() {
        Log.i(LOG_TAG + "autoLogin", "autoLogin() is invoked");

        AutoLogin.checkAutoLogin(this);
    }

    /**
     * 注册广播接收者
     */
    private void registerReceivers() {
        Log.i(LOG_TAG + "registerReceivers", "registerReceivers() is invoked");
        // 新建本地广播管理器
        localBroadcastManager = LocalBroadcastManager.getInstance(this);

        // 新建接收者
        loadingReceiver = new LoadingReceiver();

        // 注册
        localBroadcastManager.registerReceiver(loadingReceiver, loadingReceiver
                .getRegisterIntentFilter());
    }

    /**
     * 注销广播接收者
     */
    private void unregisterReceivers() {
        Log.i(LOG_TAG + "unregisterReceivers", "unregisterReceivers() is invoked");
        if (localBroadcastManager == null) {
            return;
        }

        if (loadingReceiver != null) {
            localBroadcastManager.unregisterReceiver(loadingReceiver);
        }
    }

    @Override
    protected void onDestroy() {
        // 注销广播接收者
        unregisterReceivers();

        super.onDestroy();
    }

    /**
     * 数据加载结果的广播接收者，
     * 用于提前结束启动页
     *
     * @author 超悟空
     * @version 1.0 2015/1/31
     * @since 1.0
     */
    private class LoadingReceiver extends BroadcastReceiver {

        /**
         * 动作队列信号量，
         * 初始时为注册的动作数量，
         * 当减少到0时表示数据加载完毕
         */
        private volatile int actionSemaphore = 2;

        /**
         * 得到本接收者监听的动作集合
         *
         * @return 填充完毕的意图集合
         */
        public final IntentFilter getRegisterIntentFilter() {
            // 新建动作集合
            IntentFilter filter = new IntentFilter();
            // 登录结果监听
            filter.addAction(ApplicationStaticValue.BroadcastAction.LOGIN_STATE);
            // 数据加载
            filter.addAction(StaticValue.DataListTag.CARGO_TYPE_LIST);
            filter.addAction(StaticValue.DataListTag.VEHICLE_TYPE_LIST);

            return filter;
        }

        @Override
        public void onReceive(Context context, Intent intent) {

            // 得到动作字符串
            String actionString = intent.getAction();
            Log.i(LOG_TAG + "LoadingReceiver.onReceive", "action is " + actionString);

            switch (actionString) {
                case ApplicationStaticValue.BroadcastAction.LOGIN_STATE:
                case StaticValue.DataListTag.CARGO_TYPE_LIST:
                case StaticValue.DataListTag.VEHICLE_TYPE_LIST:
                    // 完成一个动作信号量减1
                    actionSemaphore--;
                    Log.i(LOG_TAG + "LoadingReceiver.onReceive", "actionSemaphore--");
            }
            Log.i(LOG_TAG + "LoadingReceiver.onReceive", "now actionSemaphore is " +
                    actionSemaphore);

            if (actionSemaphore == 0) {
                // 数据加载完成
                jump();
            }
        }
    }

    /**
     * 数据加载完毕跳转
     */
    private void jump() {
        Log.i(LOG_TAG + "jump", "jump is invoked");

        if (GlobalApplication.getLoginStatus().isLogin()) {
            // 已登录
            // 加载用户数据
            LoadUserInfo.onLoadUserInfo();

            Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(mainIntent);
        } else {
            // 未登录
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
        }

        finish();
    }
}
