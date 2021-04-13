package com.hnlg.enzyme.entity.po;

import lombok.Data;

/**
 * 酶基础信息实体
 */
@Data
public class EnzymeBasePO extends BasePO{
    /**
     * 酶名称
     */
    private String enzymeName;
    /**
     * ec编号
     */
    private String enzymeEcCode;
    /**
     * 酶分类
     */
    private String enzymeClassification;
    /**
     * 酶类型
     */
    private String enzymeType;
    /**
     * 酶物种
     */
    private String enzymeSpecies;
    /**
     * 酶突变类型 W：野生型 M ：突变型
     */
    private String enzymeMutationType;
    /**
     * 突变体
     */
    private String enzymeMutant;

}