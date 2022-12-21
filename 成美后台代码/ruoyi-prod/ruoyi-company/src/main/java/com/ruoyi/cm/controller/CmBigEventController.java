package com.ruoyi.cm.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ruoyi.common.utils.file.FileUploadUtils;
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
import com.ruoyi.cm.domain.CmBigEvent;
import com.ruoyi.cm.service.ICmBigEventService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;
import com.ruoyi.common.utils.file.FileUploadUtils;

/**
 * 成美大事件Controller
 * 
 * @author ruoyi
 * @date 2021-05-28
 */
@Controller
@RequestMapping("/cm/bigEvent")
public class CmBigEventController extends BaseController
{
    private String prefix = "cm/bigEvent";

    @Autowired
    private ICmBigEventService cmBigEventService;

    @RequiresPermissions("cm:bigEvent:view")
    @GetMapping()
    public String bigEvent()
    {
        return prefix + "/bigEvent";
    }

    /**
     * 查询成美大事件列表
     */
    @RequiresPermissions("cm:bigEvent:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CmBigEvent cmBigEvent)
    {
        startPage();
        List<CmBigEvent> list = cmBigEventService.selectCmBigEventList(cmBigEvent);
        return getDataTable(list);
    }

    /**
     * 导出成美大事件列表
     */
    @RequiresPermissions("cm:bigEvent:export")
    @Log(title = "成美大事件", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CmBigEvent cmBigEvent)
    {
        List<CmBigEvent> list = cmBigEventService.selectCmBigEventList(cmBigEvent);
        ExcelUtil<CmBigEvent> util = new ExcelUtil<CmBigEvent>(CmBigEvent.class);
        return util.exportExcel(list, "成美大事件数据");
    }

    /**
     * 新增成美大事件
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存成美大事件
     */
    @RequiresPermissions("cm:bigEvent:add")
    @Log(title = "成美大事件", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CmBigEvent cmBigEvent)
    {
        System.out.println(cmBigEvent.toString());
        return toAjax(cmBigEventService.insertCmBigEvent(cmBigEvent));
    }

    /**
     * 修改成美大事件
     */
    @GetMapping("/edit/{beId}")
    public String edit(@PathVariable("beId") Long beId, ModelMap mmap)
    {
        CmBigEvent cmBigEvent = cmBigEventService.selectCmBigEventById(beId);
        mmap.put("cmBigEvent", cmBigEvent);
        return prefix + "/edit";
    }

    /**
     * 修改保存成美大事件
     */
    @RequiresPermissions("cm:bigEvent:edit")
    @Log(title = "成美大事件", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CmBigEvent cmBigEvent)
    {
        return toAjax(cmBigEventService.updateCmBigEvent(cmBigEvent));
    }

    /**
     * 删除成美大事件
     */
    @RequiresPermissions("cm:bigEvent:remove")
    @Log(title = "成美大事件", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(cmBigEventService.deleteCmBigEventByIds(ids));
    }
}
