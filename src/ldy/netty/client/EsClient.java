package ldy.netty.client;

import java.net.ConnectException;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ConnectTimeoutException;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import ldy.netty.client.handler.ClientHandler;
import ldy.netty.util.CommHandler;

public class EsClient {
	
	private boolean isConnected = false;  //tcp链路连接标志
	
	public void connect(int port, String host) throws Exception{
		
		//准备线程池
		EventLoopGroup group = new NioEventLoopGroup();
		
			Bootstrap cbootstrap = new Bootstrap();
			cbootstrap.group(group)
			.channel(NioSocketChannel.class)
			.option(ChannelOption.TCP_NODELAY, true)
			.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 30000) //连接超时 30s
			.handler(new ChannelHandlers());
		while(true){
			try{	
				
				if(!isConnected){
					System.out.println("connecting......");
					ChannelFuture f = cbootstrap.connect(host, port).sync();
					
					if(f.isSuccess()){
						isConnected = true;
					}
				}
				Thread.sleep(10);
				
			}catch(Exception e){
				
				if(e instanceof ConnectTimeoutException){
					
					//连接超时,host可解析，port开放但是不接收tcp连接时才会触发这个超时
					System.out.println("连接超时");
					isConnected = false;
				}else if(e instanceof ConnectException){
					
					//连接参数错误
					System.out.println("服务未打开");
					break;
				}else{
					//其他异常
					System.out.println("参数错误");
					e.printStackTrace();
					break;
				}
			}
		}
		group.shutdownGracefully();
		System.out.println("clent shutdown");
	}
	
	class ChannelHandlers extends ChannelInitializer<SocketChannel>{

		@Override
		protected void initChannel(SocketChannel ch) throws Exception {

			ch.pipeline().addLast(new ObjectDecoder(1024,ClassResolvers.cacheDisabled(this.getClass().getClassLoader())));
			ch.pipeline().addLast(new ObjectEncoder());
			ch.pipeline().addLast(new ClientHandler(EsClient.this));
		}
		
	}
	
	public static void main(String[] args){
		try {
//			new EsClient().connect(9898, "www.baidu.com");  //测试3s连接超时
			new EsClient().connect(8080, "localhost");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void reconnect(){
		isConnected = false;
	}
}
