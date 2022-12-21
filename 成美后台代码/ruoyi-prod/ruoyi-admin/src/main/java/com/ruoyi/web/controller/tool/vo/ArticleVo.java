package com.ruoyi.web.controller.tool.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel("文章实体")
public class ArticleVo {
    /** 主键 */
    @ApiModelProperty("主键")
    private Long articleId;

    /** 菜单ID */
    @ApiModelProperty("菜单ID")
    private Long menuId;

    @ApiModelProperty("菜单名称")
    private String menuName;//菜单信息

    /** 标题 */
    @ApiModelProperty("标题")
    private String title;

    /** 简介 */
    @ApiModelProperty("简介")
    private String introduce;


    /** 背景图 */
    @ApiModelProperty("背景图")
    private String bgImageUrl;


    /** 扩展属性（有些链接类的跳转的地址） */
    @ApiModelProperty("跳转的地址")
    private String ext;
    /** 扩展属性（有些链接类的跳转的地址） */
    @ApiModelProperty("PDF跳转的地址")
    private String extPdf;

    /** 访问数 */
    @ApiModelProperty("访问数")
    private Integer readNum;

    @ApiModelProperty("文章详情")
    private String detail;


    /** 创建时间 */
    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    /** 更新时间 */
    @ApiModelProperty("更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;

    public String getExtPdf() {
        return extPdf;
    }

    public void setExtPdf(String extPdf) {
        this.extPdf = extPdf;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getBgImageUrl() {
        return bgImageUrl;
    }

    public void setBgImageUrl(String bgImageUrl) {
        this.bgImageUrl = bgImageUrl;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public Integer getReadNum() {
        return readNum;
    }

    public void setReadNum(Integer readNum) {
        this.readNum = readNum;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public Date getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime)
    {
        this.updateTime = updateTime;
    }

}
