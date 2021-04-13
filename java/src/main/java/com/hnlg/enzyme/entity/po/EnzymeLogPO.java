package com.hnlg.enzyme.entity.po;

import lombok.Data;

import java.util.Date;

/**
 * 酶相关操作日志
 */
@Data
public class EnzymeLogPO extends BasePO{
    /**
     * 操作人id
     */
    private Long userId;
    /**
     * 操作人
     */
    private String userName;
    /**
     * 操作类型
     */
    private String operationType;
    /**
     * 操作时间
     */
    private Date operationTime;
    /**
     * 操作内容
     */
    private String operationBody;

}