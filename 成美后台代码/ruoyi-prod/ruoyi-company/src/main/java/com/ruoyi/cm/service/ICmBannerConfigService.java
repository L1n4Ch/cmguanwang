package com.ruoyi.cm.service;

import java.util.List;
import com.ruoyi.cm.domain.CmBannerConfig;

/**
 * 首页轮播图Service接口
 * 
 * @author djl
 * @date 2021-05-29
 */
public interface ICmBannerConfigService 
{
    /**
     * 查询首页轮播图
     * 
     * @param bId 首页轮播图ID
     * @return 首页轮播图
     */
    public CmBannerConfig selectCmBannerConfigById(Long bId);

    /**
     * 查询首页轮播图列表
     * 
     * @param cmBannerConfig 首页轮播图
     * @return 首页轮播图集合
     */
    public List<CmBannerConfig> selectCmBannerConfigList(CmBannerConfig cmBannerConfig);

    /**
     * 新增首页轮播图
     * 
     * @param cmBannerConfig 首页轮播图
     * @return 结果
     */
    public int insertCmBannerConfig(CmBannerConfig cmBannerConfig);

    /**
     * 修改首页轮播图
     * 
     * @param cmBannerConfig 首页轮播图
     * @return 结果
     */
    public int updateCmBannerConfig(CmBannerConfig cmBannerConfig);

    /**
     * 批量删除首页轮播图
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCmBannerConfigByIds(String ids);

    /**
     * 删除首页轮播图信息
     * 
     * @param bId 首页轮播图ID
     * @return 结果
     */
    public int deleteCmBannerConfigById(Long bId);
}
