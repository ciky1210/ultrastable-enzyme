package com.hnlg.enzyme.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hnlg.enzyme.constant.CommonConstant;
import com.hnlg.enzyme.constant.RedisConstant;
import com.hnlg.enzyme.dao.AdminUserDao;
import com.hnlg.enzyme.entity.dto.JwtUserDTO;
import com.hnlg.enzyme.entity.dto.LoginDTO;
import com.hnlg.enzyme.entity.po.AdminUserPO;
import com.hnlg.enzyme.entity.vo.UserVO;
import com.hnlg.enzyme.enums.ExceptionEnum;
import com.hnlg.enzyme.exception.RException;
import com.hnlg.enzyme.service.AdminUserService;
import com.hnlg.enzyme.util.JwtUtil;
import com.hnlg.enzyme.util.MD5Util;
import com.hnlg.enzyme.util.RedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

/**
 * 管理员用户实现类
 */
@Service
@Transactional
public class AdminUserServiceImpl implements AdminUserService {
    @Resource
    private AdminUserDao adminUserDao;
    @Resource
    private RedisUtil redisUtil;


    /**
     * 删除
     * @param id 主键
     * @return 操作的条数
     */
    public int delete(Long id) {
        return adminUserDao.deleteByPrimaryKey(id);
    }

    @Override
    public int save(AdminUserPO record) {
        return adminUserDao.insert(record);
    }

    @Override
    public AdminUserPO selectOne(Long id) {
        return adminUserDao.selectByPrimaryKey(id);
    }

    @Override
    public List<AdminUserPO> selectAll() {
        return adminUserDao.selectAll();
    }

    @Override
    public int updateOne(AdminUserPO record) {
        return adminUserDao.updateByPrimaryKey(record);
    }

    /**
     * 用户登录
     * @param loginDTO
     */
    public UserVO login(LoginDTO loginDTO) {
        //设置密码
        loginDTO.setPassword(MD5Util.getSaltMd5(loginDTO.getPassword()));
        //验证账户密码是否错误
        AdminUserPO loginUser = adminUserDao.userLogin(loginDTO);
        //如果查不到
        if (loginUser==null){
            throw new RException(ExceptionEnum.USER_NOT_FOUND);
        }
        //如果账号被锁定
        if(loginUser.isLockFlag()){
            throw new RException(ExceptionEnum.USER_IS_LOCK);
        }
        // 生成token
        String token = JwtUtil.generatorToken();
        //保存到redis当中
        redisUtil.set(RedisConstant.getStaffKey(loginUser.getId()),token,
                CommonConstant.DEFAULT_EXPIRE_TIME*CommonConstant.ONT_HOUR);
        //返回token
        JwtUserDTO jwtUserDTO = new JwtUserDTO(loginUser.getId(), loginUser.getNickName(),token);
        String userJson = JSONObject.toJSONString(jwtUserDTO);
        UserVO userVO = new UserVO();
        userVO.setUserName(loginUser.getNickName());
        userVO.setToken(JwtUtil.encrypt(userJson));
        return  userVO;
    }

    /**
     * 注册用户
     * @param adminUserPO
     */
    public void register(AdminUserPO adminUserPO) {
        adminUserPO.setPassword(MD5Util.getSaltMd5(adminUserPO.getPassword()));
        adminUserDao.insert(adminUserPO);
    }

    /**
     * 用户查重
     * @param adminUserPO
     * @return
     */
    public AdminUserPO checkRepeat(AdminUserPO adminUserPO) {
        return adminUserDao.checkUser(adminUserPO);
    }

    @Override
    public void lockUser(Long userId) {
        AdminUserPO adminUserPO = adminUserDao.selectByPrimaryKey(userId);
        adminUserPO.setLockFlag(!adminUserPO.isLockFlag());
        adminUserDao.updateByPrimaryKey(adminUserPO);
    }

    /**
     * 删除用户
     * @param userId
     * @return
     */
    public int deleteUser(Long userId) {
        return adminUserDao.deleteByPrimaryKey(userId);
    }

    @Override
    public void changePassword(Long userId, String newPassword) {
        AdminUserPO adminUserPO = new AdminUserPO();
        adminUserPO.setPassword(MD5Util.getSaltMd5(newPassword));
        adminUserPO.setId(userId);
        adminUserDao.updateByPrimaryKey(adminUserPO);
    }

}
