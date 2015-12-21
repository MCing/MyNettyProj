package ldy.eqsys.msg;


/**
 * 9.触发信息包
 * 发送端:客户端
 * 标识符:TI
 *
 *	未添加 getter and setter 方法
 */
public class TriggleMsg extends BaseMsg {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int initMotionTimeSec; 		//初动时间整秒   触发时间整秒值（绝对UTC时间）
	private short initMotionTimeMs;		//初动时间毫秒 触发时间整毫秒值（绝对UTC时间）
	private short  relTimeSec;			//相对时间	单位秒，与初动时间的相对时间
	private int staAndltaValue;			//触发时计算得到的STA/LTA值
	private short initMotionDir;		//初动方向	 	1:向上 -1:向下 0:不清晰
	private int udToPga;				//单位为mm/s/s,频带范围0.1-10Hz
	private int udToPgv;				//单位为mm/s,频带范围0.1-10Hz
	private int udToPgd;				//单位为um,频带范围0.1-10Hz
	private int ewToPga;
	private int ewToPgv;
	private int ewToPgd;
	private int nsToPga;
	private int nsToPgv;
	private int nsToPgd;
	private short intensity;			//烈度值  单位 度*10
	private int udToPsa03;				//单自由度系统拟加速度反应，单位mm／s/s,阻尼比为5%，周期为0.3秒
	private int udToPsa10;				//单自由度系统拟加速度反应，单位mm／s/s,阻尼比为5%，周期为1秒
	private int udToPsa30;				//单自由度系统拟加速度反应，单位mm／s/s,阻尼比为5%，周期为3秒
	private int ewToPsa03;
	private int ewToPsa10;
	private int ewToPsa30;
	private int nsToPsa03;
	private int nsToPsa10;
	private int nsToPsa30;
	

}
