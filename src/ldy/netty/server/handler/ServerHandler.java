package ldy.netty.server.handler;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import ldy.eqsys.msg.BaseMsg;
import ldy.eqsys.msg.CtrlCommandRspMsg;
import ldy.eqsys.msg.RegMsg;
import ldy.netty.client.handler.NettyClientMap;
import ldy.netty.util.CommHandler;
import ldy.netty.util.DataBuilder;

public class ServerHandler extends ChannelHandlerAdapter {

	/**
	 * 链路创建成功后调用，在channelregister之后
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channel active");
	}
	
	/**
	 * 自定义业务处理handler
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

		BaseMsg revMsg = (BaseMsg) msg;
		String msgType = revMsg.getMsgType();
		
		if(msgType.equals("RE")){   //注册包
			
			
			RegMsg regMsg = (RegMsg)msg;
			System.out.println(regMsg);
			
			//--------注册包验证、响应-----------
			String stationId = regMsg.getStationId();
			String clientId = stationId.substring(stationId.indexOf("."));
			NettyClientMap.add(clientId, (SocketChannel) ctx.channel());
			System.out.println("add "+clientId);
			
			//回应注册包
			ctx.writeAndFlush(DataBuilder.buildRegRspMsg(regMsg.getPacketNo()));
			
			//------------------------------
		}else if(msgType.equals("CR")){
			CtrlCommandRspMsg ccrMsg = (CtrlCommandRspMsg) msg;
			System.out.println(ccrMsg);
			ctx.writeAndFlush(DataBuilder.buildTransModeMsg(ccrMsg.getPacketNo()));
		}else if(msgType.equals("WC")){
			System.out.println("WC message");
		}else if(msgType.equals("WT")){
			System.out.println("WT message");
		}else if(msgType.equals("WS")){
			System.out.println("WS message");
		}else if(msgType.equals("TI")){
			System.out.println("TI message");
		}
		
	}
	
	/**
	 * 处理异常，如通信另一方链路意外断开
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		
		System.out.println("客户端意外退出");
		ctx.close();
	}
	
	/**
	 * 处理客户端链路断开后逻辑，无论是正常断开还是因为故障（在exceptionCaught捕获到异常后），这个方法都会被调用
	 */
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		
		System.out.println("remove");
		NettyClientMap.remove((SocketChannel) ctx.channel());
		
		
	}
}
