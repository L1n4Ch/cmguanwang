package com.ruoyi.web.controller.tool.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 加入我们对象 cm_join_us
 * 
 * @author ruoyi
 * @date 2021-05-25
 */
@ApiModel("加入我们实体")
public class JoinUsVo
{
    /** 姓名 */
    @ApiModelProperty("姓名")
    private String name;

    /** 性别 */
    @ApiModelProperty( "性别")
    private String gender;

    /** 年龄 */
    @ApiModelProperty( "年龄")
    private Long age;

    /** 学历 */
    @ApiModelProperty( "学历")
    private String edu;

    /** 手机号码 */
    @ApiModelProperty( "手机号码")
    private String phone;

    /** 邮箱 */
    @ApiModelProperty( "邮箱")
    private String email;

    /** 联系地址 */
    @ApiModelProperty( "联系地址")
    private String address;

    /** 身份证号码 */
    @ApiModelProperty( "身份证号码")
    private String cardId;

    /** 有否志愿者经验 */
    @ApiModelProperty( "有否志愿者经验")
    private String isVolunteer;

    /** 有否伤残/病例 */
    @ApiModelProperty( "有否伤残/病例")
    private String isMayhem;

    /** 学校/专业，单位/职业 */
    @ApiModelProperty( "学校/专业，单位/职业")
    private String vocation;

    /** 自我介绍 */
    @ApiModelProperty( "自我介绍")
    private String introduce;

    /** 每月最少服务时间 */
    @ApiModelProperty( "每月最少服务时间")
    private Long minTime;

    /** 预计服务期限 */
    @ApiModelProperty( "预计服务期限")
    private String estimateTime;
    /** 服务ID列表*/
    @ApiModelProperty( "志愿服务列表ID")
    private String[] serviceIds;

    public String[] getServiceIds() {
        return serviceIds;
    }

    public void setServiceIds(String[] serviceIds) {
        this.serviceIds = serviceIds;
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

}
