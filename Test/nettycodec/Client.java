package nettycodec;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.ReferenceCounted;

public class Client {
	private Channel channel = null;

	public void connect(){	
		EventLoopGroup workergroup = new NioEventLoopGroup(1);	
		Bootstrap b = new Bootstrap();
		try {
			b.group(workergroup);
			
			b.channel(NioSocketChannel.class);
			
			b.handler(new ChannelInitializer<SocketChannel>(){
				@Override
				protected void initChannel(SocketChannel socketChannel) throws Exception {
					channel = socketChannel;
					socketChannel.pipeline()
					.addLast(new TankMsgEncoder())
					.addLast(new Hander());
				}
				
			});
			ChannelFuture future = b.connect("localhost",8888).sync();	
			System.out.println("connected to server");
			//等待关闭
			future.channel().closeFuture().sync();
			System.out.println("go on");

		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			workergroup.shutdownGracefully();

		}
	
	}

	public void send(String text) {
		channel.writeAndFlush(Unpooled.copiedBuffer(text.getBytes()));
	}

	public void closeConnection() {
		send("__bye__");
		channel.close();
	}

	public static void main(String[] args) {
		Client client =new Client();
		client.connect();
	}
	
}

class Hander extends ChannelInboundHandlerAdapter{

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		
		ctx.writeAndFlush(new Tankmsg(5, 8));	
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		
		
		ByteBuf buf = null;
		try {
			buf = (ByteBuf)msg;
			byte[] bytes = new byte[buf.readableBytes()];
			buf.getBytes(buf.readerIndex(),bytes);
			String str = new String(bytes);
//			System.out.println(str);
		}finally {
			if(buf!=null)
			ReferenceCountUtil.release(msg);
		}	
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
		super.exceptionCaught(ctx, cause);
	}
}
