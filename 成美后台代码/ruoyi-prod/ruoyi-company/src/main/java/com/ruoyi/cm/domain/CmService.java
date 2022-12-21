package com.ruoyi.cm.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 志愿服务列表对象 cm_service
 * 
 * @author ruoyi
 * @date 2021-05-25
 */
public class CmService extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键,自增 */
    private Long sId;

    /** 志愿服务项目 */
    @Excel(name = "志愿服务项目")
    private String sItem;

    public void setsId(Long sId) 
    {
        this.sId = sId;
    }

    public Long getsId() 
    {
        return sId;
    }
    public void setsItem(String sItem) 
    {
        this.sItem = sItem;
    }

    public String getsItem() 
    {
        return sItem;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("sId", getsId())
            .append("sItem", getsItem())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
