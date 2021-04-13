package com.hnlg.enzyme.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hnlg.enzyme.constant.CommonConstant;
import com.hnlg.enzyme.dao.EnzymeBaseDao;
import com.hnlg.enzyme.dao.EnzymeDictionaryDao;
import com.hnlg.enzyme.entity.dto.EnzymeInfoDTO;
import com.hnlg.enzyme.entity.dto.JwtUserDTO;
import com.hnlg.enzyme.entity.po.EnzymeBasePO;
import com.hnlg.enzyme.entity.po.EnzymeInfoPO;
import com.hnlg.enzyme.entity.query.BaseQuery;
import com.hnlg.enzyme.entity.query.QueryEnzyme;
import com.hnlg.enzyme.enums.ExceptionEnum;
import com.hnlg.enzyme.exception.RException;
import com.hnlg.enzyme.service.DictService;
import com.hnlg.enzyme.service.EnzymeBaseService;
import com.hnlg.enzyme.service.EnzymeInfoService;
import com.hnlg.enzyme.service.EnzymeLogService;
import com.hnlg.enzyme.util.ObjectUtil;
import com.hnlg.enzyme.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * 酶基础信息服务类
 */
@Service
@Transactional
public class EnzymeBaseServiceImpl implements EnzymeBaseService {
    @Resource
    private EnzymeBaseDao enzymeBaseDao;
    @Resource
    private EnzymeInfoService enzymeInfoService;
    @Resource
    private DictService dictService;
    @Resource
    private EnzymeLogService enzymeLogService;

    /**
     * 保存酶的信息
     * @param enzymeInfoDTO
     */
    public void save(EnzymeInfoDTO enzymeInfoDTO) {
        if(!enzymeInfoDTO.getEnzymeGiNum().equals("NaN")){
            if(enzymeInfoService.selectByGiNum(enzymeInfoDTO.getEnzymeGiNum()).size()>0){
                throw new RException(String.format("酶数据[%s]的GI号[%s]已存在", enzymeInfoDTO.getEnzymeName(), enzymeInfoDTO.getEnzymeGiNum()),316);
            }
        }
        if(ObjectUtil.isEmpty(enzymeInfoDTO.getEnzymeSequence())||enzymeInfoDTO.getEnzymeSequence().equals("NaN")){
            throw new RException(ExceptionEnum.ENZYME_SEQUENCE_IS_NOT_NULL);
        }
        if(enzymeInfoService.selectBySeqce(enzymeInfoDTO.getEnzymeSequence()).size()>0){
            throw new RException(String.format("酶数据[%s]的序列号[%s]已存在", enzymeInfoDTO.getEnzymeName(), enzymeInfoDTO.getEnzymeSequence()),315);
        }
        EnzymeBasePO enzymeBasePO = new EnzymeBasePO();
        BeanUtils.copyProperties(enzymeInfoDTO,enzymeBasePO);
        EnzymeInfoPO enzymeInfoPO = new EnzymeInfoPO();
        BeanUtils.copyProperties(enzymeInfoDTO,enzymeInfoPO);
        enzymeBaseDao.insert(enzymeBasePO);
        enzymeInfoPO.setEnzymeId(enzymeBasePO.getId());
        enzymeInfoService.save(enzymeInfoPO);
    }

    /**
     * 修改酶的信息
     * @param enzymeInfoDTO
     */
    public void update(EnzymeInfoDTO enzymeInfoDTO) {
        if(!enzymeInfoDTO.getEnzymeGiNum().equals("NaN")){
            if(enzymeInfoService.selectBySeqce(enzymeInfoDTO.getEnzymeSequence()).size()>1){
                throw new RException(ExceptionEnum.SEQUENCE_IS_NOT_REPEAT);
            }
            if(enzymeInfoService.selectByGiNum(enzymeInfoDTO.getEnzymeGiNum()).size()>1){
                throw new RException(ExceptionEnum.GINUM_IS_NOT_REPEAT);
            }
        }
        EnzymeBasePO enzymeBasePO = new EnzymeBasePO();
        BeanUtils.copyProperties(enzymeInfoDTO,enzymeBasePO);
        EnzymeInfoPO enzymeInfoPO = new EnzymeInfoPO();
        BeanUtils.copyProperties(enzymeInfoDTO,enzymeInfoPO);
        enzymeBaseDao.updateByPrimaryKey(enzymeBasePO);
        enzymeInfoService.update(enzymeInfoPO);
    }

    @Override
    public  PageInfo findEnzymeList(BaseQuery qo) {
        //分页
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        List<EnzymeInfoDTO> enzymeBasePOS = enzymeBaseDao.selectAll();
        return  new PageInfo<>(enzymeBasePOS);
    }

    @Override
    public PageInfo  findEnzymeByDict(QueryEnzyme queryEnzyme) {
        //分页
        PageHelper.startPage(queryEnzyme.getCurrentPage(), queryEnzyme.getPageSize());
        List<EnzymeInfoDTO> enzymeBasePOS = enzymeBaseDao.findEnzymeByDict(queryEnzyme);
        return  new PageInfo<>(enzymeBasePOS);
    }

