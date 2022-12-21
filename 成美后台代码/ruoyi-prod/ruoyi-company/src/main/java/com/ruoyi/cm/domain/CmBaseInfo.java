package com.ruoyi.cm.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 基础信息对象 cm_base_info
 * 
 * @author ruoyi
 * @date 2021-06-03
 */
public class CmBaseInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long baseId;

    /** logo */
    @Excel(name = "logo")
    private Long logo;

    /** 网站名称 */
    @Excel(name = "网站名称")
    private String name;

    /** 微信二维码 */
    @Excel(name = "微信二维码")
    private Long wxImg;

    /** qq */
    @Excel(name = "qq")
    private String qq;

    /** 新浪微博名称 */
    @Excel(name = "新浪微博名称")
    private String xlBlog;

    /** 腾讯微博名称 */
    @Excel(name = "腾讯微博名称")
    private String txBlog;

    /** 公众微信名称 */
    @Excel(name = "公众微信名称")
    private String wxName;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 网站地址 */
    @Excel(name = "网站地址")
    private String website;

    /** 备案号 */
    @Excel(name = "备案号")
    private String icp;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String iphone;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 传真 */
    @Excel(name = "传真")
    private String fax;

    /** 捐赠截止日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "捐赠截止日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dTime;

    /** 捐赠总收入 */
    @Excel(name = "捐赠总收入")
    private BigDecimal dTotalSum;

    /** 捐赠总支出 */
    @Excel(name = "捐赠总支出")
    private BigDecimal dTotalOut;

    /** 捐赠总人数 */
    @Excel(name = "捐赠总人数")
    private Long dCount;

    /** 事实孤儿微信二维码捐款 */
    @Excel(name = "事实孤儿微信二维码捐款")
    private Long orphanWeixin;

    /** 事实孤儿支付宝二维码捐款 */
    @Excel(name = "事实孤儿支付宝二维码捐款")
    private Long orphanZhifubao;

    /** 情暖少数民族女孩微信二维码捐款 */
    @Excel(name = "情暖少数民族女孩微信二维码捐款")
    private Long girlWeixin;

    /** 情暖少数民族女孩支付宝二维码捐款 */
    @Excel(name = "情暖少数民族女孩支付宝二维码捐款")
    private Long girlZhifubao;

    public void setBaseId(Long baseId) 
    {
        this.baseId = baseId;
    }

    public Long getBaseId() 
    {
        return baseId;
    }
    public void setLogo(Long logo) 
    {
        this.logo = logo;
    }

    public Long getLogo() 
    {
        return logo;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setWxImg(Long wxImg) 
    {
        this.wxImg = wxImg;
    }

    public Long getWxImg() 
    {
        return wxImg;
    }
    public void setQq(String qq) 
    {
        this.qq = qq;
    }

    public String getQq() 
    {
        return qq;
    }
    public void setXlBlog(String xlBlog) 
    {
        this.xlBlog = xlBlog;
    }

    public String getXlBlog() 
    {
        return xlBlog;
    }
    public void setTxBlog(String txBlog) 
    {
        this.txBlog = txBlog;
    }

    public String getTxBlog() 
    {
        return txBlog;
    }
    public void setWxName(String wxName) 
    {
        this.wxName = wxName;
    }

    public String getWxName() 
    {
        return wxName;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setWebsite(String website) 
    {
        this.website = website;
    }

    public String getWebsite() 
    {
        return website;
    }
    public void setIcp(String icp) 
    {
        this.icp = icp;
    }

    public String getIcp() 
    {
        return icp;
    }
    public void setIphone(String iphone) 
    {
        this.iphone = iphone;
    }

    public String getIphone() 
    {
        return iphone;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setFax(String fax) 
    {
        this.fax = fax;
    }

    public String getFax() 
    {
        return fax;
    }
    public void setdTime(Date dTime) 
    {
        this.dTime = dTime;
    }

    public Date getdTime() 
    {
        return dTime;
    }
    public void setdTotalSum(BigDecimal dTotalSum) 
    {
        this.dTotalSum = dTotalSum;
    }

    public BigDecimal getdTotalSum() 
    {
        return dTotalSum;
    }
    public void setdTotalOut(BigDecimal dTotalOut) 
    {
        this.dTotalOut = dTotalOut;
    }

    public BigDecimal getdTotalOut() 
    {
        return dTotalOut;
    }
    public void setdCount(Long dCount) 
    {
        this.dCount = dCount;
    }

    public Long getdCount() 
    {
        return dCount;
    }
    public void setOrphanWeixin(Long orphanWeixin) 
    {
        this.orphanWeixin = orphanWeixin;
    }

    public Long getOrphanWeixin() 
    {
        return orphanWeixin;
    }
    public void setOrphanZhifubao(Long orphanZhifubao) 
    {
        this.orphanZhifubao = orphanZhifubao;
    }

    public Long getOrphanZhifubao() 
    {
        return orphanZhifubao;
    }
    public void setGirlWeixin(Long girlWeixin) 
    {
        this.girlWeixin = girlWeixin;
    }

    public Long getGirlWeixin() 
    {
        return girlWeixin;
    }
    public void setGirlZhifubao(Long girlZhifubao) 
    {
        this.girlZhifubao = girlZhifubao;
    }

    public Long getGirlZhifubao() 
    {
        return girlZhifubao;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("baseId", getBaseId())
            .append("logo", getLogo())
            .append("name", getName())
            .append("wxImg", getWxImg())
            .append("qq", getQq())
            .append("xlBlog", getXlBlog())
            .append("txBlog", getTxBlog())
            .append("wxName", getWxName())
            .append("address", getAddress())
            .append("website", getWebsite())
            .append("icp", getIcp())
            .append("iphone", getIphone())
            .append("email", getEmail())
            .append("fax", getFax())
            .append("dTime", getdTime())
            .append("dTotalSum", getdTotalSum())
            .append("dTotalOut", getdTotalOut())
            .append("dCount", getdCount())
            .append("orphanWeixin", getOrphanWeixin())
            .append("orphanZhifubao", getOrphanZhifubao())
            .append("girlWeixin", getGirlWeixin())
            .append("girlZhifubao", getGirlZhifubao())
            .toString();
    }
}
