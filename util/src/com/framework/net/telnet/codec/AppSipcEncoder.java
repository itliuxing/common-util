package com.framework.net.telnet.codec;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import com.framework.net.telnet.business.UserLoginParam;

/***
 * *
 * 类名称：		AppSipcEncoder.java 
 * 类描述：   		真实业务的编码器
 * 创建人：		
 * 创建时间：		2016-10-12下午3:18:20 
 * 修改人：		liuxing
 * 修改时间：		2016-10-12下午3:18:20 
 * 修改备注：   		
 * @version
 */
public class AppSipcEncoder extends ProtocolEncoderAdapter {
	private final Charset charset;

	public AppSipcEncoder(Charset charset) {
		this.charset = charset;
	}

	@Override
	public void encode(IoSession session, Object message,
			ProtocolEncoderOutput out) throws Exception {
		UserLoginParam sms = (UserLoginParam) message;
		CharsetEncoder ce = charset.newEncoder();
		IoBuffer buffer = IoBuffer.allocate(100).setAutoExpand(true);
		String statusLine = "M sip:wap.fetion.com.cn SIP-C/2.0";

		String sender = sms.getN();
		String receiver = sms.getP();
		String smsContent = sms.getP();
		buffer.putString(statusLine + "/n", ce);
		buffer.putString("S: " + sender + "/n", ce);
		buffer.putString("R: " + receiver + "/n", ce);
		buffer.putString("L: " + (smsContent.getBytes(charset).length) + "/n",
				ce);
		buffer.putString(smsContent, ce);
		buffer.flip();
		out.write(buffer);
	}
}
