package com.cgx.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class ServerHandler extends ChannelHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		/*ByteBuf in = (ByteBuf) msg;
		System.out.println("接收数据");
		 while (in.isReadable()) { // (1)
	              System.out.print((char) in.readByte());
	              System.out.flush();
		 }*/
		String msg1 = (String) msg;
		 System.out.println(msg1);
         System.out.flush();
		 ctx.channel().writeAndFlush(Unpooled.copiedBuffer("我的服务端反馈信息:8888".getBytes()));
			

	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
		super.exceptionCaught(ctx, cause);
	} 
	
}
