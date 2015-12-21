package ldy.eqsys.msg;

/**
 * 8.状态信息包
 * 发送端：客户端
 * 标识符：SI
 *
 *	未完成,UD 峰值 数据长度问题:
 */
public class StateMsg extends BaseMsg {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int absTime;			//计算峰峰值起始时间（绝对UTC时间）
	private int period;				//峰峰值计算时间长度，单位：秒
	private int udPeakValue;		//计算时间长度内每一秒的加速度振幅峰峰值      40bYTES???????????
	private int ewPeakValue;		//计算时间长度内每一秒的加速度振幅峰峰值
	private int nsPeakValue;		//计算时间长度内每一秒的加速度振幅峰峰值

}
