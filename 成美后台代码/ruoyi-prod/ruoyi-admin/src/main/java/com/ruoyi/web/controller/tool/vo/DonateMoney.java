package com.ruoyi.web.controller.tool.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

@ApiModel("捐款信息")
public class DonateMoney{
    /**
     * 捐赠截止日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("捐赠截止日期")
    private Date dTime;

    /**
     * 捐赠总收入
     */
    @ApiModelProperty("捐赠总收入")
    private BigDecimal dTotalSum;

    /**
     * 捐赠总支出
     */
    @ApiModelProperty("捐赠总支出")
    private BigDecimal dTotalOut;

    /**
     * 捐赠总人数
     */
    @ApiModelProperty("捐赠总人数")
    private Long dCount;

    public Date getdTime() {
        return dTime;
    }

    public void setdTime(Date dTime) {
        this.dTime = dTime;
    }

    public BigDecimal getdTotalSum() {
        return dTotalSum;
    }

    public void setdTotalSum(BigDecimal dTotalSum) {
        this.dTotalSum = dTotalSum;
    }

    public BigDecimal getdTotalOut() {
        return dTotalOut;
    }

    public void setdTotalOut(BigDecimal dTotalOut) {
        this.dTotalOut = dTotalOut;
    }

    public Long getdCount() {
        return dCount;
    }

    public void setdCount(Long dCount) {
        this.dCount = dCount;
    }
}