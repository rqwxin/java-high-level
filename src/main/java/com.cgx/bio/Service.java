package com.cgx.bio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Service {
	
	private static int port = 2342;
	
	public static void main(String[] args) {
		ServerSocket server = null;
		try {
			 server = new ServerSocket(port);
			 System.out.println("serversocket is starting......");
			 while(true){
				 Socket socket =  server.accept();
				 System.out.println("有连接");
				 new Thread( new ServerHander(socket)).start();
			 }
			 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	static class ServerHander implements Runnable {
		
		private Socket socket ;
		
		public ServerHander(Socket socket){
			this.socket = socket;
		}
		@Override
		public void run() {
			BufferedReader br = null;
			DataInputStream in = null;
			DataOutputStream out = null;
			try {
				while(true){
					in = new DataInputStream(socket.getInputStream());
					out = new DataOutputStream(socket.getOutputStream());
					String line = in.readUTF();
					System.out.println("客户端："+line);
					System.out.print("请输入:\t");    
					// 键盘录入    
					br = new BufferedReader(new InputStreamReader(System.in));  
					String outLine = br.readLine();
					out.writeUTF(outLine);
					out.flush();
				}	
			} catch (IOException e) {
				e.printStackTrace();
				if(socket !=null){
					try {
						socket.close();
					} catch (IOException e3) {
						e.printStackTrace();
					}
				}
			}finally{
				if(br != null){
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
			}
		}
	}

}
