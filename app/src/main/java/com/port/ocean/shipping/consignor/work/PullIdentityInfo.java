package com.port.ocean.shipping.consignor.work;
/**
 * Created by 超悟空 on 2015/6/25.
 */

import com.port.ocean.shipping.consignor.data.IdentityInfoData;
import com.port.ocean.shipping.consignor.util.StaticValue;

import org.mobile.library.model.work.DefaultWorkModel;

/**
 * 获取身份信息任务
 *
 * @author 超悟空
 * @version 1.0 2015/6/25
 * @since 1.0
 */
public class PullIdentityInfo extends DefaultWorkModel<String, IdentityInfoData, IdentityInfoData> {

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
        return StaticValue.Url.IDENTITY_INFO_URL;
    }

    @Override
    protected IdentityInfoData onRequestSuccessSetResult(IdentityInfoData data) {
        return data;
    }

    @Override
    protected IdentityInfoData onRequestFailedSetResult(IdentityInfoData data) {
        return data;
    }

    @Override
    protected IdentityInfoData onCreateDataModel(String... parameters) {
        // 新建数据对象
        IdentityInfoData data = new IdentityInfoData();
        data.setMobile(parameters[0]);

        return data;
    }
}
