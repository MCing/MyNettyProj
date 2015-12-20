package ldy.netty.model;

/**
 * 6.时间段触发控制包
 * 发送端：服务器
 * 标识符：CC
 *
 */
public class PeriodTriggleCtrlMsg extends BaseMsg {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private short subCommand;		//子控制命令
	private int startTime;			//触发开始时间  绝对UTC时间
	private int endTime;			//触发结束时间  绝对UTC时间
	
	//getter and setter
	public short getSubCommand() {
		return subCommand;
	}
	public void setSubCommand(short subCommand) {
		this.subCommand = subCommand;
	}
	public int getStartTime() {
		return startTime;
	}
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
	public int getEndTime() {
		return endTime;
	}
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}
	
}
