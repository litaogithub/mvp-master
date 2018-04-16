package com.common.litao.mvp.bean;

/**
 * 业务bean
 *
 */
public class BusinessBean {
	/**
	 * 状态码
	 */
	public String code = "200";
	/**
	 * 信息
	 */
    public String message;
	/**
	 * 数据
	 */
	public String data;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
