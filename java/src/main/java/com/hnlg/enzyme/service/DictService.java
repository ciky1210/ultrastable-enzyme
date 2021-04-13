package com.hnlg.enzyme.service;

import com.hnlg.enzyme.entity.dto.EnzymeInfoDTO;
import com.hnlg.enzyme.entity.dto.JwtUserDTO;
import com.hnlg.enzyme.entity.po.EnzymeDictionaryPO;

import java.util.Map;

/**
 * 字典相关服务
 */
public interface DictService {
    int delete(Long id);

    int save(EnzymeDictionaryPO record);

    EnzymeDictionaryPO selectOne(Long id);

    Map selectAll();

    /**
     * 修改酶字典信息
     * @param record
     * @return
     */
    int updateDict(EnzymeDictionaryPO record);

    /**
     * 删除字典
     * @param dictId
     * @return
     */
    int deleteDict(Long dictId);

    /**
     * 查询是否存在字典,不存在则创建字典
     * @param enzymeInfoDTO
     */
    void createDict(JwtUserDTO jwtUserDTO, EnzymeInfoDTO enzymeInfoDTO);
}
