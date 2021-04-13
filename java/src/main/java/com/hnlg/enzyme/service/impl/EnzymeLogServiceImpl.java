package com.hnlg.enzyme.service.impl;

import com.hnlg.enzyme.constant.CommonConstant;
import com.hnlg.enzyme.dao.EnzymeLogDao;
import com.hnlg.enzyme.entity.dto.JwtUserDTO;
import com.hnlg.enzyme.entity.po.EnzymeLogPO;
import com.hnlg.enzyme.service.EnzymeLogService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 日志服务类
 */
@Service
public class EnzymeLogServiceImpl implements EnzymeLogService {
    @Resource
    private EnzymeLogDao enzymeLogDao;
    @Async
    public void saveEnzymeLog(JwtUserDTO jwtUserDTO, String type, String body) {
        EnzymeLogPO enzymeLogPO = new EnzymeLogPO();
        enzymeLogPO.setUserId(jwtUserDTO.getUserId());
        enzymeLogPO.setUserName(jwtUserDTO.getNickName());
        enzymeLogPO.setOperationType(type);
        enzymeLogPO.setOperationBody(body);
        enzymeLogPO.setOperationTime(new Date());
        enzymeLogDao.insert(enzymeLogPO);
    }

    @Override
    public List<EnzymeLogPO> getLogList(Long userId) {
        return enzymeLogDao.selectById(userId);
    }


}
