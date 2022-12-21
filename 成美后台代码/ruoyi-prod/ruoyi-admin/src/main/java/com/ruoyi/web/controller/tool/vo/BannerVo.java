package com.ruoyi.web.controller.tool.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Map;

@ApiModel("Banner实体")
public class BannerVo {
    @ApiModelProperty("banner列表")
    private Map<String,List<String>> imgList;

    @ApiModelProperty("轮播速度")
    private Integer loopSpeed;

    public Map<String, List<String>> getImgList() {
        return imgList;
    }

    public void setImgList(Map<String, List<String>> imgList) {
        this.imgList = imgList;
    }

    public Integer getLoopSpeed() {
        return loopSpeed;
    }

    public void setLoopSpeed(Integer loopSpeed) {
        this.loopSpeed = loopSpeed;
    }
}
