package com.ruoyi.cm.controller;

import com.ruoyi.cm.domain.CmArticle;
import com.ruoyi.cm.service.ICmArticleService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文章发布Controller
 *
 * @author djl
 * @date 2021-05-30
 */
@Controller
@RequestMapping("/cm/article")
public class CmArticleController extends BaseController {
    private String prefix = "cm/article";

    @Autowired
    private ICmArticleService cmArticleService;

    @RequiresPermissions("cm:article:view")
    @GetMapping()
    public String article() {
        return prefix + "/article";
    }

    /**
     * 查询文章发布列表
     */
    @RequiresPermissions("cm:article:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CmArticle cmArticle) {
        startPage();
        List<CmArticle> list = cmArticleService.selectCmArticleList(cmArticle);
        return getDataTable(list);
    }

    /**
     * 导出文章发布列表
     */
    @RequiresPermissions("cm:article:export")
    @Log(title = "文章发布", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CmArticle cmArticle) {
        List<CmArticle> list = cmArticleService.selectCmArticleList(cmArticle);
        ExcelUtil<CmArticle> util = new ExcelUtil<CmArticle>(CmArticle.class);
        return util.exportExcel(list, "文章发布数据");
    }

    /**
     * 新增文章发布
     */
    @GetMapping("/add")
    public String add(ModelMap mmap) {
        /**
         * 语言类型
         */
        String languageType = getRequest().getParameter("languageType");
        mmap.put("languageType", languageType);
        return prefix + "/add";
    }

    /**
     * 新增保存文章发布
     */
    @RequiresPermissions("cm:article:add")
    @Log(title = "文章发布", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CmArticle cmArticle) {
        return toAjax(cmArticleService.insertCmArticle(cmArticle));
    }

    /**
     * 修改文章发布
     */
    @GetMapping("/edit/{articleId}")
    public String edit(@PathVariable("articleId") Long articleId, ModelMap mmap) {
        CmArticle cmArticle = cmArticleService.selectCmArticleById(articleId);
        mmap.put("cmArticle", cmArticle);
        /**
         * 语言类型
         */
        String languageType = getRequest().getParameter("languageType");
        mmap.put("languageType", languageType);
        return prefix + "/edit";
    }

    /**
     * 修改保存文章发布
     */
    @RequiresPermissions("cm:article:edit")
    @Log(title = "文章发布", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CmArticle cmArticle) {
        return toAjax(cmArticleService.updateCmArticle(cmArticle));
    }

    /**
     * 删除文章发布
     */
    @RequiresPermissions("cm:article:remove")
    @Log(title = "文章发布", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(cmArticleService.deleteCmArticleByIds(ids));
    }
}
