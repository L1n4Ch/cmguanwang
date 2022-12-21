package com.ruoyi.cm.mapper;

import java.util.List;
import com.ruoyi.cm.domain.CmVisits;

/**
 * 访问量统计Mapper接口
 * 
 * @author ruoyi
 * @date 2021-07-01
 */
public interface CmVisitsMapper 
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
     * 分组统计各个菜单的访问次数
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
     * 删除访问量统计
     * 
     * @param vid 访问量统计ID
     * @return 结果
     */
    public int deleteCmVisitsById(Long vid);

    /**
     * 批量删除访问量统计
     * 
     * @param vids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCmVisitsByIds(String[] vids);

    List<Integer> getDayData();

    List<Integer> getWeekData();

    List<Integer> getMonthData();
}
