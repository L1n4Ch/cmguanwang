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
import com.ruoyi.cm.domain.CmService;
import com.ruoyi.cm.service.ICmServiceService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 志愿服务列表Controller
 * 
 * @author ruoyi
 * @date 2021-05-25
 */
@Controller
@RequestMapping("/cm/service")
public class CmServiceController extends BaseController
{
    private String prefix = "cm/service";

    @Autowired
    private ICmServiceService cmServiceService;

    @RequiresPermissions("cm:service:view")
    @GetMapping()
    public String service()
    {
        return prefix + "/service";
    }

    /**
     * 查询志愿服务列表列表
     */
    @RequiresPermissions("cm:service:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CmService cmService)
    {
        startPage();
        List<CmService> list = cmServiceService.selectCmServiceList(cmService);
        return getDataTable(list);
    }

    /**
     * 导出志愿服务列表列表
     */
    @RequiresPermissions("cm:service:export")
    @Log(title = "志愿服务列表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CmService cmService)
    {
        List<CmService> list = cmServiceService.selectCmServiceList(cmService);
        ExcelUtil<CmService> util = new ExcelUtil<CmService>(CmService.class);
        return util.exportExcel(list, "志愿服务列表数据");
    }

    /**
     * 新增志愿服务列表
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存志愿服务列表
     */
    @RequiresPermissions("cm:service:add")
    @Log(title = "志愿服务列表", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CmService cmService)
    {
        return toAjax(cmServiceService.insertCmService(cmService));
    }

    /**
     * 修改志愿服务列表
     */
    @GetMapping("/edit/{sId}")
    public String edit(@PathVariable("sId") Long sId, ModelMap mmap)
    {
        CmService cmService = cmServiceService.selectCmServiceById(sId);
        mmap.put("cmService", cmService);
        return prefix + "/edit";
    }

    /**
     * 修改保存志愿服务列表
     */
    @RequiresPermissions("cm:service:edit")
    @Log(title = "志愿服务列表", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CmService cmService)
    {
        return toAjax(cmServiceService.updateCmService(cmService));
    }

    /**
     * 删除志愿服务列表
     */
    @RequiresPermissions("cm:service:remove")
    @Log(title = "志愿服务列表", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(cmServiceService.deleteCmServiceByIds(ids));
    }
}
