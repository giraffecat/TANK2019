package com.bryce.Nettychat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.GlobalEventExecutor;

public class NettyServer {
	
	public static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

	public static void main(String[] args) throws Exception {
		EventLoopGroup bossgroup = new NioEventLoopGroup(2);
		
		EventLoopGroup workergroup = new NioEventLoopGroup(4);
		
		ServerBootstrap b = new ServerBootstrap();
		
		b.group(bossgroup,workergroup);
		
		b.channel(NioServerSocketChannel.class);
		
		b.childHandler(new MyChildInitalizer());
		ChannelFuture future = b.bind(8888).sync();
		
		future.channel().closeFuture().sync();
		
		bossgroup.shutdownGracefully();
		workergroup.shutdownGracefully();
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
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("拿到channel");
		NettyServer.clients.add(ctx.channel());
	}
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf buf = (ByteBuf) msg;
		byte[] bytes = new byte[buf.readableBytes()];
		buf.getBytes(buf.readerIndex(),bytes);
		String str = new String(bytes);
		if(str.equals("__bye__")) {
			ctx.writeAndFlush(msg);
			NettyServer.clients.remove(ctx.channel());
			ctx.close();
		}else		
		NettyServer.clients.writeAndFlush(msg);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		NettyServer.clients.remove(ctx.channel());
		ctx.close();
	}
	
}
