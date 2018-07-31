package com.port.ocean.shipping.consignor.work;
/**
 * Created by 超悟空 on 2015/6/26.
 */

import com.port.ocean.shipping.consignor.data.Goods;
import com.port.ocean.shipping.consignor.data.GoodsSupplyData;
import com.port.ocean.shipping.consignor.util.StaticValue;

import org.mobile.library.model.work.DefaultWorkModel;

import java.util.List;

/**
 * 获取货源列表任务
 *
 * @author 超悟空
 * @version 1.0 2015/6/26
 * @since 1.0
 */
public class PullGoodsSupply extends DefaultWorkModel<String, List<Goods>, GoodsSupplyData> {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "PullGoodsSupply.";

    @Override
    protected boolean onCheckParameters(String... parameters) {
        return parameters != null && parameters.length >= 1 && parameters[0] != null;
    }

    @Override
    protected String onTaskUri() {
        return StaticValue.Url.GOODS_SUPPLY_URL;
    }

    @Override
    protected List<Goods> onRequestSuccessSetResult(GoodsSupplyData data) {
        return data.getGoodsList();
    }

    @Override
    protected List<Goods> onRequestFailedSetResult(GoodsSupplyData data) {
        return data.getGoodsList();
    }

    @Override
    protected GoodsSupplyData onCreateDataModel(String... parameters) {
        // 新建数据对象
        GoodsSupplyData data = new GoodsSupplyData();
        data.setAccount(parameters[0]);

        return data;
    }
}
