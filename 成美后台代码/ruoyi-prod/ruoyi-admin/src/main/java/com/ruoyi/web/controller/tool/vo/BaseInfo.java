package com.ruoyi.web.controller.tool.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("基础信息")
public class BaseInfo{
    /**
     * logo
     */
    @ApiModelProperty("logo")
    private String logo;

    /**
     * 网站名称
     */
    @ApiModelProperty("网站名称")
    private String name;
    /**
     * qq
     */
    @ApiModelProperty("qq")
    private String qq;

    /**
     * 新浪微博名称
     */
    @ApiModelProperty("新浪微博名称")
    private String xlBlog;

    /**
     * 腾讯微博名称
     */
    @ApiModelProperty("腾讯微博名称")
    private String txBlog;

    /**
     * 公众微信名称
     */
    @ApiModelProperty("公众微信名称")
    private String wxName;

    /**
     * 地址
     */
    @ApiModelProperty("地址")
    private String address;

    /**
     * 网站地址
     */
    @ApiModelProperty("网站地址")
    private String website;

    /**
     * 备案号
     */
    @ApiModelProperty("备案号")
    private String icp;

    /**
     * 联系电话
     */
    @ApiModelProperty("联系电话")
    private String iphone;

    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    private String email;

    /**
     * 传真
     */
    @ApiModelProperty("传真")
    private String fax;

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getXlBlog() {
        return xlBlog;
    }

    public void setXlBlog(String xlBlog) {
        this.xlBlog = xlBlog;
    }

    public String getTxBlog() {
        return txBlog;
    }

    public void setTxBlog(String txBlog) {
        this.txBlog = txBlog;
    }

    public String getWxName() {
        return wxName;
    }

    public void setWxName(String wxName) {
        this.wxName = wxName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getIcp() {
        return icp;
    }

    public void setIcp(String icp) {
        this.icp = icp;
    }

    public String getIphone() {
        return iphone;
    }

    public void setIphone(String iphone) {
        this.iphone = iphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }
}