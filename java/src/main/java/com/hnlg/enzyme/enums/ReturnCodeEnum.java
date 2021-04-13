package com.hnlg.enzyme.enums;

public enum  ReturnCodeEnum {

    /**
     * 三方接口调用成功
     */
    SUCCESS(0),

    /**
     * 三方接口调用成功调用失败
     */
    FAIL(-1);

    private int code;

    ReturnCodeEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
