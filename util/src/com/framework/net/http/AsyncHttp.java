package com.framework.net.http;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

/****
 * *
 * 类名称：		AsyncHttp.java 
 * 类描述：   		最新版本的 http 请求版本与设置超时信息版本数据
 * 创建人：		
 * 创建时间：		2016-11-16下午4:29:59 
 * 修改人：		liuxing
 * 修改时间：		2016-11-16下午4:29:59 
 * 修改备注：   		
 * @version
 */
public class AsyncHttp {

	//protected static  Logger logger = LoggerFactory.getLogger(AsyncHttp.class);  
    /*protected static CloseableHttpClient httpClient = HttpClients  
            .custom()  
            .setConnectionManager(new PoolingHttpClientConnectionManager())  
            .build();  */
  
    protected static CloseableHttpClient httpClient = HttpClients.createDefault();  
	
    protected static RequestConfig requestConfig = RequestConfig.custom()
    		.setConnectionRequestTimeout(1000)		//连接池获取连接超时1S超时
            .setSocketTimeout(10000)				//服务器响应5S超时
            .setConnectTimeout(5000).build();		//链接建立超时10S超时
}
/***

http://www.open-open.com/lib/view/open1383751765321.html		参考代码处

3.X是这样的

HttpClient httpClient=new DefaultHttpClient();
4.3是这样的
CloseableHttpClient httpClient = HttpClients.createDefault();
当然，上面这些变化只不过是一些小变化，大家看看API大家就都会了。
我要讲的是超时设置,HttpClient有三种超时设置，最近比较忙，没时间具体归纳总结，以后再补上，我这里就讲一些最简单最易用的超时设置方法。

这是个3.X的超时设置方法

HttpClient client = new HttpClient();
client.setConnectionTimeout(30000);  
client.setTimeout(30000);
HttpClient httpClient= new HttpClient();  
httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
4.X版本的超时设置(4.3后已过时)
HttpClient httpClient=new DefaultHttpClient();
httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,2000);//连接时间
httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,2000);//数据传输时间
4.3版本超时设置
CloseableHttpClient httpClient = HttpClients.createDefault();
HttpGet httpGet=new HttpGet("http://www.baidu.com");//HTTP Get请求(POST雷同)
RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();//设置请求和传输超时时间
httpGet.setConfig(requestConfig);
httpClient.execute(httpGet);//执行请求
BTW,4.3版本不设置超时的话，一旦服务器没有响应，等待时间N久(>24小时)。


***/