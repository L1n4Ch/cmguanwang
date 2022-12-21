package com.ruoyi.web.controller.tool.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("大事件VO")
public class BigEventVo {
    /** 主键 */
    @ApiModelProperty("主键")
    private Long beId;

    /** 发布语言 发布到中文版网站或英文版网站 CN中文版 EN英文版*/
    @ApiModelProperty("发布语言")
    private String pubLang;

    /** 年份 */
    @ApiModelProperty("年份")
    private Integer year;

    /** 标题 */
    @ApiModelProperty("标题")
    private String title;

    /** 简介 */
    @ApiModelProperty("简介")
    private String introduce;

    /** 详情 */
    @ApiModelProperty("详情")
    private String detail;

    /** 背景图 */
    @ApiModelProperty("背景图")
    private String bgImageUrl;

    public Long getBeId() {
        return beId;
    }

    public void setBeId(Long beId) {
        this.beId = beId;
    }

    public void setPubLang(String pubLang)
    {
        this.pubLang = pubLang;
    }
    public String getPubLang()
    {
        return pubLang;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getBgImageUrl() {
        return bgImageUrl;
    }

    public void setBgImageUrl(String bgImageUrl) {
        this.bgImageUrl = bgImageUrl;
    }
}
