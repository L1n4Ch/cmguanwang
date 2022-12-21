package com.ruoyi.cm.mapper;

import java.util.List;
import com.ruoyi.cm.domain.CmBigEvent;

/**
 * 成美大事件Mapper接口
 * 
 * @author ruoyi
 * @date 2021-05-28
 */
public interface CmBigEventMapper 
{
    /**
     * 查询成美大事件
     * 
     * @param beId 成美大事件ID
     * @return 成美大事件
     */
    public CmBigEvent selectCmBigEventById(Long beId);

    /**
     * 查询成美大事件列表
     * 
     * @param cmBigEvent 成美大事件
     * @return 成美大事件集合
     */
    public List<CmBigEvent> selectCmBigEventList(CmBigEvent cmBigEvent);

    /**
     * 新增成美大事件
     * 
     * @param cmBigEvent 成美大事件
     * @return 结果
     */
    public int insertCmBigEvent(CmBigEvent cmBigEvent);

    /**
     * 修改成美大事件
     * 
     * @param cmBigEvent 成美大事件
     * @return 结果
     */
    public int updateCmBigEvent(CmBigEvent cmBigEvent);

    /**
     * 删除成美大事件
     * 
     * @param beId 成美大事件ID
     * @return 结果
     */
    public int deleteCmBigEventById(Long beId);

    /**
     * 批量删除成美大事件
     * 
     * @param beIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteCmBigEventByIds(String[] beIds);
}
