package ldy.netty.util;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class CommHandler extends ChannelHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

		System.out.println("CommHandler");
//		ByteBuf buf = (ByteBuf) msg;
//		System.out.println("message length:"+buf.array().length);
		ctx.fireChannelRead(msg);
	}
}
