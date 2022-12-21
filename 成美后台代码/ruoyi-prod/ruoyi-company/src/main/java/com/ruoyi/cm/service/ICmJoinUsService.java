package com.ruoyi.cm.service;

import java.util.List;
import com.ruoyi.cm.domain.CmJoinUs;

/**
 * 加入我们Service接口
 * 
 * @author ruoyi
 * @date 2021-05-25
 */
public interface ICmJoinUsService 
{
    /**
     * 查询加入我们
     * 
     * @param jId 加入我们ID
     * @return 加入我们
     */
    public CmJoinUs selectCmJoinUsById(Long jId);

    /**
     * 查询加入我们列表
     * 
     * @param cmJoinUs 加入我们
     * @return 加入我们集合
     */
    public List<CmJoinUs> selectCmJoinUsList(CmJoinUs cmJoinUs);

    /**
     * 新增加入我们
     * 
     * @param cmJoinUs 加入我们
     * @return 结果
     */
    public int insertCmJoinUs(CmJoinUs cmJoinUs , List<Long> ids);

    /**
     * 修改加入我们
     * 
     * @param cmJoinUs 加入我们
     * @return 结果
     */
    public int updateCmJoinUs(CmJoinUs cmJoinUs);

    /**
     * 批量删除加入我们
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCmJoinUsByIds(String ids);

    /**
     * 删除加入我们信息
     * 
     * @param jId 加入我们ID
     * @return 结果
     */
    public int deleteCmJoinUsById(Long jId);
}
