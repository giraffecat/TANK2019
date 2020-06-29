package nettycodec;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;

public class TankmsgTest {
	
	@Test
	public void decode() {
		EmbeddedChannel ecChannel = new EmbeddedChannel();
				
		ecChannel.pipeline().addLast(new TankMsgDecoder());
				
				
		ByteBuf buf = Unpooled.buffer();
		buf.writeInt(5);
		buf.writeInt(8);
		ecChannel.writeInbound(buf);
		
		Tankmsg tm = ecChannel.readInbound();
		

		assertEquals(5, tm.x);
		assertEquals(8, tm.y);

	}
	
	@Test
	public void Encode() {
		EmbeddedChannel ecChannel = new EmbeddedChannel();
		
		ecChannel.pipeline().addLast(new TankMsgEncoder());
		Tankmsg tm = new Tankmsg(5, 8);
		
		ecChannel.writeOutbound(tm);
		
		ByteBuf buf = ecChannel.readOutbound();
		
		int x = buf.readInt();
		int y = buf.readInt();
		
		assertEquals(5, x);
		assertEquals(8, y);

	}

}
