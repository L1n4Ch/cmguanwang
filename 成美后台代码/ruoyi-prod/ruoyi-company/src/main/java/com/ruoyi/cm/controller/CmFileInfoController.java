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
import com.ruoyi.cm.domain.CmFileInfo;
import com.ruoyi.cm.service.ICmFileInfoService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 文件信息Controller
 * 
 * @author ruoyi
 * @date 2021-05-23
 */
@Controller
@RequestMapping("/cm/fileInfo")
public class CmFileInfoController extends BaseController
{
    private String prefix = "cm/fileInfo";

    @Autowired
    private ICmFileInfoService cmFileInfoService;

    @RequiresPermissions("cm:fileInfo:view")
    @GetMapping()
    public String fileInfo()
    {
        return prefix + "/fileInfo";
    }

    /**
     * 查询文件信息列表
     */
    @RequiresPermissions("cm:fileInfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CmFileInfo cmFileInfo)
    {
        startPage();
        List<CmFileInfo> list = cmFileInfoService.selectCmFileInfoList(cmFileInfo);
        return getDataTable(list);
    }

    /**
     * 导出文件信息列表
     */
    @RequiresPermissions("cm:fileInfo:export")
    @Log(title = "文件信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CmFileInfo cmFileInfo)
    {
        List<CmFileInfo> list = cmFileInfoService.selectCmFileInfoList(cmFileInfo);
        ExcelUtil<CmFileInfo> util = new ExcelUtil<CmFileInfo>(CmFileInfo.class);
        return util.exportExcel(list, "文件信息数据");
    }

    /**
     * 新增文件信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存文件信息
     */
    @RequiresPermissions("cm:fileInfo:add")
    @Log(title = "文件信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CmFileInfo cmFileInfo)
    {
        return toAjax(cmFileInfoService.insertCmFileInfo(cmFileInfo));
    }

    /**
     * 修改文件信息
     */
    @GetMapping("/edit/{fId}")
    public String edit(@PathVariable("fId") Long fId, ModelMap mmap)
    {
        CmFileInfo cmFileInfo = cmFileInfoService.selectCmFileInfoById(fId);
        mmap.put("cmFileInfo", cmFileInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存文件信息
     */
    @RequiresPermissions("cm:fileInfo:edit")
    @Log(title = "文件信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CmFileInfo cmFileInfo)
    {
        return toAjax(cmFileInfoService.updateCmFileInfo(cmFileInfo));
    }

    /**
     * 删除文件信息
     */
    @RequiresPermissions("cm:fileInfo:remove")
    @Log(title = "文件信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(cmFileInfoService.deleteCmFileInfoByIds(ids));
    }
}
