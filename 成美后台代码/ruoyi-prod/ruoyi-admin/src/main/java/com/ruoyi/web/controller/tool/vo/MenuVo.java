package com.ruoyi.web.controller.tool.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;


@ApiModel("菜单实体")
public class MenuVo {
    /** 主键 */
    @ApiModelProperty("菜单ID")
    private Long menuId;

    /** 菜单名称 */
    @ApiModelProperty("菜单名称")
    private String menuName;

    /** 菜单背景图 */
    @ApiModelProperty("菜单背景图")
    private String menuImgUrl;

    /** 菜单模板 */
    @ApiModelProperty("菜单模板")
    private String menuModel;


    /** 前端跳转地址 */
    @ApiModelProperty("前端跳转地址")
    private String url;
    /**子菜单列表*/
    @ApiModelProperty("子菜单列表")
    private List<MenuVo> children;

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

    public String getMenuImgUrl() {
        return menuImgUrl;
    }

    public void setMenuImgUrl(String menuImgUrl) {
        this.menuImgUrl = menuImgUrl;
    }

    public String getMenuModel() {
        return menuModel;
    }

    public void setMenuModel(String menuModel) {
        this.menuModel = menuModel;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<MenuVo> getChildren() {
        return children;
    }

    public void setChildren(List<MenuVo> children) {
        this.children = children;
    }
}
