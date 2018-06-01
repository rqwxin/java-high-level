package bio;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	
	private static int port = 2342;
	private static String host = "127.0.0.1";
	
	public static void main(String[] args) {
		try {
			Socket socket = new Socket(host, port);
			DataInputStream  in = null;
			DataOutputStream out = null;
			try {
				System.out.println("client socket is starting........");
				while(true){
				    in = new DataInputStream(socket.getInputStream());
				    out = new DataOutputStream(socket.getOutputStream());
				    System.out.print("请输入:\t");    
				    String outLine = new Scanner(System.in).nextLine();
				   //out.writeUTF("fuck");
				    out.writeUTF(outLine);
		    		String line = in.readUTF();
		    		System.out.println("服务端："+line);
				}	
			} catch (UnknownHostException e) {
				e.printStackTrace();
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
				
				if(in != null){
					try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
}


class ClientHandle implements Runnable{
	private Socket socket;
	ClientHandle(Socket socket){
		this.socket = socket;
	}
	@Override
	public void run() {
		DataInputStream  in = null;
		DataOutputStream out = null;
		try {
			while(true){
			    in = new DataInputStream(socket.getInputStream());
			    out = new DataOutputStream(socket.getOutputStream());
			    System.out.print("请输入:\t");    
			    String outLine = new Scanner(System.in).nextLine();
			   //out.writeUTF("fuck");
			    out.writeUTF(outLine);
	    		String line = in.readUTF();
	    		System.out.println("服务端："+line);
			}	
		} catch (UnknownHostException e) {
			e.printStackTrace();
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
			
			if(in != null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		
		}
	}
	
}
