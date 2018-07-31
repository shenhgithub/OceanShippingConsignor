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
 * 司机身份信息数据模型
 *
 * @author 超悟空
 * @version 1.0 2015/6/25
 * @since 1.0
 */
public class IdentityInfoData extends JsonDataModel {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "IdentityInfoData.";

    /**
     * 真实姓名
     */
    private String userName = null;

    /**
     * 身份证
     */
    private String identityCard = null;

    /**
     * 车牌号
     */
    private String vehicleNumber = null;

    /**
     * 车长
     */
    private String vehicleLength = null;

    /**
     * 车型
     */
    private String vehicleType = null;

    /**
     * 载重
     */
    private String tons = null;

    /**
     * 手机号
     */
    private String mobile = null;

    /**
     * 用户ID
     */
    private String userCode = null;

    /**
     * 获取用户真实姓名
     *
     * @return 姓名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 获取身份证
     *
     * @return 身份证号
     */
    public String getIdentityCard() {
        return identityCard;
    }

    /**
     * 获取车牌
     *
     * @return 车牌号
     */
    public String getVehicleNumber() {
        return vehicleNumber;
    }

    /**
     * 获取车长
     *
     * @return 车长
     */
    public String getVehicleLength() {
        return vehicleLength;
    }

    /**
     * 获取车型
     *
     * @return 车型
     */
    public String getVehicleType() {
        return vehicleType;
    }

    /**
     * 获取载重
     *
     * @return 载重
     */
    public String getTons() {
        return tons;
    }

    /**
     * 获取用户ID
     *
     * @return 用户ID
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * 设置手机号
     *
     * @param mobile 手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
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
        dataMap.put("mobile", mobile);
        Log.i(LOG_TAG + "onFillRequestParameters", "mobile is " + mobile);
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
        String resultState = jsonResult.getString("IsAuth");

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
        return result ? null : jsonResult.getString("Message");
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
        userName = jsonResult.getString("UserName");
        identityCard = jsonResult.getString("IdentityCard");
        vehicleNumber = jsonResult.getString("VehicleNum");
        vehicleLength = jsonResult.getString("VehicleLen");
        vehicleType = jsonResult.getString("VehicleType");
        tons = jsonResult.getString("Tons");
        userCode = jsonResult.getString("CodeUser");
    }
}
