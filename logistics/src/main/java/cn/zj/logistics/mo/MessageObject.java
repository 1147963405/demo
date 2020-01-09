package cn.zj.logistics.mo;

/*
 * 消息对应，以供ajax 增删改操作返回json消息
 * 
 * 
 * 
 */
public class MessageObject {
	private  Integer code; //0失败 ，1成功
	private  String msg;//提示消息
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	@Override
	public String toString() {
		return "MessageObject [code=" + code + ", msg=" + msg + "]";
	}
	public MessageObject(Integer code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	
	
}
