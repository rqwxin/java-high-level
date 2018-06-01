package com.cgx.netty;




import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class Server {
	
	private int port;
	
	public Server(int port){
		this.port=port;
	}
	
	public void run() throws Exception{
		//用来接收进来的连接
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		//处理已经被接收的连接
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
		//启动NIO服务的辅助启动
		ServerBootstrap b = new ServerBootstrap();
		//
		b.group(bossGroup, workerGroup)
		//指定channel
		 .channel(NioServerSocketChannel.class) 
		 .childHandler(new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
					//事件处理类
				//定义消息分割符
				ByteBuf delimiter = Unpooled.copiedBuffer("$".getBytes());
				ch.pipeline().addLast(new DelimiterBasedFrameDecoder(100, delimiter));
				//以字符编码接收消息
				ch.pipeline().addLast(new StringDecoder());
				ch.pipeline().addLast(new ServerHandler());
			}
		 } )
		 .option(ChannelOption.SO_BACKLOG, 128)
		 //保持心跳
		 .childOption(ChannelOption.SO_KEEPALIVE, true)
		 ;
		//启动服务
		ChannelFuture f = b.bind(port).sync();
		f.channel().closeFuture().sync();
		} finally {
			 workerGroup.shutdownGracefully();
			   bossGroup.shutdownGracefully();

		}
	}
	
	public static void main(String[] args) {
		 try {
			 
			 System.out.println("开始测试netty");
			new Server(1256).run();
			System.out.println("结束测试netty");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
