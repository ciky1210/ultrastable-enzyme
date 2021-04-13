package com.hnlg.enzyme.dao;

import com.hnlg.enzyme.entity.dto.EnzymeInfoDTO;
import com.hnlg.enzyme.entity.po.EnzymeBasePO;
import com.hnlg.enzyme.entity.query.QueryEnzyme;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 酶基础信息
 */
public interface EnzymeBaseDao {
    int deleteByPrimaryKey(Long id);

    int insert(EnzymeBasePO record);

    EnzymeBasePO selectByPrimaryKey(Long id);

    List<EnzymeInfoDTO> selectAll();

    int updateByPrimaryKey(EnzymeBasePO record);

    List<EnzymeInfoDTO> findEnzymeByDict(QueryEnzyme queryEnzyme);
    /**
     * 批量修改
     * @param enzymeByDict
     */
    void batchUpdate(@Param("list") List<EnzymeInfoDTO> enzymeByDict);
    /**
     * 查询数据的条数
     */
    int getEnzymeNum();

    /**
     * 根据id数组查询
     * @param split
     * @return
     */
    List<EnzymeInfoDTO> findEnzymeByIdList(@Param("array")String[] split);

    /**
     * 复合查询
     * @param enzymeInfoDTO
     * @return
     */
    List<EnzymeInfoDTO> complexFindToEnzyme(EnzymeInfoDTO enzymeInfoDTO);
    /**
     * 查询类型数据
     * @return
     */
    int countEnzymeTypeData();
    /**
     * 查询物种数据
     * @return
     */
    int countEnzymeSpeciesData();
}