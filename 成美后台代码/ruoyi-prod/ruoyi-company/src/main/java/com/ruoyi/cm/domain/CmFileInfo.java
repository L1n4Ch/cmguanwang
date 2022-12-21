package com.ruoyi.cm.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 文件信息对象 cm_file_info
 * 
 * @author ruoyi
 * @date 2021-05-23
 */
public class CmFileInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long fId;

    /** 文件类型(0图片 1pdf) */
    @Excel(name = "文件类型(0图片 1pdf)")
    private String fileType;

    /** 文件名称 */
    @Excel(name = "文件名称")
    private String fileName;

    /** 文件大小 */
    @Excel(name = "文件大小")
    private String fileSize;

    /** 文件地址 */
    @Excel(name = "文件地址")
    private String fileAddr;
    /** 文件路径*/
    private String fileUrl;
    /** 图片宽高比例：用于截取图片*/
    private Double rate;

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public void setfId(Long fId)
    {
        this.fId = fId;
    }

    public Long getfId() 
    {
        return fId;
    }
    public void setFileType(String fileType) 
    {
        this.fileType = fileType;
    }

    public String getFileType() 
    {
        return fileType;
    }
    public void setFileName(String fileName) 
    {
        this.fileName = fileName;
    }

    public String getFileName() 
    {
        return fileName;
    }
    public void setFileSize(String fileSize) 
    {
        this.fileSize = fileSize;
    }

    public String getFileSize() 
    {
        return fileSize;
    }
    public void setFileAddr(String fileAddr) 
    {
        this.fileAddr = fileAddr;
    }

    public String getFileAddr() 
    {
        return fileAddr;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("fId", getfId())
            .append("fileType", getFileType())
            .append("fileName", getFileName())
            .append("fileSize", getFileSize())
            .append("fileAddr", getFileAddr())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
