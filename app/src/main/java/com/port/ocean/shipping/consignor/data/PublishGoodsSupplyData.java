package com.port.ocean.shipping.consignor.data;
/**
 * Created by 超悟空 on 2015/6/25.
 */

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import org.mobile.library.model.data.base.JsonDataModel;

import java.util.Map;

/**
 * 发布货源数据模型
 *
 * @author 超悟空
 * @version 1.0 2015/6/25
 * @since 1.0
 */
public class PublishGoodsSupplyData extends JsonDataModel {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "PublishGoodsSupplyData.";

    /**
     * 用户ID
     */
    private String account = null;

    private String SFDProvince = null;

    private String SFDCity = null;

    private String SFDCounty = null;

    private String MDDProvince = null;

    private String MDDCity = null;

    private String MDDCounty = null;

    private String goods = null;

    private String weight = null;

    private String volume = null;

    private String vehicleLen = null;

    private String vehicleType = null;

    private String mobile = null;

    private String phone = null;

    private String contact = null;

    private String distance = null;

    public void setSFDProvince(String SFDProvince) {
        this.SFDProvince = SFDProvince;
    }

    public void setSFDCity(String SFDCity) {
        this.SFDCity = SFDCity;
    }

    public void setSFDCounty(String SFDCounty) {
        this.SFDCounty = SFDCounty;
    }

    public void setMDDProvince(String MDDProvince) {
        this.MDDProvince = MDDProvince;
    }

    public void setMDDCity(String MDDCity) {
        this.MDDCity = MDDCity;
    }

    public void setMDDCounty(String MDDCounty) {
        this.MDDCounty = MDDCounty;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public void setVehicleLen(String vehicleLen) {
        this.vehicleLen = vehicleLen;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    /**
     * 设置用户ID
     *
     * @param account 用户ID
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 填充服务请求所需的参数，
     * 即设置{@link #serialization()}返回值
     *
     * @param dataMap 参数数据集<参数名,参数值>
     */
    @Override
    protected void onFillRequestParameters(Map<String, String> dataMap) {
        // 加入用户名和密码
        dataMap.put("CodeUser", account);
        Log.i(LOG_TAG + "onFillRequestParameters", "CodeUser is " + account);
        dataMap.put("SFDProvince", SFDProvince);
        Log.i(LOG_TAG + "onFillRequestParameters", "SFDProvince is " + SFDProvince);
        dataMap.put("SFDCity", SFDCity);
        Log.i(LOG_TAG + "onFillRequestParameters", "SFDCity is " + SFDCity);
        dataMap.put("SFDCounty", SFDCounty);
        Log.i(LOG_TAG + "onFillRequestParameters", "SFDCounty is " + SFDCounty);
        dataMap.put("MDDProvince", MDDProvince);
        Log.i(LOG_TAG + "onFillRequestParameters", "MDDProvince is " + MDDProvince);
        dataMap.put("MDDCity", MDDCity);
        Log.i(LOG_TAG + "onFillRequestParameters", "MDDCity is " + MDDCity);
        dataMap.put("MDDCounty", MDDCounty);
        Log.i(LOG_TAG + "onFillRequestParameters", "MDDCounty is " + MDDCounty);
        dataMap.put("goods", goods);
        Log.i(LOG_TAG + "onFillRequestParameters", "goods is " + goods);
        dataMap.put("weight", weight);
        Log.i(LOG_TAG + "onFillRequestParameters", "weight is " + weight);
        dataMap.put("volume", volume);
        Log.i(LOG_TAG + "onFillRequestParameters", "volume is " + volume);
        dataMap.put("vehicleLen", vehicleLen);
        Log.i(LOG_TAG + "onFillRequestParameters", "vehicleLen is " + vehicleLen);
        dataMap.put("vehicleType", vehicleType);
        Log.i(LOG_TAG + "onFillRequestParameters", "vehicleType is " + vehicleType);
        dataMap.put("mobile", mobile);
        Log.i(LOG_TAG + "onFillRequestParameters", "mobile is " + mobile);
        dataMap.put("phone", phone);
        Log.i(LOG_TAG + "onFillRequestParameters", "phone is " + phone);
        dataMap.put("Contact", contact);
        Log.i(LOG_TAG + "onFillRequestParameters", "Contact is " + contact);
        dataMap.put("Distance", distance);
        Log.i(LOG_TAG + "onFillRequestParameters", "Distance is " + distance);
    }

    /**
     * 提取服务执行结果
     *
     * @param jsonResult Json结果集
     *
     * @return 服务请求结果，true表示请求成功，false表示请求失败
     *
     * @throws JSONException 解析过程中出现错误
     */
    @Override
    protected boolean onRequestResult(JSONObject jsonResult) throws JSONException {
        // 得到执行结果
        String resultState = jsonResult.getString("IsRelease");

        return resultState != null && "yes".equals(resultState.trim().toLowerCase());
    }

    /**
     * 提取服务返回的结果消息，
     * 在{@link #onRequestResult(JSONObject)}之后被调用
     *
     * @param result     服务请求执行结果，
     *                   即{@link #onRequestResult(JSONObject)}返回值
     * @param jsonResult Json结果集
     *
     * @return 消息字符串
     *
     * @throws JSONException 解析过程中出现错误
     */
    @Override
    protected String onRequestMessage(boolean result, JSONObject jsonResult) throws JSONException {
        return jsonResult.getString("Message");
    }

    /**
     * 提取服务反馈的结果数据，
     * 在服务请求成功后调用，
     * 即{@link #onRequestResult(JSONObject)}返回值为true时，
     * 在{@link #onRequestMessage(boolean , JSONObject)}之后被调用，
     *
     * @param jsonResult Json结果集
     *
     * @throws JSONException 解析过程中出现错误
     */
    @Override
    protected void onRequestSuccess(JSONObject jsonResult) throws JSONException {

    }
}
