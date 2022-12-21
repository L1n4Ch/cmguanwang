package com.ruoyi.cm.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 访问量统计对象 cm_visits
 * 
 * @author ruoyi
 * @date 2021-07-01
 */
public class CmVisits extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long vid;

    /** 菜单名称 */
    @Excel(name = "菜单名称")
    private String mName;

    /** 访问时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "访问时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date atime;

    private Long vs ;
    public Long getVs() {
        return vs;
    }

    public void setVs(Long vs) {
        this.vs = vs;
    }



    public void setVid(Long vid)
    {
        this.vid = vid;
    }

    public Long getVid() 
    {
        return vid;
    }

    public void setmName(String mName) 
    {
        this.mName = mName;
    }

    public String getmName() 
    {
        return mName;
    }
    public void setAtime(Date atime)
    {
        this.atime = atime;
    }

    public Date getAtime()
    {
        return atime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("vid", getVid())
            .append("mName", getmName())
            .append("atime", getAtime())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
