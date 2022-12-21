package com.ruoyi.cm.controller;

import java.util.List;

import com.ruoyi.cm.domain.CmFileInfo;
import com.ruoyi.cm.service.ICmFileInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.cm.domain.CmBaseInfo;
import com.ruoyi.cm.service.ICmBaseInfoService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 基础信息Controller
 * 
 * @author ruoyi
 * @date 2021-05-23
 */
@Controller
@RequestMapping("/cm/baseInfo")
public class CmBaseInfoController extends BaseController
{
    private String prefix = "cm/baseInfo";

    @Autowired
    private ICmBaseInfoService cmBaseInfoService;
    @Autowired
    private ICmFileInfoService cmFileInfoService;

    @RequiresPermissions("cm:baseInfo:view")
    @GetMapping()
    public String baseInfo(ModelMap mmap)
    {
        CmBaseInfo cmBaseInfo = cmBaseInfoService.selectCmBaseInfoList(new CmBaseInfo()).get(0);
        mmap.put("cmBaseInfo",cmBaseInfo);
        Long logo = cmBaseInfo.getLogo();
        Long wxImg = cmBaseInfo.getWxImg();

        Long orphanW = cmBaseInfo.getOrphanWeixin();
        Long orphanZ = cmBaseInfo.getOrphanZhifubao();

        Long girlW = cmBaseInfo.getGirlWeixin() ;
        Long girlZ = cmBaseInfo.getGirlZhifubao();

        CmFileInfo logoInfo =
                cmFileInfoService.selectCmFileInfoById(logo);
        CmFileInfo wxImgInfo =
                cmFileInfoService.selectCmFileInfoById(wxImg);
        //根据id获取微信和支付宝二维码图片
        CmFileInfo orphanWInfo = cmFileInfoService.selectCmFileInfoById(orphanW);
        CmFileInfo orphanZInfo = cmFileInfoService.selectCmFileInfoById(orphanZ);

        CmFileInfo girlWInfo = cmFileInfoService.selectCmFileInfoById(girlW);
        CmFileInfo girlZInfo = cmFileInfoService.selectCmFileInfoById(girlZ);

        if(logoInfo != null){
            mmap.put("logoUrl",logoInfo.getFileUrl());
        }
        if(wxImgInfo != null){
            mmap.put("wxImgUrl",wxImgInfo.getFileUrl());
        }

        if(orphanWInfo != null)
            mmap.put("orphanWUrl" ,orphanWInfo.getFileUrl());
        if(orphanZInfo != null)
            mmap.put("orphanZUrl" ,orphanZInfo.getFileUrl());
        if(girlWInfo != null)
            mmap.put("girlWUrl" ,girlWInfo.getFileUrl());
        if(girlZInfo != null)
            mmap.put("girlZUrl" ,girlZInfo.getFileUrl());

        return prefix + "/baseInfo";
    }


    /**
     * 修改保存基础信息
     */
    @RequiresPermissions("cm:baseInfo:edit")
    @Log(title = "基础信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CmBaseInfo cmBaseInfo)
    {
        return toAjax(cmBaseInfoService.updateCmBaseInfo(cmBaseInfo));
    }
}
