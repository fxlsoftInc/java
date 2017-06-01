package com.jialian.api.domain.basic;

import java.io.Serializable;
import java.util.Map;

/**
 *@Description:service返回值包装对象
 *@Author: 刘德宜  wudihaike@vip.qq.com
 *@Since: 2015年12月2日
 *@Version:1.0
 */
public class ServiceResult<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 557229634762292882L;

	private boolean isOk = false;
	
	/**
	 *  msg 状态0表示异常、1表示正确
	 */
	private int msgType = 0;
	
	/**
	 * 备注
	 */
	private String comment;

	/**
	 * 消息code
	 */
	private String msgCode;
	/**
	 * 消息参数
	 */
	private Object[] msgParams;

	/**
	 * 数据
	 */
	private T data;

	/**
	 * 数据Map
	 */
	private Map<String, Object> dataMap;
	
	public boolean isOk() {
		return isOk;
	}

	public void setOk(boolean isOk) {
		this.isOk = isOk;
	}
	
	public int getMsgType(){
		return this.msgType;
	}

	public String getMsgCode() {
		return msgCode;
	}

	/**
	 * 根据msg第二位字母取得msg类型
	 * @param msgCode
	 */
	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
		try {
			char state = msgCode.charAt(1);
			switch (state) {
			case 'I':
				this.msgType = 1;
				break;
			case 'Q':
				this.msgType = 1;
				break;
			case 'E':
				this.msgType = 0;
				break;
				
			default:
				this.msgType = 0;
				break;
			}
			
		} catch (Exception e) {
			this.msgType = 0;
		}
	}
	
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Object[] getMsgParams() {
		return msgParams;
	}

	public void setMsgParams(Object[] msgParams) {
		this.msgParams = msgParams;
	}

}
