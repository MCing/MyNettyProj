package ldy.netty.model;

import java.io.Serializable;

public class BaseMsg implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String msgType;  			//类型
	protected int packetNo;  				//包序号
	private String serverId;				//服务端ID 2bytes
	protected String stationId;			    //烈度仪ID  5bytes
	
	//getter and setter
	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getServerId() {
		return serverId;
	}
	public void setServerId(String serverId) {
		this.serverId = serverId;
	}
	public int getPacketNo() {
		return packetNo;
	}
	public void setPacketNo(int packetNo) {
		this.packetNo = packetNo;
	}
	public String getStationId() {
		return stationId;
	}
	public void setStationId(String stationId) {
		this.stationId = stationId;
	}
}
