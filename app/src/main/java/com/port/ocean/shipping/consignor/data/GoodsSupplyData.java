package com.port.ocean.shipping.consignor.data;
/**
 * Created by 超悟空 on 2015/6/26.
 */

import android.util.Log;

import com.port.ocean.shipping.consignor.util.TypeConvert;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.mobile.library.model.data.base.JsonDataModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 货源列表数据模型
 *
 * @author 超悟空
 * @version 1.0 2015/6/26
 * @since 1.0
 */
public class GoodsSupplyData extends JsonDataModel {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "GoodsSupplyData.";

    /**
     * 用户ID
     */
    private String account = null;

    /**
     * 货源列表
     */
    private List<Goods> goodsList = new ArrayList<>();

    /**
     * 设置用户ID
     *
     * @param account 用户ID
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 获取货源列表
     *
     * @return 货源列表
     */
    public List<Goods> getGoodsList() {
        return goodsList;
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
        String resultState = TypeConvert.JsonArrayToString(jsonResult.getJSONArray("IsGet"))[0];


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
        return null;
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
        JSONArray jsonArray = jsonResult.getJSONArray("GoodsReleasing");
        Log.i(LOG_TAG + "parse", "jsonArray count is " + jsonArray.length());
        for (int i = 0; i < jsonArray.length(); i++) {
            String[] goodsStrings = TypeConvert.JsonArrayToString(jsonArray.getJSONArray(i));
            Log.i(LOG_TAG + "parse", "goodsStrings count is " + goodsStrings.length);
            if (goodsStrings.length >= 17) {
                Goods goods = new Goods();
                goods.setGoodsId(goodsStrings[0]);
                goods.setSFDProvince(goodsStrings[1]);
                goods.setSFDCity(goodsStrings[2]);
                goods.setSFDCounty(goodsStrings[3]);
                goods.setMDDProvince(goodsStrings[4]);
                goods.setMDDCity(goodsStrings[5]);
                goods.setMDDCounty(goodsStrings[6]);
                goods.setGoods(goodsStrings[7]);
                goods.setWeight(goodsStrings[8]);
                goods.setVolume(goodsStrings[9]);
                goods.setVehicleLen(goodsStrings[10]);
                goods.setVehicleType(goodsStrings[11]);
                goods.setMobile(goodsStrings[12]);
                goods.setPhone(goodsStrings[13]);
                goods.setTime(goodsStrings[14]);
                goods.setDistance(goodsStrings[15]);
                goods.setContactMan(goodsStrings[16]);

                goodsList.add(goods);
            }
        }
    }
}
