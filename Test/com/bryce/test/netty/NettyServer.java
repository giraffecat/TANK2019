package com.bryce.test.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {

	public static void main(String[] args) throws Exception {
		EventLoopGroup bossgroup = new NioEventLoopGroup(2);
		
		EventLoopGroup workergroup = new NioEventLoopGroup(4);
		
		ServerBootstrap b = new ServerBootstrap();
		
		b.group(bossgroup,workergroup);
		
		b.channel(NioServerSocketChannel.class);
		
		b.childHandler(new MyChildInitalizer());
		b.bind(8888).sync();	
	}
	
}

class MyChildInitalizer extends ChannelInitializer<SocketChannel>{
	
	@Override
	protected void initChannel(SocketChannel socketChannel) throws Exception{
		socketChannel.pipeline().addLast(new MyChildHander());
	}
}

class MyChildHander extends ChannelInboundHandlerAdapter{

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf buf = (ByteBuf) msg;
		System.out.println(buf.toString());
		ctx.writeAndFlush(msg);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
		super.exceptionCaught(ctx, cause);
	}
	
}
