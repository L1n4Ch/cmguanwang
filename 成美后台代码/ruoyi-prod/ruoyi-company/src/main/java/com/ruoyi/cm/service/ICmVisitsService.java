package com.ruoyi.cm.service;

import java.util.List;
import com.ruoyi.cm.domain.CmVisits;

/**
 * 访问量统计Service接口
 * 
 * @author ruoyi
 * @date 2021-07-01
 */
public interface ICmVisitsService 
{
    /**
     * 查询访问量统计
     * 
     * @param vid 访问量统计ID
     * @return 访问量统计
     */
    public CmVisits selectCmVisitsById(Long vid);

    /**
     * 查询访问量统计列表
     * 
     * @param cmVisits 访问量统计
     * @return 访问量统计集合
     */
    public List<CmVisits> selectCmVisitsList(CmVisits cmVisits);

    /**
     * 按照日期分组统计
     * @param cmVisits
     * @return
     */
    public List<CmVisits> selectCmVisitsListGroupByMName(CmVisits cmVisits);

    /**
     * 新增访问量统计
     * 
     * @param cmVisits 访问量统计
     * @return 结果
     */
    public int insertCmVisits(CmVisits cmVisits);

    /**
     * 修改访问量统计
     * 
     * @param cmVisits 访问量统计
     * @return 结果
     */
    public int updateCmVisits(CmVisits cmVisits);

    /**
     * 批量删除访问量统计
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCmVisitsByIds(String ids);

    /**
     * 删除访问量统计信息
     * 
     * @param vid 访问量统计ID
     * @return 结果
     */
    public int deleteCmVisitsById(Long vid);

    List<Integer> getDayData();

    List<Integer> getWeekData();

    List<Integer> getMonthData();
}
