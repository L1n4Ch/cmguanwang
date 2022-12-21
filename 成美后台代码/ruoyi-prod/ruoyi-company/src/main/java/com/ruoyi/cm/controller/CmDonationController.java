package com.ruoyi.cm.controller;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
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
import com.ruoyi.cm.domain.CmDonation;
import com.ruoyi.cm.service.ICmDonationService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 捐赠记录Controller
 * 
 * @author ruoyi
 * @date 2021-05-24
 */
@Controller
@RequestMapping("/cm/donation")
public class CmDonationController extends BaseController
{
    private String prefix = "cm/donation";

    @Autowired
    private ICmDonationService cmDonationService;

    @RequiresPermissions("cm:donation:view")
    @GetMapping()
    public String donation()
    {
        return prefix + "/donation";
    }

    /**
     * 查询捐赠记录列表
     */
    @RequiresPermissions("cm:donation:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CmDonation cmDonation)
    {
        startPage();
        List<CmDonation> list = cmDonationService.selectCmDonationList(cmDonation);
        return getDataTable(list);
    }

    /**
     * 导出捐赠记录列表
     */
    @RequiresPermissions("cm:donation:export")
    @Log(title = "捐赠记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CmDonation cmDonation)
    {
        List<CmDonation> list = cmDonationService.selectCmDonationList(cmDonation);
        ExcelUtil<CmDonation> util = new ExcelUtil<CmDonation>(CmDonation.class);
        return util.exportExcel(list, "捐赠记录数据");
    }

    /**
     * 新增捐赠记录
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存捐赠记录
     */
    @RequiresPermissions("cm:donation:add")
    @Log(title = "捐赠记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CmDonation cmDonation)
    {
        return toAjax(cmDonationService.insertCmDonation(cmDonation));
    }

    /**
     * 修改捐赠记录
     */
    @GetMapping("/edit/{donationId}")
    public String edit(@PathVariable("donationId") Long donationId, ModelMap mmap)
    {
        CmDonation cmDonation = cmDonationService.selectCmDonationById(donationId);
        mmap.put("cmDonation", cmDonation);
        return prefix + "/edit";
    }

    /**
     * 修改保存捐赠记录
     */
    @RequiresPermissions("cm:donation:edit")
    @Log(title = "捐赠记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CmDonation cmDonation)
    {
        return toAjax(cmDonationService.updateCmDonation(cmDonation));
    }

    /**
     * 删除捐赠记录
     */
    @RequiresPermissions("cm:donation:remove")
    @Log(title = "捐赠记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(cmDonationService.deleteCmDonationByIds(ids));
    }

    // 导入方法
    @RequiresPermissions("cm:donation:import")
    @Log(title = "捐赠记录", businessType = BusinessType.IMPORT)
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<CmDonation> util = new ExcelUtil<CmDonation>(CmDonation.class);
        List<CmDonation> cmDonationList = util.importExcel(file.getInputStream());
        String message = cmDonationService.importCmDonation(cmDonationList, updateSupport);
        return AjaxResult.success(message);
    }


    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<CmDonation> util = new ExcelUtil<CmDonation>(CmDonation.class);
        return util.importTemplateExcel("捐款记录");
    }
}
