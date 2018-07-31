package com.port.ocean.shipping.consignor.work;
/**
 * Created by 超悟空 on 2015/6/26.
 */

import com.port.ocean.shipping.consignor.R;
import com.port.ocean.shipping.consignor.data.DealData;
import com.port.ocean.shipping.consignor.util.StaticValue;

import org.mobile.library.global.GlobalApplication;
import org.mobile.library.model.work.DefaultWorkModel;

/**
 * 货主发起交易任务
 *
 * @author 超悟空
 * @version 1.0 2015/6/26
 * @since 1.0
 */
public class Deal extends DefaultWorkModel<String, String, DealData> {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "Deal.";

    @Override
    protected boolean onCheckParameters(String... parameters) {
        return parameters != null && parameters.length >= 6 && parameters[0] != null &&
                parameters[1] != null && parameters[2] != null;
    }

    @Override
    protected String onTaskUri() {
        return StaticValue.Url.DEAL_URL;
    }

    @Override
    protected String onParseFailedSetResult(DealData data) {
        return GlobalApplication.getGlobal().getString(R.string.info_incomplete);
    }

    @Override
    protected String onRequestSuccessSetResult(DealData data) {
        return data.getMessage();
    }

    @Override
    protected String onRequestFailedSetResult(DealData data) {
        return data.getMessage();
    }

    @Override
    protected DealData onCreateDataModel(String... parameters) {
        // 新建数据对象
        DealData data = new DealData();
        data.setFirstAccount(parameters[0]);
        data.setSecondAccount(parameters[1]);
        data.setOriginId(parameters[2]);
        data.setStart(parameters[3]);
        data.setEnd(parameters[4]);
        data.setAmount(parameters[5]);

        return data;
    }
}
