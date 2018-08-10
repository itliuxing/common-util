package com.framework.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/****
 * *
 * 类名称：		FileOutStream.java 
 * 类描述：   		文件下载----http 文件流写出去
 * 创建人：		
 * 创建时间：		2016-9-1下午5:35:25 
 * 修改人：		liuxing
 * 修改时间：		2016-9-1下午5:35:25 
 * 修改备注：   		
 * @version
 */
public class FileDownloadOutStream {
	
	private static Logger log = Logger.getLogger( FileDownloadOutStream.class ) ;
	
	/****
	 * Excel文件的下载方法
	 * @param response	写出文件流
	 * @param fileName	下载出去的文件名（）
	 * @param filePath	文件在本机的路径
	 * @param fileType	下载文件的文件类型
	 * @param isDelete	下载完后是否需要删除原始文件
	 */
	public static void excelDownlaod( HttpServletResponse response, String fileName, 
			String defaultPath ,String fileType ,boolean isDelete) {		
		InputStream is = null;
		OutputStream os = null;
		File file = null;
		try {
			if ( defaultPath != null && new File(defaultPath.toString()).exists()) {
				file = new File( defaultPath.toString() );
				byte[] b = new byte[16000];
				int i = 0;
				is = new FileInputStream(file.getAbsolutePath());
				os = response.getOutputStream();
				response.setContentType("application/x-msdownload");
				response.setHeader(	"Content-Disposition", "attachment; filename="
								+ new String( fileName.toString().getBytes("gb2312"),
										"ISO-8859-1") + fileType );		//".xls"
				while ((i = is.read(b)) != -1) {
					os.write(b, 0, i);
					i = 0;
				}
				os.flush();
			}
		} catch (Exception e) {
			log.error( "文件不存在:" + e.getMessage() );
		} finally {
			try {
				os.close();
				is.close();
				if ( file.exists() && isDelete ) {
					file.delete();
				}
			} catch (Exception e) {
			}
		}
	}

}
