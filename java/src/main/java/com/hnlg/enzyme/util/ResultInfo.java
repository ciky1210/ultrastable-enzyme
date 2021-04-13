package com.hnlg.enzyme.util;


import com.hnlg.enzyme.constant.CommonConstant;
import com.hnlg.enzyme.enums.ExceptionEnum;
import com.hnlg.enzyme.enums.TipMsgEnum;

import java.io.Serializable;

/**
 * 返回结果封装
 */
public class ResultInfo implements Serializable {

    private Integer code;

    private String msg;

    private Object data;

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        if (data==null){
            data="";
        }
        this.data = data;
    }


    /**
     * 提供给部分不需要出參的接口
     *
     * @return
     */
    public static ResultInfo success() {
        return success(TipMsgEnum.SUCCESS);
    }

    /**
     * 返回成功的结果
     *
     * @param data
     * @return
     */
    public static ResultInfo success(Object data) {
        return success(TipMsgEnum.SUCCESS, data);
    }

    /**
     * 返回成功的结果
     *
     * @param msg
     * @return
     */
    public static ResultInfo success(TipMsgEnum msg) {
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode(CommonConstant.DEFAULT_SUCCESS_CODE);
        resultInfo.setMsg(msg.toString());
        resultInfo.setData("");
        return resultInfo;
    }

    /**
     * 返回成功的结果
     *
     * @param object
     * @return
     */
    public static ResultInfo success(TipMsgEnum msg, Object object) {
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode(CommonConstant.DEFAULT_SUCCESS_CODE);
        resultInfo.setMsg(msg.toString());
        resultInfo.setData(object);
        return resultInfo;
    }

    /**
     * 返回失败的结果
     *
     * @return
     */
    public static ResultInfo error(Integer code, Object data) {
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode(code);
        resultInfo.setMsg(TipMsgEnum.FAIL.getDesc());
        resultInfo.setData(data);
        return resultInfo;
    }


    /**
     * 自定义错误信息
     *
     * @param code
     * @param msg
     * @return
     */
    public static ResultInfo error(Integer code, String msg) {
        ResultInfo result = new ResultInfo();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }

    /**
     * 返回异常信息，在已知的范围内
     *
     * @param exceptionEnum
     * @return
     */
    public static ResultInfo error(ExceptionEnum exceptionEnum) {
        ResultInfo result = new ResultInfo();
        result.setCode(exceptionEnum.getCode());
        result.setMsg(exceptionEnum.getMsg());
        result.setData(null);
        return result;
    }
}