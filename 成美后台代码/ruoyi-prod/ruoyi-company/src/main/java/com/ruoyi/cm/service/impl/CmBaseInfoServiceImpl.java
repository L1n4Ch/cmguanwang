package com.ruoyi.cm.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.cm.mapper.CmBaseInfoMapper;
import com.ruoyi.cm.domain.CmBaseInfo;
import com.ruoyi.cm.service.ICmBaseInfoService;
import com.ruoyi.common.core.text.Convert;

/**
 * 基础信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-06-03
 */
@Service
public class CmBaseInfoServiceImpl implements ICmBaseInfoService 
{
    @Autowired
    private CmBaseInfoMapper cmBaseInfoMapper;

    /**
     * 查询基础信息
     * 
     * @param baseId 基础信息ID
     * @return 基础信息
     */
    @Override
    public CmBaseInfo selectCmBaseInfoById(Long baseId)
    {
        return cmBaseInfoMapper.selectCmBaseInfoById(baseId);
    }

    /**
     * 查询基础信息列表
     * 
     * @param cmBaseInfo 基础信息
     * @return 基础信息
     */
    @Override
    public List<CmBaseInfo> selectCmBaseInfoList(CmBaseInfo cmBaseInfo)
    {
        return cmBaseInfoMapper.selectCmBaseInfoList(cmBaseInfo);
    }

    /**
     * 新增基础信息
     * 
     * @param cmBaseInfo 基础信息
     * @return 结果
     */
    @Override
    public int insertCmBaseInfo(CmBaseInfo cmBaseInfo)
    {
        return cmBaseInfoMapper.insertCmBaseInfo(cmBaseInfo);
    }

    /**
     * 修改基础信息
     * 
     * @param cmBaseInfo 基础信息
     * @return 结果
     */
    @Override
    public int updateCmBaseInfo(CmBaseInfo cmBaseInfo)
    {
        return cmBaseInfoMapper.updateCmBaseInfo(cmBaseInfo);
    }

    /**
     * 删除基础信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCmBaseInfoByIds(String ids)
    {
        return cmBaseInfoMapper.deleteCmBaseInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除基础信息信息
     * 
     * @param baseId 基础信息ID
     * @return 结果
     */
    @Override
    public int deleteCmBaseInfoById(Long baseId)
    {
        return cmBaseInfoMapper.deleteCmBaseInfoById(baseId);
    }
}
