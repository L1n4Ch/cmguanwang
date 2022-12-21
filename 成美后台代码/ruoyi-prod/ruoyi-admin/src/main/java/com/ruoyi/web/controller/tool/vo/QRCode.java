package com.ruoyi.web.controller.tool.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("二维码配置")
public class QRCode{
    /**
     * 事实孤儿微信二维码捐款
     */
    @ApiModelProperty("事实孤儿微信二维码捐款")
    private String orphanWeixin;

    /**
     * 事实孤儿支付宝二维码捐款
     */
    @ApiModelProperty("事实孤儿支付宝二维码捐款")
    private String orphanZhifubao;

    /**
     * 情暖少数民族女孩微信二维码捐款
     */
    @ApiModelProperty("情暖少数民族女孩微信二维码捐款")
    private String girlWeixin;

    /**
     * 情暖少数民族女孩支付宝二维码捐款
     */
    @ApiModelProperty("情暖少数民族女孩支付宝二维码捐款")
    private String girlZhifubao;

    /**
     * 微信二维码
     */
    @ApiModelProperty("微信二维码")
    private String wxImg;

    public String getOrphanWeixin() {
        return orphanWeixin;
    }

    public void setOrphanWeixin(String orphanWeixin) {
        this.orphanWeixin = orphanWeixin;
    }

    public String getOrphanZhifubao() {
        return orphanZhifubao;
    }

    public void setOrphanZhifubao(String orphanZhifubao) {
        this.orphanZhifubao = orphanZhifubao;
    }

    public String getGirlWeixin() {
        return girlWeixin;
    }

    public void setGirlWeixin(String girlWeixin) {
        this.girlWeixin = girlWeixin;
    }

    public String getGirlZhifubao() {
        return girlZhifubao;
    }

    public void setGirlZhifubao(String girlZhifubao) {
        this.girlZhifubao = girlZhifubao;
    }

    public String getWxImg() {
        return wxImg;
    }

    public void setWxImg(String wxImg) {
        this.wxImg = wxImg;
    }
}