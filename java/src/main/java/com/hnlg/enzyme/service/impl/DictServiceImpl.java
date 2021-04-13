package com.hnlg.enzyme.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hnlg.enzyme.constant.CommonConstant;
import com.hnlg.enzyme.dao.EnzymeBaseDao;
import com.hnlg.enzyme.dao.EnzymeDictionaryDao;
import com.hnlg.enzyme.dao.EnzymeInfoDao;
import com.hnlg.enzyme.entity.dto.EnzymeInfoDTO;
import com.hnlg.enzyme.entity.dto.JwtUserDTO;
import com.hnlg.enzyme.entity.po.EnzymeBasePO;
import com.hnlg.enzyme.entity.po.EnzymeDictionaryPO;
import com.hnlg.enzyme.entity.po.EnzymeInfoPO;
import com.hnlg.enzyme.entity.query.QueryEnzyme;
import com.hnlg.enzyme.service.DictService;
import com.hnlg.enzyme.service.EnzymeLogService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class DictServiceImpl implements DictService {
    @Resource
    private EnzymeDictionaryDao enzymeDictionaryDao;
    @Resource
    private EnzymeBaseDao enzymeBaseDao;
    @Resource
    private EnzymeInfoDao enzymeInfoDao;
    @Resource
    private EnzymeLogService enzymeLogService;

    @Override
    public int delete(Long id) {
        return enzymeDictionaryDao.deleteByPrimaryKey(id);
    }

    @Override
    public int save(EnzymeDictionaryPO record) {
        return enzymeDictionaryDao.insert(record);
    }

    @Override
    public EnzymeDictionaryPO selectOne(Long id) {
        return enzymeDictionaryDao.selectByPrimaryKey(id);
    }

    @Override
    public Map selectAll() {
        List<EnzymeDictionaryPO> enzymeDictionaryPOS = enzymeDictionaryDao.selectAll();
        HashMap<String, Object> dictListMap = new HashMap<>();
        ArrayList<EnzymeDictionaryPO> ECHBList = new ArrayList<>();
        ArrayList<EnzymeDictionaryPO> FLList = new ArrayList<>();
        ArrayList<EnzymeDictionaryPO> LXList = new ArrayList<>();
        ArrayList<EnzymeDictionaryPO> WDList = new ArrayList<>();
        ArrayList<EnzymeDictionaryPO> SJList = new ArrayList<>();
        ArrayList<EnzymeDictionaryPO> NSFLList = new ArrayList<>();
        for (EnzymeDictionaryPO enzymeDictionaryPO : enzymeDictionaryPOS) {
            if (enzymeDictionaryPO.getEnzymeDictCode().equalsIgnoreCase(CommonConstant.ECBH)){
                ECHBList.add(enzymeDictionaryPO);
            }else if(enzymeDictionaryPO.getEnzymeDictCode().equalsIgnoreCase(CommonConstant.FL)){
                FLList.add(enzymeDictionaryPO);
            }else if(enzymeDictionaryPO.getEnzymeDictCode().equalsIgnoreCase(CommonConstant.LX)){
                LXList.add(enzymeDictionaryPO);
            }else if(enzymeDictionaryPO.getEnzymeDictCode().equalsIgnoreCase(CommonConstant.WD)){
                WDList.add(enzymeDictionaryPO);
            }else if(enzymeDictionaryPO.getEnzymeDictCode().equalsIgnoreCase(CommonConstant.SJ)){
                SJList.add(enzymeDictionaryPO);
            }else if(enzymeDictionaryPO.getEnzymeDictCode().equalsIgnoreCase(CommonConstant.NSFL)){
                NSFLList.add(enzymeDictionaryPO);
            }
        }
        dictListMap.put(CommonConstant.ECBH,ECHBList);
        dictListMap.put(CommonConstant.FL,FLList);
        dictListMap.put(CommonConstant.LX,LXList);
        dictListMap.put(CommonConstant.WD,WDList);
        dictListMap.put(CommonConstant.SJ,SJList);
        dictListMap.put(CommonConstant.NSFL,NSFLList);
        return dictListMap;
    }

    @Override
    public int updateDict(EnzymeDictionaryPO record) {
        //查询出原本的字典信息
        EnzymeDictionaryPO enzymeDictionaryPO = enzymeDictionaryDao.selectByPrimaryKey(record.getId());
        //查询出对应的酶信息
        QueryEnzyme queryEnzyme = new QueryEnzyme();
        queryEnzyme.setType(enzymeDictionaryPO.getEnzymeDictType());
        queryEnzyme.setKeyword(enzymeDictionaryPO.getEnzymeDictName());
        List<EnzymeInfoDTO> enzymeByDict = enzymeBaseDao.findEnzymeByDict(queryEnzyme);
        EnzymeInfoPO enzymeInfoPO = null;
        ArrayList<EnzymeInfoPO> infoList = new ArrayList<>();
        int i=1;
        //创建修改详细信息的对象
        if(enzymeByDict.size()>0){
            for (EnzymeInfoDTO enzymeBasePO : enzymeByDict) {
                enzymeInfoPO=new EnzymeInfoPO();
                switch (record.getEnzymeDictType()) {
                    case "EC编号":
                        enzymeBasePO.setEnzymeEcCode(record.getEnzymeDictName());
                        break;
                    case "分类":
                        enzymeBasePO.setEnzymeClassification(record.getEnzymeDictName());
                        break;
                    case "类型":
                        enzymeBasePO.setEnzymeType(record.getEnzymeDictName());
                        break;
                    case "温度耐受性":
                        i=2;
                        enzymeInfoPO.setEnzymeId(enzymeBasePO.getId());
                        enzymeInfoPO.setEnzymeTemperatureTolerance(record.getEnzymeDictName());
                        infoList.add(enzymeInfoPO);
                        break;
                    case "酸碱耐受性":
                        i=2;
                        enzymeInfoPO.setEnzymeId(enzymeBasePO.getId());
                        enzymeInfoPO.setEnzymeAcidAlkaliTolerance(record.getEnzymeDictName());
                        infoList.add(enzymeInfoPO);
                        break;
                    case "耐受性分类":
                        i=2;
                        enzymeInfoPO.setEnzymeId(enzymeBasePO.getId());
                        enzymeInfoPO.setEnzymeToleranceClassification(record.getEnzymeDictName());
                        infoList.add(enzymeInfoPO);
                        break;
                }
            }
            if (i==1){
                enzymeBaseDao.batchUpdate(enzymeByDict);
            }else{
                enzymeInfoDao.batchUpdate(infoList);
            }
        }
        return enzymeDictionaryDao.updateByPrimaryKey(record);
    }

    /**
     * 删除字典
     * @param dictId 字典id
     * @return 删除格式
     */
    @Override
    public int deleteDict(Long dictId) {
        return enzymeDictionaryDao.deleteByPrimaryKey(dictId);
    }

    /**
     * 查询是否存在字典,不存在则创建字典
     * @param enzymeInfoDTO
     */
    @Override
    public void createDict(JwtUserDTO jwtUserDTO, EnzymeInfoDTO enzymeInfoDTO) {
        //ec编号
        String enzymeEcCode = enzymeInfoDTO.getEnzymeEcCode();
        //温度耐受性
        String enzymeTemperatureTolerance = enzymeInfoDTO.getEnzymeTemperatureTolerance();
        //酸碱耐受性
        String enzymeAcidAlkaliTolerance = enzymeInfoDTO.getEnzymeAcidAlkaliTolerance();
        //耐受性分类
        String enzymeToleranceClassification = enzymeInfoDTO.getEnzymeToleranceClassification();
        //酶类型
        String enzymeType = enzymeInfoDTO.getEnzymeType();
        EnzymeDictionaryPO dict=null;
        if(StringUtils.hasText(enzymeEcCode)&&!"NaN".equals(enzymeEcCode)){
            int i = enzymeDictionaryDao.selectByDictName(enzymeEcCode);
            if(i==0){
                dict=new EnzymeDictionaryPO();
                dict.setEnzymeDictCode(CommonConstant.ECBH);
                dict.setEnzymeDictType("EC编号");
                dict.setEnzymeDictName(enzymeEcCode);
                enzymeDictionaryDao.insert(dict);
                enzymeLogService.saveEnzymeLog(jwtUserDTO, CommonConstant.ADD, JSONObject.toJSONString(dict));
            }
        }
        if(StringUtils.hasText(enzymeTemperatureTolerance)&&!"NaN".equals(enzymeTemperatureTolerance)){
            int i = enzymeDictionaryDao.selectByDictName(enzymeTemperatureTolerance);
            if(i==0){
                dict=new EnzymeDictionaryPO();
                dict.setEnzymeDictCode(CommonConstant.WD);
                dict.setEnzymeDictType("温度耐受性");
                dict.setEnzymeDictName(enzymeTemperatureTolerance);
                enzymeDictionaryDao.insert(dict);
                enzymeLogService.saveEnzymeLog(jwtUserDTO, CommonConstant.ADD, JSONObject.toJSONString(dict));

            }
        }
        if(StringUtils.hasText(enzymeAcidAlkaliTolerance)&&!"NaN".equals(enzymeAcidAlkaliTolerance)){
            int i = enzymeDictionaryDao.selectByDictName(enzymeAcidAlkaliTolerance);
            if(i==0){
                dict=new EnzymeDictionaryPO();
                dict.setEnzymeDictCode(CommonConstant.SJ);
                dict.setEnzymeDictType("酸碱耐受性");
                dict.setEnzymeDictName(enzymeAcidAlkaliTolerance);
                enzymeDictionaryDao.insert(dict);
                enzymeLogService.saveEnzymeLog(jwtUserDTO, CommonConstant.ADD, JSONObject.toJSONString(dict));
            }
        }
        if(StringUtils.hasText(enzymeToleranceClassification)&&!"NaN".equals(enzymeToleranceClassification)){
            int i = enzymeDictionaryDao.selectByDictName(enzymeToleranceClassification);
            if(i==0){
                dict=new EnzymeDictionaryPO();
                dict.setEnzymeDictCode(CommonConstant.NSFL);
                dict.setEnzymeDictType("耐受性分类");
                dict.setEnzymeDictName(enzymeToleranceClassification);
                enzymeDictionaryDao.insert(dict);
                enzymeLogService.saveEnzymeLog(jwtUserDTO, CommonConstant.ADD, JSONObject.toJSONString(dict));
            }
        }
        if(StringUtils.hasText(enzymeType)&&!"NaN".equals(enzymeType)){
            int i = enzymeDictionaryDao.selectByDictName(enzymeType);
            if(i==0){
                dict=new EnzymeDictionaryPO();
                dict.setEnzymeDictCode(CommonConstant.LX);
                dict.setEnzymeDictType("类型");
                dict.setEnzymeDictName(enzymeType);
                enzymeDictionaryDao.insert(dict);
                enzymeLogService.saveEnzymeLog(jwtUserDTO, CommonConstant.ADD, JSONObject.toJSONString(dict));
            }
        }

    }
}
