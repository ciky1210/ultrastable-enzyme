package com.hnlg.enzyme.dao;

import com.hnlg.enzyme.entity.po.EnzymeLogPO;
import java.util.List;

/**
 * 操作相关Dao
 */
public interface EnzymeLogDao {

    int insert(EnzymeLogPO record);

    List<EnzymeLogPO> selectAll();

    List<EnzymeLogPO> selectById(Long userId);



}