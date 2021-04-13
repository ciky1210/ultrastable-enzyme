package com.hnlg.enzyme.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.hnlg.enzyme.constant.CommonConstant;
import com.hnlg.enzyme.entity.dto.EnzymeInfoDTO;
import com.hnlg.enzyme.entity.dto.JwtUserDTO;
import com.hnlg.enzyme.entity.po.AdminUserPO;
import com.hnlg.enzyme.entity.po.EnzymeDictionaryPO;
import com.hnlg.enzyme.enums.ExceptionEnum;
import com.hnlg.enzyme.exception.RException;
import com.hnlg.enzyme.service.*;
import com.hnlg.enzyme.util.ObjectUtil;
import com.hnlg.enzyme.util.ResultInfo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * 管理员用户controller
 */
@RestController
@RequestMapping("/enzymeAdminUser")
@CrossOrigin
public class AdminUserController extends BaseController{
    @Resource
    private AdminUserService adminUserService;
    @Resource
    private DictService dictService;
    @Resource
    private EnzymeBaseService enzymeBaseService;
    @Resource
    private EnzymeInfoService enzymeInfoService;
    @Resource
    private EnzymeLogService enzymeLogService;
    /**
     * 注册用户
     */
    @RequestMapping("/register")
    public ResultInfo register(AdminUserPO adminUserPO){
        if(ObjectUtil.isEmpty(adminUserPO)){
            throw new RException(ExceptionEnum.PARAM_IS_NOT_ALL);
        }
        //账户名重复检查
        if(!ObjectUtil.isEmpty(adminUserService.checkRepeat(adminUserPO))){
            throw new RException(ExceptionEnum.NAME_IS_REPETITION);
        }
        try {
            adminUserService.register(adminUserPO);
        } catch (Exception e) {
            e.printStackTrace();
            return  ResultInfo.error(ExceptionEnum.UNKNOW_ERROR);
        }
        return  ResultInfo.success();
    }

    /**
     * 增加与修改字典
     * @param enzymeDictionaryPO
     * @return
     */
    @RequestMapping("/saveOrUpdateDict")
    public ResultInfo saveOrUpdate(EnzymeDictionaryPO enzymeDictionaryPO){
        if(ObjectUtil.isEmpty(enzymeDictionaryPO)){
            throw new RException(ExceptionEnum.PARAM_IS_NOT_ALL);
        }
        if (enzymeDictionaryPO.getId()==null){
            dictService.save(enzymeDictionaryPO);
            //保存日志
            enzymeLogService.saveEnzymeLog(getCurrentLoginUser(), CommonConstant.ADD, JSONObject.toJSONString(enzymeDictionaryPO));
        }else {
            dictService.updateDict(enzymeDictionaryPO);
            enzymeLogService.saveEnzymeLog(getCurrentLoginUser(), CommonConstant.UPDATE, JSONObject.toJSONString(enzymeDictionaryPO));
        }
        return ResultInfo.success();
    }

