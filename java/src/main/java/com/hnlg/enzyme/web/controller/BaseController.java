package com.hnlg.enzyme.web.controller;

import com.hnlg.enzyme.constant.CommonConstant;
import com.hnlg.enzyme.entity.dto.JwtUserDTO;
import com.hnlg.enzyme.entity.dto.RequestInfoDTO;
import com.hnlg.enzyme.enums.ExceptionEnum;
import com.hnlg.enzyme.exception.RException;
import com.hnlg.enzyme.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 基础controller
 */
public class BaseController {

    @Autowired
    private HttpServletRequest request;

    @Value("${spring.application.name}")
    private String serveName;

    /**
     * 系统启动
     *
     * @return
     */
    @RequestMapping("/")
    public String initSys() {
        return serveName + " is running success!";
    }

    /**
     * 获取当前登录IP
     */
    @GetMapping("/ip")
    String getIp() {
        return HttpUtil.getIpAddr(request);
    }

    /**
     * 获取session参数
     *
     * @param attrName
     * @return
     */
    Object getSessionAttr(String attrName) {
        return request.getSession().getAttribute(attrName);
    }

    /**
     * 设置Session参数
     *
     * @param attrName
     * @param obj
     */
    void setSessionAttr(String attrName, Object obj) {
        request.getSession().setAttribute(attrName, obj);
    }

    /**
     * 获取当前登录用户
     *
     * @return
     */
    JwtUserDTO getCurrentLoginUser() {
        JwtUserDTO attribute = (JwtUserDTO) request.getAttribute(CommonConstant.CURRENT_LOGIN_STAFF);
        if (attribute == null) {
            throw new RException(ExceptionEnum.CAN_NOT_FIND_USER_FROM_REQ);
        }
        return attribute;
    }

    /**
     * 获取当前登录信息
     */
    RequestInfoDTO getRequestInfo() {
        return (RequestInfoDTO) request.getAttribute(CommonConstant.REQUEST_INFO);
    }

}
