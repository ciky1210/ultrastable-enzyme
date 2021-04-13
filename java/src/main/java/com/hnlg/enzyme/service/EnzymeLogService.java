package com.hnlg.enzyme.service;

import com.hnlg.enzyme.entity.dto.JwtUserDTO;
import com.hnlg.enzyme.entity.po.EnzymeLogPO;

import java.util.List;

/**
 * 日志服务类
 */
public interface EnzymeLogService {
    /**
     * 添加日志
     */
    void saveEnzymeLog(JwtUserDTO jwtUserDTO,String type,String body);

    /**
     * 获取日志列表
     * @return
     */
    List<EnzymeLogPO> getLogList(Long userId);


}
