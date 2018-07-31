package com.port.ocean.shipping.consignor.data;
/**
 * Created by 超悟空 on 2015/7/9.
 */

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import org.mobile.library.model.data.base.JsonDataModel;

import java.util.Map;

/**
 * 撤销货源数据模型
 *
 * @author 超悟空
 * @version 1.0 2015/7/9
 * @since 1.0
 */
public class RationingGoodsData extends JsonDataModel {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "RationingGoodsData.";

    /**
     * 用户ID
     */
    private String account = null;

    /**
     * 货源ID
     */
    private String goodsId = null;

    /**
     * 设置用户ID
     *
     * @param account 用户ID
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 设置货源ID
     *
     * @param goodsId 货源ID
     */
    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    @Override
    protected void onFillRequestParameters(Map<String, String> map) {
        map.put("CodeUser", account);
        Log.i(LOG_TAG + "onFillRequestParameters", "CodeUser is " + account);
        map.put("ID", goodsId);
        Log.i(LOG_TAG + "onFillRequestParameters", "ID is " + goodsId);
    }

    @Override
    protected boolean onRequestResult(JSONObject jsonObject) throws JSONException {
        // 得到执行结果
        String resultState = jsonObject.getString("IsRepeal");

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
