package com.ruoyi.cm.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 捐赠人信息对象 cm_donation_user
 *
 * @author ruoyi
 * @date 2021-05-24
 */
public class CmDonationUser extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long duId;

    /**
     * 姓名
     */
    @Excel(name = "姓名")
    private String duName;

    /**
     * 手机号码
     */
    @Excel(name = "手机号码")
    private String duPhone;

    /**
     * 用户邮箱
     */
    @Excel(name = "用户邮箱")
    private String duEmail;

    /**
     * 用户地址
     */
    @Excel(name = "用户地址")
    private String duAddress;

    /**
     * 语言标记
     */
    @Excel(name = "语言标记")
    private String langMark;

    /**
     * 文件ID
     */
    private Long fileId;
    /**
     * 文件地址
     */
    private String fileUrl;

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public void setDuId(Long duId) {
        this.duId = duId;
    }

    public Long getDuId() {
        return duId;
    }

    public void setDuName(String duName) {
        this.duName = duName;
    }

    public String getDuName() {
        return duName;
    }

    public void setDuPhone(String duPhone) {
        this.duPhone = duPhone;
    }

    public String getDuPhone() {
        return duPhone;
    }

    public void setDuEmail(String duEmail) {
        this.duEmail = duEmail;
    }

    public String getDuEmail() {
        return duEmail;
    }

    public void setDuAddress(String duAddress) {
        this.duAddress = duAddress;
    }

    public String getDuAddress() {
        return duAddress;
    }

    public void setLangMark(String langMark) {
        this.langMark = langMark;
    }
    public String getLangMark() {
        return langMark;
    }

    @Override
    public String toString() {
        return new StringBuffer()
                .append("姓名：" + getDuName())
                .append("手机号码：" + getDuPhone())
                .append("用户邮箱：" + getDuEmail())
                .append("用户地址：" + getDuAddress())
                .append("来源：" + getLangMark())
                .toString();
    }
}
