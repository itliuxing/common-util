package com.framework.net.tcp.muchTheardTCP;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class WorkThread implements Runnable{
	
	private SelectionKey key;
    public WorkThread(SelectionKey key){
        this.key = key;
    }
     
    @Override
    public void run() {
        SocketChannel sc = (SocketChannel)key.channel();
        ByteBuffer buf = (ByteBuffer)key.attachment();
        buf.clear();
        int len = 0;
        try{
            while((len=sc.read(buf))>0){//非阻塞，立刻读取缓冲区可用字节
                buf.flip();
                System.out.println("客户端："+Server.charset.decode(buf).toString());
                buf.clear();
            }
            if(len==-1){
                System.out.println("客户端断开。。。");
                sc.close();
            }
            //没有可用字节,继续监听OP_READ
            key.interestOps(key.interestOps()|SelectionKey.OP_READ);
            key.selector().wakeup();
        }catch (Exception e) {
            try {
                sc.close();
            } catch (IOException e1) {
            }
        }
    }

}
