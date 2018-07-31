package com.port.ocean.shipping.consignor.data;
/**
 * Created by 超悟空 on 2015/7/10.
 */

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import org.mobile.library.model.data.base.JsonDataModel;

import java.util.Map;

/**
 * 更新货源数据模型
 *
 * @author 超悟空
 * @version 1.0 2015/7/10
 * @since 1.0
 */
public class UpdateGoodsData extends JsonDataModel {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "UpdateGoodsData.";

    /**
     * 要更新的货源
     */
    private Goods goods = null;

    /**
     * 设置将要更新的货源
     *
     * @param goods 货源对象
     */
    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    @Override
    protected void onFillRequestParameters(Map<String, String> dataMap) {

        dataMap.put("ID", goods.getGoodsId());
        Log.i(LOG_TAG + "onFillRequestParameters", "ID is " + goods.getGoodsId());
        dataMap.put("SFDProvince", goods.getSFDProvince());
        Log.i(LOG_TAG + "onFillRequestParameters", "SFDProvince is " + goods.getSFDProvince());
        dataMap.put("SFDCity", goods.getSFDCity());
        Log.i(LOG_TAG + "onFillRequestParameters", "SFDCity is " + goods.getSFDCity());
        dataMap.put("SFDCounty", goods.getSFDCounty());
        Log.i(LOG_TAG + "onFillRequestParameters", "SFDCounty is " + goods.getSFDCounty());
        dataMap.put("MDDProvince", goods.getMDDProvince());
        Log.i(LOG_TAG + "onFillRequestParameters", "MDDProvince is " + goods.getMDDProvince());
        dataMap.put("MDDCity", goods.getMDDCity());
        Log.i(LOG_TAG + "onFillRequestParameters", "MDDCity is " + goods.getMDDCity());
        dataMap.put("MDDCounty", goods.getMDDCounty());
        Log.i(LOG_TAG + "onFillRequestParameters", "MDDCounty is " + goods.getMDDCounty());
        dataMap.put("goods", goods.getGoods());
        Log.i(LOG_TAG + "onFillRequestParameters", "goods is " + goods.getGoods());
        dataMap.put("weight", goods.getWeight());
        Log.i(LOG_TAG + "onFillRequestParameters", "weight is " + goods.getWeight());
        dataMap.put("volume", goods.getVolume());
        Log.i(LOG_TAG + "onFillRequestParameters", "volume is " + goods.getVolume());
        dataMap.put("vehicleLen", goods.getVehicleLen());
        Log.i(LOG_TAG + "onFillRequestParameters", "vehicleLen is " + goods.getVehicleLen());
        dataMap.put("vehicleType", goods.getVehicleType());
        Log.i(LOG_TAG + "onFillRequestParameters", "vehicleType is " + goods.getVehicleType());
        dataMap.put("mobile", goods.getMobile());
        Log.i(LOG_TAG + "onFillRequestParameters", "mobile is " + goods.getMobile());
        dataMap.put("phone", goods.getPhone());
        Log.i(LOG_TAG + "onFillRequestParameters", "phone is " + goods.getPhone());
        dataMap.put("Contact", goods.getContactMan());
        Log.i(LOG_TAG + "onFillRequestParameters", "Contact is " + goods.getContactMan());
        dataMap.put("Distance", goods.getDistance());
        Log.i(LOG_TAG + "onFillRequestParameters", "Distance is " + goods.getDistance());
    }

    @Override
    protected boolean onRequestResult(JSONObject jsonObject) throws JSONException {
        // 得到执行结果
        String resultState = jsonObject.getString("IsModify");

        return resultState != null && "yes".equals(resultState.trim().toLowerCase());
    }

    @Override
    protected String onRequestMessage(boolean b, JSONObject jsonObject) throws JSONException {
        return jsonObject.getString("Message");
    }

    @Override
    protected void onRequestSuccess(JSONObject jsonObject) throws JSONException {

    }
}
