package com.ruoyi.cm.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 加入我们对象 cm_join_us
 * 
 * @author ruoyi
 * @date 2021-05-25
 */
public class CmJoinUs extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long jId;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 性别 */
    @Excel(name = "性别")
    private String gender;

    /** 年龄 */
    @Excel(name = "年龄")
    private Long age;

    /** 学历 */
    @Excel(name = "学历")
    private String edu;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String phone;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 联系地址 */
    @Excel(name = "联系地址")
    private String address;

    /** 身份证号码 */
    @Excel(name = "身份证号码")
    private String cardId;

    /** 有否志愿者经验 */
    @Excel(name = "有否志愿者经验")
    private String isVolunteer;

    /** 有否伤残/病例 */
    @Excel(name = "有否伤残/病例")
    private String isMayhem;

    /** 学校/专业，单位/职业 */
    @Excel(name = "学校/专业，单位/职业")
    private String vocation;

    /** 自我介绍 */
    @Excel(name = "自我介绍")
    private String introduce;

    /** 每月最少服务时间 */
    @Excel(name = "每月最少服务时间")
    private Long minTime;

    /** 预计服务期限 */
    @Excel(name = "预计服务期限")
    private String estimateTime;

    public void setjId(Long jId) 
    {
        this.jId = jId;
    }

    public Long getjId() 
    {
        return jId;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setGender(String gender) 
    {
        this.gender = gender;
    }

    public String getGender() 
    {
        return gender;
    }
    public void setAge(Long age) 
    {
        this.age = age;
    }

    public Long getAge() 
    {
        return age;
    }
    public void setEdu(String edu) 
    {
        this.edu = edu;
    }

    public String getEdu() 
    {
        return edu;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setCardId(String cardId) 
    {
        this.cardId = cardId;
    }

    public String getCardId() 
    {
        return cardId;
    }
    public void setIsVolunteer(String isVolunteer) 
    {
        this.isVolunteer = isVolunteer;
    }

    public String getIsVolunteer() 
    {
        return isVolunteer;
    }
    public void setIsMayhem(String isMayhem) 
    {
        this.isMayhem = isMayhem;
    }

    public String getIsMayhem() 
    {
        return isMayhem;
    }
    public void setVocation(String vocation) 
    {
        this.vocation = vocation;
    }

    public String getVocation() 
    {
        return vocation;
    }
    public void setIntroduce(String introduce) 
    {
        this.introduce = introduce;
    }

    public String getIntroduce() 
    {
        return introduce;
    }
    public void setMinTime(Long minTime) 
    {
        this.minTime = minTime;
    }

    public Long getMinTime() 
    {
        return minTime;
    }
    public void setEstimateTime(String estimateTime) 
    {
        this.estimateTime = estimateTime;
    }

    public String getEstimateTime() 
    {
        return estimateTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("jId", getjId())
            .append("name", getName())
            .append("gender", getGender())
            .append("age", getAge())
            .append("edu", getEdu())
            .append("phone", getPhone())
            .append("email", getEmail())
            .append("address", getAddress())
            .append("cardId", getCardId())
            .append("isVolunteer", getIsVolunteer())
            .append("isMayhem", getIsMayhem())
            .append("vocation", getVocation())
            .append("introduce", getIntroduce())
            .append("minTime", getMinTime())
            .append("estimateTime", getEstimateTime())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
