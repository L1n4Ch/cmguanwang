package com.ruoyi.cm.controller;

import com.ruoyi.cm.domain.CmFileInfo;
import com.ruoyi.cm.service.ICmFileInfoService;
import com.ruoyi.cm.service.impl.CmFileInfoServiceImpl;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.config.ServerConfig;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.system.service.ISysConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.Map;

/**
 * 公用控制类
 */
@Controller
@RequestMapping("/cm/common")
public class CmCommonController {

    private static final Logger log = LoggerFactory.getLogger(CmCommonController.class);

    @Autowired
    private ServerConfig serverConfig;
    @Autowired
    private ICmFileInfoService cmFileInfoService;
    @Autowired
    private ISysConfigService sysConfigService;

    private String prefix = "cm";
    /**
     * 打开图片剪切页面
     * @param mmap
     * @param imgId
     * @param aspectRatio 剪切比例
     * @return
     */
    @GetMapping("/bgImg")
    public String bgImg(ModelMap mmap,String imgId,String aspectRatio)
    {
        mmap.put("aspectRatio",aspectRatio);
        // 根据图片ID获取图片信息
        if(StringUtils.isNotEmpty(imgId) && StringUtils.isNumeric(imgId)){
            CmFileInfo cmFileInfo = cmFileInfoService.selectCmFileInfoById(Long.valueOf(imgId));
            if(cmFileInfo != null){
                mmap.put("cmFileInfo",cmFileInfo);
            }else{
                // 图片不存在的情况
                mmap.put("cmFileInfo",new CmFileInfo());
            }
        }
        return prefix + "/bgImg";
    }
    /**
     * 打开图片剪切页面
     * @param mmap
     * @param imgId
     * @param aspectRatio 剪切比例
     * @return
     */
    @GetMapping("/imgUpload")
    public String imageUpload(ModelMap mmap,String imgId,String aspectRatio)
    {
        mmap.put("aspectRatio",aspectRatio);
        // 根据图片ID获取图片信息
        if(StringUtils.isNotEmpty(imgId) && StringUtils.isNumeric(imgId)){
            CmFileInfo cmFileInfo = cmFileInfoService.selectCmFileInfoById(Long.valueOf(imgId));
            if(cmFileInfo != null){
                mmap.put("cmFileInfo",cmFileInfo);
            }else{
                // 图片不存在的情况
                mmap.put("cmFileInfo",new CmFileInfo());
            }
        }
        return prefix + "/imgUpload";
    }

    /**
     * 上传图片
     */
    @PostMapping("/uploadImg")
    @ResponseBody
    public AjaxResult uploadFile(@RequestParam("imageFile") MultipartFile file,@RequestParam(value = "type",required=false) String type) throws Exception
    {
        try
        {
            // 获取图片大小限制参数
            String fileLimit = sysConfigService.selectConfigByKey("img_limit");
            // 上传并返回新文件名称
            Map<String,Object> map = FileUploadUtils.upload2(file,type);

            File f = (File) map.get("file");
            String url = (String) map.get("url");
            CmFileInfo cmFileInfo = new CmFileInfo();
            cmFileInfo.setFileType("0");
            if("pdf".equals(type)){
                cmFileInfo.setFileType("1"); // 上传PDF
                fileLimit = sysConfigService.selectConfigByKey("pdf_limit");
            }
            volidate(fileLimit,file.getSize());
            cmFileInfo.setFileUrl(url);
            cmFileInfo.setFileSize(file.getSize()+"");
            cmFileInfo.setFileName(f.getName());
            cmFileInfo.setFileAddr(f.getAbsolutePath());
            cmFileInfo.setCreateBy(ShiroUtils.getSysUser().getUserName());
            cmFileInfo.setCreateTime(new Date());

            // 保存数据库
            cmFileInfoService.insertCmFileInfo(cmFileInfo);
            AjaxResult ajax = AjaxResult.success();
            ajax.put("cmFileInfo", cmFileInfo);
            return ajax;
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     *
     * @param fileLimit 配置的文件限制大小
     * @param size 文件大小
     */
    private void volidate(String fileLimit, long size) {
        if(StringUtils.isNotEmpty(fileLimit) && StringUtils.isNumeric(fileLimit)){
           if( Long.valueOf(fileLimit) < size){
               throw new BaseException("文件大小超过配置限制大小！");
           }
        }
    }

}
