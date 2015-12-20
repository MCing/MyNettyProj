package ldy.netty.util;

import java.util.Random;

import ldy.netty.model.CtrlCommandRspMsg;
import ldy.netty.model.RegMsg;
import ldy.netty.model.RegRspMsg;
import ldy.netty.model.TransModeMsg;

public class DataBuilder {

	Random random = new Random();
	public static RegMsg buildRegMsg(int id){
		
		RegMsg regMsg = new RegMsg();
		regMsg.setAuthenCode("15577392350");
		regMsg.setAltitude((short) 50);
		regMsg.setLatitude(500000);
		regMsg.setLongitude(500000);
		regMsg.setMsgType("RE");
		regMsg.setPacketNo(id);
		regMsg.setSensitivity(1000);
		regMsg.setStationId("xx.xxxx"+id);
		regMsg.setTransMode((short) 1);
		regMsg.setTriggerThreshold((short) 100);
		
		return regMsg;

	}
	
public static RegRspMsg buildRegRspMsg(int id){
		
		RegRspMsg msg = new RegRspMsg();
		
		msg.setMsgType("RR");
		msg.setPacketNo(id);
		msg.setStationId("xx.xxxxx");
		msg.setAuthenState((short) 0);
		msg.setLastPacketNo(0xffffffff);
		
		return msg;

	}
public static TransModeMsg buildTransModeMsg(int id){
	
	TransModeMsg msg = new TransModeMsg();
	
	msg.setMsgType("CC");
	msg.setPacketNo(id);
	msg.setStationId("xx.xxxxx");
	msg.setSubCommand((short) 1);
	msg.setSubTransMode((short) 0);
	
	return msg;

}
public static CtrlCommandRspMsg buildCtrlCommandRspMsg(int id){
	
	CtrlCommandRspMsg msg = new CtrlCommandRspMsg();
	
	msg.setMsgType("CR");
	msg.setPacketNo(id);
	msg.setStationId("xx.xxxxx");
	msg.setSubCommand((short) 1);
	msg.setStateDetil("state detil message");
	msg.setRspState((short) 0);
	
	return msg;

}
}
