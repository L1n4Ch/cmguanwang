package com.ruoyi.cm.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.cm.mapper.CmRecruitMapper;
import com.ruoyi.cm.domain.CmRecruit;
import com.ruoyi.cm.service.ICmRecruitService;
import com.ruoyi.common.core.text.Convert;

/**
 * 人才招聘Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-06-01
 */
@Service
public class CmRecruitServiceImpl implements ICmRecruitService 
{
    @Autowired
    private CmRecruitMapper cmRecruitMapper;

    /**
     * 查询人才招聘
     * 
     * @param positionId 人才招聘ID
     * @return 人才招聘
     */
    @Override
    public CmRecruit selectCmRecruitById(Long positionId)
    {
        return cmRecruitMapper.selectCmRecruitById(positionId);
    }

    /**
     * 查询人才招聘列表
     * 
     * @param cmRecruit 人才招聘
     * @return 人才招聘
     */
    @Override
    public List<CmRecruit> selectCmRecruitList(CmRecruit cmRecruit)
    {
        return cmRecruitMapper.selectCmRecruitList(cmRecruit);
    }

    /**
     * 新增人才招聘
     * 
     * @param cmRecruit 人才招聘
     * @return 结果
     */
    @Override
    public int insertCmRecruit(CmRecruit cmRecruit)
    {
        cmRecruit.setCreateTime(DateUtils.getNowDate());
        return cmRecruitMapper.insertCmRecruit(cmRecruit);
    }

    /**
     * 修改人才招聘
     * 
     * @param cmRecruit 人才招聘
     * @return 结果
     */
    @Override
    public int updateCmRecruit(CmRecruit cmRecruit)
    {
        cmRecruit.setUpdateTime(DateUtils.getNowDate());
        return cmRecruitMapper.updateCmRecruit(cmRecruit);
    }

    /**
     * 删除人才招聘对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCmRecruitByIds(String ids)
    {
        return cmRecruitMapper.deleteCmRecruitByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除人才招聘信息
     * 
     * @param positionId 人才招聘ID
     * @return 结果
     */
    @Override
    public int deleteCmRecruitById(Long positionId)
    {
        return cmRecruitMapper.deleteCmRecruitById(positionId);
    }
}
