package com.port.ocean.shipping.consignor.util;
/**
 * Created by 超悟空 on 2015/6/23.
 */

/**
 * 全局常量
 *
 * @author 超悟空
 * @version 1.0 2015/6/23
 * @since 1.0
 */
public interface StaticValue {

    /**
     * 应用代码
     */
    String APP_CODE = "SPHHZ";

    /**
     * 应用令牌
     */
    String APP_TOKEN = "2F67BEF1E0CD3242E053A864016A3242";

    /**
     * 数据列表工具标签
     */
    interface DataListTag {
        /**
         * 货物类别标签
         */
        String CARGO_TYPE_LIST = "cargo_type_list";

        /**
         * 车型标签
         */
        String VEHICLE_TYPE_LIST = "vehicle_type_list";
    }

    /**
     * 广播事件
     */
    interface BroadcastAction {
        /**
         * 用户信息变更
         */
        String USER_INFO_CHANGE_TAG = "user_info_change_tag";
    }

    /**
     * 意图数据传递标签
     */
    interface IntentTag {
        /**
         * 货源取值标签
         */
        String GOODS_INTENT_TAG = "goods_intent_tag";

        /**
         * 货源ID取值标签
         */
        String GOODS_ID_TAG = "goods_id_tag";

        /**
         * 手机号取值标签
         */
        String MOBILE_TAG = "mobile_tag";

        /**
         * 用户编号取值标签
         */
        String USER_CODE_TAG = "user_code_tag";
    }

    /**
     * 网络请求接口地址
     */
    interface Url {
        /**
         * 获取车型列表的请求地址
         */
        String VEHICLE_TYPE_URL = "http://218.92.115.55/M_Sph/Base/GetVehicleType.aspx";

        /**
         * 获取货物类型列表的请求地址
         */
        String CARGO_TYPE_LIST_URL = "http://218.92.115.55/M_Sph/Base/GetCargoType.aspx";

        /**
         * 首页广告页面地址
         */
        String MAIN_ADVERTISING = "http://218.92.115.55/M_Sph//Page/Advertisement.html";

        /**
         * 撤销货源请求地址
         */
        String RATIONING_GOODS_URL = "http://218.92.115.55/M_Sph/Goods/RepealGoods.aspx";

        /**
         * 发布货源地址
         */
        String PUBLISH_GOODS_SUPPLY_URL = "http://218.92.115.55/M_Sph/Goods/ReleaseGoods.aspx";

        /**
         * 获取货源的地址
         */
        String GOODS_SUPPLY_URL = "http://218.92.115.55/M_Sph/Goods/GetGoodsReleasing.aspx";

        /**
         * 货主身份认证地址
         */
        String IDENTITY_URL = "http://218.92.115.55/M_Sph/Auth/AuthForConsignor.aspx";

        /**
         * 司机身份信息获取地址
         */
        String IDENTITY_INFO_URL = "http://218.92.115.55/M_Sph/Auth/IdentityAuthForDriver.aspx";

        /**
         * 货主身份信息获取地址
         */
        String IDENTITY_CONSIGNOR_INFO_URL = "http://218.92.115" +
                ".55/M_Sph/UserInfo/InfoForConsignor" + ".aspx";

        /**
         * 货主发起交易
         */
        String DEAL_URL = "http://218.92.115.55/M_Sph/Deal/DealForConsingor.aspx";

        /**
         * 更新货源地址
         */
        String UPDATE_GOODS_SUPPLY_URL = "http://218.92.115.55/M_Sph/Goods/ModifyGoods.aspx";
    }
}
