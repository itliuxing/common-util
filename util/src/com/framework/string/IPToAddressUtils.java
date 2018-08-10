package com.framework.string;

import java.nio.charset.Charset;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.framework.http.getpost.HttpReqUtil;
import com.framework.net.iptoaddr.IpToAddr;
import com.framework.net.iptoaddr.RequestUtils;

/**
 * 根据IP地址获取详细的地域信息 淘宝API :
 * http://ip.taobao.com/service/getIpInfo.php?ip=218.192.3.42 新浪API :
 * http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip=218.192.3.42
 * 
 * @Class IPToAddressUtils.java
 * 
 * @Author 作者姓名:Liuxing
 * @Version 1.0
 * @Date 创建时间：2018年8月10日 上午11:46:43
 * @Copyright Copyright by Liuxing
 * @Direction 类说明
 */
public class IPToAddressUtils {

	// 淘宝IP获取对应的服务所在的信息
	private final static String TaoBaoURL = "http://ip.taobao.com/service/getIpInfo.php?ip=";

	public static Logger requestLog = Logger.getLogger(RequestUtils.class);

	public static void main(String[] args) throws Exception {
		getAddressByIp();

	}

	@SuppressWarnings("unchecked")
	public static IpToAddr getAddressByIp() throws Exception {
		// 参数ip
		String ip = "221.235.44.19";

		HttpResponse httpResponse = HttpReqUtil.getObjectReq(TaoBaoURL + ip, null); // getObjectReq
		String resultStr = "";
		IpToAddr result = null;
		if (httpResponse != null) {
			// 请求得到响应后，分析只有200的时候才去分析得到的数据，否认都任务失败
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				try {
					result = (IpToAddr) httpResponse.getEntity();
				} catch (Exception e) {
					resultStr = EntityUtils.toString(httpResponse.getEntity(), Charset.forName("UTF-8"));
					// 将返回的数据解析成一个字符串
					requestLog.info(ip + "==IP==解析出来的STR.\n" + resultStr);

					JSONObject jsonObject = JSONObject.parseObject(resultStr);
					// json对象转Map
					Map<String, Object> map = (Map<String, Object>) jsonObject;
					map = (Map<String, Object>) map.get("data");
					result = (IpToAddr) JSONObject.parseObject(resultStr, IpToAddr.class);
				}
			}
		}
		return result;
	}

}
