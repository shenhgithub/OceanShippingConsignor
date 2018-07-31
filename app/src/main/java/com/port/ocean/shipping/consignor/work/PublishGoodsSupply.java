package com.port.ocean.shipping.consignor.work;
/**
 * Created by 超悟空 on 2015/6/23.
 */

import com.port.ocean.shipping.consignor.R;
import com.port.ocean.shipping.consignor.data.PublishGoodsSupplyData;
import com.port.ocean.shipping.consignor.util.StaticValue;

import org.mobile.library.global.GlobalApplication;
import org.mobile.library.model.work.DefaultWorkModel;

/**
 * 发布货源任务
 *
 * @author 超悟空
 * @version 1.0 2015/6/25
 * @since 1.0
 */
public class PublishGoodsSupply extends DefaultWorkModel<PublishGoodsSupplyData, String,
        PublishGoodsSupplyData> {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "PublishGoodsSupply.";

    @Override
    protected boolean onCheckParameters(PublishGoodsSupplyData... parameters) {
        return parameters != null && parameters.length >= 1;
    }

    @Override
    protected String onTaskUri() {
        return StaticValue.Url.PUBLISH_GOODS_SUPPLY_URL;
    }

    @Override
    protected String onParseFailedSetResult(PublishGoodsSupplyData data) {
        return GlobalApplication.getGlobal().getString(R.string.info_incomplete);
    }

    @Override
    protected String onRequestSuccessSetResult(PublishGoodsSupplyData data) {
        return data.getMessage();
    }

    @Override
    protected String onRequestFailedSetResult(PublishGoodsSupplyData data) {
        return data.getMessage();
    }

    @Override
    protected PublishGoodsSupplyData onCreateDataModel(PublishGoodsSupplyData... parameters) {
        // 获取数据对象
        PublishGoodsSupplyData data = parameters[0];

        return data;
    }
}
