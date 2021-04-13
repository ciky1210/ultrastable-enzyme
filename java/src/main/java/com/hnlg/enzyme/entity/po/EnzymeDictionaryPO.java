package com.hnlg.enzyme.entity.po;

import lombok.Data;

/**
 * 酶字典实体
 */
@Data
public class EnzymeDictionaryPO extends BasePO{
    /**
     * 字典名称
     */
    private String enzymeDictName;
    /**
     * 字典类型
     */
    private String enzymeDictType;
    /**
     * 字典编码
     */
    private String enzymeDictCode;


}