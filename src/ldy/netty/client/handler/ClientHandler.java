package ldy.netty.client.handler;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.concurrent.EventExecutor;
import ldy.netty.client.EsClient;
import ldy.netty.model.BaseMsg;
import ldy.netty.model.RegRspMsg;
import ldy.netty.model.TransModeMsg;
import ldy.netty.util.DataBuilder;

public class ClientHandler extends ChannelHandlerAdapter {

	private EsClient client;
	private boolean isReg;
	
	public ClientHandler(EsClient client){
		this.client = client;
	}
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		
			System.out.println("client channel active");
			ChannelFuture f = ctx.writeAndFlush(DataBuilder.buildRegMsg(new Random().nextInt(100)));  //发送注册信息包
//			f.addListener(new ChannelFutureListener() {
//				
//				@Override
//				public void operationComplete(ChannelFuture future) throws Exception {
//					//ctx.close();
//				}
//			});
			isReg = false;
			EventExecutor loop = ctx.executor();
			loop.schedule(new ReconnectTask(), 3000, TimeUnit.MILLISECONDS);
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		
		BaseMsg revMsg = (BaseMsg) msg;
		String msgType = revMsg.getMsgType();
		if("RR".equals(msgType)){
			RegRspMsg rrMsg = (RegRspMsg) msg;
			System.out.println(rrMsg);
			isReg = true;
		}else if("CC".equals(msgType)){
			TransModeMsg ccrM = (TransModeMsg) msg;
			System.out.println(ccrM);
		}
	}
	
	/**
	 * 处理连接异常
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		
		System.out.println("服务器连接断开");
		ctx.close();
	}
	
	/**
	 * 连接重连
	 * @author ban
	 *
	 */
	private class ReconnectTask implements Runnable{

		@Override
		public void run() {
			
			//如果连接失败则重连
			if(!isReg){
				System.out.println("定时任务  重连");
				client.reconnect();
			}else{
				System.out.println("定时任务 无需重连");
			}
		}
	}
	
}
