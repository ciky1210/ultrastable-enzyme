package com.hnlg.enzyme.dao;

import com.hnlg.enzyme.entity.dto.LoginDTO;
import com.hnlg.enzyme.entity.po.AdminUserPO;

import java.util.List;

/**
 * 管理员用户dao
 */
public interface AdminUserDao {
    /**
     * 删除
     * @param id 主键
     * @return 操作的条数
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 增加
     * @param record 管理员用户对象
     * @return 操作的条数
     */
    int insert(AdminUserPO record);

    /**
     * 单条查询
     * @param id 主键
     * @return
     */
    AdminUserPO selectByPrimaryKey(Long id);

    /**
     * 查询全部管理员用户
     * @return
     */
    List<AdminUserPO> selectAll();

    /**
     * 修改管理员用户
     * @param record 主体
     * @return
     */
    int updateByPrimaryKey(AdminUserPO record);

    /**
     * 用户查询
     * @param adminUserPO 查询封装类
     * @returnz
     */
    AdminUserPO userLogin(LoginDTO adminUserPO);

    /**
     * 用户查重
     * @param adminUserPO
     * @return
     */
    AdminUserPO checkUser(AdminUserPO adminUserPO);
}