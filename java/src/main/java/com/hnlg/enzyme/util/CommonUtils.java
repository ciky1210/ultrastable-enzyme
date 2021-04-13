package com.hnlg.enzyme.util;

/**
 * 通用工具类
 */
public class CommonUtils {

	public static String getTraceInfo() {

		StringBuffer sb = new StringBuffer();

		StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
		sb.append("(");
		sb.append("className:").append(stackTraceElement.getClassName());
		sb.append(";fieldName:").append(stackTraceElement.getFileName());
		sb.append(";methodName:").append(stackTraceElement.getMethodName());
		sb.append(";lineNumber:").append(stackTraceElement.getLineNumber());
		sb.append(")");
		return sb.toString();
	}
}