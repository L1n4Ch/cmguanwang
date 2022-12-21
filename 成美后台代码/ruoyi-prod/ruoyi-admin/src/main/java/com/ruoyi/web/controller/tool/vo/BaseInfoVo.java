package com.ruoyi.web.controller.tool.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("基础配置实体")
public class BaseInfoVo {

    @ApiModelProperty("基础配置信息")
    private BaseInfo baseInfo;
    @ApiModelProperty("捐款信息配置")
    private DonateMoney donateMoeny;
    @ApiModelProperty("二维码配置")
    private QRCode QRcode;

    public BaseInfo getBaseInfo() {
        return baseInfo;
    }

    public void setBaseInfo(BaseInfo baseInfo) {
        this.baseInfo = baseInfo;
    }

    public DonateMoney getDonateMoeny() {
        return donateMoeny;
    }

    public void setDonateMoeny(DonateMoney donateMoeny) {
        this.donateMoeny = donateMoeny;
    }

    public QRCode getQRcode() {
        return QRcode;
    }

    public void setQRcode(QRCode QRcode) {
        this.QRcode = QRcode;
    }
}

