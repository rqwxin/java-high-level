package com.cgx.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;


public class ServerTest {
	
	private static Selector selector ;
	
	public static void main(String[] args) {
		Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						start();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  
				}
			});
		t.start();
	
	}

	private static void start() throws IOException, ClosedChannelException {
		int port = 1234;
		startSelector(port);  
		while(true){
			selector.select();
			Set<SelectionKey> keys = selector.selectedKeys();
			Iterator<SelectionKey> it = keys.iterator();  
			SelectionKey key = null;  
			while(it.hasNext()){  
				key = it.next();  

				it.remove(); 
				if(key.isValid()){
					handleInput(key);  
				}
			}
			
		}
	}

	private static void handleInput(SelectionKey key) throws IOException {
		if(key.isAcceptable()){
			ServerSocketChannel ssc =  (ServerSocketChannel) key.channel();
			SocketChannel sc = ssc.accept();
			sc.configureBlocking(false);
			sc.register(selector, SelectionKey.OP_READ);
		}
			if(key.isReadable()){
				SocketChannel sc = (SocketChannel) key.channel();
				SocketChannel sch =  (SocketChannel) key.channel();
				ByteBuffer buffer = ByteBuffer.allocate(1024);
				int readBytes = sc.read(buffer);
				if(readBytes>0){
					buffer.flip();  
					//根据缓冲区可读字节数创建字节数组  
					byte[] bytes = new byte[buffer.remaining()]; 
					buffer.get(bytes);  
					String expression = new String(bytes,"UTF-8");  
					System.out.println("服务器收到消息：" + expression); 
				}
				String response = "发送消息到客户端";
				byte[] bytes = response.getBytes();  
				//ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
				//writeBuffer.put(bytes);
				//writeBuffer.flip();
				//sch.write(writeBuffer);
			}
			
	} 

	private static void startSelector(int port) throws IOException, ClosedChannelException {
		selector = Selector.open();
		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		serverChannel.configureBlocking(false);
		serverChannel.socket().bind(new InetSocketAddress(port),1024);
		serverChannel.register(selector, SelectionKey.OP_ACCEPT); 
		
		System.out.println("服务器已启动，端口号：" + port);
	}

}
