package com.framework.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import org.apache.log4j.Logger;

import com.framework.other.DecodeUtil;

/***
 * *
 * 类名称：		ZipUtils.java 
 * 类描述：   		功能： 1 、实现把指定文件夹下的所有文件压缩为指定文件夹下指定 ZIP文件 2 、实现把指定文件夹下的 ZIP文件解压到指定目录下
 * 创建人：		
 * 创建时间：		2016-10-11上午9:46:42 
 * 修改人：		liuxing
 * 修改时间：		2016-10-11上午9:46:42 
 * 修改备注：   		
 * @version
 */
public class ZipUtils {
	
	private static final Logger logger = Logger.getLogger(ZipUtils.class);
	
	public static final int BUFFER = 1024;// 缓存大小

	/**
	 * ZIP压缩功能. 压缩baseDir(文件夹目录)下所有文件，包括子目录
	 * 
	 * @throws Exception
	 */
	public static void zipFile(String baseDir, String fileName)
			throws Exception {
		List<?> fileList = getSubFiles(new File(baseDir));
		ZipOutputStream zos = new ZipOutputStream(
				new FileOutputStream(fileName));
		ZipEntry ze = null;
		byte[] buf = new byte[BUFFER];
		int readLen = 0;
		for (int i = 0; i < fileList.size(); i++) {
			File f = (File) fileList.get(i);
			ze = new ZipEntry(getAbsFileName(baseDir, f));
			ze.setSize(f.length());
			ze.setTime(f.lastModified());
			zos.putNextEntry(ze);
			InputStream is = new BufferedInputStream(new FileInputStream(f));
			while ((readLen = is.read(buf, 0, BUFFER)) != -1) {
				zos.write(buf, 0, readLen);
			}
			is.close();
		}
		zos.close();
	}
	
	/***
	 * 给定文件，然后给定压缩到哪个文件，返回执行状态
	 * @param files
	 * @param baseDir
	 * @throws Exception
	 */
	public static boolean zipFile(List<File> fileList ,File baseDir ){
		try {
			ZipOutputStream zos = new ZipOutputStream(
					new FileOutputStream( baseDir ));
			ZipEntry ze = null;
			byte[] buf = new byte[BUFFER];
			int readLen = 0;
			for (int i = 0; i < fileList.size(); i++) {
				File f = (File) fileList.get(i);
				ze = new ZipEntry( f.getName() );
				ze.setSize(f.length());
				ze.setTime(f.lastModified());
				zos.putNextEntry(ze);
				InputStream is = new BufferedInputStream(new FileInputStream(f));
				while ((readLen = is.read(buf, 0, BUFFER)) != -1) {
					zos.write(buf, 0, readLen);
				}
				is.close();
			}
			zos.close();
			return true ;
		} catch (FileNotFoundException e) {
			logger.error("指定压缩文件未找到.") ;
			return false ;
		} catch (IOException e) {
			logger.error("Zip压缩异常...") ;
			return false ;
		}
	}

	/**
	 * 解压缩ZIP文件，将ZIP文件里的内容解压到targetDIR目录下
	 * 
	 * @param zipName
	 *            待解压缩的ZIP文件名
	 * @param targetBaseDirName
	 *            目标目录
	 * @throws IOException
	 */
	public static void upZipFile(String zipFileName, String targetBaseDirName)
			throws IOException {
		if (!targetBaseDirName.endsWith(File.separator)) {
			targetBaseDirName += File.separator;
		}
		// 根据ZIP文件创建ZipFile对象
		ZipFile zipFile = new ZipFile(zipFileName);
		ZipEntry entry = null;
		String entryName = null;
		String targetFileName = null;
		byte[] buffer = new byte[BUFFER];
		int bytes_read;
		// 获取ZIP文件里所有的entry
		Enumeration<?> entrys = zipFile.entries();
		// 遍历所有entry
		while (entrys.hasMoreElements()) {
			entry = (ZipEntry) entrys.nextElement();
			// 获得entry的名字
			entryName = entry.getName();
			targetFileName = targetBaseDirName + entryName;
			if (entry.isDirectory()) {
				// 如果entry是一个目录，则创建目录
				new File(targetFileName).mkdirs();
				continue;
			} else {
				// 如果entry是一个文件，则创建父目录
				new File(targetFileName).getParentFile().mkdirs();
			}

			// 否则创建文件
			File targetFile = new File(targetFileName);
			// 打开文件输出流
			FileOutputStream os = new FileOutputStream(targetFile);
			// 从ZipFile对象中打开entry的输入流
			InputStream is = zipFile.getInputStream(entry);
			while ((bytes_read = is.read(buffer)) != -1) {
				os.write(buffer, 0, bytes_read);
			}
			// 关闭流
			os.close();
			is.close();
		}
	}

	/**
	 * 给定根目录，返回另一个文件名的相对路径，用于zip文件中的路径.
	 * 
	 * @param baseDir
	 *            java.lang.String 根目录
	 * @param realFileName
	 *            java.io.File 实际的文件名
	 * @return 相对文件名
	 */
	private static String getAbsFileName(String baseDir, File realFileName) {
		File real = realFileName;
		File base = new File(baseDir);
		String ret = real.getName();
		while (true) {
			real = real.getParentFile();
			if (real == null)
				break;
			if (real.equals(base))
				break;
			else
				ret = real.getName() + "/" + ret;
		}
		return ret;
	}

	/**
	 * 取得指定目录下的所有文件列表，包括子目录.
	 * 
	 * @param baseDir
	 *            File 指定的目录
	 * @return 包含java.io.File的List
	 */
	private static List<File> getSubFiles(File baseDir) {
		List<File> ret = new ArrayList<File>();
		File[] tmp = baseDir.listFiles();
		for (int i = 0; i < tmp.length; i++) {
			if (tmp[i].isFile())
				ret.add(tmp[i]);
			if (tmp[i].isDirectory())
				ret.addAll(getSubFiles(tmp[i]));
		}
		return ret;
	}

	public static void main(String[] args) {
		// 打包
		List<File> files = new ArrayList<File>();
		files.add(new File("D:/a.xls"));
		files.add(new File("D:/b.xls"));
		File file = new File("D:/certpics.rar");
		boolean zipfil = ZipUtils.zipFile(files, file);
	}
	
}
