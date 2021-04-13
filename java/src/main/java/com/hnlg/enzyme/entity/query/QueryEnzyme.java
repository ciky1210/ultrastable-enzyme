package com.hnlg.enzyme.entity.query;

import lombok.Data;

/**
 * 查询酶信息
 */
@Data
public class QueryEnzyme extends BaseQuery {
    private String keyword;
    private String type;
    private String condition;
    private String typeValue;

}
