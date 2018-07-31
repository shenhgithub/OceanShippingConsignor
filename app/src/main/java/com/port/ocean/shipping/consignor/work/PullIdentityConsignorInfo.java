package com.port.ocean.shipping.consignor.work;
/**
 * Created by 超悟空 on 2015/6/25.
 */

import com.port.ocean.shipping.consignor.data.IdentityConsignorInfoData;
import com.port.ocean.shipping.consignor.util.StaticValue;

import org.mobile.library.global.GlobalApplication;
import org.mobile.library.model.work.DefaultWorkModel;
import org.mobile.library.util.BroadcastUtil;

/**
 * 获取货主身份信息任务
 *
 * @author 超悟空
 * @version 1.0 2015/6/25
 * @since 1.0
 */
public class PullIdentityConsignorInfo extends DefaultWorkModel<String,
        IdentityConsignorInfoData, IdentityConsignorInfoData> {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "PullIdentityInfo.";

    @Override
    protected boolean onCheckParameters(String... parameters) {
        return parameters != null && parameters.length >= 1 && parameters[0] != null;
    }

    @Override
    protected String onTaskUri() {
        return StaticValue.Url.IDENTITY_CONSIGNOR_INFO_URL;
    }

    @Override
    protected IdentityConsignorInfoData onRequestSuccessSetResult(IdentityConsignorInfoData data) {
        return data;
    }

    @Override
    protected IdentityConsignorInfoData onCreateDataModel(String... parameters) {
        // 新建数据对象
        IdentityConsignorInfoData data = new IdentityConsignorInfoData();
        data.setAccount(parameters[0]);

        return data;
    }

    @Override
    protected void onFinish() {
        BroadcastUtil.sendBroadcast(GlobalApplication.getGlobal(), StaticValue.BroadcastAction
                .USER_INFO_CHANGE_TAG);
    }
}
