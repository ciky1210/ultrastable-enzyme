package com.hnlg.enzyme.web.controller;

import com.hnlg.enzyme.entity.dto.LoginDTO;
import com.hnlg.enzyme.enums.ExceptionEnum;
import com.hnlg.enzyme.exception.RException;
import com.hnlg.enzyme.service.AdminUserService;
import com.hnlg.enzyme.util.ObjectUtil;
import com.hnlg.enzyme.util.ResultInfo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 管理员用户登录
 */
@RestController
@RequestMapping("/login")
public class    LoginController {
    @Resource
    private AdminUserService adminUserService;

    /**
     * 用户登录
     */
    @RequestMapping("/userLogin")
    public ResultInfo userLogin(@RequestBody LoginDTO LoginDTO){
        if(ObjectUtil.isEmpty(LoginDTO)){
            throw new RException(ExceptionEnum.PARAM_IS_NOT_ALL);
        }
        return ResultInfo.success(adminUserService.login(LoginDTO));
    }
}
