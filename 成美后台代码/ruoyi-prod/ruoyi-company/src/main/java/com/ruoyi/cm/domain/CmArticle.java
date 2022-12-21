package com.ruoyi.cm.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 文章发布对象 cm_article
 *
 * @author djl
 * @date 2021-05-30
 */
public class CmArticle extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long articleId;

    /**
     * 菜单ID
     */
    @Excel(name = "菜单ID")
    private Long menuId;

    private CmMenu cmMenu;//菜单信息

    /**
     * 标题
     */
    @Excel(name = "标题")
    private String title;

    /**
     * 简介
     */
    @Excel(name = "简介")
    private String introduce;

    /**
     * 详情
     */
    @Excel(name = "详情")
    private String detail;

    /**
     * 背景图
     */
    @Excel(name = "背景图")
    private Long bgImage;

    /**
     * 对应的图片信息
     */
    private CmFileInfo imageFile;

    /**
     * 扩展属性
     */
    private String ext;
    /**
     * 扩展属性
     */
    private String extPdf;

    /**
     * 状态
     */
    @Excel(name = "状态")
    private String status;

    /**
     * 显示顺序
     */
    @Excel(name = "显示顺序")
    private Integer orderNum;

    /**
     * 首页展示
     */
    @Excel(name = "首页展示")
    private String pageShow;

    /**
     * 访问数
     */
    @Excel(name = "访问数")
    private Integer readNum;

    /**
     * 语言类型：0 中文 1 英文
     */
    private String languageType;

    public String getExtPdf() {
        return extPdf;
    }

    public void setExtPdf(String extPdf) {
        this.extPdf = extPdf;
    }

    public CmMenu getCmMenu() {
        return cmMenu;
    }

    public void setCmMenu(CmMenu cmMenu) {
        this.cmMenu = cmMenu;
    }

    public CmFileInfo getImageFile() {
        return imageFile;
    }

    public void setImageFile(CmFileInfo imageFile) {
        this.imageFile = imageFile;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }

    public void setBgImage(Long bgImage) {
        this.bgImage = bgImage;
    }

    public Long getBgImage() {
        return bgImage;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getExt() {
        return ext;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setPageShow(String pageShow) {
        this.pageShow = pageShow;
    }

    public String getPageShow() {
        return pageShow;
    }

    public void setReadNum(Integer readNum) {
        this.readNum = readNum;
    }

    public Integer getReadNum() {
        return readNum;
    }

    public String getLanguageType() {
        return languageType;
    }

    public void setLanguageType(String languageType) {
        this.languageType = languageType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("articleId", getArticleId())
                .append("menuId", getMenuId())
                .append("title", getTitle())
                .append("introduce", getIntroduce())
                .append("detail", getDetail())
                .append("bgImage", getBgImage())
                .append("ext", getExt())
                .append("status", getStatus())
                .append("orderNum", getOrderNum())
                .append("pageShow", getPageShow())
                .append("readNum", getReadNum())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("languageType", getLanguageType())
                .toString();
    }
}
