package com.framework.net.tcp.muchTheardTCP;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
	
	private final int PORT = 1234;
    private ExecutorService pool;
    private ServerSocketChannel ssc;
    private static Selector selector;
    public static Charset charset = Charset.forName("utf-8");
    private static ByteBuffer buf = ByteBuffer.allocate(1024);
    private int n;
     
    public static void main(String[] args) throws IOException{
        Server server = new Server();
        server.doService();
    }
     
    public Server() throws IOException{
        pool = Executors.newFixedThreadPool(500);
         
        ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ServerSocket ss = ssc.socket();
        ss.bind(new InetSocketAddress(PORT));
        selector = Selector.open();
        ssc.register(selector,SelectionKey.OP_ACCEPT);
        System.out.println("Server started...");
    }
     
    public void doService(){
        while(true){
            try{
                n = selector.select();
            }catch (IOException e) {
                throw new RuntimeException("Selector.select()异常!");
            }
            if(n==0)
                continue;
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iter = keys.iterator();
            while(iter.hasNext()){
                SelectionKey key = iter.next();
                if(key.isAcceptable()){
                	pool.execute(new createThread(key));
                }
                else if(key.isReadable()){
                    key.interestOps(key.interestOps()&(~SelectionKey.OP_READ));
                    pool.execute(new WorkThread(key));
                }
                iter.remove();
            }
        }
    }
    
    public static class createThread implements Runnable{
    	
    	private SelectionKey key;
        public createThread(SelectionKey key){
            this.key = key;
        }
    	
    	@Override
        public void run() {
    		SocketChannel sc = null;
            try{
                sc = ((ServerSocketChannel)key.channel()).accept();
                sc.configureBlocking(false);
                //System.out.println("客户端:"+sc.socket().getInetAddress().getHostAddress()+" 已连接");
                //SelectionKey k = sc.register(selector, SelectionKey.OP_READ);
                //k.attach(buf);
                sc.register(selector, SelectionKey.OP_READ).attach(buf);
            }catch (Exception e) {
                try{
                    sc.close();
                }catch (Exception ex) {
                }
            }    		
    	}
    	
    }

    public static class WorkThread implements Runnable{
    	
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
                   // System.out.println("客户端断开。。。");
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

}