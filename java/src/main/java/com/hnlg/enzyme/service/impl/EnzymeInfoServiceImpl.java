package com.hnlg.enzyme.service.impl;

import com.hnlg.enzyme.dao.EnzymeInfoDao;
import com.hnlg.enzyme.entity.dto.EnzymeInfoDTO;
import com.hnlg.enzyme.entity.po.EnzymeInfoPO;
import com.hnlg.enzyme.service.EnzymeInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

/**
 * 酶详细信息服务类
 */
@Service
@Transactional
public class EnzymeInfoServiceImpl implements EnzymeInfoService {
    @Resource
    private EnzymeInfoDao EnzymeInfoDao;

    @Override
    public void save(EnzymeInfoPO enzymeInfoPO) {
        EnzymeInfoDao.insert(enzymeInfoPO);
    }

    @Override
    public void update(EnzymeInfoPO enzymeInfoPO) {
        EnzymeInfoDao.updateByPrimaryKey(enzymeInfoPO);
    }

    @Override
    public EnzymeInfoDTO selectOne(Long enzymeId) {
        return EnzymeInfoDao.selectByPrimaryKey(enzymeId);
    }

    /**
     * 根据序列号查询
     * @param seqce
     * @return
     */
    public List<EnzymeInfoPO> selectBySeqce(String seqce) {
        return EnzymeInfoDao.selectBySeqce(seqce);
    }

    /**
     * 根据Gi号查询
     * @param giNum
     * @return
     */
    public List<EnzymeInfoPO> selectByGiNum(String giNum) {
        return EnzymeInfoDao.selectByGiNum(giNum);
    }

    @Override
    public int deleteByEnzymeBaseId(Long enzymeId) {
        return EnzymeInfoDao.deleteByEnzymeBaseId(enzymeId);
    }
}
