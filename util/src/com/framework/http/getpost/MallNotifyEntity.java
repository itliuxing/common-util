package com.framework.http.getpost;

import java.io.Serializable;

/**
 * @Class 	MallNotifyEntity.java
 * @Author 	作者姓名:刘兴 
 * @Version	1.0
 * @Date	创建时间：2018-2-28 下午12:07:50
 * @Copyright Copyright by 智多星
 * @Direction 类说明   商会数据变更数据同步通知实体
 */
public class MallNotifyEntity implements Serializable {
	
	private String version ;					// 1.0
	private String appid ;						// zhdsbang
	private String orgid ;						// 商会组织ID
	private String operation ;					// 删除时必填delete
	private String shanghui_name ;				// 商会名称，添加时候必填
	private String shanghui_parent ;			// 商会上级的组织ID，当上级不存在时候将置空
	private String shanghui_logo ;				// 商会LOGO
	private String shanghui_auth ;				// 商会授权标徽URL
	private String shanghui_qrcode ;			// 商会关注二维码
	private String shanghui_url ;				// 商会推荐链接
	private String shanghui_wechat ;			// 商会微信号
	private String country ;					// 商会所在国家
	private String province ;					// 商会所在省
	private String city ;						// 商会所在市
	private String district ;					// 商会所在区/县
	private String address ;					// 商会地址
	private String longitude ;					// 商会经度
	private String latitude ;					// 商会纬度
	private String contacts ;					// 商会联系人
	private String tel ;						// 商会联系电话
	private String displayorder ;				// 商会排序
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getOrgid() {
		return orgid;
	}
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getShanghui_name() {
		return shanghui_name;
	}
	public void setShanghui_name(String shanghui_name) {
		this.shanghui_name = shanghui_name;
	}
	public String getShanghui_parent() {
		return shanghui_parent;
	}
	public void setShanghui_parent(String shanghui_parent) {
		this.shanghui_parent = shanghui_parent;
	}
	public String getShanghui_logo() {
		return shanghui_logo;
	}
	public void setShanghui_logo(String shanghui_logo) {
		this.shanghui_logo = shanghui_logo;
	}
	public String getShanghui_auth() {
		return shanghui_auth;
	}
	public void setShanghui_auth(String shanghui_auth) {
		this.shanghui_auth = shanghui_auth;
	}
	public String getShanghui_qrcode() {
		return shanghui_qrcode;
	}
	public void setShanghui_qrcode(String shanghui_qrcode) {
		this.shanghui_qrcode = shanghui_qrcode;
	}
	public String getShanghui_url() {
		return shanghui_url;
	}
	public void setShanghui_url(String shanghui_url) {
		this.shanghui_url = shanghui_url;
	}
	public String getShanghui_wechat() {
		return shanghui_wechat;
	}
	public void setShanghui_wechat(String shanghui_wechat) {
		this.shanghui_wechat = shanghui_wechat;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getDisplayorder() {
		return displayorder;
	}
	public void setDisplayorder(String displayorder) {
		this.displayorder = displayorder;
	}
	
	

}
