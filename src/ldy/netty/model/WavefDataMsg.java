package ldy.netty.model;

/**
 * 波形数据包
 * @author ban
 *
 */
public class WavefDataMsg extends BaseMsg{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int packetNo;  		//包序号 
	private String qualityIdenty; //质量标识符 1B  常量D
	private String stationId; //台站标识符 5B
	private String localtionId; //位置标识符 2B
	private String channelId; //通道标识符  3B
	private String serverId;  //台网代码    2B
	private String startTime; //记录起始时间 绝对UTC 10byte ????
	private short sampleNum; //样品数量
	private short sampleFactor; //采样因子
	private short sampleMul; //采样率乘数
	private byte activitySign; //活动标志
	private byte ioClockSing; //输入输出和时钟标志
	private byte dataQualitySign; //数据质量标志
	private byte subBlockNum; //后面子块数目
	private int timeCorr; //时间校正值
	private short dataStartOffset; //数据开始偏移量
	private short firstBlockOffset; //第一个数据子块偏移
	
	//子块1000
	//private short subBlockType; //子块类型
	private short nextSubBlockType; //下一子块字节号
	private byte codeFormat; //编码格式
	private byte byteOrder; //子序
	private byte dataLength; //记录数据长度
	//子块1002
	private byte dimension; //量纲
	private byte sensFactor; //灵敏度因子
	//数据块
	
	
	
	
}