    /**
     * 查询数据的条数
     */
    public int getEnztmeNum() {
        return enzymeBaseDao.getEnzymeNum();
    }

    @Override
    public int deleteEnzyme(Long enzymeId) {
        enzymeBaseDao.deleteByPrimaryKey(enzymeId);
        return enzymeInfoService.deleteByEnzymeBaseId(enzymeId);
    }

    @Override
    public String importExcel(JwtUserDTO jwtUserDTO, MultipartFile file) {
        // 1. 将excel 转换为 List<Object> list
        InputStream inputStream=null;
        try {
            inputStream = new BufferedInputStream(file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 文档名
        String fileName = file.getOriginalFilename();
        List<EnzymeInfoDTO> list = EasyExcel.read(inputStream).head(EnzymeInfoDTO.class).sheet().doReadSync();
        int success=0;
        int error=0;
        for (EnzymeInfoDTO enzymeInfoDTO : list) {
            ObjectUtil.objectNullToEmptyOrZero(enzymeInfoDTO);
            save(enzymeInfoDTO);
            try {
                enzymeLogService.saveEnzymeLog(jwtUserDTO, CommonConstant.IMPL_SUCCESS, JSONObject.toJSONString(enzymeInfoDTO));
                //查询是否属于字典 没有则创建字典
                dictService.createDict(jwtUserDTO,enzymeInfoDTO);
                success++;
            } catch (RException e) {
                enzymeLogService.saveEnzymeLog(jwtUserDTO, CommonConstant.IMPL_ERROR, e.getMessage());
                error++;
            }
        }
        return String.format("导入完成,成功[%d]条,失败[%d]条", success, error);
    }

    @Override
    public List<EnzymeInfoDTO> findEnzymeByIdList(String ids) {
        String[] split = ids.split(",");
        return enzymeBaseDao.findEnzymeByIdList(split);
    }

    @Override
    public PageInfo complexFindToEnzyme(QueryEnzyme qo) {
        String types = qo.getType();
        String condition = qo.getCondition();
        String typeValues = qo.getTypeValue();
        EnzymeInfoDTO enzymeInfoDTO = new EnzymeInfoDTO();
        //设置查询的列和值
        if(!StringUtil.isEmpty(types)){
            String[] typeSplit = types.split(",");
            String[] typeValueSplit = typeValues.split(",");
            for (int i=0;i<typeSplit.length;i++) {
                //把类型设置到dto中
                if(typeSplit.length!=1&& i==typeSplit.length-1){
                    enzymeInfoDTO.setTypeTwo(typeSplit[i]);
                }else{
                    enzymeInfoDTO.setTypeOne(typeSplit[i]);
                }
                //把值设置到dto中
                if(CommonConstant.FL.equals(typeSplit[i])){
                    enzymeInfoDTO.setEnzymeClassification(typeValueSplit[i]);
                }if(CommonConstant.LX.equals(typeSplit[i])){
                    enzymeInfoDTO.setEnzymeType(typeValueSplit[i]);
                }else if(CommonConstant.MC.equals(typeSplit[i])){
                    enzymeInfoDTO.setEnzymeName(typeValueSplit[i]);
                }else if(CommonConstant.WZ.equals(typeSplit[i])){
                    enzymeInfoDTO.setEnzymeSpecies(typeValueSplit[i]);
                }else if(CommonConstant.FYGC.equals(typeSplit[i])){
                    enzymeInfoDTO.setEnzymeSubstrate(typeValueSplit[i]);
                }else if(CommonConstant.FYTJ.equals(typeSplit[i])){
                    enzymeInfoDTO.setEnzymeMinPhScope(typeValueSplit[i]);
                }else if(CommonConstant.NSX.equals(typeSplit[i])){
                    enzymeInfoDTO.setEnzymeTolerance(typeValueSplit[i]);
                }
            }
        }
        //设置查询的条件
        if(!StringUtil.isEmpty(condition)){
            enzymeInfoDTO.setCondition(condition);
        }
        //分页
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        List<EnzymeInfoDTO> enzymeInfoDTOS = enzymeBaseDao.complexFindToEnzyme(enzymeInfoDTO);
        return  new PageInfo<>(enzymeInfoDTOS);

    }




    @Override
    public JSONObject countEnzymeData() {
        JSONObject jsonObject = new JSONObject();
        int type = enzymeBaseDao.countEnzymeTypeData();
        int enzymeNum = enzymeBaseDao.getEnzymeNum();
        int countEnzymeSpecies = enzymeBaseDao.countEnzymeSpeciesData();
        jsonObject.put("totalCount",enzymeNum);
        jsonObject.put("typeCount",type);
        jsonObject.put("speciesCount",countEnzymeSpecies);
        return jsonObject;
    }

}
