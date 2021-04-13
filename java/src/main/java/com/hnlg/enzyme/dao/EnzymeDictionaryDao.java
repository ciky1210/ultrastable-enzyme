package com.hnlg.enzyme.dao;

import com.hnlg.enzyme.entity.po.EnzymeDictionaryPO;
import java.util.List;

/**
 * 酶字典Dao层
 */
public interface EnzymeDictionaryDao {
    int deleteByPrimaryKey(Long id);

    int insert(EnzymeDictionaryPO record);

    EnzymeDictionaryPO selectByPrimaryKey(Long id);

    List<EnzymeDictionaryPO> selectAll();

    int updateByPrimaryKey(EnzymeDictionaryPO record);

    /**
     * 根据ec编号查询数据
     * @param enzymeEcCode
     */
    int selectByDictName(String enzymeEcCode);
}