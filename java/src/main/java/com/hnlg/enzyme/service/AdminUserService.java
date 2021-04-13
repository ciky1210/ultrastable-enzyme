package com.hnlg.enzyme.service;

import com.hnlg.enzyme.entity.dto.LoginDTO;
import com.hnlg.enzyme.entity.po.AdminUserPO;
import com.hnlg.enzyme.entity.vo.UserVO;

import java.util.List;

/**
 * 管理员用户接口
 */
public interface AdminUserService {
    /**
     * 删除
     * @param id 主键
     * @return 操作的条数
     */
    int delete(Long id);

    /**
     * 增加
     * @param record 管理员用户对象
     * @return 操作的条数
     */
    int save(AdminUserPO record);

    /**
     * 单条查询
     * @param id 主键
     * @return
     */
    AdminUserPO selectOne(Long id);

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
    int updateOne(AdminUserPO record);

    /**
     * 用户登录
     * @param loginDTO
     */
    UserVO login(LoginDTO loginDTO);

    /**
     * 注册用户
     * @param adminUserPO
     */
    void register(AdminUserPO adminUserPO);

    /**
     * 用户查重
     * @param adminUserPO
     * @return
     */
    AdminUserPO checkRepeat(AdminUserPO adminUserPO);

    /**
     * 锁定
     * @param userId
     */
    void lockUser(Long userId);

    /**
     * 删除用户
     * @param userId
     * @return
     */
    int deleteUser(Long userId);

    /**
     * 吸怪面膜
     * @param userId 用户id
     * @param newPassword 新密码
     */
    void changePassword(Long userId, String newPassword);
}
