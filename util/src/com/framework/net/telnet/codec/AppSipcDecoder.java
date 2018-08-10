package com.framework.net.telnet.codec;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import com.framework.net.telnet.business.UserLoginParam;

/****
 * *
 * 类名称：		APPSipcDecoder.java 
 * 类描述：   		真实业务的解码器
 * 创建人：		
 * 创建时间：		2016-10-12下午3:13:27 
 * 修改人：		liuxing
 * 修改时间：		2016-10-12下午3:13:27 
 * 修改备注：   		
 * @version
 */
public class AppSipcDecoder extends CumulativeProtocolDecoder {
	private final Charset charset;

	public AppSipcDecoder(Charset charset) {
		this.charset = charset;
	}

	@Override
	protected boolean doDecode(IoSession session, IoBuffer in,
			ProtocolDecoderOutput out) throws Exception {
		IoBuffer buffer = IoBuffer.allocate(100).setAutoExpand(true);
		CharsetDecoder cd = charset.newDecoder();
		int matchCount = 0;
		String statusLine = "", sender = "", receiver = "", length = "", sms = "";
		int i = 1;

		//这一步获取到十六进制的数据进行测试
		System.out.println( in.getHexDump() );
		System.out.println( ioBufferToString(in) );
		
		while (in.hasRemaining()) {
			byte b = in.get();
			buffer.put(b);
			if (b == 10 && i < 5) {
				matchCount++;
				if (i == 1) {
					buffer.flip();
					statusLine = buffer.getString(matchCount, cd);
					statusLine = statusLine.substring(0,
							statusLine.length() - 1);
					matchCount = 0;
					buffer.clear();
				}
				if (i == 2) {
					buffer.flip();
					sender = buffer.getString(matchCount, cd);
					sender = sender.substring(0, sender.length() - 1);
					matchCount = 0;
					buffer.clear();
				}
				if (i == 3) {
					buffer.flip();
					receiver = buffer.getString(matchCount, cd);
					receiver = receiver.substring(0, receiver.length() - 1);
					matchCount = 0;
					buffer.clear();
				}
				if (i == 4) {
					buffer.flip();
					length = buffer.getString(matchCount, cd);
					length = length.substring(0, length.length() - 1);
					matchCount = 0;
					buffer.clear();
				}
				i++;
			} else if (i == 5) {
				matchCount++;
				if (matchCount == Long.parseLong(length.split(": ")[1])) {
					buffer.flip();
					sms = buffer.getString(matchCount, cd);
					i++;
					break;
				}
			} else {
				matchCount++;
			}
		}
		UserLoginParam smsObject = new UserLoginParam();
		//smsObject.setN(sender.split(": ")[1]);
		//smsObject.setP(receiver.split(": ")[1]);
		out.write(smsObject);
		return false;
	}
	
	public static String ioBufferToString(Object message) {
		if (!(message instanceof IoBuffer)) {
			return "";
		}
		IoBuffer ioBuffer = (IoBuffer) message;
		byte[] b = new byte[ioBuffer.limit()];
		ioBuffer.get(b);
		StringBuffer stringBuffer = new StringBuffer();

		for (int i = 0; i < b.length; i++) {

			stringBuffer.append((char) b[i]);
		}
		return stringBuffer.toString();
	}
}