package com.framework.net.telnet.codec;

import java.nio.charset.Charset;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

/***
 * *
 * 类名称：		APPCodecFactory.java 
 * 类描述：   		项目自己实现一个自己的编解码器工厂
 * 创建人：		
 * 创建时间：		2016-10-12下午3:08:56 
 * 修改人：		liuxing
 * 修改时间：		2016-10-12下午3:08:56 
 * 修改备注：   		
 * @version
 */
public class AppSipcCodecFactory implements ProtocolCodecFactory { 
	
	private final AppSipcEncoder encoder;    
	private final AppSipcDecoder decoder;    
	public AppSipcCodecFactory() {    
		this(Charset.defaultCharset());    
	}    
	public AppSipcCodecFactory(Charset charSet) {    
		this.encoder = new AppSipcEncoder(charSet);    
		this.decoder = new AppSipcDecoder(charSet);    
	}    

	@Override    
	public ProtocolDecoder getDecoder(IoSession session) throws    
	Exception {    
		return decoder;    
	}    
	@Override    
	public ProtocolEncoder getEncoder(IoSession session) throws    
	Exception {    
		return encoder;    
	}    
	
}
