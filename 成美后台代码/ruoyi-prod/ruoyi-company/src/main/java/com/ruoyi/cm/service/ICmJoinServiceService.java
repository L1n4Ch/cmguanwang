package com.ruoyi.cm.service;

import java.util.List;
import com.ruoyi.cm.domain.CmJoinService;

/**
 * 加入-志愿列表Service接口
 * 
 * @author ruoyi
 * @date 2021-05-25
 */
public interface ICmJoinServiceService 
{
    /**
     * 查询加入-志愿列表
     * 
     * @param jsId 加入-志愿列表ID
     * @return 加入-志愿列表
     */
    public CmJoinService selectCmJoinServiceById(Long jsId);

    /**
     * 查询加入-志愿列表列表
     * 
     * @param cmJoinService 加入-志愿列表
     * @return 加入-志愿列表集合
     */
    public List<CmJoinService> selectCmJoinServiceList(CmJoinService cmJoinService);

    /**
     * 新增加入-志愿列表
     * 
     * @param cmJoinService 加入-志愿列表
     * @return 结果
     */
    public int insertCmJoinService(CmJoinService cmJoinService);

    /**
     * 修改加入-志愿列表
     * 
     * @param cmJoinService 加入-志愿列表
     * @return 结果
     */
    public int updateCmJoinService(CmJoinService cmJoinService);

    /**
     * 批量删除加入-志愿列表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCmJoinServiceByIds(String ids);

    /**
     * 删除加入-志愿列表信息
     * 
     * @param jsId 加入-志愿列表ID
     * @return 结果
     */
    public int deleteCmJoinServiceById(Long jsId);
}
