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
import com.ruoyi.cm.domain.CmJoinService;
import com.ruoyi.cm.service.ICmJoinServiceService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 加入-志愿列表Controller
 * 
 * @author ruoyi
 * @date 2021-05-25
 */
@Controller
@RequestMapping("/cm/joinService")
public class CmJoinServiceController extends BaseController
{
    private String prefix = "cm/joinService";

    @Autowired
    private ICmJoinServiceService cmJoinServiceService;

    @RequiresPermissions("cm:joinService:view")
    @GetMapping()
    public String joinService()
    {
        return prefix + "/joinService";
    }

    /**
     * 查询加入-志愿列表列表
     */
    @RequiresPermissions("cm:joinService:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CmJoinService cmJoinService)
    {
        startPage();
        List<CmJoinService> list = cmJoinServiceService.selectCmJoinServiceList(cmJoinService);
        return getDataTable(list);
    }

    /**
     * 导出加入-志愿列表列表
     */
    @RequiresPermissions("cm:joinService:export")
    @Log(title = "加入-志愿列表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CmJoinService cmJoinService)
    {
        List<CmJoinService> list = cmJoinServiceService.selectCmJoinServiceList(cmJoinService);
        ExcelUtil<CmJoinService> util = new ExcelUtil<CmJoinService>(CmJoinService.class);
        return util.exportExcel(list, "加入-志愿列表数据");
    }

    /**
     * 新增加入-志愿列表
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存加入-志愿列表
     */
    @RequiresPermissions("cm:joinService:add")
    @Log(title = "加入-志愿列表", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CmJoinService cmJoinService)
    {
        return toAjax(cmJoinServiceService.insertCmJoinService(cmJoinService));
    }

    /**
     * 修改加入-志愿列表
     */
    @GetMapping("/edit/{jsId}")
    public String edit(@PathVariable("jsId") Long jsId, ModelMap mmap)
    {
        CmJoinService cmJoinService = cmJoinServiceService.selectCmJoinServiceById(jsId);
        mmap.put("cmJoinService", cmJoinService);
        return prefix + "/edit";
    }

    /**
     * 修改保存加入-志愿列表
     */
    @RequiresPermissions("cm:joinService:edit")
    @Log(title = "加入-志愿列表", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CmJoinService cmJoinService)
    {
        return toAjax(cmJoinServiceService.updateCmJoinService(cmJoinService));
    }

    /**
     * 删除加入-志愿列表
     */
    @RequiresPermissions("cm:joinService:remove")
    @Log(title = "加入-志愿列表", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(cmJoinServiceService.deleteCmJoinServiceByIds(ids));
    }
}
