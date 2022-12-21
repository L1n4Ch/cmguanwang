package com.ruoyi.cm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.ruoyi.cm.domain.CmVisits;
import com.ruoyi.cm.service.ICmVisitsService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 访问量统计Controller
 * 
 * @author ruoyi
 * @date 2021-07-01
 */
@Controller
@RequestMapping("/cm/visits")
public class CmVisitsController extends BaseController
{
    private String prefix = "cm/visits";

    @Autowired
    private ICmVisitsService cmVisitsService;

    @RequiresPermissions("cm:visits:view")
    @GetMapping()
    public String visits()
    {
        return prefix + "/visits";
    }

    /**
     * 查询访问量统计列表
     */
    @RequiresPermissions("cm:visits:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CmVisits cmVisits)
    {
        startPage();
        List<CmVisits> list = cmVisitsService.selectCmVisitsListGroupByMName(cmVisits);
        return getDataTable(list);
    }



    /**
     * 导出访问量统计列表
     */
    @RequiresPermissions("cm:visits:export")
    @Log(title = "访问量统计", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CmVisits cmVisits)
    {
        List<CmVisits> list = cmVisitsService.selectCmVisitsList(cmVisits);
        ExcelUtil<CmVisits> util = new ExcelUtil<CmVisits>(CmVisits.class);
        return util.exportExcel(list, "访问量统计数据");
    }

    /**
     * 新增访问量统计
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存访问量统计
     */
    @RequiresPermissions("cm:visits:add")
    @Log(title = "访问量统计", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CmVisits cmVisits)
    {
        return toAjax(cmVisitsService.insertCmVisits(cmVisits));
    }

    /**
     * 修改访问量统计
     */
    @GetMapping("/edit/{vid}")
    public String edit(@PathVariable("vid") Long vid, ModelMap mmap)
    {
        CmVisits cmVisits = cmVisitsService.selectCmVisitsById(vid);
        mmap.put("cmVisits", cmVisits);
        return prefix + "/edit";
    }

    /**
     * 修改保存访问量统计
     */
    @RequiresPermissions("cm:visits:edit")
    @Log(title = "访问量统计", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CmVisits cmVisits)
    {
        return toAjax(cmVisitsService.updateCmVisits(cmVisits));
    }

    /**
     * 删除访问量统计
     */
    @RequiresPermissions("cm:visits:remove")
    @Log(title = "访问量统计", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(cmVisitsService.deleteCmVisitsByIds(ids));
    }

    /**
     * 获取访问量
     * @return
     */
    @GetMapping("/getData")
    @ResponseBody
    public AjaxResult getData(){
        // 按小时统计当天
        List<Integer> hoursCount =  cmVisitsService.getDayData();
        // 按天统计周
        List<Integer> weekCount =  cmVisitsService.getWeekData();
        // 按天统计月
        List<Integer> monthCount =  cmVisitsService.getMonthData();
        AjaxResult retMap = new AjaxResult();
        retMap.put("hoursCount",hoursCount);
        retMap.put("weekCount",weekCount);
        retMap.put("monthCount",monthCount);
        retMap.put("hoursSum",hoursCount.stream().reduce(0,(x,y)->x+y));
        retMap.put("weekSum",weekCount.stream().reduce(0,(x,y)->x+y));
        retMap.put("monthSum",monthCount.stream().reduce(0,(x,y)->x+y));
        return retMap;
    }
}
