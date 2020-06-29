package nettycodec;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class TankMsgDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext arg0, ByteBuf byteBuf, List<Object> out) throws Exception {
		if(byteBuf.readableBytes()<8) return;
		
		int x = byteBuf.readInt();
		int y = byteBuf.readInt();
		
		out.add(new Tankmsg(x,y));

	}

}
