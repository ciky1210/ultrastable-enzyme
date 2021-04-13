package com.hnlg.enzyme.dao;

import com.hnlg.enzyme.entity.dto.EnzymeInfoDTO;
import com.hnlg.enzyme.entity.po.EnzymeBasePO;
import com.hnlg.enzyme.entity.po.EnzymeInfoPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 酶详细信息Dao
 */
public interface EnzymeInfoDao {
    int deleteByPrimaryKey(Long id);

    int insert(EnzymeInfoPO record);

    EnzymeInfoDTO selectByPrimaryKey(Long id);

    List<EnzymeInfoPO> selectAll();

    int updateByPrimaryKey(EnzymeInfoPO record);


    int deleteByEnzymeBaseId(Long enzymeId);
    /**
     * 根据序列号查询
     * @param seqce
     * @return
     */
    List<EnzymeInfoPO> selectBySeqce(String seqce);
    /**
     * 根据Gi号查询
     * @param giNum
     * @return
     */
    List<EnzymeInfoPO> selectByGiNum(String giNum);

    /**
     * 批量修改
     * @param enzymeByDict
     */
    void batchUpdate(@Param("list") List<EnzymeInfoPO> enzymeByDict);

}