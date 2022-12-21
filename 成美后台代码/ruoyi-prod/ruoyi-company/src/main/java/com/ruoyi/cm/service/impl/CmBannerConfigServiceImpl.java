package com.ruoyi.cm.service.impl;

import com.ruoyi.cm.domain.CmBannerConfig;
import com.ruoyi.cm.domain.CmFileInfo;
import com.ruoyi.cm.mapper.CmBannerConfigMapper;
import com.ruoyi.cm.service.ICmBannerConfigService;
import com.ruoyi.cm.service.ICmFileInfoService;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 首页轮播图Service业务层处理
 * 
 * @author djl
 * @date 2021-05-29
 */
@Service
public class CmBannerConfigServiceImpl implements ICmBannerConfigService 
{
    @Autowired
    private CmBannerConfigMapper cmBannerConfigMapper;
    @Autowired
    private ICmFileInfoService cmFileInfoService;
    /**
     * 查询首页轮播图
     * 
     * @param bId 首页轮播图ID
     * @return 首页轮播图
     */
    @Override
    public CmBannerConfig selectCmBannerConfigById(Long bId)
    {
        CmBannerConfig cmBannerConfig = cmBannerConfigMapper.selectCmBannerConfigById(bId);
        if(cmBannerConfig != null && cmBannerConfig.getImgId() != null){
            CmFileInfo cmFileInfo = cmFileInfoService.selectCmFileInfoById(cmBannerConfig.getImgId());
            cmBannerConfig.setImageFile(cmFileInfo);
        }
        return cmBannerConfig;
    }

    /**
     * 查询首页轮播图列表
     * 
     * @param cmBannerConfig 首页轮播图
     * @return 首页轮播图
     */
    @Override
    public List<CmBannerConfig> selectCmBannerConfigList(CmBannerConfig cmBannerConfig)
    {
        return cmBannerConfigMapper.selectCmBannerConfigList(cmBannerConfig);
    }

    /**
     * 新增首页轮播图
     * 
     * @param cmBannerConfig 首页轮播图
     * @return 结果
     */
    @Override
    public int insertCmBannerConfig(CmBannerConfig cmBannerConfig)
    {
        cmBannerConfig.setCreateTime(DateUtils.getNowDate());
        return cmBannerConfigMapper.insertCmBannerConfig(cmBannerConfig);
    }

    /**
     * 修改首页轮播图
     * 
     * @param cmBannerConfig 首页轮播图
     * @return 结果
     */
    @Override
    public int updateCmBannerConfig(CmBannerConfig cmBannerConfig)
    {
        cmBannerConfig.setUpdateTime(DateUtils.getNowDate());
        return cmBannerConfigMapper.updateCmBannerConfig(cmBannerConfig);
    }

    /**
     * 删除首页轮播图对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCmBannerConfigByIds(String ids)
    {
        return cmBannerConfigMapper.deleteCmBannerConfigByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除首页轮播图信息
     * 
     * @param bId 首页轮播图ID
     * @return 结果
     */
    @Override
    public int deleteCmBannerConfigById(Long bId)
    {
        return cmBannerConfigMapper.deleteCmBannerConfigById(bId);
    }
}
