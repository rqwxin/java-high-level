package com.cgx.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class ClientTest {
	
	private static Selector selector;  
    private static SocketChannel socketChannel;  
    
    public ClientTest(String ip,int port){
    	try {
			selector = Selector.open();
			socketChannel = SocketChannel.open();
			//socketChannel.configureBlocking(false);//开启非阻塞模式  
			//创建连接的地址
			InetSocketAddress address = new InetSocketAddress(ip,port);
			socketChannel.connect(address);
			System.out.println("连接"+ip+",端口:"+port);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	public static void main(String[] args) {
		
			ClientTest c = new ClientTest("127.0.0.1", 1234);
				try {
					c.start();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
	}
	
	public static void start() throws IOException{
            	   
            	   while(true){
            		 //定义一个字节数组，然后使用系统录入功能：
	       				byte[] bytes = new byte[1024];
	       				System.in.read(bytes);
	       				String reqStr = new String(bytes,"UTF-8");
	       				if("exit".equals(reqStr)){
	       					break;
	       				}
	       				if(!socketChannel.isConnected()){
	       					System.err.println("未建立连接");
	       				}
	           	        //根据数组容量创建ByteBuffer  
	           	        ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);  
	           	        //将字节数组复制到缓冲区  
	           	        writeBuffer.put(bytes);  
	           	        //flip操作  
	           	        writeBuffer.flip();  
	           	        //发送缓冲区的字节数组  
	           	       socketChannel.write(writeBuffer);
		           	  //清空缓冲区数据
		           	    writeBuffer.clear();
            	   }
		   }
}
