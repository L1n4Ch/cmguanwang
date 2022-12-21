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
import com.ruoyi.cm.domain.CmDonationUser;
import com.ruoyi.cm.service.ICmDonationUserService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 捐赠人信息Controller
 * 
 * @author ruoyi
 * @date 2021-05-24
 */
@Controller
@RequestMapping("/cm/donationUser")
public class CmDonationUserController extends BaseController
{
    private String prefix = "cm/donationUser";

    @Autowired
    private ICmDonationUserService cmDonationUserService;

    @RequiresPermissions("cm:donationUser:view")
    @GetMapping()
    public String donationUser()
    {
        return prefix + "/donationUser";
    }

    /**
     * 查询捐赠人信息列表
     */
    @RequiresPermissions("cm:donationUser:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CmDonationUser cmDonationUser)
    {
        startPage();
        List<CmDonationUser> list = cmDonationUserService.selectCmDonationUserList(cmDonationUser);
        return getDataTable(list);
    }

    /**
     * 导出捐赠人信息列表
     */
    @RequiresPermissions("cm:donationUser:export")
    @Log(title = "捐赠人信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CmDonationUser cmDonationUser)
    {
        List<CmDonationUser> list = cmDonationUserService.selectCmDonationUserList(cmDonationUser);
        ExcelUtil<CmDonationUser> util = new ExcelUtil<CmDonationUser>(CmDonationUser.class);
        return util.exportExcel(list, "捐赠人信息数据");
    }

    /**
     * 新增捐赠人信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存捐赠人信息
     */
    @RequiresPermissions("cm:donationUser:add")
    @Log(title = "捐赠人信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CmDonationUser cmDonationUser)
    {
        return toAjax(cmDonationUserService.insertCmDonationUser(cmDonationUser));
    }

    /**
     * 修改捐赠人信息
     */
    @GetMapping("/edit/{duId}")
    public String edit(@PathVariable("duId") Long duId, ModelMap mmap)
    {
        CmDonationUser cmDonationUser = cmDonationUserService.selectCmDonationUserById(duId);
        mmap.put("cmDonationUser", cmDonationUser);
        return prefix + "/edit";
    }

    /**
     * 修改保存捐赠人信息
     */
    @RequiresPermissions("cm:donationUser:edit")
    @Log(title = "捐赠人信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CmDonationUser cmDonationUser)
    {
        return toAjax(cmDonationUserService.updateCmDonationUser(cmDonationUser));
    }

    /**
     * 删除捐赠人信息
     */
    @RequiresPermissions("cm:donationUser:remove")
    @Log(title = "捐赠人信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(cmDonationUserService.deleteCmDonationUserByIds(ids));
    }
}
