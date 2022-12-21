package com.ruoyi.web.controller.tool.vo;


import io.swagger.annotations.ApiModelProperty;

public class MsgVo {
    /** 姓名 */
    @ApiModelProperty("留言人姓名")
    private String msgUse;

    /** 手机号码 */
    @ApiModelProperty("留言人姓名")
    private String msgPhone;

    /** 用户邮箱 */
    @ApiModelProperty("留言人姓名")
    private String msgEmail;

    /** 留言内容 */
    @ApiModelProperty("留言人姓名")
    private String msgDetail;

    public String getMsgUse() {
        return msgUse;
    }

    public void setMsgUse(String msgUse) {
        this.msgUse = msgUse;
    }

    public String getMsgPhone() {
        return msgPhone;
    }

    public void setMsgPhone(String msgPhone) {
        this.msgPhone = msgPhone;
    }

    public String getMsgEmail() {
        return msgEmail;
    }

    public void setMsgEmail(String msgEmail) {
        this.msgEmail = msgEmail;
    }

    public String getMsgDetail() {
        return msgDetail;
    }

    public void setMsgDetail(String msgDetail) {
        this.msgDetail = msgDetail;
    }
}
