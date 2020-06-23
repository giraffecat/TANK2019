package com.bryce.test.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {

	public static void main(String[] args) throws Exception {
		
		EventLoopGroup workergroup = new NioEventLoopGroup(1);
		
		Bootstrap b = new Bootstrap();
		
		b.group(workergroup);
		
		b.channel(NioSocketChannel.class);
		
		b.handler(new ChannelInitializer<SocketChannel>(){

			@Override
			protected void initChannel(SocketChannel socketChannel) throws Exception {
				socketChannel.pipeline().addLast(new MyHander());
			}
			
		});
		b.connect("localhost",8888).sync();	
		System.in.read();
		workergroup.shutdownGracefully();
	}
	
}

class MyHander extends ChannelInboundHandlerAdapter{

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		
		ByteBuf buf = Unpooled.copiedBuffer("bryce".getBytes());
		ctx.writeAndFlush(buf);	
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println(msg.toString());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
		super.exceptionCaught(ctx, cause);
	}
	
}
