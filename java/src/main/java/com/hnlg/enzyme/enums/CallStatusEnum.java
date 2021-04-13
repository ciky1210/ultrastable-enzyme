package com.hnlg.enzyme.enums;

public enum  CallStatusEnum {

    /**
     * 调用成功
     */
    SUCCESS(1),

    /**
     * 调用失败
     */
    FAIL(0);




    private int code;

    CallStatusEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
