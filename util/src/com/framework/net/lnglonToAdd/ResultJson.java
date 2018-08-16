package com.framework.net.lnglonToAdd;

import java.util.List;

/**
 * @Class ResultJson.java
 * @Author 作者姓名:刘兴
 * @Version 1.0
 * @Date 创建时间：2017-5-6 下午12:56:47
 * @Copyright Copyright by 智多星
 * @Direction 类说明				返回的数据格式化
 */

public class ResultJson {
	private String info;
	private String status;
	private String infocode;
	private String count;
	private List<ResultChildJson> geocodes;

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getInfocode() {
		return infocode;
	}

	public void setInfocode(String infocode) {
		this.infocode = infocode;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public List<ResultChildJson> getGeocodes() {
		return geocodes;
	}

	public void setGeocodes(List<ResultChildJson> geocodes) {
		this.geocodes = geocodes;
	}

}
