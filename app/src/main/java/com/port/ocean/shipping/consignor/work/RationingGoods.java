package com.port.ocean.shipping.consignor.work;
/**
 * Created by 超悟空 on 2015/7/10.
 */

import com.port.ocean.shipping.consignor.data.RationingGoodsData;
import com.port.ocean.shipping.consignor.util.StaticValue;

import org.mobile.library.model.work.DefaultWorkModel;

/**
 * 撤销货源任务
 *
 * @author 超悟空
 * @version 1.0 2015/7/10
 * @since 1.0
 */
public class RationingGoods extends DefaultWorkModel<String, String, RationingGoodsData> {

    @Override
    protected boolean onCheckParameters(String... strings) {
        return strings != null && strings.length >= 2;
    }

    @Override
    protected String onTaskUri() {
        return StaticValue.Url.RATIONING_GOODS_URL;
    }

    @Override
    protected String onRequestSuccessSetResult(RationingGoodsData rationingGoodsData) {
        return rationingGoodsData.getMessage();
    }

    @Override
    protected String onRequestFailedSetResult(RationingGoodsData rationingGoodsData) {
        return rationingGoodsData.getMessage();
    }

    @Override
    protected RationingGoodsData onCreateDataModel(String... strings) {
        RationingGoodsData rationingGoodsData = new RationingGoodsData();
        rationingGoodsData.setAccount(strings[0]);
        rationingGoodsData.setGoodsId(strings[1]);
        return rationingGoodsData;
    }
}
