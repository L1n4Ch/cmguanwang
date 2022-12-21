package com.ruoyi.cm.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 首页轮播图对象 cm_banner_config
 * 
 * @author djl
 * @date 2021-05-29
 */
public class CmBannerConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long bId;

    /** 轮播顺序 */
    @Excel(name = "轮播顺序")
    private Integer numSort;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 图片 */
    @Excel(name = "图片")
    private Long imgId;

    /** 图片类型，数据字典cm_banner_type */
    @Excel(name = "图片类型，数据字典cm_banner_type")
    private Long typeId;

    /**
     * 客户端类型，数据字段 cm_banner_client_type
     */
    private Long clientType;

    /** 对应的图片信息*/
    private CmFileInfo imageFile;

    public CmFileInfo getImageFile() {
        return imageFile;
    }

    public void setImageFile(CmFileInfo imageFile) {
        this.imageFile = imageFile;
    }

    public void setbId(Long bId)
    {
        this.bId = bId;
    }

    public Long getbId() 
    {
        return bId;
    }
    public void setNumSort(Integer numSort) 
    {
        this.numSort = numSort;
    }

    public Integer getNumSort() 
    {
        return numSort;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setImgId(Long imgId) 
    {
        this.imgId = imgId;
    }

    public Long getImgId() 
    {
        return imgId;
    }

    public void setTypeId(Long typeId)
    {
        this.typeId = typeId;
    }

    public Long getTypeId()
    {
        return typeId;
    }

    public Long getClientType() {
        return clientType;
    }

    public void setClientType(Long clientType) {
        this.clientType = clientType;
    }

    @Override
    public String toString() {
        return "CmBannerConfig{" +
                "bId=" + bId +
                ", numSort=" + numSort +
                ", status='" + status + '\'' +
                ", imgId=" + imgId +
                ", typeId=" + typeId +
                ", clientType=" + clientType +
                ", imageFile=" + imageFile +
                '}';
    }
}
