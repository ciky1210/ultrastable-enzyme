package com.hnlg.enzyme.constant;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 数字常亮
 */
@Data
public class NumberConstant {
    public static final BigDecimal DEFAULT_NUMBER=new BigDecimal(0);

    /**
     * 连接池超时设置
     */
    public static final int CONNECT_TIMEOUT = 2000;

    /**
     * SOCKET超时设置
     */
    public static final int SOCKET_TIMEOUT = 10000;

}
