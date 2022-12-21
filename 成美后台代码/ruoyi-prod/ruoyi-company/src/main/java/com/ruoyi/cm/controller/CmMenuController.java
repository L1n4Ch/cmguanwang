package com.ruoyi.cm.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.ruoyi.cm.domain.CmFileInfo;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.framework.web.service.DictService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.cm.domain.CmMenu;
import com.ruoyi.cm.service.ICmMenuService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.domain.Ztree;

/**
 * 成美菜单Controller
 *
 * @author djl
 * @date 2021-05-29
 */
@Controller
@RequestMapping("/cm/menu")
public class CmMenuController extends BaseController {
    private String prefix = "cm/menu";

    @Autowired
    private ICmMenuService cmMenuService;
    @Autowired
    private DictService dictService;

    @RequiresPermissions("cm:menu:view")
    @GetMapping()
    public String menu() {
        return prefix + "/menu";
    }

    /**
     * 查询成美菜单树列表
     */
    @RequiresPermissions("cm:menu:list")
    @PostMapping("/list")
    @ResponseBody
    public List<CmMenu> list(CmMenu cmMenu) {
        List<CmMenu> list = cmMenuService.selectCmMenuList(cmMenu);
        return list;
    }

    /**
     * 根据菜单ID获取菜单信息
     *
     * @param menuId
     * @return
     */
    @RequiresPermissions("cm:menu:list")
    @PostMapping("/get")
    @ResponseBody
    public CmMenu get(String menuId) {
        CmMenu cmMenu = cmMenuService.selectCmMenuById(Long.valueOf(menuId));
        // 图片裁剪比例初始化
        String parentName = cmMenu.getParentName();
        String menuName = cmMenu.getMenuName();
        List<SysDictData> rate = dictService.getType("img_cut_rate");
        if (rate != null && rate.size() > 0) {
            for (SysDictData x : rate) {
                if (x.getDictLabel().equals(parentName) || x.getDictLabel().equals(menuName)) {
                    String dictValue = x.getDictValue();
                    CmFileInfo imageFile = cmMenu.getImageFile();
                    if (imageFile != null) {
                        imageFile.setRate(Double.valueOf(dictValue));
                    } else {
                        imageFile = new CmFileInfo();
                        imageFile.setRate(Double.valueOf(dictValue));
                        cmMenu.setImageFile(imageFile);
                    }
                    break;
                }
            }
        }
        return cmMenu;
    }

    /**
     * 导出成美菜单列表
     */
    @RequiresPermissions("cm:menu:export")
    @Log(title = "成美菜单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CmMenu cmMenu) {
        List<CmMenu> list = cmMenuService.selectCmMenuList(cmMenu);
        ExcelUtil<CmMenu> util = new ExcelUtil<CmMenu>(CmMenu.class);
        return util.exportExcel(list, "成美菜单数据");
    }

    /**
     * 新增成美菜单
     */
    @GetMapping(value = {"/add/{menuId}", "/add/"})
    public String add(@PathVariable(value = "menuId", required = false) Long menuId, ModelMap mmap) {
        if (StringUtils.isNotNull(menuId)) {
            mmap.put("cmMenu", cmMenuService.selectCmMenuById(menuId));
        }
        /**
         * 语言类型
         */
        String languageType = getRequest().getParameter("languageType");
        mmap.put("languageType", languageType);
        return prefix + "/add";
    }

    /**
     * 新增保存成美菜单
     */
    @RequiresPermissions("cm:menu:add")
    @Log(title = "成美菜单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CmMenu cmMenu) {
        return toAjax(cmMenuService.insertCmMenu(cmMenu));
    }

    /**
     * 修改成美菜单
     */
    @GetMapping("/edit/{menuId}")
    public String edit(@PathVariable("menuId") Long menuId, ModelMap mmap) {
        CmMenu cmMenu = cmMenuService.selectCmMenuById(menuId);
        mmap.put("cmMenu", cmMenu);
        /**
         * 语言类型
         */
        String languageType = getRequest().getParameter("languageType");
        mmap.put("languageType", languageType);
        return prefix + "/edit";
    }

    /**
     * 修改保存成美菜单
     */
    @RequiresPermissions("cm:menu:edit")
    @Log(title = "成美菜单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CmMenu cmMenu) {
        return toAjax(cmMenuService.updateCmMenu(cmMenu));
    }

    /**
     * 删除
     */
    @RequiresPermissions("cm:menu:remove")
    @Log(title = "成美菜单", businessType = BusinessType.DELETE)
    @GetMapping("/remove/{menuId}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("menuId") Long menuId) {
        return toAjax(cmMenuService.deleteCmMenuById(menuId));
    }

    /**
     * 选择成美菜单树
     */
    @GetMapping(value = {"/selectMenuTree/{menuId}", "/selectMenuTree/"})
    public String selectMenuTree(@PathVariable(value = "menuId", required = false) Long menuId, ModelMap mmap) {
        if (StringUtils.isNotNull(menuId)) {
            mmap.put("cmMenu", cmMenuService.selectCmMenuById(menuId));
        }
        /**
         * 语言类型
         */
        String languageType = getRequest().getParameter("languageType");
        mmap.put("languageType", languageType);
        return prefix + "/tree";
    }

    /**
     * 加载成美菜单树列表
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData(@RequestParam(name = "languageType",defaultValue = "0") String languageType) {
        List<Ztree> ztrees = cmMenuService.selectCmMenuTree(languageType);
        return ztrees;
    }
}
