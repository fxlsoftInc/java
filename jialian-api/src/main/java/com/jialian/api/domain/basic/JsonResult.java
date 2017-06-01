package com.jialian.api.domain.basic;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


/**
 *@Description:ajax json 返回值包装对象
 *@Author: 刘德宜  wudihaike@vip.qq.com
 *@Since: 2015年12月2日
 *@Version:1.0
 */
public class JsonResult implements Serializable {
	/**
	 *                                                                                                                                                                   
	 */
	private static final long serialVersionUID = 2955969952867505921L;
	/**
	 * 操作结果（成功或失败）
	 */
	private boolean success = false;
	/**
	 * 操作状态码
	 */
	private String stateCode = Const.ERROR_PARAM_CODE;
	/**
	 * 操作提示信息
	 */
	private String message = "";
	/**
	 * 备注
	 */
	private String comment ="";
	
	/**
	 * 额外数据Map
	 */
	private Map<String, Object> data = new HashMap<String, Object>();
	
	/**
	 * 数据Object
	 */
	private Object dataObj;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public void addData(String key, Object obj){
		this.data.put(key, obj);
	}
	
	public void deleteData(String key){
		this.data.remove(key);
	}

	public Object getDataObj() {
		return dataObj;
	}

	public void setDataObj(Object dataObj) {
		this.dataObj = dataObj;
	}

}