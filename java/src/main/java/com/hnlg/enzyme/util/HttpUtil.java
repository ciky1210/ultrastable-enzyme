package com.hnlg.enzyme.util;

import com.hnlg.enzyme.constant.CommonConstant;
import com.hnlg.enzyme.enums.ExceptionEnum;
import com.hnlg.enzyme.exception.RException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

/**
 * http工具类
 */
public class HttpUtil {


    /**
     * 从request中获取参数
     *
     * @param param
     * @return
     */
    public static String getRequestParam(HttpServletRequest httpRequest, String param) {
        // 从header中获取参数
        String rParam = httpRequest.getHeader(param);
        // 如果header中不存在，则从参数中获取
        if (StringUtil.isEmpty(rParam)) {
            rParam = httpRequest.getParameter(param);
        }
        if(StringUtil.isEmpty(rParam)){
            throw new RException(ExceptionEnum.CAN_NOT_FIND_USER_FROM_REQ);
        }
        String replace = rParam.replace("\"", "");

        return replace;
    }

    /**
     * 获取JWT TOKEN
     *
     * @param request
     * @return
     */
    public static String getJwtToken(HttpServletRequest request) {
        String jwtToken = HttpUtil.getRequestParam(request, CommonConstant.JWT_TOKEN);
        // 验证参数不全
        if (StringUtil.isEmpty(jwtToken)) {
            throw new RException(ExceptionEnum.VERIFY_PARAM_INCOMPLETE);
        }
        return jwtToken;
    }

    /**
     * 获取http
     *
     * @return
     */
    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 获取IP地址
     * <p>
     * 使用Nginx等反向代理软件， 则不能通过request.getRemoteAddr()获取IP地址
     * 如果使用了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP地址，X-Forwarded-
     * For中第一个非unknown的有效IP字符串，则为真实IP地址
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtil.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if (StringUtil.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            return ip;
        }
        return request.getRemoteAddr();
    }

    /**
     * 校验是否是合格IP地址
     *
     * @param ip
     * @return
     */
    public static boolean isIp(String ip) {
        Pattern p = Pattern.compile("^((25[0-5]|2[0-4]\\d|[1]\\d\\d|[1-9]\\d|\\d)($|(?!\\.$)\\.)){4}$");
        return p.matcher(ip).matches();
    }

    /**
     * 获取IP地址的归属地
     *
     * @param ip
     * @return
     */
    public static String getIpLocation(String ip) {
        return "";
    }

    /**
     * 把Url  结尾加上 /
     *
     * @param url
     * @return
     */
    public static String formatEndUrl(String url) {
        if (url.endsWith("/")) {
            return url;
        } else {
            return url + "/";
        }
    }
}