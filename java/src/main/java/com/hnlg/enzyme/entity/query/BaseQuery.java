package com.hnlg.enzyme.entity.query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseQuery {
    //当前页
    private int currentPage;
    //页面大小
    private int pageSize;
}
