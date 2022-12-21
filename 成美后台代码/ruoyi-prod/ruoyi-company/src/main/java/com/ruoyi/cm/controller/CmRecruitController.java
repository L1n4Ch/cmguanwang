package com.ruoyi.cm.controller;

import java.util.List;
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
import com.ruoyi.cm.domain.CmRecruit;
import com.ruoyi.cm.service.ICmRecruitService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 人才招聘Controller
 * 
 * @author ruoyi
 * @date 2021-06-01
 */
@Controller
@RequestMapping("/cm/recruit")
public class CmRecruitController extends BaseController
{
    private String prefix = "cm/recruit";

    @Autowired
    private ICmRecruitService cmRecruitService;

    @RequiresPermissions("cm:recruit:view")
    @GetMapping()
    public String recruit()
    {
        return prefix + "/recruit";
    }

    /**
     * 查询人才招聘列表
     */
    @RequiresPermissions("cm:recruit:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CmRecruit cmRecruit)
    {
        startPage();
        List<CmRecruit> list = cmRecruitService.selectCmRecruitList(cmRecruit);
        return getDataTable(list);
    }

    /**
     * 导出人才招聘列表
     */
    @RequiresPermissions("cm:recruit:export")
    @Log(title = "人才招聘", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CmRecruit cmRecruit)
    {
        List<CmRecruit> list = cmRecruitService.selectCmRecruitList(cmRecruit);
        ExcelUtil<CmRecruit> util = new ExcelUtil<CmRecruit>(CmRecruit.class);
        return util.exportExcel(list, "人才招聘数据");
    }

    /**
     * 新增人才招聘
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存人才招聘
     */
    @RequiresPermissions("cm:recruit:add")
    @Log(title = "人才招聘", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CmRecruit cmRecruit)
    {
        return toAjax(cmRecruitService.insertCmRecruit(cmRecruit));
    }

    /**
     * 修改人才招聘
     */
    @GetMapping("/edit/{positionId}")
    public String edit(@PathVariable("positionId") Long positionId, ModelMap mmap)
    {
        CmRecruit cmRecruit = cmRecruitService.selectCmRecruitById(positionId);
        mmap.put("cmRecruit", cmRecruit);
        return prefix + "/edit";
    }

    /**
     * 修改保存人才招聘
     */
    @RequiresPermissions("cm:recruit:edit")
    @Log(title = "人才招聘", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CmRecruit cmRecruit)
    {
        return toAjax(cmRecruitService.updateCmRecruit(cmRecruit));
    }

    /**
     * 删除人才招聘
     */
    @RequiresPermissions("cm:recruit:remove")
    @Log(title = "人才招聘", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(cmRecruitService.deleteCmRecruitByIds(ids));
    }
}
