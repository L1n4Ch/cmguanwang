package com.ruoyi.web.controller.tool.vo;

import com.ruoyi.common.core.domain.entity.SysDictData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("字典配置实体")
public class DictVo {
    @ApiModelProperty("key")
    private String key;
    @ApiModelProperty("value")
    private String value;


    public void toVo(SysDictData dictData){
        this. value = dictData.getDictLabel();
        this. key = dictData.getDictValue();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
