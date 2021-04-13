package com.hnlg.enzyme.exception;


import com.hnlg.enzyme.enums.ExceptionEnum;

import java.io.Serializable;

/**
 * 自定义异常
 */
public class RException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 1L;

	private String msg;
	private int code;

	public RException(ExceptionEnum exceptionEnum) {
		super(exceptionEnum.getMsg());
		this.msg = exceptionEnum.getMsg();
		this.code = exceptionEnum.getCode();
	}

	public RException(String msg, Throwable e) {
		super(msg, e);
		this.msg = msg;
	}

	public RException(String msg, int code) {
		super(msg);
		this.msg = msg;
		this.code = code;
	}

	public RException(String msg) {
		super();
		this.msg = msg;
	}

	public RException() {
		super();
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
