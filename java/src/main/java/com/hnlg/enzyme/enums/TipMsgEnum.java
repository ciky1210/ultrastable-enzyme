package com.hnlg.enzyme.enums;

import com.alibaba.fastjson.annotation.JSONType;

/**
 * 提示消息枚举类
 */
@JSONType(serializeEnumAsJavaBean = true)
public enum TipMsgEnum {
    /**
     * 成功
     */
    SUCCESS("成功"),

    /**
     * 失败
     */
    FAIL("失败"),

    /**
     * 错误
     */
    ERROR("错误"),

    /**
     * 保存成功
     */
    SAVE_SUCCESS("保存成功"),
    /**
     * 修改成功
     */
    UPDATE_SUCCESS("修改成功"),
    /**
     * 删除成功
     */
    DELETE_SUCCESS("删除成功"),
    /**
     * 充值成功
     */
    RECHARGE_SUCCESS("充值成功"),

    REFUND_SUCCESS("退款成功");

    private String desc;

    TipMsgEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return desc;
    }
}
