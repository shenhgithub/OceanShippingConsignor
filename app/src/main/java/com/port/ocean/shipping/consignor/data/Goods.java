package com.port.ocean.shipping.consignor.data;
/**
 * Created by 超悟空 on 2015/6/26.
 */

import java.io.Serializable;

/**
 * 货源数据结构
 *
 * @author 超悟空
 * @version 1.0 2015/6/26
 * @since 1.0
 */
public class Goods implements Serializable {

    private String goodsId = null;

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

    private String time = null;

    private String distance = null;

    private String contactMan = null;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getSFDProvince() {
        return SFDProvince;
    }

    public void setSFDProvince(String SFDProvince) {
        this.SFDProvince = SFDProvince;
    }

    public String getSFDCity() {
        return SFDCity;
    }

    public void setSFDCity(String SFDCity) {
        this.SFDCity = SFDCity;
    }

    public String getSFDCounty() {
        return SFDCounty;
    }

    public void setSFDCounty(String SFDCounty) {
        this.SFDCounty = SFDCounty;
    }

    public String getMDDProvince() {
        return MDDProvince;
    }

    public void setMDDProvince(String MDDProvince) {
        this.MDDProvince = MDDProvince;
    }

    public String getMDDCity() {
        return MDDCity;
    }

    public void setMDDCity(String MDDCity) {
        this.MDDCity = MDDCity;
    }

    public String getMDDCounty() {
        return MDDCounty;
    }

    public void setMDDCounty(String MDDCounty) {
        this.MDDCounty = MDDCounty;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getVehicleLen() {
        return vehicleLen;
    }

    public void setVehicleLen(String vehicleLen) {
        this.vehicleLen = vehicleLen;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getContactMan() {
        return contactMan;
    }

    public void setContactMan(String contactMan) {
        this.contactMan = contactMan;
    }
}
