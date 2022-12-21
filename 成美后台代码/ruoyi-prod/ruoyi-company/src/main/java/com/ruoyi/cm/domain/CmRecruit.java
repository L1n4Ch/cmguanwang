package com.ruoyi.cm.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 人才招聘对象 cm_recruit
 * 
 * @author ruoyi
 * @date 2021-06-01
 */
public class CmRecruit extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long positionId;

    /** 职位名称 */
    @Excel(name = "职位名称")
    private String positionName;

    /** 招聘对象 */
    @Excel(name = "招聘对象")
    private String positionObject;

    /** 地点 */
    @Excel(name = "地点")
    private String positionAddress;

    /** 需求人数 */
    @Excel(name = "需求人数")
    private Integer needNum;

    /** 职位描述 */
    @Excel(name = "职位描述")
    private String positionDetail;

    /** 状态（0 发布 1 下架） */
    @Excel(name = "状态", readConverterExp = "0=,发=布,1=,下=架")
    private String status;

    /** 发布时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发布时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date publishTime;
    /**
     * 学历
     */
    private String edu;

    /**
     * 学历中文描述
     */
    private String eduStr;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getPositionObject() {
        return positionObject;
    }

    public void setPositionObject(String positionObject) {
        this.positionObject = positionObject;
    }

    public String getPositionAddress() {
        return positionAddress;
    }

    public void setPositionAddress(String positionAddress) {
        this.positionAddress = positionAddress;
    }

    public Integer getNeedNum() {
        return needNum;
    }

    public void setNeedNum(Integer needNum) {
        this.needNum = needNum;
    }

    public String getPositionDetail() {
        return positionDetail;
    }

    public void setPositionDetail(String positionDetail) {
        this.positionDetail = positionDetail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getEdu() {
        return edu;
    }

    public void setEdu(String edu) {
        this.edu = edu;
    }

    public String getEduStr() {
        return eduStr;
    }

    public void setEduStr(String eduStr) {
        this.eduStr = eduStr;
    }

    @Override
    public String toString() {
        return "CmRecruit{" +
                "positionId=" + positionId +
                ", positionName='" + positionName + '\'' +
                ", positionObject='" + positionObject + '\'' +
                ", positionAddress='" + positionAddress + '\'' +
                ", needNum=" + needNum +
                ", positionDetail='" + positionDetail + '\'' +
                ", status='" + status + '\'' +
                ", publishTime=" + publishTime +
                ", edu='" + edu + '\'' +
                ", eduStr='" + eduStr + '\'' +
                '}';
    }
}
