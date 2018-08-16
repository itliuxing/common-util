package com.framework.net.lnglonToAdd;

/**
 * @Class 	ResultChildJson.java
 * @Author 	作者姓名:刘兴 
 * @Version	1.0
 * @Date	创建时间：2017-5-8 上午10:29:00
 * @Copyright Copyright by 智多星
 * @Direction 类说明			返回的数据格式化子项也是最终取数据的地方
 */

public class ResultChildJson {
	private String formatted_address; // 格式化后的地址
	private String province; // 省
	private String city; // 市
	private String district; // 区
	private String citycode; // 城市编号
	private String location; // 经纬度

	public String getFormatted_address() {
		return formatted_address;
	}

	public void setFormatted_address(String formatted_address) {
		this.formatted_address = formatted_address;
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

	public String getCitycode() {
		return citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
