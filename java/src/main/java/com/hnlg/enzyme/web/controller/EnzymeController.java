package com.hnlg.enzyme.web.controller;

import com.hnlg.enzyme.entity.query.BaseQuery;
import com.hnlg.enzyme.entity.query.QueryEnzyme;
import com.hnlg.enzyme.service.EnzymeBaseService;
import com.hnlg.enzyme.service.EnzymeInfoService;
import com.hnlg.enzyme.util.ResultInfo;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 酶信息相关控制器
 */
@RestController
@RequestMapping("/enzyme")
public class EnzymeController {
    @Resource
    private EnzymeBaseService enzymeBaseService;
    @Resource
    private EnzymeInfoService enzymeInfoService;

    /**
     * 查询首页列表
     * @param qo
     * @return
     */
    @RequestMapping("/findEnzymeList")
    public ResultInfo findEnzymeList(BaseQuery qo){
        return ResultInfo.success(enzymeBaseService.findEnzymeList(qo));
    }

    /**
     * 查询对应的详细信息
     */
    @RequestMapping("/findEnzymeInfo")
    public ResultInfo findEnzymeInfo(@RequestParam Long enzymeId){
        return ResultInfo.success(enzymeInfoService.selectOne(enzymeId));
    }
    /**
     * 根据分类查询
     */
    @RequestMapping("/findEnzymeByDict")
    public ResultInfo findEnzymeByDict(QueryEnzyme queryEnzyme){
        return ResultInfo.success(enzymeBaseService.findEnzymeByDict(queryEnzyme));
    }
    /**
     * 查询数据的条数
     */
    @RequestMapping("/getEnztmeNum")
    public ResultInfo getEnztmeNum(){
        return ResultInfo.success(enzymeBaseService.getEnztmeNum());
    }
    /**
     * 多选展示功能
     */
    @RequestMapping("/findEnzymeByIdList")
    public ResultInfo findEnzymeByIdList(@RequestParam("ids") String ids){
        return ResultInfo.success(enzymeBaseService.findEnzymeByIdList(ids));
    }
    /**
     * 复合搜索功能
     */
    @RequestMapping("/complexFindToEnzyme")
    public ResultInfo complexFindToEnzyme(QueryEnzyme qo){
        return ResultInfo.success(enzymeBaseService.complexFindToEnzyme(qo));
    }
    /**
     * 首页数据统计
     */
    @RequestMapping("/countEnzymeData")
    public ResultInfo countEnzyme(){
        return ResultInfo.success(enzymeBaseService.countEnzymeData());
    }

    @RequestMapping("/File/download")
    public void  testDownload(HttpServletResponse response ) {
        //待下载文件名
        String fileName = "超稳酶数据库导入文档.xlsx";
        try {
            response.setHeader("Content-disposition",String.format("attachment; filename=\"%s\"", new String(fileName.getBytes("UTF-8"), "iso-8859-1")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setContentType("application/octet-stream");
        byte[] buff = new byte[1024];
        //创建缓冲输入流
        BufferedInputStream bis = null;
        OutputStream outputStream = null;

        try {
            outputStream = response.getOutputStream();

            //这个路径为待下载文件的路径
            bis = new BufferedInputStream(new FileInputStream(new File("/usr/local/hnlgenzyme/file/" + fileName )));
//            bis = new BufferedInputStream(new FileInputStream(new File("/Users/gaozhirui/" + fileName )));
            int read = bis.read(buff);

            //通过while循环写入到指定了的文件夹中
            while (read != -1) {
                outputStream.write(buff, 0, buff.length);
                outputStream.flush();
                read = bis.read(buff);
            }
        } catch ( IOException e ) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
