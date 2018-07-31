package com.port.ocean.shipping.consignor.data;
/**
 * Created by 超悟空 on 2015/6/26.
 */

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import org.mobile.library.model.data.base.JsonDataModel;

import java.util.Map;

/**
 * 发起交易数据模型
 *
 * @author 超悟空
 * @version 1.0 2015/6/26
 * @since 1.0
 */
public class DealData extends JsonDataModel {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "DealData.";

    /**
     * 货主用户ID
     */
    private String firstAccount = null;

    /**
     * 司机用户ID
     */
    private String secondAccount = null;

    /**
     * 货源ID
     */
    private String originId = null;

    /**
     * 起始地址
     */
    private String start = null;

    /**
     * 目的地址
     */
    private String end = null;

    /**
     * 信息费
     */
    private String amount = null;

    /**
     * 保险
     */
    private String insurance = "1";

    /**
     * 设置货主ID
     *
     * @param firstAccount 货主ID
     */
    public void setFirstAccount(String firstAccount) {
        this.firstAccount = firstAccount;
    }

    /**
     * 设置司机用户ID
     *
     * @param secondAccount 司机用户ID
     */
    public void setSecondAccount(String secondAccount) {
        this.secondAccount = secondAccount;
    }

    /**
     * 设置货源ID
     *
     * @param originId 货源ID
     */
    public void setOriginId(String originId) {
        this.originId = originId;
    }

    /**
     * 设置起始地
     *
     * @param start 起始地
     */
    public void setStart(String start) {
        this.start = start;
    }

    /**
     * 设置目的地
     *
     * @param end 目的地
     */
    public void setEnd(String end) {
        this.end = end;
    }

    /**
     * 设置信息费
     *
     * @param amount 信息费
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    protected void onFillRequestParameters(Map<String, String> dataMap) {
        // 加入用户名和密码
        dataMap.put("FirstAccount", firstAccount);
        Log.i(LOG_TAG + "onFillRequestParameters", "FirstAccount is " + firstAccount);
        dataMap.put("SecondAccount", secondAccount);
        Log.i(LOG_TAG + "onFillRequestParameters", "SecondAccount is " + secondAccount);
        dataMap.put("OriginId", originId);
        Log.i(LOG_TAG + "onFillRequestParameters", "OriginId is " + originId);
        dataMap.put("SFD", start);
        Log.i(LOG_TAG + "onFillRequestParameters", "SFD is " + start);
        dataMap.put("MDD", end);
        Log.i(LOG_TAG + "onFillRequestParameters", "MDD is " + end);
        dataMap.put("OrderNum", "");
        dataMap.put("Amount", amount);
        Log.i(LOG_TAG + "onFillRequestParameters", "Amount is " + amount);
        dataMap.put("insurance", insurance);
        Log.i(LOG_TAG + "onFillRequestParameters", "insurance is " + insurance);
    }

    @Override
    protected boolean onRequestResult(JSONObject jsonObject) throws JSONException {
        // 得到执行结果
        String resultState = jsonObject.getString("IsDeal");

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
