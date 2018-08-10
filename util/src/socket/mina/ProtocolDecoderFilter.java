package socket.mina;

import java.nio.charset.Charset;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

/***
 * *
 * 类名称：		ProtocolDecoderFilter.java 
 * 类描述：   		协议过滤器
 * 创建人：		
 * 创建时间：		2015-5-6下午2:25:30 
 * 修改人：		liuxing
 * 修改时间：		2015-5-6下午2:25:30 
 * 修改备注：   		实现ProtocolDecoder接口，覆盖decode()方法
 * @version
 */
public class ProtocolDecoderFilter implements ProtocolDecoder {

	private Charset charset = Charset.forName("UTF-8");

	IoBuffer buf = IoBuffer.allocate(100).setAutoExpand(true);

	public void decode(IoSession session, IoBuffer in, ProtocolDecoderOutput out)
			throws Exception {
		while (in.hasRemaining()) {
			byte b = in.get();
			buf.put(b);
			if (b == '\n') {
				buf.flip();
				byte[] msg = new byte[buf.limit()];
				buf.get(msg);
				String message = new String(msg, charset);
				//解码成功，把buf重置
				buf = IoBuffer.allocate(100).setAutoExpand(true);
				out.write(message);
			}
		}

	}

	public void dispose(IoSession session) throws Exception {
	}

	public void finishDecode(IoSession session, ProtocolDecoderOutput out)
			throws Exception {
	}
	
}
