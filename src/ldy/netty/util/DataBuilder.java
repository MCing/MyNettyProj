package ldy.netty.util;

import java.util.Random;

import ldy.eqsys.msg.CtrlCommandRspMsg;
import ldy.eqsys.msg.RegMsg;
import ldy.eqsys.msg.RegRspMsg;
import ldy.eqsys.msg.TransModeMsg;

public class DataBuilder {

	private static Random random = new Random();
	public static RegMsg buildRegMsg(){
		
		RegMsg regMsg = new RegMsg();
		regMsg.setAuthenCode("PASSWORD");
		regMsg.setAltitude((short) random.nextInt(200));
		regMsg.setLatitude(random.nextInt(300)*100000);
		regMsg.setLongitude(random.nextInt(300)*100000);
		regMsg.setMsgType("RE");
		regMsg.setPacketNo(random.nextInt(100));
		regMsg.setSensitivity(1000);
		regMsg.setStationId("00"+random.nextInt(100));
		regMsg.setServerId("007");
		regMsg.setTransMode((short) 1);
		regMsg.setTriggerThreshold((short) random.nextInt(200));
		
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
