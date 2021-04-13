package com.hnlg.enzyme.entity.po;

import lombok.Data;

/**
 * 管理员用户实体类
 */
@Data
public class AdminUserPO extends BasePO{
    /**
     *账号
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户名称
     */
    private String nickName;
    /**
     * 是否是管理员
     */
    private boolean adminFlag;
    /**
     * 是否被锁定
     */
    private boolean lockFlag;

}