package com.ruoyi.cm.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 捐赠记录对象 cm_donation
 * 
 * @author ruoyi
 * @date 2021-05-24
 */
public class CmDonation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long donationId;

    /** 捐赠日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "捐赠日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date donationTime;

    /** 捐赠项目 */
    @Excel(name = "捐赠项目")
    private String donationItem;

    /** 捐赠金额 */
    @Excel(name = "捐赠金额")
    private BigDecimal donationMoney;

    /** 捐赠企业 */
    @Excel(name = "捐赠企业")
    private String donationCompany;

    public void setDonationId(Long donationId) 
    {
        this.donationId = donationId;
    }

    public Long getDonationId() 
    {
        return donationId;
    }
    public void setDonationTime(Date donationTime) 
    {
        this.donationTime = donationTime;
    }

    public Date getDonationTime() 
    {
        return donationTime;
    }
    public void setDonationItem(String donationItem) 
    {
        this.donationItem = donationItem;
    }

    public String getDonationItem() 
    {
        return donationItem;
    }
    public void setDonationMoney(BigDecimal donationMoney) 
    {
        this.donationMoney = donationMoney;
    }

    public BigDecimal getDonationMoney() 
    {
        return donationMoney;
    }
    public void setDonationCompany(String donationCompany) 
    {
        this.donationCompany = donationCompany;
    }

    public String getDonationCompany() 
    {
        return donationCompany;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("donationId", getDonationId())
            .append("donationTime", getDonationTime())
            .append("donationItem", getDonationItem())
            .append("donationMoney", getDonationMoney())
            .append("donationCompany", getDonationCompany())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
