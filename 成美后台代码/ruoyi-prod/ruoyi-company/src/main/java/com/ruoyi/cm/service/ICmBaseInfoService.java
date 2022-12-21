package com.ruoyi.cm.service;

import java.util.List;
import com.ruoyi.cm.domain.CmBaseInfo;

/**
 * 基础信息Service接口
 * 
 * @author ruoyi
 * @date 2021-06-03
 */
public interface ICmBaseInfoService 
{
    /**
     * 查询基础信息
     * 
     * @param baseId 基础信息ID
     * @return 基础信息
     */
    public CmBaseInfo selectCmBaseInfoById(Long baseId);

    /**
     * 查询基础信息列表
     * 
     * @param cmBaseInfo 基础信息
     * @return 基础信息集合
     */
    public List<CmBaseInfo> selectCmBaseInfoList(CmBaseInfo cmBaseInfo);

    /**
     * 新增基础信息
     * 
     * @param cmBaseInfo 基础信息
     * @return 结果
     */
    public int insertCmBaseInfo(CmBaseInfo cmBaseInfo);

    /**
     * 修改基础信息
     * 
     * @param cmBaseInfo 基础信息
     * @return 结果
     */
    public int updateCmBaseInfo(CmBaseInfo cmBaseInfo);

    /**
     * 批量删除基础信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCmBaseInfoByIds(String ids);

    /**
     * 删除基础信息信息
     * 
     * @param baseId 基础信息ID
     * @return 结果
     */
    public int deleteCmBaseInfoById(Long baseId);
}
