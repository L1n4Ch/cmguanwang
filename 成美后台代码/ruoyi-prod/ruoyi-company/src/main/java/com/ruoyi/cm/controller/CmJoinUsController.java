package com.ruoyi.cm.controller;

import java.util.List;

import com.ruoyi.cm.domain.CmJoinService;
import com.ruoyi.cm.domain.CmService;
import com.ruoyi.cm.service.ICmJoinServiceService;
import com.ruoyi.cm.service.ICmServiceService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.cm.domain.CmJoinUs;
import com.ruoyi.cm.service.ICmJoinUsService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 加入我们Controller
 * 
 * @author ruoyi
 * @date 2021-05-25
 */
@Controller
@RequestMapping("/cm/us")
public class CmJoinUsController extends BaseController
{
    private String prefix = "cm/us";

    @Autowired
    private ICmJoinUsService cmJoinUsService;

    @Autowired
    private ICmServiceService cmServiceService ;

    @Autowired
    private ICmJoinServiceService cmJoinServiceService ;

    @RequiresPermissions("cm:us:view")
    @GetMapping()
    public String us()
    {
        return prefix + "/us";
    }

    /**
     * 查询加入我们列表
     */
    @RequiresPermissions("cm:us:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CmJoinUs cmJoinUs)
    {
        startPage();
        List<CmJoinUs> list = cmJoinUsService.selectCmJoinUsList(cmJoinUs);
        return getDataTable(list);
    }

    /**
     * 导出加入我们列表
     */
    @RequiresPermissions("cm:us:export")
    @Log(title = "加入我们", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CmJoinUs cmJoinUs)
    {
        List<CmJoinUs> list = cmJoinUsService.selectCmJoinUsList(cmJoinUs);
        ExcelUtil<CmJoinUs> util = new ExcelUtil<CmJoinUs>(CmJoinUs.class);
        return util.exportExcel(list, "加入我们数据");
    }

    /**
     * 新增加入我们
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存加入我们
     */
    @RequiresPermissions("cm:us:add")
    @Log(title = "加入我们", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CmJoinUs cmJoinUs, @RequestParam(value = "ids") List<Long> ids)
    {
        return toAjax(cmJoinUsService.insertCmJoinUs(cmJoinUs, ids));
    }

    /**
     * 修改加入我们
     */
    @GetMapping("/edit/{jId}")
    public String edit(@PathVariable("jId") Long jId, ModelMap mmap)
    {
        CmJoinUs cmJoinUs = cmJoinUsService.selectCmJoinUsById(jId);
        //查询服务列表
        List<CmService> cmServiceList = cmServiceService.selectCmServiceList(null);
        //查询用户选择的选项
        CmJoinService cjs = new CmJoinService();
        cjs.setjId(jId);
        //查询用户选择的列表
        List<CmJoinService> cmJoinServiceList = cmJoinServiceService.selectCmJoinServiceList(cjs);

        //for
        for (CmService js: cmServiceList) {
            for (CmJoinService cmj:cmJoinServiceList) {
                if(js.getsId().equals(cmj.getsId())){
                    js.setCreateBy("1");
                    break;
                }
            }
        }
        /*for (CmService js: cmServiceList) {
            System.out.println(js.getCreateBy()  + "--------------");
        }*/
        mmap.put("cmJoinUs", cmJoinUs);
        mmap.put("cmServiceList" , cmServiceList) ;
        return prefix + "/edit";
    }

    /**
     * 修改保存加入我们
     */
    @RequiresPermissions("cm:us:edit")
    @Log(title = "加入我们", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CmJoinUs cmJoinUs)
    {
        return toAjax(cmJoinUsService.updateCmJoinUs(cmJoinUs));
    }

    /**
     * 删除加入我们
     */
    @RequiresPermissions("cm:us:remove")
    @Log(title = "加入我们", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(cmJoinUsService.deleteCmJoinUsByIds(ids));
    }
}
