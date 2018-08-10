package com.framework.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @Class 	FileHelper.java
 * @Author 	作者姓名:刘兴 
 * @Version	1.0
 * @Date	创建时间：2017-9-7 下午3:46:15
 * @Copyright Copyright by 智多星
 * @Direction 类说明
 */

public class FileHelper {
	
	
	/***
	 * 
	 * @param sourceFile		源文件夹
	 * @param targetFile		目标文件夹
	 * @throws IOException
	 */
	public static void copyFile( String sourceFile, String targetFile) throws IOException{
		List<File> list = Arrays.asList(new File( sourceFile ).listFiles());
        File file2 = new File( targetFile );
        file2.mkdirs();
        /*
         	请使用:JDK 1.8
        list.forEach((x) -> {
            File fs = x;
            try {
                Files.copy(Paths.get(fs.getAbsolutePath()), Paths.get(file2.getAbsolutePath() + "\\" + fs.getName()), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });*/
        //请使用:JDK 1.7 nio特性操作
        for (int i = 0; i < list.size(); i++) {
            File fs = list.get(i);            
            Files.copy(Paths.get(fs.getAbsolutePath()), Paths.get(file2.getAbsolutePath() + "\\" + fs.getName()), StandardCopyOption.REPLACE_EXISTING);
            if( fs.isDirectory() ){
            	copyFile( new StringBuffer( sourceFile ).append("\\").append( fs.getName() ).toString(), 
            			new StringBuffer( targetFile ).append("\\").append( fs.getName() ).toString() ) ;
            }
        }
	}

	/**
     * 删除空目录
     * @param dir 将要删除的目录路径
     */
	public static void doDeleteEmptyDir(String dir) {
        boolean success = (new File(dir)).delete();
        if (success) {
            System.out.println("Successfully deleted empty directory: " + dir);
        } else {
            System.out.println("Failed to delete empty directory: " + dir);
        }
    }

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful.
     *                 If a deletion fails, the method stops attempting to
     *                 delete and returns "false".
     */
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }
    
    /***
     * 寻找文件夹内版本最大的那个文件名
     * @param baseDir
     * @return
     */
    public static int findMaxVersion( File baseDir){
    	String[] children = baseDir.list();
    	List<Integer> dirFile = new ArrayList<Integer>();
        //递归删除目录中的子目录下
        for (int i=0; i<children.length; i++) {
        	File file = new File( baseDir, children[i] ) ;
        	if ( !file.isDirectory() ) {
        		try {
					dirFile.add( Integer.parseInt( getFileNameNoEx(file.getName() ) ) ) ;
				} catch (NumberFormatException e) {}
            }
        }
        if( dirFile.size() < 1 ){
        	return 0 ;
        }else{
        	Collections.sort( dirFile ) ;
        	return dirFile.get( dirFile.size() -1 );
        }
    }
    
    /***
     * 寻找文件夹内版本最大的那个文件名
     * @param baseDir
     * @return
     */
    public static File findMaxVersionFile( File baseDir){
    	String[] children = baseDir.list();
    	List<Integer> dirFile = new ArrayList<Integer>();
        //递归删除目录中的子目录下
        for (int i=0; i<children.length; i++) {
        	File file = new File( baseDir, children[i] ) ;
        	if ( !file.isDirectory() ) {
        		try {
					dirFile.add( Integer.parseInt( getFileNameNoEx(file.getName() ) ) ) ;
				} catch (NumberFormatException e) {}
            }
        }
        if( dirFile.size() < 1 ){
        	return null ;
        }else{
        	Collections.sort( dirFile ) ;
        	return new File( baseDir, dirFile.get( dirFile.size() -1 ) + ".html" ) ;
        }
    }
    
    /***
     * Java文件操作 获取文件扩展名
     * @param filename
     * @return
     */
	public static String getExtensionName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1);
			}
		}
		return filename;
	}

	/***
	 * Java文件操作 获取不带扩展名的文件名
	 * @param filename
	 * @return
	 */
	public static String getFileNameNoEx(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length()))) {
				return filename.substring(0, dot);
			}
		}
		return filename;
	}
	
	/****
	 * 修改文件名，在同文件夹下
	 * @param oldFile
	 * @param newFileName
	 * @return
	 */
	public static boolean fileRename( File oldFile ,String newFileName ){
		String c = oldFile.getParent();   
        File newFile = new File ( new StringBuffer(c).append("/").append( newFileName).toString() );   
        if( oldFile.renameTo(newFile) ){   
        	return true ;
        }else{   
        	return false ;
        }  
	}
	
	/***
	 * 
	 * @param oldFile
	 * @param newFile
	 * @return
	 * @throws Exception
	 */
	public static long forTransfer(File oldFile,File newFile) throws Exception{
        long time=new Date().getTime();
        int length=2097152;
        FileInputStream in=new FileInputStream(oldFile);
        FileOutputStream out=new FileOutputStream(newFile);
        FileChannel inC=in.getChannel();
        FileChannel outC=out.getChannel();
        int i=0;
        while(true){
            if(inC.position()==inC.size()){
                inC.close();
                outC.close();
                return new Date().getTime()-time;
            }
            if((inC.size()-inC.position())<20971520)
                length=(int)(inC.size()-inC.position());
            else
                length=20971520;
            inC.transferTo(inC.position(),length,outC);
            inC.position(inC.position()+length);
            i++;
        }
    }
}
