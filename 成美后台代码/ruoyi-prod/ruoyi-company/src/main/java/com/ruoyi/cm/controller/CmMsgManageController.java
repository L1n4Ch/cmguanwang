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
import com.ruoyi.cm.domain.CmMsgManage;
import com.ruoyi.cm.service.ICmMsgManageService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 留言管理Controller
 * 
 * @author ruoyi
 * @date 2021-05-24
 */
@Controller
@RequestMapping("/cm/msgManage")
public class CmMsgManageController extends BaseController
{
    private String prefix = "cm/msgManage";

    @Autowired
    private ICmMsgManageService cmMsgManageService;

    @RequiresPermissions("cm:msgManage:view")
    @GetMapping()
    public String msgManage()
    {
        return prefix + "/msgManage";
    }

    /**
     * 查询留言管理列表
     */
    @RequiresPermissions("cm:msgManage:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CmMsgManage cmMsgManage)
    {
        startPage();
        List<CmMsgManage> list = cmMsgManageService.selectCmMsgManageList(cmMsgManage);
        return getDataTable(list);
    }

    /**
     * 导出留言管理列表
     */
    @RequiresPermissions("cm:msgManage:export")
    @Log(title = "留言管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CmMsgManage cmMsgManage)
    {
        List<CmMsgManage> list = cmMsgManageService.selectCmMsgManageList(cmMsgManage);
        ExcelUtil<CmMsgManage> util = new ExcelUtil<CmMsgManage>(CmMsgManage.class);
        return util.exportExcel(list, "留言管理数据");
    }

    /**
     * 新增留言管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存留言管理
     */
    @RequiresPermissions("cm:msgManage:add")
    @Log(title = "留言管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CmMsgManage cmMsgManage)
    {
        return toAjax(cmMsgManageService.insertCmMsgManage(cmMsgManage));
    }

    /**
     * 修改留言管理
     */
    @GetMapping("/edit/{msgId}")
    public String edit(@PathVariable("msgId") Long msgId, ModelMap mmap)
    {
        CmMsgManage cmMsgManage = cmMsgManageService.selectCmMsgManageById(msgId);
        mmap.put("cmMsgManage", cmMsgManage);
        return prefix + "/edit";
    }

    /**
     * 修改保存留言管理
     */
    @RequiresPermissions("cm:msgManage:edit")
    @Log(title = "留言管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CmMsgManage cmMsgManage)
    {
        return toAjax(cmMsgManageService.updateCmMsgManage(cmMsgManage));
    }

    /**
     * 删除留言管理
     */
    @RequiresPermissions("cm:msgManage:remove")
    @Log(title = "留言管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(cmMsgManageService.deleteCmMsgManageByIds(ids));
    }
}
