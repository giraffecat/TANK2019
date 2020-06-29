package nettycodec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class TankMsgEncoder extends MessageToByteEncoder<Tankmsg> {

	@Override
	protected void encode(ChannelHandlerContext ctx, Tankmsg tankmsg, ByteBuf byteBuf) throws Exception {
		byteBuf.writeInt(tankmsg.x);
		byteBuf.writeInt(tankmsg.y);
	}

	
}
