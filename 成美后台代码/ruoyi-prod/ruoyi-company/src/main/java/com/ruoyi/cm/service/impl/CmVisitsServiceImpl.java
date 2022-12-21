package com.ruoyi.cm.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.cm.mapper.CmVisitsMapper;
import com.ruoyi.cm.domain.CmVisits;
import com.ruoyi.cm.service.ICmVisitsService;
import com.ruoyi.common.core.text.Convert;

/**
 * 访问量统计Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-07-01
 */
@Service
public class CmVisitsServiceImpl implements ICmVisitsService 
{
    @Autowired
    private CmVisitsMapper cmVisitsMapper;

    /**
     * 查询访问量统计
     * 
     * @param vid 访问量统计ID
     * @return 访问量统计
     */
    @Override
    public CmVisits selectCmVisitsById(Long vid)
    {
        return cmVisitsMapper.selectCmVisitsById(vid);
    }

    /**
     * 查询访问量统计列表
     * 
     * @param cmVisits 访问量统计
     * @return 访问量统计
     */
    @Override
    public List<CmVisits> selectCmVisitsList(CmVisits cmVisits)
    {
        return cmVisitsMapper.selectCmVisitsList(cmVisits);
    }

    @Override
    public List<CmVisits> selectCmVisitsListGroupByMName(CmVisits cmVisits) {
        return cmVisitsMapper.selectCmVisitsListGroupByMName(cmVisits);
    }

    /**
     * 新增访问量统计
     * 
     * @param cmVisits 访问量统计
     * @return 结果
     */
    @Override
    public int insertCmVisits(CmVisits cmVisits)
    {
        cmVisits.setAtime(DateUtils.getNowDate());
        cmVisits.setCreateTime(DateUtils.getNowDate());
        return cmVisitsMapper.insertCmVisits(cmVisits);
    }

    /**
     * 修改访问量统计
     * 
     * @param cmVisits 访问量统计
     * @return 结果
     */
    @Override
    public int updateCmVisits(CmVisits cmVisits)
    {
        cmVisits.setUpdateTime(DateUtils.getNowDate());
        return cmVisitsMapper.updateCmVisits(cmVisits);
    }

    /**
     * 删除访问量统计对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCmVisitsByIds(String ids)
    {
        return cmVisitsMapper.deleteCmVisitsByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除访问量统计信息
     * 
     * @param vid 访问量统计ID
     * @return 结果
     */
    @Override
    public int deleteCmVisitsById(Long vid)
    {
        return cmVisitsMapper.deleteCmVisitsById(vid);
    }

    @Override
    public List<Integer> getDayData() {
        return cmVisitsMapper.getDayData();
    }

    @Override
    public List<Integer> getWeekData() {
        return cmVisitsMapper.getWeekData();
    }

    @Override
    public List<Integer> getMonthData() {
        return cmVisitsMapper.getMonthData();
    }
}
