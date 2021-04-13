package com.hnlg.enzyme.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.hnlg.enzyme.entity.dto.EnzymeInfoDTO;
import com.hnlg.enzyme.entity.dto.JwtUserDTO;
import com.hnlg.enzyme.entity.po.EnzymeBasePO;
import com.hnlg.enzyme.entity.query.BaseQuery;
import com.hnlg.enzyme.entity.query.QueryEnzyme;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 酶基础信息接口
 */
public interface EnzymeBaseService {
    /**
     * 保存酶的信息
     * @param enzymeInfoDTO
     */
    void save(EnzymeInfoDTO enzymeInfoDTO);

    /**
     * 修改酶的信息
     * @param enzymeInfoDTO
     */
    void update(EnzymeInfoDTO enzymeInfoDTO);

    /**
     * 获取酶的基础信息
     * @return
     */
    PageInfo findEnzymeList(BaseQuery qo);
    /**
     * 根据分类查询
     */
    PageInfo  findEnzymeByDict(QueryEnzyme queryEnzyme);
    /**
     * 查询数据的条数
     */
    int getEnztmeNum();

    /**
     * 删除酶信息
     * @param enzymeId
     * @return
     */
    int deleteEnzyme(Long enzymeId);

    /**
     * 文件数据导入
     * @param file
     */
    String importExcel(JwtUserDTO jwtUserDTO, MultipartFile file);

    /**
     * 根据多个id查询数据
     * @param ids
     */
    List<EnzymeInfoDTO>  findEnzymeByIdList(String ids);

    /**
     * 复合搜索功能
     * @param qo
     * @return
     */
    PageInfo complexFindToEnzyme(QueryEnzyme qo);
    /**
     * 统计酶数据信息
     * @return 酶数据统计信息
     */
    JSONObject countEnzymeData();
}