    /**
     * 增加与修改酶信息
     */
    @RequestMapping("/saveOrUpdateEnzyme")
    public ResultInfo saveOrUpdateEnzyme(EnzymeInfoDTO enzymeInfoDTO){
        //参数校验
        if(ObjectUtil.isEmpty(enzymeInfoDTO)){
            throw new RException(ExceptionEnum.PARAM_IS_NOT_ALL);
        }
        if(ObjectUtil.isEmpty(enzymeInfoDTO.getEnzymeName())){
            throw new RException(ExceptionEnum.NAME_IS_NULL);
        }
        if(ObjectUtil.isEmpty(enzymeInfoDTO.getEnzymeEcCode())){
            throw new RException(ExceptionEnum.EC_CODE_IS_NOT_NULL);
        }
        if(ObjectUtil.isEmpty(enzymeInfoDTO.getEnzymeClassification())){
            throw new RException(ExceptionEnum.CLASSIFICATION_IS_NOT_NULL);
        }
        if(ObjectUtil.isEmpty(enzymeInfoDTO.getEnzymeType())){
            throw new RException(ExceptionEnum.ENZYME_TYOE_IS_NOT_NULL);
        }
        if(ObjectUtil.isEmpty(enzymeInfoDTO.getEnzymeSpecies())){
            throw new RException(ExceptionEnum.ENZYME_SPECIES_IS_NOT_NULL);
        }
        if(ObjectUtil.isEmpty(enzymeInfoDTO.getEnzymeMutationType())){
            throw new RException(ExceptionEnum.ENZYME_MUTATION_TYPE_IS_NOT_NULL);
        }
        if(ObjectUtil.isEmpty(enzymeInfoDTO.getEnzymeSequence())||enzymeInfoDTO.getEnzymeSequence().equals("NaN")){
            throw new RException(ExceptionEnum.ENZYME_SEQUENCE_IS_NOT_NULL);
        }
        if(ObjectUtil.isEmpty(enzymeInfoDTO.getEnzymeBelong())){
            throw new RException(ExceptionEnum.ENZYME_BELONG_IS_NOT_NULL);
        }
        if (enzymeInfoDTO.getId()==null){
            enzymeBaseService.save(enzymeInfoDTO);
            enzymeLogService.saveEnzymeLog(getCurrentLoginUser(), CommonConstant.ADD, JSONObject.toJSONString(enzymeInfoDTO));
        }else {
            enzymeBaseService.update(enzymeInfoDTO);
            enzymeLogService.saveEnzymeLog(getCurrentLoginUser(), CommonConstant.UPDATE, JSONObject.toJSONString(enzymeInfoDTO));
        }
        return ResultInfo.success();
    }

    /**
     * 获取用户列表
     */
    @RequestMapping("/getUserList")
    public ResultInfo getUserList(){
        JwtUserDTO currentLoginUser = getCurrentLoginUser();
        AdminUserPO adminUserPO = adminUserService.selectOne(currentLoginUser.getUserId());
        if(adminUserPO.isAdminFlag()){
            return ResultInfo.success(adminUserService.selectAll());
        }else{
            return ResultInfo.success(adminUserPO);
        }
    }

    /**
     * 获取当前登录用户信息
     */
    @RequestMapping("/getCurrLoginUser")
    public ResultInfo getCurrLoginUser(){
        return ResultInfo.success(adminUserService.selectOne(getCurrentLoginUser().getUserId()));
    }
    /**
     * 锁定与解锁用户
     */
    @RequestMapping("/lockUser")
    public ResultInfo lockUser(@RequestParam Long userId){
        checkUserAdmin();
        adminUserService.lockUser(userId);
        return ResultInfo.success();
    }

    /**
     * 修改密码
     */
    @RequestMapping("/changePassword")
    public ResultInfo changePassword(@RequestParam Long userId,@RequestParam String newPassword){
        adminUserService.changePassword(userId,newPassword);
        return ResultInfo.success();
    }
    /**
     * 获取日志列表
     */
    @RequestMapping("/getLogList")
    public ResultInfo getLogList(@RequestParam Long userId){
        return ResultInfo.success(enzymeLogService.getLogList(userId));
    }

    /**
     * 删除酶信息
     * @param enzymeId
     * @return
     */
    @RequestMapping("/deleteEnzyme")
    public ResultInfo deleteEnzyme(@RequestParam Long enzymeId){
        return ResultInfo.success(enzymeBaseService.deleteEnzyme(enzymeId));
    }

    /**
     * 删除用户
     */
    @RequestMapping("/deleteUser")
    public ResultInfo deleteUser(@RequestParam Long userId){
        checkUserAdmin();
        return ResultInfo.success(adminUserService.deleteUser(userId));
    }

    /**
     * 删除字典
     */
    @RequestMapping("/deleteDict")
    public ResultInfo deleteDict(@RequestParam Long dictId){
        return ResultInfo.success(dictService.deleteDict(dictId));
    }
    /**
     * excel导入
     */
    @RequestMapping("/importExcel")
    @CrossOrigin
    public ResultInfo importExcel(@RequestParam(name = "file", required = false) MultipartFile file){
        return ResultInfo.success(enzymeBaseService.importExcel(getCurrentLoginUser(),file));
    }
    private void checkUserAdmin() {
        JwtUserDTO currentLoginUser = getCurrentLoginUser();
        AdminUserPO adminUserPO = adminUserService.selectOne(currentLoginUser.getUserId());
        if (!adminUserPO.isAdminFlag()) {
            throw new RException(ExceptionEnum.USER_IS_NOT_ADMIN);
        }
    }
}
