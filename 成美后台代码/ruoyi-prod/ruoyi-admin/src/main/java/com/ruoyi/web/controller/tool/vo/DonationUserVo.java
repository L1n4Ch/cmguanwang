package com.ruoyi.web.controller.tool.vo;

import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.multipart.MultipartFile;

/**
 * 捐赠人信息对象 cm_donation_user
 *
 * @author ruoyi
 * @date 2021-05-24
 */
@ApiModel("捐赠人信息实体")
public class DonationUserVo
{
    /** 姓名 */
    @ApiModelProperty("姓名")
    private String duName;

    /** 手机号码 */
    @ApiModelProperty("手机号码")
    private String duPhone;

    /** 用户邮箱 */
    @ApiModelProperty("用户邮箱")
    private String duEmail;

    /** 用户地址 */
    @ApiModelProperty("用户地址")
    private String duAddress;

    /** 语言标记 */
    @ApiModelProperty("语言标记")
    private String langMark;

    @ApiModelProperty("上传附件")
    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public void setDuName(String duName)
    {
        this.duName = duName;
    }

    public String getDuName()
    {
        return duName;
    }
    public void setDuPhone(String duPhone)
    {
        this.duPhone = duPhone;
    }

    public String getDuPhone()
    {
        return duPhone;
    }
    public void setDuEmail(String duEmail)
    {
        this.duEmail = duEmail;
    }

    public String getDuEmail()
    {
        return duEmail;
    }
    public void setDuAddress(String duAddress)
    {
        this.duAddress = duAddress;
    }

    public String getDuAddress()
    {
        return duAddress;
    }

    public void setLangMark(String langMark) {
        this.langMark = langMark;
    }
    public String getLangMark() {
        return langMark;
    }
}
