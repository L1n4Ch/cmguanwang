package com.ruoyi.cm.mapper;

import java.util.List;
import com.ruoyi.cm.domain.CmJoinUs;

/**
 * 加入我们Mapper接口
 * 
 * @author ruoyi
 * @date 2021-05-25
 */
public interface CmJoinUsMapper 
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
    public int insertCmJoinUs(CmJoinUs cmJoinUs);

    /**
     * 修改加入我们
     * 
     * @param cmJoinUs 加入我们
     * @return 结果
     */
    public int updateCmJoinUs(CmJoinUs cmJoinUs);

    /**
     * 删除加入我们
     * 
     * @param jId 加入我们ID
     * @return 结果
     */
    public int deleteCmJoinUsById(Long jId);

    /**
     * 批量删除加入我们
     * 
     * @param jIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteCmJoinUsByIds(String[] jIds);
}
