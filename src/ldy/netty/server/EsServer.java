package ldy.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import ldy.netty.server.handler.ServerHandler;

public class EsServer {

	private int port;
	
	public EsServer(int port){
		this.port = port;
	}
	
	public void run() throws Exception{
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		try{
			
			//初始化启动器
			ServerBootstrap sbootstrap = new ServerBootstrap();
			sbootstrap.group(bossGroup, workerGroup)
			.channel(NioServerSocketChannel.class)
			.childHandler(new ChannelHandlerInit())
			.option(ChannelOption.SO_BACKLOG, 128)
			.childOption(ChannelOption.SO_KEEPALIVE, true);
			
			//绑定端口
			ChannelFuture future = sbootstrap.bind(port);
			future.addListener(new ChannelFutureListener() {
				
				@Override
				public void operationComplete(ChannelFuture future) throws Exception {
					System.out.println("server启动成功");
				}
			});
			
			//等待监听通道关闭
			future.channel().closeFuture().sync();
			
		}catch(Exception e){
			System.out.println("启动失败");
			e.printStackTrace();
		}finally{
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
		
	}
	
	private class ChannelHandlerInit extends ChannelInitializer<SocketChannel>{

		@Override
		protected void initChannel(SocketChannel ch) throws Exception {
			
			
			ch.pipeline().addLast(new ObjectDecoder(
					1024, 
					ClassResolvers.weakCachingConcurrentResolver(
							this.getClass().getClassLoader())
					));
			ch.pipeline().addLast(new ObjectEncoder());
			ch.pipeline().addLast(new ServerHandler());
		}
		
	}
	
	public static void main(String[] args){
		try {
			new EsServer(8080).run();
		} catch (Exception e) {
			e.printStackTrace();
		};
	}
}
