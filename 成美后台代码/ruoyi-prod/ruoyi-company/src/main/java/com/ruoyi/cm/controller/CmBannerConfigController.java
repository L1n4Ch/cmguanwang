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
import com.ruoyi.cm.domain.CmBannerConfig;
import com.ruoyi.cm.service.ICmBannerConfigService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 首页轮播图Controller
 * 
 * @author djl
 * @date 2021-05-29
 */
@Controller
@RequestMapping("/cm/bannerConfig")
public class CmBannerConfigController extends BaseController
{
    private String prefix = "cm/bannerConfig";

    @Autowired
    private ICmBannerConfigService cmBannerConfigService;

    @RequiresPermissions("cm:bannerConfig:view")
    @GetMapping()
    public String bannerConfig()
    {
        return prefix + "/bannerConfig";
    }

    /**
     * 查询首页轮播图列表
     */
    @RequiresPermissions("cm:bannerConfig:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CmBannerConfig cmBannerConfig)
    {
        startPage();
        List<CmBannerConfig> list = cmBannerConfigService.selectCmBannerConfigList(cmBannerConfig);
        return getDataTable(list);
    }

    /**
     * 导出首页轮播图列表
     */
    @RequiresPermissions("cm:bannerConfig:export")
    @Log(title = "首页轮播图", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CmBannerConfig cmBannerConfig)
    {
        List<CmBannerConfig> list = cmBannerConfigService.selectCmBannerConfigList(cmBannerConfig);
        ExcelUtil<CmBannerConfig> util = new ExcelUtil<CmBannerConfig>(CmBannerConfig.class);
        return util.exportExcel(list, "首页轮播图数据");
    }

    /**
     * 新增首页轮播图
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存首页轮播图
     */
    @RequiresPermissions("cm:bannerConfig:add")
    @Log(title = "首页轮播图", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CmBannerConfig cmBannerConfig)
    {
        return toAjax(cmBannerConfigService.insertCmBannerConfig(cmBannerConfig));
    }

    /**
     * 修改首页轮播图
     */
    @GetMapping("/edit/{bId}")
    public String edit(@PathVariable("bId") Long bId, ModelMap mmap)
    {
        CmBannerConfig cmBannerConfig = cmBannerConfigService.selectCmBannerConfigById(bId);
        mmap.put("cmBannerConfig", cmBannerConfig);
        return prefix + "/edit";
    }

    /**
     * 修改保存首页轮播图
     */
    @RequiresPermissions("cm:bannerConfig:edit")
    @Log(title = "首页轮播图", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CmBannerConfig cmBannerConfig)
    {
        return toAjax(cmBannerConfigService.updateCmBannerConfig(cmBannerConfig));
    }

    /**
     * 删除首页轮播图
     */
    @RequiresPermissions("cm:bannerConfig:remove")
    @Log(title = "首页轮播图", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(cmBannerConfigService.deleteCmBannerConfigByIds(ids));
    }
}
