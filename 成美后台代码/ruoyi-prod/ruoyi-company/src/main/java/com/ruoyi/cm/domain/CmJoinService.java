package com.ruoyi.cm.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 加入-志愿列表对象 cm_join_service
 * 
 * @author ruoyi
 * @date 2021-05-25
 */
public class CmJoinService extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键,自增 */
    private Long jsId;

    /** cm_join_us 主键 */
    @Excel(name = "cm_join_us 主键")
    private Long jId;

    /** cm_service 主键 */
    @Excel(name = "cm_service 主键")
    private Long sId;

    public void setJsId(Long jsId) 
    {
        this.jsId = jsId;
    }

    public Long getJsId() 
    {
        return jsId;
    }
    public void setjId(Long jId) 
    {
        this.jId = jId;
    }

    public Long getjId() 
    {
        return jId;
    }
    public void setsId(Long sId) 
    {
        this.sId = sId;
    }

    public Long getsId() 
    {
        return sId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("jsId", getJsId())
            .append("jId", getjId())
            .append("sId", getsId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
