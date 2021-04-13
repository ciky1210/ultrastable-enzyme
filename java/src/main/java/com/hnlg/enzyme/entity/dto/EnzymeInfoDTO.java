package com.hnlg.enzyme.entity.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.hnlg.enzyme.entity.po.BasePO;
import lombok.Data;

/**
 * 酶信息录入用DTO
 */
@Data
public class EnzymeInfoDTO extends BasePO {
    /**
     * 酶名称
     */
    @ExcelProperty("酶名称")
    private String enzymeName;
    /**
     * ec编号
     */
    @ExcelProperty("EC编号")
    private String enzymeEcCode;
    /**
     * 酶分类
     */
    @ExcelProperty("分类")
    private String enzymeClassification;
    /**
     * 酶类型
     */
    @ExcelProperty("酶类型")
    private String enzymeType;
    /**
     * 酶物种
     */
    @ExcelProperty("所属物种")
    private String enzymeSpecies;
    /**
     * 酶突变类型 W：野生型 M ：突变型
     */
    @ExcelProperty("野生型（W）/突变体（M）")
    private String enzymeMutationType;
    /**
     * 突变体
     */
    @ExcelProperty("是否有突变体")
    private String enzymeMutant;
    /**
     * 基本信息id
     */
    private Long enzymeId;
    /**
     * 酶gi号
     */
    @ExcelProperty("gi号")
    private String enzymeGiNum;
    /**
     * 酶分子量
     */
    @ExcelProperty("分子量(kDa)")
    private String enzymeKda;
    /**
     * 氨基酸数量
     */
    @ExcelProperty("氨基酸数量")
    private String enzymeAminoAcidNum;
    /**
     * 突变前
     */
    @ExcelProperty("突变前")
    private String enzymeMutationBefore;
    /**
     * 突变后
     */
    @ExcelProperty("突变后")
    private String enzymeMutationAfter;
    /**
     * 序列
     */
    @ExcelProperty("序列")
    private String enzymeSequence;
    /**
     * 底物
     */
    @ExcelProperty("底物")
    private String enzymeSubstrate;
    /**
     *产物
     */
    @ExcelProperty("产物")
    private String enzymeProduct;
    /**
     * 所属途径
     */
    @ExcelProperty("所属途径")
    private String enzymeBelong;
    /**
     * 最小ph范围
     */
    @ExcelProperty("最小pH")
    private String enzymeMinPhScope;
    /**
     * 最大ph范围
     */
    @ExcelProperty("最大pH")
    private String enzymeMaxPhScope;
    /**
     * 最适合ph范围
     */
    @ExcelProperty("最适pH")
    private String enzymePerfectScope;
    /**
     * 最小温度范围
     */
    @ExcelProperty("最小温度")
    private String enzymeMinTemperatureScope;
    /**
     * 最大温度范围
     */
    @ExcelProperty("最大温度")
    private String enzymeMaxTemperatureScope;
    /**
     * 最适宜温度范围
     */
    @ExcelProperty("最适温度")
    private String enzymePerfectTemperatureScope;
    /**
     * 耐受性
     */
    @ExcelProperty("其他耐受性")
    private String enzymeTolerance;
    /**
     * Vmax（mmo/（L·s））评价
     */
    @ExcelProperty("Vmax（mmo/（L·s））评价")
    private String enzymeVmaxMmoEvaluation;
    /**
     * Km（mmol/L）评价
     */
    @ExcelProperty("Km（mmol/L）评价")
    private String enzymeKmMmolEvaluation;
    /**
     * Kcat（s-1）评价
     */
    @ExcelProperty("Kcat（s-1）评价")
    private String enzymeKcatEvaluation;

    /**
     * Kcat/Km （%）评价
     */
    @ExcelProperty("Kcat/Km （%）评价")
    private String enzymeKcatKmEvaluation;
    /**
     * 比活评价
     */
    @ExcelProperty("比活评价")
    private String enzymeBhEvaluation;
    /**
     * 参考文献
     */
    @ExcelProperty("参考文献")
    private String enzymeReference;
    /**
     * 参考文献地址
     */
    @ExcelProperty("参考文献地址")
    private String enzymeReferenceUrl;
    /**
     * Tm值（℃）
     */
    @ExcelProperty("Tm值（℃）")
    private String enzymeTm;
    /**
     * 半衰期
     */
    @ExcelProperty("半衰期")
    private String enzymeHalfLife;
    /**
     * 温度耐受性
     */
    @ExcelProperty("温度耐受性")
    private String enzymeTemperatureTolerance;
    /**
     * 酸碱耐受性
     */
    @ExcelProperty("酸碱耐受性")
    private String enzymeAcidAlkaliTolerance;
    /**
     * 耐受性分类
     */
    @ExcelProperty("耐受性分类")
    private String enzymeToleranceClassification;
    /**
     * 总分评价
     */
    @ExcelProperty("总分评价")
    private String enzymeOverallEvaluation;
    /**
     * 查询条件
     */
    private String condition;
    /**
     * 查询条件1
     */
    private String typeOne;
    /**
     * 查询条件2
     */
    private String typeTwo;
}
