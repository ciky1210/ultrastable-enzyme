package com.hnlg.enzyme.service;

import com.hnlg.enzyme.entity.dto.EnzymeInfoDTO;
import com.hnlg.enzyme.entity.po.EnzymeInfoPO;

import java.util.List;

/**
 * 酶详细信息服务
 */
public interface EnzymeInfoService {
    /**
     * 添加
     * @param enzymeInfoPO
     */
    void save(EnzymeInfoPO enzymeInfoPO);

    /**
     * 修改
     * @param enzymeInfoPO
     */
    void update(EnzymeInfoPO enzymeInfoPO);

    /**
     * 单条查询
     * @param enzymeId
     * @return
     */
    EnzymeInfoDTO selectOne(Long enzymeId);

    /**
     * 序列号查询
     * @param seqce
     */
    List<EnzymeInfoPO> selectBySeqce(String seqce);
    /**
     * 根据Gi号查询
     * @param giNum
     */
    List<EnzymeInfoPO> selectByGiNum(String giNum);

    /**
     * 根据酶基本信息id删除详细信息
     * @param enzymeId
     * @return
     */
    int deleteByEnzymeBaseId(Long enzymeId);
}
