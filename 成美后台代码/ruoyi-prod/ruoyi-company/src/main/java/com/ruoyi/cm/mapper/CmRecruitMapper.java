package com.ruoyi.cm.mapper;

import java.util.List;
import com.ruoyi.cm.domain.CmRecruit;

/**
 * 人才招聘Mapper接口
 * 
 * @author ruoyi
 * @date 2021-06-01
 */
public interface CmRecruitMapper 
{
    /**
     * 查询人才招聘
     * 
     * @param positionId 人才招聘ID
     * @return 人才招聘
     */
    public CmRecruit selectCmRecruitById(Long positionId);

    /**
     * 查询人才招聘列表
     * 
     * @param cmRecruit 人才招聘
     * @return 人才招聘集合
     */
    public List<CmRecruit> selectCmRecruitList(CmRecruit cmRecruit);

    /**
     * 新增人才招聘
     * 
     * @param cmRecruit 人才招聘
     * @return 结果
     */
    public int insertCmRecruit(CmRecruit cmRecruit);

    /**
     * 修改人才招聘
     * 
     * @param cmRecruit 人才招聘
     * @return 结果
     */
    public int updateCmRecruit(CmRecruit cmRecruit);

    /**
     * 删除人才招聘
     * 
     * @param positionId 人才招聘ID
     * @return 结果
     */
    public int deleteCmRecruitById(Long positionId);

    /**
     * 批量删除人才招聘
     * 
     * @param positionIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteCmRecruitByIds(String[] positionIds);
}
