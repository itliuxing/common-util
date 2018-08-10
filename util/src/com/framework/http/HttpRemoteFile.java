package com.framework.http;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRemoteFile {
	
	public static void main(String[] args) throws Exception {

        downRemoteFile(
                "https://image.baidu.com/search/down?tn=download&ipn=dwnl&word=download&ie=utf8&fr=result&url=http%3A%2F%2Fh.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2F060828381f30e9240ff2cd434c086e061d95f76a.jpg&thumburl=https%3A%2F%2Fss2.bdstatic.com%2F70cFvnSh_Q1YnxGkpoWK1HF6hhy%2Fit%2Fu%3D2504031429%2C1727248259%26fm%3D26%26gp%3D0.jpg",
                "sssss.jpg", "D:/sss");

    }
	
	/**
     * @Name: downRemoteFile。
     * @Description: 下载远程文件。
     * @Parameters: remoteFileUrl，要下载的远程文件地址。
     * @Parameters: saveFileName，下载后保存的文件名。
     * @Parameters: saveDir，下载后保存的文件路径。
     * @Return: String，文件保存的地址。
     * @Author: PeiFeng
     * @Version: V1.00
     * @Create Date: 2017-8-12
     */
    public static String downRemoteFile(String remoteFileUrl, String saveFileName, String saveDir) {

        HttpURLConnection conn = null;
        OutputStream oputstream = null;
        InputStream iputstream = null;

        try {
            // 创建保存文件的目录
            File savePath = new File(saveDir);
            if (!savePath.exists()) {
                savePath.mkdir();
            }
            // 创建保存的文件
            File file = new File(savePath + "/" + saveFileName);
            if (file != null && !file.exists()) {
                file.createNewFile();
            }

            URL url = new URL(remoteFileUrl);
            // 将url以open方法返回的urlConnection连接强转为HttpURLConnection连接(标识一个url所引用的远程对象连接)
            // 此时cnnection只是为一个连接对象,待连接中
            conn = (HttpURLConnection) url.openConnection();
            // 设置是否要从 URL连接读取数据,默认为true
            conn.setDoInput(true);
            // 建立连接
            // (请求未开始,直到connection.getInputStream()方法调用时才发起,以上各个参数设置需在此方法之前进行)
            conn.connect();
            // 连接发起请求,处理服务器响应 (从连接获取到输入流)
            iputstream = conn.getInputStream();
            // 创建文件输出流，用于保存下载的远程文件
            oputstream = new FileOutputStream(file);
            //  用来存储响应数据
            byte[] buffer = new byte[4 * 1024];
            int byteRead = -1;
            //  循环读取流
            while ((byteRead = (iputstream.read(buffer))) != -1) {
                oputstream.write(buffer, 0, byteRead);
            }
            //  输出完成后刷新并关闭流
            oputstream.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                //  重要且易忽略步骤 (关闭流,切记!)
                if (iputstream != null) {
                    iputstream.close();
                }
                if (oputstream != null) {
                    oputstream.close();
                }
                // 销毁连接
                if (conn != null) {
                    conn.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 返回保存后的文件路径
        return saveDir + "/" + saveFileName;
    }

}
