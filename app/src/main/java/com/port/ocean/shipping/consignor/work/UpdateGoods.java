package com.port.ocean.shipping.consignor.work;
/**
 * Created by 超悟空 on 2015/9/11.
 */

import com.port.ocean.shipping.consignor.data.Goods;
import com.port.ocean.shipping.consignor.data.UpdateGoodsData;
import com.port.ocean.shipping.consignor.util.StaticValue;

import org.mobile.library.model.work.DefaultWorkModel;

/**
 * 更新货源任务
 *
 * @author 超悟空
 * @version 1.0 2015/9/11
 * @since 1.0
 */
public class UpdateGoods extends DefaultWorkModel<Goods, String, UpdateGoodsData> {
    @Override
    protected boolean onCheckParameters(Goods... parameters) {
        return parameters != null && parameters.length >= 1 && parameters[0] != null;
    }

    @Override
    protected String onTaskUri() {
        return StaticValue.Url.UPDATE_GOODS_SUPPLY_URL;
    }

    @Override
    protected String onRequestSuccessSetResult(UpdateGoodsData data) {
        return data.getMessage();
    }

    @Override
    protected String onRequestFailedSetResult(UpdateGoodsData data) {
        return data.getMessage();
    }

    @Override
    protected UpdateGoodsData onCreateDataModel(Goods... parameters) {
        // 新建数据对象
        UpdateGoodsData data = new UpdateGoodsData();
        data.setGoods(parameters[0]);

        return data;
    }
}
