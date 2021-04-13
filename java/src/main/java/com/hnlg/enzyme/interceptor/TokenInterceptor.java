package com.hnlg.enzyme.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.hnlg.enzyme.constant.CommonConstant;
import com.hnlg.enzyme.constant.RedisConstant;
import com.hnlg.enzyme.entity.dto.JwtUserDTO;
import com.hnlg.enzyme.enums.ExceptionEnum;
import com.hnlg.enzyme.exception.RException;
import com.hnlg.enzyme.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * token拦截器
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisUtil redisUtil;
    /**
     * 前置拦截器
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) {
        // 获取校验参数
        String jwtToken = HttpUtil.getJwtToken(httpServletRequest);
        // 从redis中获取token并验证
        return checkRedisToken(jwtToken, httpServletRequest);
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
                           ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Object o, Exception e) {
    }

    /**
     * 检测redis token方案
     *
     * @param jwtToken           token
     * @param httpServletRequest request
     */
    private boolean checkRedisToken(String jwtToken, HttpServletRequest httpServletRequest) {
        // 根据uid appId 从缓存中获取当前登录用户
        JwtUserDTO jwtUserDTO;
        try {
            String decrypt = JwtUtil.decrypt(jwtToken);
             jwtUserDTO = JSONObject.parseObject(decrypt, JwtUserDTO.class);
        } catch (Exception e) {
            throw new RException(ExceptionEnum.DECRYPT_USER_INFO_ERROR);
        }
        //校验用户信息 比对
        checkTokenUserInfo(jwtUserDTO);
        // 将 当前登录用户 放入request
        httpServletRequest.setAttribute(CommonConstant.CURRENT_LOGIN_STAFF, jwtUserDTO);
        return true;
    }



    /**
     * 校验token
     *
     * @return
     */
    public void checkTokenUserInfo(JwtUserDTO jwtUserDTO) {
        String staffKey = RedisConstant.getStaffKey(jwtUserDTO.getUserId());
        String token = (String) redisUtil.get(staffKey);
        // 如果缓存中命中失败,从数据库获取用户信息
        if (!jwtUserDTO.getToken().equals(token)) {
            // 验证用户token不同
            throw new RException(ExceptionEnum.TOKEN_VERIFY_FAIL);
        } else if (StringUtil.isEmpty(jwtUserDTO.getToken())) {
            // 如果用户当前没有token，说明没有登录或token过期
            throw new RException(ExceptionEnum.TOKEN_INVALID);
        }
    }
}
