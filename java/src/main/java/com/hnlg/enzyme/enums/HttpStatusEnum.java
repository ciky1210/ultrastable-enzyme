package com.hnlg.enzyme.enums;


public enum HttpStatusEnum {

    /**
     *  内部服务器错误
     */
    SERVER_ERROR(500),

    /**
     * 返回数据错误
     */
    RETURN_ERROR(501),

    /**
     * 用户不存在
     */

    USER_IS_NOT_EXIST(3000),


    /**
     * appId不能为空
     */
    APP_ID_IS_NOT_EXIST(3001),

    /**
     * 账户可调用次数为0
     */
    ZERO_CALLABLE_TIMES(3002),

    /**
     * 请求成功,无返回值
     */
    NOT_RETURN_VALUE(3003),

    /**
     * 链接超时
     */
    SOCKET_TIMEOUT(3004),

    /**
     * IO异常
     */
    IO_EXCEPTION(3005),

    JSON_ERROR(3006),

    SET_VALUE_ERROR(3007);




    private int code;

    HttpStatusEnum(int code) {
        this.code = code;
    }



    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }

}
