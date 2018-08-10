package com.framework.net.http;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.framework.io.ZipUtils;
import com.framework.vo.Json;
import com.mongodb.io.ZipUtil;


/****
 * *
 * 类名称：		HttpDownload.java 
 * 类描述：   		http文件下载
 * 创建人：		
 * 创建时间：		2015-9-9上午11:45:17 
 * 修改人：		liuxing
 * 修改时间：		2015-9-9上午11:45:17 
 * 修改备注：   		
 * @version
 */
public class HttpDownload {
	
	public static void main(String[] args) {
		//打包
		List<File> files = new ArrayList<File>();
		files.add( new File("D:/a.xls") ) ;
		files.add( new File("D:/b.xls") ) ;
		File file = new File("D:/certpics.rar");
		boolean zipfil = ZipUtils.zipFile( files, file ) ;
		HttpServletResponse response = null ;
		//打包成功---下载
		if( zipfil ){
			downloadZip( file, response );
		}
	}
	
	/***
	 * 单个文件保存进服务器文件管理中心，返回的是文件的URL根路径
	 * @param path					文件要保存的地址
	 * @param file					要保存的文件
	 * @param fileName				要保存文件的文件名称
	 * @return						成功就TRUE,else false
	 */
	public Json fileSave( StringBuffer path , File file , String fileName ){
		Json json = new Json();
		File fileFather =new File( path.toString() );  
		//判断目标文件下是否有项目注册号 这个文件夹，没有就创建
		if( !fileFather.exists() ){
			fileFather.mkdirs();
		}
		//得到文件
		//System.out.println( file.getName() );
		File file1=new File( path.append( "\\" ).append( fileName ).toString() );  
		if( !file1.exists() ){
			file.renameTo( file1 );
			json.setSuccess( true ) ;
		}else{
			file1.delete() ;
			file.renameTo( file1 );
			json.setSuccess( true ) ;
		}
		json.setMsg( path.toString().split("webapps")[1] ) ;
		return json ;
	}
	
	/***
	 * 导出文件
	 * @param filePath   需要下载文件的路径
	 * @param isDelete   是否需要删除文件的路径下的文件
	 * @author liux
	 */
	public void downloadProjectFile( HttpServletRequest request,HttpServletResponse response , String outputName , String filePath,boolean isDelete ){
		InputStream inStream = null;
		OutputStream os = null;
		try {
			// 读到流中
			String newpath = toUtf8String( request , outputName ) ; 
			inStream = new FileInputStream(filePath);// 文件的存放路径
			// 设置输出的格式
			response.reset();
			os = response.getOutputStream();
			response.setContentType("application/x-msdownload;charset=UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename=" + newpath);
			
			// 循环取出流中的数据
			byte[] b = new byte[100];
			int len;
			while ((len = inStream.read(b)) > 0) {
				os.write(b, 0, len);
				len = 0;
			}
			os.flush();
			os.close();
			inStream.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (os != null) {
					os.close();
					os = null;
				}
				if (inStream != null) {
					inStream.close();
					inStream = null;
				}
				/*if( isDelete ){
					File file = new File(filePath);
					if (file.isFile() && file.exists()) {
						file.delete();
					}
				}*/
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	/**
	 * 根据不同浏览器将文件名中的汉字转为UTF8编码的串,以便下载时能正确显示另存的文件名.
	 * @param s 原文件名
	 * @return 重新编码后的文件名
	 */
	public String toUtf8String(HttpServletRequest request, String s) {
		String agent = request.getHeader("User-Agent");
		try {
//			boolean isFireFox = (agent != null && agent.toLowerCase().indexOf(
//					"firefox") != -1);
//			if (isFireFox) {
//				s = new String(s.getBytes("UTF-8"), "ISO8859-1");
//			} else {
				s = toUtf8String(s);
				if ((agent != null && agent.indexOf("MSIE") != -1)) {
					// see http://support.microsoft.com/default.aspx?kbid=816868
					if (s.length() > 150) {
						// 根据request的locale 得出可能的编码
						s = new String(s.getBytes("UTF-8"), "ISO8859-1");
					}
				}
//			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return s ;
	}
	
	/***
	 * 字符串格式转换
	 * @param s
	 * @return
	 */
	public String toUtf8String(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 0 && c <= 255) {
				sb.append(c);
			} else {
				byte[] b;
				try {
					b = Character.toString(c).getBytes("utf-8");
				} catch (Exception ex) {
					//exceptionUtil.error( "将文件名中的汉字转为UTF8编码的串时错误，输入的字符串为：" + s );
					b = new byte[0];
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0)
						k += 256;
					sb.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return sb.toString();
	}

	/***
	 * java后台下载文件
	 * @param file
	 * @param response
	 */
	public static void downloadZip( File file,HttpServletResponse response ) {
		try {
			// 以流的形式下载文件。
			InputStream fis = new BufferedInputStream(new FileInputStream(
					file.getPath()));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();

			OutputStream toClient = new BufferedOutputStream(
					response.getOutputStream());
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ file.getName());
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				File f = new File(file.getPath());
				f.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
