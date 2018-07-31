package com.port.ocean.shipping.consignor.work;
/**
 * Created by 超悟空 on 2015/6/24.
 */

import com.port.ocean.shipping.consignor.R;
import com.port.ocean.shipping.consignor.data.IdentityData;
import com.port.ocean.shipping.consignor.util.StaticValue;

import org.mobile.library.global.GlobalApplication;
import org.mobile.library.model.work.DefaultWorkModel;

/**
 * 身份认证任务
 *
 * @author 超悟空
 * @version 1.0 2015/6/24
 * @since 1.0
 */
public class IdentityAuthenticate extends DefaultWorkModel<String, String, IdentityData> {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "IdentityAuthenticate.";

    @Override
    protected boolean onCheckParameters(String... parameters) {
        return parameters != null && parameters.length >= 3 && parameters[0] != null &&
                parameters[1] != null && parameters[2] != null;
    }

    @Override
    protected String onTaskUri() {
        return StaticValue.Url.IDENTITY_URL;
    }

    @Override
    protected String onParseFailedSetResult(IdentityData data) {
        return GlobalApplication.getGlobal().getString(R.string.info_incomplete);
    }

    @Override
    protected String onRequestSuccessSetResult(IdentityData data) {
        return data.getMessage();
    }

    @Override
    protected String onRequestFailedSetResult(IdentityData data) {
        return data.getMessage();
    }

    @Override
    protected IdentityData onCreateDataModel(String... parameters) {
        // 新建数据对象
        IdentityData data = new IdentityData();
        data.setAccount(parameters[0]);
        data.setUserName(parameters[1]);
        data.setIdentityCard(parameters[2]);

        return data;
    }
}
