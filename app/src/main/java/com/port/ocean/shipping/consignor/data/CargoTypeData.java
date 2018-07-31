package com.port.ocean.shipping.consignor.data;
/**
 * Created by 超悟空 on 2016/3/16.
 */

import org.json.JSONArray;
import org.json.JSONObject;
import org.mobile.library.model.data.base.SimpleJsonDataModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 货物类型数据模型
 *
 * @author 超悟空
 * @version 1.0 2016/3/16
 * @since 1.0
 */
public class CargoTypeData extends SimpleJsonDataModel {

    /**
     * 货物类型列表
     */
    private List<String> dataList = new ArrayList<>();

    /**
     * 获取货物类型列表
     *
     * @return 货物类型列表
     */
    public List<String> getDataList() {
        return dataList;
    }

    @Override
    protected void onExtractData(JSONObject jsonData) throws Exception {
        JSONArray jsonArray = jsonData.getJSONArray("Data");

        for (int i = 0; i < jsonArray.length(); i++) {
            dataList.add(jsonArray.getString(i));
        }
    }

    @Override
    protected void onFillRequestParameters(Map<String, String> dataMap) {

    }
}
