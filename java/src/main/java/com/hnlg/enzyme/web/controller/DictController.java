package com.hnlg.enzyme.web.controller;

import com.hnlg.enzyme.service.DictService;
import com.hnlg.enzyme.util.ResultInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 字典控制类
 */
@RestController
@RequestMapping("/enzymeDict")
public class DictController {
    @Resource
    private DictService dictService;

    /**
     * 查询字典列表
     */
    @RequestMapping("/findDictList")
    public ResultInfo findDictList(){
        return ResultInfo.success(dictService.selectAll());
    }
}
