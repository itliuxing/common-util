package com.framework.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;

/**
 * @Class 	FileUtils.java
 * @Author 	作者姓名:刘兴 
 * @Version	1.0
 * @Date	创建时间：2017-9-13 下午4:03:22
 * @Copyright Copyright by 智多星
 * @Direction 类说明
 */

public class FileUtils {
	
	/**
     * 删除空目录
     * @param dir 将要删除的目录路径
     */
    private static void doDeleteEmptyDir(String dir) {
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
    private static boolean deleteDir(File dir) {
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

}
