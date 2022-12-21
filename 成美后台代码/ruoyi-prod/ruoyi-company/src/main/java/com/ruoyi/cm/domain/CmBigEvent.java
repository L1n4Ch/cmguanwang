package com.ruoyi.cm.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 成美大事件对象 cm_big_event
 *
 * @author ruoyi
 * @date 2021-05-28
 */
public class CmBigEvent extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long beId;

    /** 发布语言 发布到中文版网站或英文版网站 CN中文版 EN英文版*/
    @Excel(name = "发布语言")
    private String pubLang;

    /** 年份 */
    @Excel(name = "年份")
    private Integer year;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 简介 */
    @Excel(name = "简介")
    private String introduce;

    /** 详情 */
    @Excel(name = "详情")
    private String detail;

    /** 背景图 */
    @Excel(name = "背景图")
    private Long bgImage;

    /**
     * 背景图片信息
     */
    private CmFileInfo bgImageFile;

    public CmFileInfo getBgImageFile() {
        return bgImageFile;
    }

    public void setBgImageFile(CmFileInfo bgImageFile) {
        this.bgImageFile = bgImageFile;
    }

    public void setBeId(Long beId)
    {
        this.beId = beId;
    }

    public Long getBeId()
    {
        return beId;
    }

    public void setPubLang(String pubLang)
    {
        this.pubLang = pubLang;
    }
    public String getPubLang()
    {
        return pubLang;
    }

    public void setYear(Integer year)
    {
        this.year = year;
    }

    public Integer getYear()
    {
        return year;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }
    public void setIntroduce(String introduce)
    {
        this.introduce = introduce;
    }

    public String getIntroduce()
    {
        return introduce;
    }
    public void setDetail(String detail)
    {
        this.detail = detail;
    }

    public String getDetail()
    {
        return detail;
    }
    public void setBgImage(Long bgImage)
    {
        this.bgImage = bgImage;
    }

    public Long getBgImage()
    {
        return bgImage;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("beId", getBeId())
            .append("pubLang", getPubLang())
            .append("year", getYear())
            .append("title", getTitle())
            .append("introduce", getIntroduce())
            .append("detail", getDetail())
            .append("bgImage", getBgImage())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}
