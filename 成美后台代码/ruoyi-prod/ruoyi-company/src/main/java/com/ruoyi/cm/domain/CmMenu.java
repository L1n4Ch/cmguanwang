package com.ruoyi.cm.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.TreeEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 成美菜单对象 cm_menu
 *
 * @author djl
 * @date 2021-05-29
 */
public class CmMenu extends TreeEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long menuId;

    /**
     * 菜单名称
     */
    @Excel(name = "菜单名称")
    private String menuName;

    /**
     * 菜单背景图
     */
    @Excel(name = "菜单背景图")
    private Long menuImg;

    /**
     * 菜单模板
     */
    @Excel(name = "菜单模板")
    private String menuModel;

    /**
     * 状态
     */
    @Excel(name = "状态")
    private String status;

    /**
     * 前端跳转地址
     */
    @Excel(name = "前端跳转地址")
    private String url;

    /**
     * 语言类型：0 中文 1 英文
     */
    private String languageType;

    /**
     * 对应的图片信息
     */
    private CmFileInfo imageFile;

    public CmFileInfo getImageFile() {
        return imageFile;
    }

    public void setImageFile(CmFileInfo imageFile) {
        this.imageFile = imageFile;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuImg(Long menuImg) {
        this.menuImg = menuImg;
    }

    public Long getMenuImg() {
        return menuImg;
    }

    public void setMenuModel(String menuModel) {
        this.menuModel = menuModel;
    }

    public String getMenuModel() {
        return menuModel;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
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
                .append("menuId", getMenuId())
                .append("menuName", getMenuName())
                .append("parentId", getParentId())
                .append("orderNum", getOrderNum())
                .append("menuImg", getMenuImg())
                .append("menuModel", getMenuModel())
                .append("status", getStatus())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("url", getUrl())
                .append("languageType", getLanguageType())
                .toString();
    }
}
