package com.port.ocean.shipping.consignor.function;

import com.port.ocean.shipping.consignor.data.IdentityConsignorInfoData;
import com.port.ocean.shipping.consignor.util.UserInfo;
import com.port.ocean.shipping.consignor.work.PullIdentityConsignorInfo;

import org.mobile.library.global.GlobalApplication;
import org.mobile.library.model.work.WorkBack;

/**
 * 加载用户数据
 *
 * @author 超悟空
 * @version 1.0 2015/7/8
 * @since 1.0
 */
public class LoadUserInfo {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "LoadUserInfo.";

    /**
     * 用户信息回调接口
     */
    public interface OnLoadUserInfoEndListener {
        /**
         * 加载用户信息结束回调
         *
         * @param state 执行结果
         */
        void onFinish(boolean state);
    }

    /**
     * 加载用户数据
     */
    public static void onLoadUserInfo() {
        onLoadUserInfo(null);
    }

    /**
     * 加载用户数据
     *
     * @param onLoadUserInfoEndListener 回调接口
     */
    public static void onLoadUserInfo(final OnLoadUserInfoEndListener onLoadUserInfoEndListener) {
        UserInfo.getInstance().Reset();

        // 新建任务
        PullIdentityConsignorInfo pullIdentityConsignorInfo = new PullIdentityConsignorInfo();

        pullIdentityConsignorInfo.setWorkEndListener(new WorkBack<IdentityConsignorInfoData>() {
            @Override
            public void doEndWork(boolean state, IdentityConsignorInfoData data) {
                if (state) {
                    // 填充数据
                    UserInfo.getInstance().setRealName(data.getUserName());
                }

                if (onLoadUserInfoEndListener != null) {
                    onLoadUserInfoEndListener.onFinish(state);
                }
            }
        });

        pullIdentityConsignorInfo.beginExecute(GlobalApplication.getLoginStatus().getUserID());
    }
}