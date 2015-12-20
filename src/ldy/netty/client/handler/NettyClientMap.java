package ldy.netty.client.handler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.netty.channel.socket.SocketChannel;

public class NettyClientMap {

	private static Map<String, SocketChannel> map = new ConcurrentHashMap<String, SocketChannel>();
	
	public static void add(String clientId, SocketChannel socketChannel){
		map.put(clientId, socketChannel);
	}
	public static void remove(SocketChannel socketChannel){
		for(Map.Entry entry : map.entrySet()){
			if(entry.getValue() == socketChannel){
				map.remove(entry.getKey());
			}
		}
	}
	public static void printClient(){
		for(Map.Entry entry : map.entrySet()){
			System.out.println(entry.getKey());
		}
	}
}
