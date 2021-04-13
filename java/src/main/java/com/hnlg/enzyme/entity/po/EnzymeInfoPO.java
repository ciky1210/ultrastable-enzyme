package com.hnlg.enzyme.entity.po;

import lombok.Data;

/**
 * 详细酶信息
 */
@Data
public class EnzymeInfoPO extends BasePO{
    /**
     * 基本信息id
     */
    private Long enzymeId;
    /**
     * 酶gi号
     */
    private String enzymeGiNum;
    /**
     * 酶分子量
     */
    private String enzymeKda;
    /**
     * 氨基酸数量
     */
    private String enzymeAminoAcidNum;
    /**
     * 突变前
     */
    private String enzymeMutationBefore;
    /**
     * 突变后
     */
    private String enzymeMutationAfter;
    /**
     * 序列
     */
    private String enzymeSequence;
    /**
     * 底物
     */
    private String enzymeSubstrate;
    /**
     *产物
     */
    private String enzymeProduct;
    /**
     * 所属途径
     */
    private String enzymeBelong;
    /**
     * 最小ph范围
     */
    private String enzymeMinPhScope;
    /**
     * 最大ph范围
     */
    private String enzymeMaxPhScope;
    /**
     * 最适合ph范围
     */
    private String enzymePerfectScope;
    /**
     * 最小温度范围
     */
    private String enzymeMinTemperatureScope;
    /**
     * 最大温度范围
     */
    private String enzymeMaxTemperatureScope;
    /**
     * 最适宜温度范围
     */
    private String enzymePerfectTemperatureScope;
    /**
     * 耐受性
     */
    private String enzymeTolerance;
    /**
     * Vmax（mmo/（L·s））评价
     */
    private String enzymeVmaxMmoEvaluation;
    /**
     * Km（mmol/L）评价
     */
    private String enzymeKmMmolEvaluation;
    /**
     * Kcat（s-1）评价
     */
    private String enzymeKcatEvaluation;

    /**
     * Kcat/Km （%）评价
     */
    private String enzymeKcatKmEvaluation;
    /**
     * 比活评价
     */
    private String enzymeBhEvaluation;
    /**
     * 参考文献
     */
    private String enzymeReference;
    /**
     * 参考文献地址
     */
    private String enzymeReferenceUrl;
    /**
     * Tm值（℃）
     */
    private String enzymeTm;
    /**
     * 半衰期
     */
    private String enzymeHalfLife;
    /**
     * 温度耐受性
     */
    private String enzymeTemperatureTolerance;
    /**
     * 酸碱耐受性
     */
    private String enzymeAcidAlkaliTolerance;
    /**
     * 耐受性分类
     */
    private String enzymeToleranceClassification;
    /**
     * 总分评价
     */
    private String enzymeOverallEvaluation;

}